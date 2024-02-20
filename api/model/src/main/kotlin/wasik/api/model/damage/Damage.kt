package wasik.api.model.model.damage

import io.swagger.v3.oas.annotations.media.Schema
import wasik.api.model.DieType

@Schema(description = "Damage description of items, and spells")
data class Damage(
    val damageType: DamageType,
    @field:Schema(description = "How many dice of the type are needed", required = true)
    val dieAmount: Int,
    @field:Schema(required = true)
    val dieType: DieType,
    @field:Schema(
        description = "The flat damage bonus",
        example = "2",
        required = true
    )
    val bonus: Int
)
