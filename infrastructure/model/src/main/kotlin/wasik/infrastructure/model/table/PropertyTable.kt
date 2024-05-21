package wasik.infrastructure.model.table

import org.jetbrains.exposed.dao.id.LongIdTable

object PropertyTable : LongIdTable(name = "property") {
    val name = text("name")
    val description = text("description")
}

