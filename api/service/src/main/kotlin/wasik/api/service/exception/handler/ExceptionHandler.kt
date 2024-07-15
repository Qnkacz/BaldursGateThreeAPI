package wasik.api.service.exception.handler

import org.springframework.http.HttpStatus
import wasik.api.model.exception.ErrorResponse

interface ExceptionHandler<T : Throwable> {

    suspend fun getResponseInfo(exception: T): Pair<ErrorResponse, HttpStatus>

    suspend fun getResponseStatus(exception: T): HttpStatus

    /* Hi me from the future,
    *  For now I'm gonna implement Error responses to take the Exception type and simple convert it to a string
    *   In the future, you might want to create a map of ENUM to CODE (a new type)
    *   I.E DomainExceptionType.Validation -> REQ.DOM.001 (or smth else)
    *   What you have now is good for the moment and for testing purposes, but you might want to change it
    *   And add run types DEPLOY/TEST to appconfig and depending on that return enum strings or proper codes
    *   The user doesn't really need to know the exact enum
    * */
    fun mapErrorData(exception: T): ErrorResponse

    companion object {
        val DEFAULT_EXCEPTION_MESSAGE: String
            get() = "Default error message"
    }
}