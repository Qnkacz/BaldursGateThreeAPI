package wasik.infrastructure.logic.repository

import domain.model.damage.Damage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import wasik.infrastructure.logic.mapper.DamageInfrastructureMapper
import wasik.infrastructure.model.table.DamageTable
import java.util.concurrent.CompletableFuture

@Repository
open class DamageRepository(
    open val damageInfrastructureMapper: DamageInfrastructureMapper
) {

    fun save(damage: Damage): CompletableFuture<EntityID<Long>> {
        val result = CompletableFuture<EntityID<Long>>()
        CoroutineScope(Dispatchers.IO).launch {
            val damageAmountString = damageInfrastructureMapper.damageToString(damage.dieType, damage.dieAmount)
            transaction {
                val insertAndGetId = DamageTable.insertAndGetId {
                    it[type] = damage.damageType.ordinal
                    it[amount] = damageAmountString
                }
                result.complete(insertAndGetId)
            }
        }
        return result
    }

    fun findById(id: Long): CompletableFuture<Damage> {
        val result = CompletableFuture<Damage>()
        CoroutineScope(Dispatchers.IO).launch {
            transaction {
                val damage: Damage? = DamageTable.selectAll().where { DamageTable.id eq id }.firstOrNull()?.let {
                    damageInfrastructureMapper.mapToDamage(it)
                }
                if (damage == null) {
                    throw NullPointerException("Damage with id $id not found")
                }
                result.complete(damage)
            }
        }
        return result
    }
}