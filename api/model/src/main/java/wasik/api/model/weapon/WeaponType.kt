package wasik.api.model.weapon

import io.swagger.v3.oas.annotations.media.Schema

@Schema(
    description = "Weapon type",
    required = true,
    example = "Melee"
)
enum class WeaponType {
    MELEE,
    VERSATILE,
    TWO_HANDED_MELEE,
    TWO_HANDED_RANGED
}