package wasik.items

import model.item.weapon.Weapon
import wasik.entity.item.weapon.WeaponEntity

interface WeaponService {
    suspend fun postWeapon(weapon: Weapon): WeaponEntity
    suspend fun getWeaponByName(name: String): Weapon
    suspend fun updateWeapon(name: String, weapon: Weapon): Weapon
}