package wasik.api.mapper.item

import model.item.CommonItemData
import wasik.api.restResource.items.model.IItem

interface CommonItemDataMapper {
    suspend fun mapToCommonItemInfo(item: IItem): CommonItemData
}