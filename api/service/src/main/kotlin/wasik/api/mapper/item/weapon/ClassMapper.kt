package wasik.api.mapper.item.weapon

import wasik.api.restResource.items.model.weapon.WeaponClass
import model.item.weapon.WeaponClass as DomainWeaponClass

interface ClassMapper {
    suspend fun mapToClass(weaponClass: WeaponClass) : DomainWeaponClass
}