package wasik.api.service.exception.handler

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import wasik.api.model.exception.ErrorResponse
import wasik.infrastructure.model.exception.InfrastructureException
import wasik.infrastructure.model.exception.InfrastructureExceptionType
import wasik.infrastructure.model.exception.InfrastructureExceptionType.DATABASE_CONNECTION_ISSUE
import wasik.infrastructure.model.exception.InfrastructureExceptionType.ENTITY_MAPPING_ERROR


@Component
class InfrastructureExceptionHandler : ExceptionHandler<InfrastructureException> {
    private val logger = LoggerFactory.getLogger(InfrastructureExceptionHandler::class.java)

    override fun mapErrorData(exception: InfrastructureException): ErrorResponse {
        return ErrorResponse(code = exception.type.toString(), message = exception.message ?: ExceptionHandler.DEFAULT_EXCEPTION_MESSAGE)
    }

    override fun getResponseStatus(exception: InfrastructureException): HttpStatus {
        logger.error(exception.message, exception)
        return when (exception.type) {
            ENTITY_MAPPING_ERROR -> HttpStatus.CONFLICT
            DATABASE_CONNECTION_ISSUE -> HttpStatus.INTERNAL_SERVER_ERROR
            InfrastructureExceptionType.NOT_FOUND -> HttpStatus.NOT_FOUND
        }
    }

    override fun getResponseInfo(exception: InfrastructureException): Pair<ErrorResponse, HttpStatus> {
        return Pair(mapErrorData(exception), getResponseStatus(exception))
    }
}