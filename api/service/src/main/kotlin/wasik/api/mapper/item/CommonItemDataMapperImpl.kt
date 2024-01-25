package wasik.api.mapper.item

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import model.item.CommonItemData
import org.springframework.stereotype.Component
import wasik.api.restResource.items.model.IItem

@Component
class CommonItemDataMapperImpl(private val rarityMapper: RarityMapper) : CommonItemDataMapper {
    override suspend fun mapToCommonItemInfo(item: IItem): CommonItemData = coroutineScope {
        val rarity = async { rarityMapper.mapToRarity(item.rarity) }
        CommonItemData(item.name, rarity.await(), item.value, item.weight, item.description)
    }
}