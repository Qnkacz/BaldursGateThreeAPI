package wasik.api.service.mapper.item.weapon

import org.springframework.stereotype.Component
import wasik.api.model.model.weapon.WeaponProficiency
import domain.model.item.weapon.WeaponProficiency as DomainWeaponProficiency

@Component
class ProficiencyMapper {
    suspend fun mapToProficiency(weaponProficiency: WeaponProficiency): DomainWeaponProficiency {
        return when(weaponProficiency) {
            WeaponProficiency.MARTIAL -> DomainWeaponProficiency.MARTIAL
            WeaponProficiency.SIMPLE -> DomainWeaponProficiency.SIMPLE
            else -> throw Exception("Bad Die value") // TODO: Implement proper Exception throwing with exception handlers
        }
    }
}