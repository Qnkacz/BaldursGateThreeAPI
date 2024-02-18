package wasik.domain.logic.items.mapper

import domain.model.misc.Action
import wasik.infrastructure.model.entity.ActionEntity

interface ActionMapper {
    suspend fun mapToActionEntity(action: Action): ActionEntity
}