package wasik.api.service.mapper.item.weapon

import wasik.api.model.model.weapon.Weapon
import domain.model.item.weapon.Weapon as DomainWeapon

interface WeaponMapper {
    suspend fun mapToWeapon(weapon: Weapon) : DomainWeapon
}