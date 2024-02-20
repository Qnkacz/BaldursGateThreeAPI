package wasik.api.model.item

import io.swagger.v3.oas.annotations.media.Schema

@Schema(
    description = "Rarity of Item",
    required = true,
)
enum class ItemRarity {
    COMMON,
    UNCOMMON,
    RARE,
    VERY_RARE,
    LEGENDARY
}