package wasik.domain.logic.misc

import kotlinx.coroutines.flow.Flow
import domain.model.misc.Action
import wasik.infrastructure.model.entity.ActionEntity

interface ActionService {
    suspend fun postAction(action: Action)
    suspend fun postActions(actions: Collection<Action>): Flow<ActionEntity>
    suspend fun getAction(name: String): Action
    suspend fun updateAction(action: Action): Action
}