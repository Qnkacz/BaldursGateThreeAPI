package wasik.api.mapper.item.weapon

import wasik.api.restResource.items.model.weapon.Weapon
import model.item.weapon.Weapon as DomainWeapon

interface WeaponMapper {
    suspend fun mapToWeapon(weapon: Weapon) : DomainWeapon
}