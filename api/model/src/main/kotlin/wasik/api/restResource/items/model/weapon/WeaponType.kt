package wasik.api.restResource.items.model.weapon

import io.swagger.v3.oas.annotations.media.Schema

@Schema(
    description = "Weapon type",
    required = true,
    example = "MELEE"
)
enum class WeaponType {
    MELEE,
    VERSATILE,
    RANGED,
    TWO_HANDED_MELEE,
    TWO_HANDED_RANGED
}