package wasik.api.model.model.weapon

import io.swagger.v3.oas.annotations.media.Schema
import wasik.api.model.ItemInterface
import wasik.api.model.item.ItemRarity
import wasik.api.model.Property
import wasik.api.model.model.damage.Damage

@Schema(description = "Model for weapons used in Baldur's Gate 3")
data class Weapon(
    @field:Schema(
        description = "Name of the weapon",
        example = "Longsword +1",
        required = true
    )
    override val name: String,
    override val rarity: ItemRarity,
    val proficiency: WeaponProficiency,
    val type: WeaponType,
    val weaponClass: WeaponClass,
    @field:Schema(
        description = "Range of the weapon",
        required = true,
        example = "1,5"
    )
    val range: Float,
    @field:Schema(
        description = "Weight of the item",
        required = true,
        example = "50,9"
    )
    override val weight: Float,
    @field:Schema(
        description = "Item value",
        required = true,
        example = "33,6"
    )
    override val value: Float,
    val damage: Set<Damage>,
    val properties: Set<Property>,
    val actions: Set<Action>,
    override val description: String
) : ItemInterface
