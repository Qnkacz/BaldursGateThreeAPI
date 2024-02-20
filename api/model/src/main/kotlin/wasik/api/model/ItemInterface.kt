package wasik.api.model

import wasik.api.model.item.ItemRarity

interface ItemInterface {
    val name: String
    val rarity: ItemRarity
    val value: Float
    val weight: Float
    val description: String
}