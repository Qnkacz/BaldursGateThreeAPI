package wasik.api.model.model

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Range
import wasik.api.model.DieType
import wasik.api.model.model.damage.DamageType

@Schema(description = "Damage description of items, and spells")
data class Damage(
    @field:Schema(required = true)
    @field:NotNull
    val damageType: DamageType,
    @field:Schema(description = "How many dice of the type are needed", required = true)
    @field:NotNull
    @field:Range(min = 0, max = 3)
    val dieAmount: Int,
    @field:Schema(required = true)
    val dieType: DieType,
    @field:Schema(
        description = "The flat damage bonus",
        example = "2",
        required = true
    )
    @field:NotNull
    @field:Range(min = 0, max = 3)
    val bonus: Int
)
