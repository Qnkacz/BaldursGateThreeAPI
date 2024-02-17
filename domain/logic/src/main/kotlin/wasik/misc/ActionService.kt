package wasik.misc

import kotlinx.coroutines.flow.Flow
import model.misc.Action
import wasik.entity.ActionEntity

interface ActionService {
    suspend fun postAction(action: Action)
    suspend fun postActions(actions: Collection<Action>): Flow<ActionEntity>
    suspend fun getAction(name: String): Action
    suspend fun updateAction(action: Action): Action
}