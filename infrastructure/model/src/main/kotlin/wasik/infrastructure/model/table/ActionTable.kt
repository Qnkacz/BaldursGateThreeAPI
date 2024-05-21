package wasik.infrastructure.model.table

import org.jetbrains.exposed.dao.id.LongIdTable

object ActionTable : LongIdTable("actions") {
    val name = text("name")
    val description = text("description")
}
