package wasik.api.model.exception

import java.io.Serializable

data class ApiException(override val message: String?, val type: ApiExceptionType) : Serializable,
    RuntimeException(message)