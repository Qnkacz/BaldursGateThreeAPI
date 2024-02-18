package wasik.api.service.mapper.item

import domain.model.item.CommonItemData
import wasik.api.model.IItem

interface CommonItemDataMapper {
    suspend fun mapToCommonItemInfo(item: IItem): CommonItemData
}