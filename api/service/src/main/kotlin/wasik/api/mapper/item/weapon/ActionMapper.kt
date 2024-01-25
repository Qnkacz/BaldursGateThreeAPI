package wasik.api.mapper.item.weapon

import wasik.api.restResource.items.model.weapon.Action
import model.item.weapon.Action as DomainAction

interface ActionMapper {
    suspend fun mapToAction(action: Action): DomainAction
}