package wasik.api.model.model.damage

import io.swagger.v3.oas.annotations.media.Schema

@Schema(
    description = "Damage types occurring in the game",
    example = "ELECTRIC"
)
enum class DamageType {
    PIERCING,
    SLASHING,
    BLUDGEONING,
    NECROTIC,
    FIRE,
    ELECTRIC,
    COLD,
    RADIANT,
    PSYCHIC,
    FORCE,
    POISON
}