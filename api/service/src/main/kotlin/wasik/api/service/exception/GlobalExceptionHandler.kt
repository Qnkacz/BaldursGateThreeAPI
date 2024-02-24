package wasik.api.service.exception

import domain.model.exception.DomainException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeExceptions(ex: RuntimeException): ResponseEntity<ErrorResponse> {
        return when (ex) {
            is DomainException -> handleDomainException(ex)
            is ApiException -> handleApiException(ex)
            is InfrastructureException -> handleInfraStructureException(ex)
            else -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(defaultErrorResponse())
        }
    }

    private fun defaultErrorResponse(): ErrorResponse {
        return ErrorResponse(code = "Unknown", message = "Unknown error occured")
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