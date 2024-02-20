package domain.model.item

data class CommonItemData(
    val name: String,
    val rarity: ItemRarity,
    val value: Float,
    val weight: Float,
    val description: String
)
