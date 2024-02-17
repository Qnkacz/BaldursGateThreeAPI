package wasik.misc

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import model.damage.Damage
import model.misc.Action
import org.springframework.stereotype.Service
import wasik.entity.DamageEntity
import wasik.items.mapper.DamageInfraMapper
import wasik.repository.DamageRepository

@Service
class DamageServiceImpl(
    private val damageInfraMapper: DamageInfraMapper,
    private val damageRepository: DamageRepository
) : DamageService {
    override suspend fun postDamage(damage: Damage) {
        TODO("Not yet implemented")
    }

    override suspend fun postDamages(damages: Collection<Damage>): Flow<DamageEntity> = coroutineScope {
        val weaponDamage = damages.map {
            async {
                damageInfraMapper.mapToDamageEntity(it)
            }
        }.awaitAll()
        return@coroutineScope damageRepository.saveAll(weaponDamage)
    }

    override suspend fun getDamage(name: String): Action {
        TODO("Not yet implemented")
    }

    override suspend fun updateDamage(damage: Damage): Damage {
        TODO("Not yet implemented")
    }
}