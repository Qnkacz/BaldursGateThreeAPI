package wasik.api.model

import wasik.api.model.item.ItemRarity

open class ItemCommonData(
    open val description: String,
    open val name: String,
    open val rarity: ItemRarity,
    open val weight: Float,
    open val value: Float
)