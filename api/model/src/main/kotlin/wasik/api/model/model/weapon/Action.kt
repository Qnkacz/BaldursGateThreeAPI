package wasik.api.model.model.weapon

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

@Schema(description = "Action that the weapon gives you")
data class Action(
    @field: Schema(required = true)
    @field:NotBlank
    val name: String,
    val description: String?
)
