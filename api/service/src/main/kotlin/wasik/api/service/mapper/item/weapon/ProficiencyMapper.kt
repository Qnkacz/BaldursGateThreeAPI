package wasik.api.service.mapper.item.weapon

import org.springframework.stereotype.Component
import wasik.api.model.exception.ApiException
import wasik.api.model.exception.ApiExceptionType
import wasik.api.model.model.weapon.WeaponProficiency
import domain.model.item.weapon.WeaponProficiency as DomainWeaponProficiency

@Component
class ProficiencyMapper {
    suspend fun mapToProficiency(weaponProficiency: WeaponProficiency): DomainWeaponProficiency {
        return when(weaponProficiency) {
            WeaponProficiency.MARTIAL -> DomainWeaponProficiency.MARTIAL
            WeaponProficiency.SIMPLE -> DomainWeaponProficiency.SIMPLE
            else -> throw ApiException(type = ApiExceptionType.MAPPING_ERROR, message = "Could not map weapon proficiency")
        }
    }
}