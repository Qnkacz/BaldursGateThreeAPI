package wasik.api.service.mapper.item.weapon

import wasik.api.model.model.weapon.WeaponClass
import domain.model.item.weapon.WeaponClass as DomainWeaponClass

interface ClassMapper {
    suspend fun mapToClass(weaponClass: WeaponClass) : DomainWeaponClass
}