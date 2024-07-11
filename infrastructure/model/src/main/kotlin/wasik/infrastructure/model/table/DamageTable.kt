package wasik.infrastructure.model.table

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable

object DamageTable : LongIdTable(name = "damage") {
    val type = integer("type")
    val amount = text("amount")
}

class DamageEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<DamageEntity>(DamageTable)

    var type by DamageTable.type
    var amount by DamageTable.amount
}
