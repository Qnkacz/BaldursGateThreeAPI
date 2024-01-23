package wasik.api.restResource.items.model

interface IItem {
    val name: String
    val rarity: ItemRarity
    val value: Float
    val weight: Float
    val description: String
}