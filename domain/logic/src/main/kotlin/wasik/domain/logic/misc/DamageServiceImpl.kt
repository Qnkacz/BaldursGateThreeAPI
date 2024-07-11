package wasik.domain.logic.misc

import domain.model.damage.Damage
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.future.await
import org.jetbrains.exposed.dao.id.EntityID
import org.springframework.stereotype.Service
import wasik.infrastructure.logic.repository.DamageRepository
import wasik.infrastructure.model.table.DamageEntity

@Service
class DamageServiceImpl(
    private val damageRepository: DamageRepository
) : DamageService {
    override suspend fun postDamage(damage: Damage) {
        TODO("Not yet implemented")
    }

    override suspend fun postDamages(damages: Collection<Damage>): List<DamageEntity> = coroutineScope {
        damages.map { damage: Damage ->
            async { damageRepository.save(damage).await() }
        }.awaitAll()
    }

    override suspend fun getDamageById(id: Long): Damage = coroutineScope {
        damageRepository.findById(id).await()
    }

    override suspend fun updateDamage(damage: Damage): Damage {
        TODO("Not yet implemented")
    }
}