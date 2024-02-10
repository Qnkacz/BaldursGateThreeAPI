package wasik.items

import model.item.weapon.Weapon

interface WeaponService {
    suspend fun postWeapon(weapon: Weapon)
    suspend fun getWeaponByName(name: String): Weapon
    suspend fun updateWeapon(name: String, weapon: Weapon)
}