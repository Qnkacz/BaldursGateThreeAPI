package wasik.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "damage")
data class DamageEntity(
    @Id
    val id: Int?,
    val type: Int,
    val amount: String
)
