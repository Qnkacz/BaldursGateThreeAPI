package wasik

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import model.DieType
import model.damage.Damage
import org.springframework.stereotype.Component
import wasik.entity.DamageEntity

@Component
class DamageInfraMapperImpl : DamageInfraMapper {
    override suspend fun mapToDamageEntity(damage: Damage): DamageEntity = coroutineScope {
        val damageEntity = async { damageToString(damage.dieType, damage.dieAmount) }
        DamageEntity(null, type = damage.damageType.ordinal, amount = damageEntity.await())
    }

    private suspend fun damageToString(dieType: DieType, dieAmount: Int) = coroutineScope {
        return@coroutineScope "$${dieAmount}d$dieType"
    }
}