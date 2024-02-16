package wasik.items.mapper

import wasik.entity.ActionEntity
import model.item.weapon.Action

interface ActionMapper {
    suspend fun mapToActionEntity(action: Action): ActionEntity
}