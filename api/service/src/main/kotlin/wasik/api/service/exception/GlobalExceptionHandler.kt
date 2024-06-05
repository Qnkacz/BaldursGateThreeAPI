package wasik.api.service.exception

import domain.model.exception.DomainException
import org.slf4j.LoggerFactory
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
) {

    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeExceptions(ex: RuntimeException): ResponseEntity<ErrorResponse> {
        return when (ex) {
            is DomainException -> handleDomainException(ex)
            is ApiException -> handleApiException(ex)
            is InfrastructureException -> handleInfraStructureException(ex)
            else -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(defaultErrorResponse(ex))
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleConstraintValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val incorrectFields = ex.bindingResult.fieldErrors.joinToString(separator = " and ") { it.field }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorResponse(
                code = "Parameter constraints",
                message = "The following fields were not filled in correctly: $incorrectFields"
            )
        )
    }

    private fun defaultErrorResponse(ex: RuntimeException): ErrorResponse {
        logger.error(ex.message, ex)
        return ErrorResponse(code = "Unknown", message = ex.message!!)
    }

    private fun handleApiException(ex: ApiException): ResponseEntity<ErrorResponse> {
        val (errorResponse, httpStatus) = apiExceptionHandler.getResponseInfo(ex)
        return ResponseEntity.status(httpStatus)
            .body(errorResponse)
    }

    private fun handleDomainException(ex: DomainException): ResponseEntity<ErrorResponse> {
        val (errorResponse, httpStatus) = domainExceptionHandler.getResponseInfo(ex)
        return ResponseEntity.status(httpStatus)
            .body(errorResponse)
    }

    private fun handleInfraStructureException(ex: InfrastructureException): ResponseEntity<ErrorResponse> {
        val (errorResponse, httpStatus) = infrastructureExceptionHandler.getResponseInfo(ex)
        return ResponseEntity.status(httpStatus)
            .body(errorResponse)
    }
}