package wasik.infrastructure.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "property")
data class PropertyEntity(
    @Id val id: Int?,
    val name: String,
    val description: String
)

