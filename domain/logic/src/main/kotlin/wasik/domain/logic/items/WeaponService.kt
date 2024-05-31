package wasik.domain.logic.items

import domain.model.item.weapon.WeaponCommand

interface WeaponService {
    suspend fun postWeapon(weaponCommand: WeaponCommand)
    suspend fun getWeaponByName(name: String): List<WeaponCommand>
    suspend fun updateWeapon(name: String, weaponCommand: WeaponCommand)
}