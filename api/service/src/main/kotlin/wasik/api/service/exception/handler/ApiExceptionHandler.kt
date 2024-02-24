package wasik.api.service.exception.handler

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import wasik.api.model.exception.ApiException
import wasik.api.model.exception.ApiExceptionType.AUTHORIZATION_ERROR
import wasik.api.model.exception.ApiExceptionType.MAPPING_ERROR
import wasik.api.model.exception.ErrorResponse

@Component
class ApiExceptionHandler : ExceptionHandler<ApiException> {
    override fun mapErrorData(exception: ApiException): ErrorResponse {
        return ErrorResponse(code = exception.type.toString(), message = exception.message ?: DEFAULT_EXCEPTION_MESSAGE)
    }

    override fun getResponseStatus(exception: ApiException): HttpStatus {
        return when(exception.type) {
            AUTHORIZATION_ERROR -> HttpStatus.FORBIDDEN
            MAPPING_ERROR -> HttpStatus.BAD_REQUEST
        }
    }

    override fun getResponseInfo(exception: ApiException): Pair<ErrorResponse, HttpStatus> {
        return Pair(mapErrorData(exception), getResponseStatus(exception))
    }
}