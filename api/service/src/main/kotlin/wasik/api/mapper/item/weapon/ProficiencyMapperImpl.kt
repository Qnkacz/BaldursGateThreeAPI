package wasik.api.mapper.item.weapon

import org.springframework.stereotype.Component
import wasik.api.restResource.items.model.weapon.WeaponProficiency
import model.item.weapon.WeaponProficiency as DomainWeaponProficiency

@Component
class ProficiencyMapperImpl : ProficiencyMapper {
    override suspend fun mapToProficiency(weaponProficiency: WeaponProficiency): DomainWeaponProficiency {
        return when(weaponProficiency) {
            WeaponProficiency.MARTIAL -> DomainWeaponProficiency.MARTIAL
            WeaponProficiency.SIMPLE -> DomainWeaponProficiency.SIMPLE
            else -> throw Exception("Bad Die value") // TODO: Implement proper Exception throwing with exception handlers
        }
    }
}