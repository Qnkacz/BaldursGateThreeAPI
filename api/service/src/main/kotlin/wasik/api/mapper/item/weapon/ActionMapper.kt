package wasik.api.mapper.item.weapon

import wasik.api.restResource.items.model.weapon.Action
import model.misc.Action as DomainAction

interface ActionMapper {
    suspend fun mapToAction(action: Action): DomainAction
}