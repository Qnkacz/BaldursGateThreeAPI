package wasik.domain.logic.misc

import domain.model.misc.Action
import org.jetbrains.exposed.dao.id.EntityID

interface ActionService {
    suspend fun postAction(action: Action)
    suspend fun postActions(actions: Collection<Action>): List<EntityID<Long>>
    suspend fun getAction(id: Long): Action
    suspend fun updateAction(action: Action): Action
}