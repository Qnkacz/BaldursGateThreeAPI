package wasik.api.service.mapper.item

import domain.model.item.CommonItemData
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Component
import wasik.api.model.ItemCommonData

@Component
class CommonItemDataMapper(private val rarityMapper: RarityMapper) {
    suspend fun mapToCommonItemInfo(item: ItemCommonData): CommonItemData = coroutineScope {
        val rarity = async { rarityMapper.mapToDomainRarity(item.rarity) }
        CommonItemData(item.name, rarity.await(), item.value, item.weight, item.description)
    }

    suspend fun mapToApiItemCommonData(data: CommonItemData): ItemCommonData = coroutineScope {
        val rarity = async { rarityMapper.mapToApiRarity(data.rarity) }
        return@coroutineScope ItemCommonData(
            description = data.description,
            rarity = rarity.await(),
            name = data.name,
            value = data.value,
            weight = data.weight
        )
    }
}