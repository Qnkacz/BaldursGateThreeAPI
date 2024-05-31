package wasik.infrastructure.model.table.item.weapon

import org.jetbrains.exposed.dao.id.LongIdTable

object WeaponTable : LongIdTable("weapons") {
    val name = text("name")
    val rarity = integer("rarity")
    val value = float("value")
    val weight = float("weight")
    val description = text("description")
    val weaponClass = integer("class")
    val proficiency = integer("proficiency")
    val handType = integer("hand_type")
    val type = integer("type")
    val upgrade = integer("upgrade")
    val range = float("range")
}
