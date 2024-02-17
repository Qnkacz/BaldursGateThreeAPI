package wasik.api.mapper.item.weapon

import org.springframework.stereotype.Component
import wasik.api.restResource.items.model.weapon.Action
import model.misc.Action as DomainAction

@Component
class ActionMapperImpl : ActionMapper {
    override suspend fun mapToAction(action: Action): DomainAction {
        return DomainAction(action.name, action.description)
    }
}