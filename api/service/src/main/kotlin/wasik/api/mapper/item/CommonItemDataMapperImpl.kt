package wasik.api.mapper.item

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import model.item.CommonItemData
import org.springframework.stereotype.Component
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.ServerResponse.async
import wasik.api.restResource.items.model.IItem

@Component
class CommonItemDataMapperImpl(private val rarityMapper: RarityMapper) : CommonItemDataMapper {
    override suspend fun mapToCommonItemInfo(item: IItem): CommonItemData  = coroutineScope{
        val rarity = rarityMapper.mapToRarity(item.rarity);
        CommonItemData(item.name, rarity, item.value, item.weight, item.description)
    }
}