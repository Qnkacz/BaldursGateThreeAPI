package wasik.api.service.mapper.item.weapon

import wasik.api.model.model.weapon.WeaponProficiency
import domain.model.item.weapon.WeaponProficiency as DomainWeaponProficiency


interface ProficiencyMapper {
    suspend fun mapToProficiency(weaponProficiency: WeaponProficiency): DomainWeaponProficiency
}