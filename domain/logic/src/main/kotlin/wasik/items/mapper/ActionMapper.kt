package wasik.items.mapper

import model.misc.Action
import wasik.entity.ActionEntity

interface ActionMapper {
    suspend fun mapToActionEntity(action: Action): ActionEntity
}