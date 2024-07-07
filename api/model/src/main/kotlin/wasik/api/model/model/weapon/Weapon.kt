package wasik.api.model.model.weapon

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import wasik.api.model.ItemCommonData
import wasik.api.model.Property
import wasik.api.model.item.ItemRarity
import wasik.api.model.item.weapon.WeaponClass
import wasik.api.model.item.weapon.WeaponProficiency
import wasik.api.model.item.weapon.WeaponType
import wasik.api.model.model.Damage

@Schema(description = "Model for weapons used in Baldur's Gate 3")
data class Weapon(
    @field:Schema(
        description = "Name of the weapon",
        example = "Longsword +1",
        required = true
    )
    @field:NotBlank
    override val name: String,
    @field:NotNull
    override val rarity: ItemRarity,
    @field:NotNull
    val proficiency: WeaponProficiency,
    @field:NotNull
    val type: WeaponType,
    @field:NotNull
    val weaponClass: WeaponClass,
    @field:Schema(
        description = "Range of the weapon",
        required = true,
        example = "1,5"
    )
    @field:NotNull
    @field:Positive
    val range: Float,
    @field:Schema(
        description = "Weight of the item",
        required = true,
        example = "50,9"
    )
    @field:NotNull
    @field:Positive
    override val weight: Float,
    @field:Schema(
        description = "Item value",
        required = true,
        example = "33,6"
    )
    @field:NotNull
    @field:Positive
    override val value: Float,
    @field:Valid
    val damage: Set<Damage>,
    @field:Valid
    val properties: Set<Property>,
    @field:Valid
    val actions: Set<Action>,
    @field:Schema(required = true)
    @field:NotBlank
    override val description: String
) : ItemCommonData(description, name, rarity, weight, value)
