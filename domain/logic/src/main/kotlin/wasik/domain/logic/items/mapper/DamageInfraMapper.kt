package wasik.domain.logic.items.mapper

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import domain.model.damage.Damage
import domain.model.misc.DieType
import org.springframework.stereotype.Component
import wasik.infrastructure.model.entity.DamageEntity

@Component
class DamageInfraMapper {
    suspend fun mapToDamageEntity(damage: Damage): DamageEntity = coroutineScope {
        val damageEntity = async { damageToString(damage.dieType, damage.dieAmount) }
        DamageEntity(null, type = damage.damageType.ordinal, amount = damageEntity.await())
    }

    private suspend fun damageToString(dieType: DieType, dieAmount: Int) = coroutineScope {
        return@coroutineScope "$${dieAmount}d$dieType"
    }
}