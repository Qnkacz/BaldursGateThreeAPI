package wasik.api.model.weapon

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Action that the weapon gives you")
data class Action(
    val name: String,
    val description: String
)
