package wasik.api.service.mapper.item.damage

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Component
import wasik.api.model.model.Damage
import domain.model.damage.Damage as DomainDamage

@Component
class DamageMapper(
    private val dieMapper: wasik.api.service.mapper.item.DieMapper,
    private val damageTypeMapper: DamageTypeMapper
) {
    suspend fun mapToDamage(damage: Damage): DomainDamage = coroutineScope {
        val die = async { dieMapper.mapDie(damage.dieType) }
        val damageType = async { damageTypeMapper.mapToDamageType(damage.damageType) }
        DomainDamage(damageType.await(), damage.dieAmount, die.await(), damage.bonus)
    }
}