package wasik.api.service.exception.handler

import domain.model.exception.DomainException
import domain.model.exception.DomainExceptionType.*
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import wasik.api.model.exception.ErrorResponse

@Component
class DomainExceptionHandler : ExceptionHandler<DomainException> {
    private val logger = LoggerFactory.getLogger(DomainExceptionHandler::class.java)

    override fun mapErrorData(exception: DomainException): ErrorResponse {
        return ErrorResponse(code = exception.type.toString(), message = exception.message ?: ExceptionHandler.DEFAULT_EXCEPTION_MESSAGE)
    }

    override fun getResponseStatus(exception: DomainException): HttpStatus {
        logger.error(exception.message, exception)
        return when (exception.type) {
            WRONG_DIE_VALUE, WRONG_DIE_AMOUNT -> HttpStatus.BAD_REQUEST
            VALIDATION_ERROR -> HttpStatus.NOT_ACCEPTABLE
        }
    }

    override fun getResponseInfo(exception: DomainException): Pair<ErrorResponse, HttpStatus> {
        return Pair(mapErrorData(exception), getResponseStatus(exception))
    }
}