package wasik.api.mapper.item

import wasik.api.restResource.items.model.ItemRarity
import model.item.ItemRarity as DomainRarity

interface RarityMapper {
    suspend fun mapToRarity(apiRarity: ItemRarity) : DomainRarity
}