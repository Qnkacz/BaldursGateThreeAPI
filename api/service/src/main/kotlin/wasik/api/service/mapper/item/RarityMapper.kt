package wasik.api.service.mapper.item

import org.springframework.stereotype.Component
import wasik.api.model.item.ItemRarity
import domain.model.item.ItemRarity as DomainRarity

@Component
class RarityMapper {
    suspend fun mapToRarity(apiRarity: ItemRarity): DomainRarity {
        return when(apiRarity) {
            ItemRarity.COMMON -> DomainRarity.COMMON
            ItemRarity.UNCOMMON -> DomainRarity.UNCOMMON
            ItemRarity.RARE -> DomainRarity.RARE
            ItemRarity.VERY_RARE -> DomainRarity.VERY_RARE
            ItemRarity.LEGENDARY -> DomainRarity.LEGENDARY
            else -> throw Exception("Bad Die value") // TODO: Implement proper Exception throwing with exception handlers
        }
    }
}