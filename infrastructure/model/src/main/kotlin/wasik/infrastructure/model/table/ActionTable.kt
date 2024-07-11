package wasik.infrastructure.model.table

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable

object ActionTable : LongIdTable("actions") {
    val name = text("name")
    val description = text("description")
}

class ActionEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<ActionEntity>(ActionTable)

    var name: String by ActionTable.name
    var description: String by ActionTable.description
}
