package wasik.infrastructure.model.table.item.weapon

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import wasik.infrastructure.model.table.ActionEntity
import wasik.infrastructure.model.table.DamageEntity
import wasik.infrastructure.model.table.PropertyEntity

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

class WeaponEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<WeaponEntity>(WeaponTable)

    var name: String by WeaponTable.name
    var rarity: Int by WeaponTable.rarity
    var value: Float by WeaponTable.value
    var weight: Float by WeaponTable.weight
    var description: String by WeaponTable.description
    var weaponClass: Int by WeaponTable.weaponClass
    var proficiency: Int by WeaponTable.proficiency
    var handType: Int by WeaponTable.handType
    var type: Int by WeaponTable.type
    var upgrade: Int by WeaponTable.upgrade
    var range: Float by WeaponTable.range

    var actions by ActionEntity via WeaponActionTable
    var damageEntityList by DamageEntity via WeaponDamageTable
    var properties by PropertyEntity via WeaponPropertyTable
}
