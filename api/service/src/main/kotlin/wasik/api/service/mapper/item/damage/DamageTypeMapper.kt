package wasik.api.service.mapper.item.damage

import org.springframework.stereotype.Component
import wasik.api.model.model.damage.DamageType
import domain.model.damage.DamageType as DomainDamageType

@Component
class DamageTypeMapper {
    suspend fun mapToDamageType(damageType: DamageType): DomainDamageType {
        return when(damageType) {
            DamageType.COLD -> DomainDamageType.COLD
            DamageType.PIERCING -> DomainDamageType.PIERCING
            DamageType.SLASHING -> DomainDamageType.SLASHING
            DamageType.BLUDGEONING -> DomainDamageType.BLUDGEONING
            DamageType.NECROTIC -> DomainDamageType.NECROTIC
            DamageType.FIRE -> DomainDamageType.FIRE
            DamageType.ELECTRIC -> DomainDamageType.ELECTRIC
            DamageType.RADIANT -> DomainDamageType.RADIANT
            DamageType.PSYCHIC -> DomainDamageType.PSYCHIC
            DamageType.FORCE -> DomainDamageType.FORCE
            DamageType.POISON -> DomainDamageType.POISON
            else -> throw Exception("Bad Die value") // TODO: Implement proper Exception throwing with exception handlers
        }
    }
}