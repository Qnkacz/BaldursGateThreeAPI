package wasik.api.service.exception

import domain.model.exception.DomainException
import io.klogging.Klogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import wasik.api.model.exception.ApiException
import wasik.api.model.exception.ErrorResponse
import wasik.api.service.exception.handler.ApiExceptionHandler
import wasik.api.service.exception.handler.DomainExceptionHandler
import wasik.api.service.exception.handler.InfrastructureExceptionHandler
import wasik.infrastructure.model.exception.InfrastructureException

@ControllerAdvice
class GlobalExceptionHandler(
    private val apiExceptionHandler: ApiExceptionHandler,
    private val domainExceptionHandler: DomainExceptionHandler,
    private val infrastructureExceptionHandler: InfrastructureExceptionHandler
) :Klogging {

    @ExceptionHandler(RuntimeException::class)
    suspend fun handleRuntimeExceptions(ex: RuntimeException): ResponseEntity<ErrorResponse> {
        return when (ex) {
            is DomainException -> handleDomainException(ex)
            is ApiException -> handleApiException(ex)
            is InfrastructureException -> handleInfraStructureException(ex)
            else -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(defaultErrorResponse(ex))
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    suspend fun handleConstraintValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        logger.error(ex.message!!, ex)
        val incorrectFields = ex.bindingResult.fieldErrors.joinToString(separator = " and ") { it.field }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorResponse(
                code = "Parameter constraints",
                message = "The following fields were not filled in correctly: $incorrectFields"
            )
        )
    }

    private suspend fun defaultErrorResponse(ex: RuntimeException): ErrorResponse {
        logger.error(ex.message!!, ex)
        return ErrorResponse(code = "Unknown", message = ex.message!!)
    }

    private suspend fun handleApiException(ex: ApiException): ResponseEntity<ErrorResponse> {
        val (errorResponse, httpStatus) = apiExceptionHandler.getResponseInfo(ex)
        return ResponseEntity.status(httpStatus)
            .body(errorResponse)
    }

    private suspend fun handleDomainException(ex: DomainException): ResponseEntity<ErrorResponse> {
        val (errorResponse, httpStatus) = domainExceptionHandler.getResponseInfo(ex)
        return ResponseEntity.status(httpStatus)
            .body(errorResponse)
    }

    private suspend fun handleInfraStructureException(ex: InfrastructureException): ResponseEntity<ErrorResponse> {
        val (errorResponse, httpStatus) = infrastructureExceptionHandler.getResponseInfo(ex)
        return ResponseEntity.status(httpStatus)
            .body(errorResponse)
    }
}