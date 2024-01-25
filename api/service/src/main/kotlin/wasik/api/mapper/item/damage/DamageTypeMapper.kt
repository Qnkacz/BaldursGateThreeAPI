package wasik.api.mapper.item.damage

import wasik.api.restResource.items.model.damage.DamageType
import model.damage.DamageType as DomainDamageType

interface DamageTypeMapper {
    suspend fun mapToDamageType(damageType: DamageType) : DomainDamageType
}