package wasik.api.service.mapper.item.weapon

import org.springframework.stereotype.Component
import wasik.api.model.exception.ApiException
import wasik.api.model.exception.ApiExceptionType
import wasik.api.model.model.weapon.WeaponProficiency
import domain.model.item.weapon.WeaponProficiency as DomainProficiency

@Component
class ProficiencyMapper {
    fun mapToProficiency(weaponProficiency: WeaponProficiency): DomainProficiency {
        return when (weaponProficiency) {
            WeaponProficiency.MARTIAL -> DomainProficiency.MARTIAL
            WeaponProficiency.SIMPLE -> DomainProficiency.SIMPLE
            else -> throw ApiException(
                type = ApiExceptionType.MAPPING_ERROR,
                message = MAPPING_ERROR_MSG
            )
        }
    }

    fun mapToApiProficiency(domainProficiency: DomainProficiency): WeaponProficiency {
        return when (domainProficiency) {
            DomainProficiency.SIMPLE -> WeaponProficiency.SIMPLE
            DomainProficiency.MARTIAL -> WeaponProficiency.MARTIAL
            else -> throw ApiException(
                type = ApiExceptionType.MAPPING_ERROR,
                message = MAPPING_ERROR_MSG
            )
        }
    }

    private companion object {
        const val MAPPING_ERROR_MSG = "Could not map weapon proficiency"
    }
}