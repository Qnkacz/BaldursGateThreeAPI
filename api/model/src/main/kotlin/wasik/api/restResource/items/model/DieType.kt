package wasik.api.restResource.items.model

import io.swagger.v3.oas.annotations.media.Schema

@Schema(
    description = "Dies used in the game",
    example = "D6"
)
enum class DieType {
    D4,
    D6,
    D8,
    D10,
    D12,
    D20,
    D100
}