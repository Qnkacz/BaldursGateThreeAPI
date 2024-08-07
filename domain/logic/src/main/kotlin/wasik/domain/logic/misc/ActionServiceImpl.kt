package wasik.domain.logic.misc

import domain.model.misc.Action
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.future.await
import org.springframework.stereotype.Service
import wasik.infrastructure.logic.repository.ActionRepository
import wasik.infrastructure.model.table.ActionEntity

@Service
class ActionServiceImpl(
    private val actionRepository: ActionRepository,
) : ActionService {
    override suspend fun postAction(action: Action) {
        TODO("Not yet implemented")
    }

    override suspend fun postActions(actions: Collection<Action>): List<ActionEntity> = coroutineScope {
        actions.map { action: Action ->
            async { actionRepository.saveAction(action).await() }
        }.awaitAll()
    }

    override suspend fun getAction(id: Long): Action = coroutineScope {
        actionRepository.findById(id).await()
    }

    override suspend fun updateAction(action: Action): Action {
        TODO("Not yet implemented")
    }
}