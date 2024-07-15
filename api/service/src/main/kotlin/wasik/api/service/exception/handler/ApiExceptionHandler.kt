package wasik.api.service.exception.handler

import io.klogging.Klogging
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import wasik.api.model.exception.ApiException
import wasik.api.model.exception.ApiExceptionType.AUTHORIZATION_ERROR
import wasik.api.model.exception.ApiExceptionType.MAPPING_ERROR
import wasik.api.model.exception.ErrorResponse

@Component
class ApiExceptionHandler : ExceptionHandler<ApiException>, Klogging {

    override fun mapErrorData(exception: ApiException): ErrorResponse {
        return ErrorResponse(code = exception.type.toString(), message = exception.message ?: ExceptionHandler.DEFAULT_EXCEPTION_MESSAGE)
    }

    override suspend fun getResponseStatus(exception: ApiException): HttpStatus {
        logger.error(exception.message!!, exception)

        return when (exception.type) {
            AUTHORIZATION_ERROR -> HttpStatus.FORBIDDEN
            MAPPING_ERROR -> HttpStatus.BAD_REQUEST
        }
    }

    override suspend fun getResponseInfo(exception: ApiException): Pair<ErrorResponse, HttpStatus> {
        return Pair(mapErrorData(exception), getResponseStatus(exception))
    }
}