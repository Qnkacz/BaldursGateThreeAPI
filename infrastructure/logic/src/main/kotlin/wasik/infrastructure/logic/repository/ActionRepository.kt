package wasik.infrastructure.logic.repository

import domain.model.misc.Action
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import wasik.infrastructure.model.table.ActionEntity
import wasik.infrastructure.model.table.ActionTable
import java.util.concurrent.CompletableFuture

@Repository
open class ActionRepository {

    fun saveAction(action: Action): CompletableFuture<ActionEntity> {
        val result = CompletableFuture<ActionEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            transaction {
                val actionEntity = ActionEntity.new {
                    name = action.name
                    description = action.description ?: ""
                }
                result.complete(actionEntity)
            }
        }
        return result
    }

    fun findById(id: Long): CompletableFuture<Action> {
        val result = CompletableFuture<Action>()
        CoroutineScope(Dispatchers.IO).launch {
            transaction {
                val action: Action? = ActionTable.selectAll().where { ActionTable.id eq id }.firstOrNull()?.let {
                    Action(it[ActionTable.name], it[ActionTable.description])
                }
                if (action == null) {
                    throw IllegalStateException("No action found for id $id")
                }
                result.complete(action)
            }
        }
        return result
    }
}