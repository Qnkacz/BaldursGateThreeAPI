package wasik.api.service.mapper.item

import wasik.api.model.item.ItemRarity
import domain.model.item.ItemRarity as DomainRarity

interface RarityMapper {
    suspend fun mapToRarity(apiRarity: ItemRarity) : DomainRarity
}