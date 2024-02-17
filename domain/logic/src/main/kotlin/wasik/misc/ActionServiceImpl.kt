package wasik.misc

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import model.misc.Action
import org.springframework.stereotype.Service
import wasik.entity.ActionEntity
import wasik.items.mapper.ActionMapper
import wasik.repository.ActionRepository

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