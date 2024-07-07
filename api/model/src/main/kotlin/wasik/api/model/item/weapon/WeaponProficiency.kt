package wasik.api.model.item.weapon

import io.swagger.v3.oas.annotations.media.Schema

@Schema(
    description = "Weapon proficiency",
    required = true,
    example = "MARTIAL"
)
enum class WeaponProficiency {
    MARTIAL,
    SIMPLE
}
