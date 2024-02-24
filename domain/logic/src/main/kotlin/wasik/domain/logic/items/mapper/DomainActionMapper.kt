package wasik.domain.logic.items.mapper

import domain.model.misc.Action
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Component
import wasik.infrastructure.model.entity.ActionEntity

@Component
class DomainActionMapper {
    suspend fun mapToActionEntity(action: Action): ActionEntity = coroutineScope {
        ActionEntity(id = null, name = action.name, description = action.description ?: "")
    }
}