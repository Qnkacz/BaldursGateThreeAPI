package wasik.entity.item.weapon

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("weapon_damage")
class WeaponDamageEntity(@Id val weaponDamageId: WeaponDamageId, val damageId: Int, val weaponId: Int) {
    data class WeaponDamageId(val damageId: Int, val weaponId: Int)
}