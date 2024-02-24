package domain.model.exception

import java.io.Serializable

data class DomainException(override val message: String?, val type: DomainExceptionType) : Serializable,
    RuntimeException(message)