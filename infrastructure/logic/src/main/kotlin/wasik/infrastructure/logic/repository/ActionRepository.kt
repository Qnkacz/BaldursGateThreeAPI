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
import wasik.infrastructure.model.table.ActionTable
import java.util.concurrent.CompletableFuture

@Repository
open class ActionRepository {

    fun saveAction(action: Action): CompletableFuture<EntityID<Long>> {
        val completableFuture = CompletableFuture<EntityID<Long>>()
        CoroutineScope(Dispatchers.IO).launch {
            transaction {
                val insertAndGetId = ActionTable.insertAndGetId {
                    it[name] = action.name
                    it[description] = action.description ?: ""
                }
                completableFuture.complete(insertAndGetId)
            }
        }
        return completableFuture
    }

    fun findById(id: Long): CompletableFuture<Action> {
        val completableFuture = CompletableFuture<Action>()
        CoroutineScope(Dispatchers.IO).launch {
            transaction {
                val action: Action? = ActionTable.selectAll().where { ActionTable.id eq id }.firstOrNull()?.let {
                    Action(it[ActionTable.name], it[ActionTable.description])
                }
                if (action == null) {
                    throw IllegalStateException("No action found for id $id")
                }
                completableFuture.complete(action)
            }
        }
        return completableFuture
    }
}