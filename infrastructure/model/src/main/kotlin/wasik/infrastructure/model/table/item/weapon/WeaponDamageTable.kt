package wasik.infrastructure.model.table.item.weapon

import org.jetbrains.exposed.sql.Table
import wasik.infrastructure.model.table.DamageTable

object WeaponDamageTable : Table("weapon_damage") {
    val weaponId = reference("weapon_id", WeaponTable)
    val damageId = reference("damage_id", DamageTable)
    override val primaryKey: PrimaryKey = PrimaryKey(weaponId, damageId)
}