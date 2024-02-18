package wasik.infrastructure.model.entity.item.weapon

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(name = "weapons")
data class WeaponEntity(
    @Id
    val id: Int?,
    val name: String,
    val rarity: Int,
    val value: Float,
    val weight: Float,
    val description: String?,
    @Column(value = "class")
    val weaponClass: Int,
    val proficiency: Int,
    val handType: Int,
    val type: Int,
    val upgrade: Int?
)
