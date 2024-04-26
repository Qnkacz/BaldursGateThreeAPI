package wasik.infrastructure.model.table

import org.jetbrains.exposed.dao.id.LongIdTable

object DamageTable : LongIdTable(name = "damage") {
    val type = integer("type")
    val amount = text("amount")
}
