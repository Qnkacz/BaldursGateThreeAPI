package wasik.items.mapper

import kotlinx.coroutines.coroutineScope
import model.item.weapon.Action
import org.springframework.stereotype.Component
import wasik.entity.ActionEntity

@Component
class DomainActionMapperImpl : ActionMapper {
    override suspend fun mapToActionEntity(action: Action): ActionEntity = coroutineScope{
        ActionEntity(id = null, name = action.name, description = action.description)
    }
}