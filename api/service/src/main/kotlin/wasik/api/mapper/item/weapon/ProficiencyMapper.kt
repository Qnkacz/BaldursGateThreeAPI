package wasik.api.mapper.item.weapon

import wasik.api.restResource.items.model.weapon.WeaponProficiency
import model.item.weapon.WeaponProficiency as DomainWeaponProficiency


interface ProficiencyMapper {
    suspend fun mapToProficiency(weaponProficiency: WeaponProficiency): DomainWeaponProficiency
}