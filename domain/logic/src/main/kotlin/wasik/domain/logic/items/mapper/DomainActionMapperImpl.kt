package wasik.domain.logic.items.mapper

import kotlinx.coroutines.coroutineScope
import domain.model.misc.Action
import org.springframework.stereotype.Component
import wasik.infrastructure.model.entity.ActionEntity

@Component
class DomainActionMapperImpl : ActionMapper {
    override suspend fun mapToActionEntity(action: Action): ActionEntity = coroutineScope{
        ActionEntity(id = null, name = action.name, description = action.description)
    }
}