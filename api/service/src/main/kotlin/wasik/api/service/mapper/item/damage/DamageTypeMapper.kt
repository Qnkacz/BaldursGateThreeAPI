package wasik.api.service.mapper.item.damage

import wasik.api.model.model.damage.DamageType
import domain.model.damage.DamageType as DomainDamageType

interface DamageTypeMapper {
    suspend fun mapToDamageType(damageType: DamageType) : DomainDamageType
}