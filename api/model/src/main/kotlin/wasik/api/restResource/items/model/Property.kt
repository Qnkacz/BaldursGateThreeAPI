package wasik.api.restResource.items.model

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Properties that the weapon can have")
data class Property(
    @field:Schema(description = "Name of the weapon property", example = "Light")
    val name: String,
    @field:Schema(description = "Description of what the property does")
    val description: String
)
