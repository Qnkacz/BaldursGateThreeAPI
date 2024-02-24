package wasik.api.service.exception.handler

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import wasik.api.model.exception.ErrorResponse
import wasik.infrastructure.model.exception.InfrastructureException
import wasik.infrastructure.model.exception.InfrastructureExceptionType.DATABASE_CONNECTION_ISSUE
import wasik.infrastructure.model.exception.InfrastructureExceptionType.ENTITY_MAPPING_ERROR


@Component
class InfrastructureExceptionHandler : ExceptionHandler<InfrastructureException> {

    override fun mapErrorData(exception: InfrastructureException): ErrorResponse {
        return ErrorResponse(code = exception.type.toString(), message = exception.message ?: DEFAULT_EXCEPTION_MESSAGE)
    }

    override fun getResponseStatus(exception: InfrastructureException): HttpStatus {
        return when (exception.type) {
            ENTITY_MAPPING_ERROR -> HttpStatus.CONFLICT
            DATABASE_CONNECTION_ISSUE -> HttpStatus.INTERNAL_SERVER_ERROR
        }
    }

    override fun getResponseInfo(exception: InfrastructureException): Pair<ErrorResponse, HttpStatus> {
        return Pair(mapErrorData(exception), getResponseStatus(exception))
    }
}