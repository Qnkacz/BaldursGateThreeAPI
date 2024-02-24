package wasik.infrastructure.model.exception

import java.io.Serializable

data class InfrastructureException(override val message: String?, val type: InfrastructureExceptionType) : Serializable,
    RuntimeException(message)