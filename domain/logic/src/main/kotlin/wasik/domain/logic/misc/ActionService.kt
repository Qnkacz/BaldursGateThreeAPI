package wasik.domain.logic.misc

import domain.model.misc.Action
import wasik.infrastructure.model.table.ActionEntity

interface ActionService {
    suspend fun postAction(action: Action)
    suspend fun postActions(actions: Collection<Action>): List<ActionEntity>
    suspend fun getAction(id: Long): Action
    suspend fun updateAction(action: Action): Action
}