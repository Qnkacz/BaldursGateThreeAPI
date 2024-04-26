package wasik.infrastructure.model.table.item.weapon

import org.jetbrains.exposed.sql.Table
import wasik.infrastructure.model.table.ActionTable

object WeaponActionTable : Table("weapon_actions") {
    val weaponId = reference("weapon_id", WeaponTable)
    val actionId = reference("actions_id", ActionTable)
    override val primaryKey: PrimaryKey = PrimaryKey(weaponId, actionId)
}
