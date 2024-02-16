package wasik.items.mapper.weapon

import model.item.weapon.Weapon
import wasik.entity.item.weapon.WeaponEntity

interface WeaponInfraMapper {
    suspend fun mapToWeaponEntity(weapon: Weapon): WeaponEntity
}