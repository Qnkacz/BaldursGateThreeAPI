package wasik.api.service.mapper.item.weapon

import org.springframework.stereotype.Component
import wasik.api.model.model.weapon.Action
import domain.model.misc.Action as DomainAction

@Component
class ActionMapper {
    fun mapToAction(action: Action): DomainAction {
        return DomainAction(action.name, action.description)
    }

    fun mapToApiAction(action: DomainAction): Action {
        return Action(action.name, action.description)
    }
}