package wasik.infrastructure.model.table

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable

object PropertyTable : LongIdTable(name = "properties") {
    val name = text("name")
    val description = text("description")
}

class PropertyEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<PropertyEntity>(PropertyTable)

    var name by PropertyTable.name
    var description by PropertyTable.description
}

