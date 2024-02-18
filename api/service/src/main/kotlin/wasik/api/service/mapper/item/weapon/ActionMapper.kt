package wasik.api.service.mapper.item.weapon

import wasik.api.model.model.weapon.Action
import domain.model.misc.Action as DomainAction

interface ActionMapper {
    suspend fun mapToAction(action: Action): DomainAction
}