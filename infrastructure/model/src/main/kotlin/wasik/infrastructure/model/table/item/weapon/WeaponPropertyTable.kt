package wasik.infrastructure.model.table.item.weapon

import org.jetbrains.exposed.sql.Table
import wasik.infrastructure.model.table.PropertyTable

object WeaponPropertyTable : Table("weapon_properties") {
    val weaponId = reference("weapon_id", WeaponTable)
    val propertyId = reference("property_id", PropertyTable)
}
