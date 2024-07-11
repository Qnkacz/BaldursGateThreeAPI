package wasik.infrastructure.model.table

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable

object ActionTable : LongIdTable("actions") {
    val name = text("name")
    val description = text("description")
}

//TODO find if i have to add ids if i want to schema from kotlin exposed to postgre
// if i'm using a longIdTable so it is probably handled
// i found out how to check the db if a table exists, on spring boot startup
// see more here: https://chatgpt.com/c/fc5aa43a-af33-4e49-8089-82008f31f76e
class ActionEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<ActionEntity>(ActionTable)

    var name: String by ActionTable.name
    var description: String by ActionTable.description
}
