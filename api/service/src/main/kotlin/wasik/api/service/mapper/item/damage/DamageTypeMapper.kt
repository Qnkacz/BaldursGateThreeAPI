package wasik.api.service.mapper.item.damage

import org.springframework.stereotype.Component
import wasik.api.model.exception.ApiException
import wasik.api.model.exception.ApiExceptionType
import wasik.api.model.model.damage.DamageType
import domain.model.damage.DamageType as DomainDamageType

@Component
class DamageTypeMapper {
    fun mapToDamageType(damageType: DamageType): DomainDamageType {
        return when (damageType) {
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
            else -> throw ApiException(type = ApiExceptionType.MAPPING_ERROR, message = MAPPING_ERROR_MSG)
        }
    }

    fun mapToApiDamageType(domainDamageType: DomainDamageType): DamageType {
        return when (domainDamageType) {
            DomainDamageType.PIERCING -> DamageType.PIERCING
            DomainDamageType.SLASHING -> DamageType.SLASHING
            DomainDamageType.BLUDGEONING -> DamageType.BLUDGEONING
            DomainDamageType.FIRE -> DamageType.FIRE
            DomainDamageType.ELECTRIC -> DamageType.ELECTRIC
            DomainDamageType.POISON -> DamageType.POISON
            DomainDamageType.NECROTIC -> DamageType.NECROTIC
            DomainDamageType.FORCE -> DamageType.FORCE
            DomainDamageType.RADIANT -> DamageType.RADIANT
            DomainDamageType.PSYCHIC -> DamageType.PSYCHIC
            DomainDamageType.COLD -> DamageType.COLD
            else -> throw ApiException(type = ApiExceptionType.MAPPING_ERROR, message = MAPPING_ERROR_MSG)
        }
    }

    private companion object {
        const val MAPPING_ERROR_MSG = "Could not map damage type"
    }
}