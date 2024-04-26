package wasik.domain.logic.items

import domain.model.item.weapon.Weapon

interface WeaponService {
    suspend fun postWeapon(weapon: Weapon)
    suspend fun getWeaponByName(name: String): List<Weapon>
    suspend fun updateWeapon(name: String, weapon: Weapon)
}