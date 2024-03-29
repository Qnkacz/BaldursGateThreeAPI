package wasik.api.service.mapper.item

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import domain.model.item.CommonItemData
import org.springframework.stereotype.Component
import wasik.api.model.ItemInterface

@Component
class CommonItemDataMapper(private val rarityMapper: RarityMapper) {
    suspend fun mapToCommonItemInfo(item: ItemInterface): CommonItemData = coroutineScope {
        val rarity = async { rarityMapper.mapToRarity(item.rarity) }
        CommonItemData(item.name, rarity.await(), item.value, item.weight, item.description)
    }
}