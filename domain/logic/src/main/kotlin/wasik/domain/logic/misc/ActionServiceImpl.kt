package wasik.domain.logic.misc

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import domain.model.misc.Action
import org.springframework.stereotype.Service
import wasik.infrastructure.model.entity.ActionEntity
import wasik.domain.logic.items.mapper.ActionMapper
import wasik.infrastructure.logic.repository.ActionRepository

@Service
class ActionServiceImpl(
    private val actionRepository: ActionRepository,
    private val domainActionMapperImpl: ActionMapper,
) : ActionService {
    override suspend fun postAction(action: Action) {
        TODO("Not yet implemented")
    }

    override suspend fun postActions(actions: Collection<Action>): Flow<ActionEntity> = coroutineScope {
        val weaponDamage = actions.map {
            async {
                domainActionMapperImpl.mapToActionEntity(it)
            }
        }.awaitAll()
        return@coroutineScope actionRepository.saveAll(weaponDamage)
    }

    override suspend fun getAction(name: String): Action {
        TODO("Not yet implemented")
    }

    override suspend fun updateAction(action: Action): Action {
        TODO("Not yet implemented")
    }
}