package wasik.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("actions")
data class ActionEntity(
    @Id val id: Int?,
    val name: String,
    val description: String
)
