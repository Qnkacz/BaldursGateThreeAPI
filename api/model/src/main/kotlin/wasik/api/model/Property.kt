package wasik.api.model

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

@Schema(description = "Properties that the weapon can have")
data class Property(
    @field:Schema(description = "Name of the weapon property", example = "Light", required = true)
    @field:NotBlank
    val name: String,
    @field:Schema(description = "Description of what the property does")
    val description: String?
)
