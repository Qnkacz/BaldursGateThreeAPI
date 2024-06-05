package wasik.infrastructure.logic.repository.weapon

import domain.model.item.CommonItemData
import domain.model.item.ItemRarity
import domain.model.item.weapon.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import wasik.infrastructure.model.exception.InfrastructureException
import wasik.infrastructure.model.exception.InfrastructureExceptionType
import wasik.infrastructure.model.table.item.weapon.WeaponActionTable
import wasik.infrastructure.model.table.item.weapon.WeaponDamageTable
import wasik.infrastructure.model.table.item.weapon.WeaponPropertyTable
import wasik.infrastructure.model.table.item.weapon.WeaponTable
import java.util.concurrent.CompletableFuture

@Repository
open class WeaponRepository {

    fun saveWeapon(weaponCommand: WeaponCommand): CompletableFuture<EntityID<Long>> {
        val result = CompletableFuture<EntityID<Long>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                transaction {
                    val insertAndGetId: EntityID<Long> = WeaponTable.insertAndGetId {
                        it[name] = weaponCommand.commonData.name
                        it[rarity] = weaponCommand.commonData.rarity.ordinal
                        it[value] = weaponCommand.commonData.value
                        it[weight] = weaponCommand.commonData.weight
                        it[description] = weaponCommand.commonData.description
                        it[weaponClass] = weaponCommand.weaponClass.ordinal
                        it[proficiency] = weaponCommand.proficiency.ordinal
                        it[handType] = weaponCommand.handType.ordinal
                        it[type] = weaponCommand.type.ordinal
                        it[range] = weaponCommand.range
                        it[upgrade] = 69
                    }
                    result.complete(insertAndGetId)
                }
            } catch (ex: Exception) {
                throw InfrastructureException(
                    "Cannot insert property",
                    InfrastructureExceptionType.DATABASE_CONNECTION_ISSUE
                )
            }
        }
        return result
    }

    fun findByName(name: String): CompletableFuture<List<Pair<EntityID<Long>, WeaponCommand.Builder>>> {
        val result = CompletableFuture<List<Pair<EntityID<Long>, WeaponCommand.Builder>>>()
        CoroutineScope(Dispatchers.IO).launch {
            transaction {
                val weapons = WeaponTable
                    .selectAll()
                    .where { WeaponTable.name eq name }
                    .map { Pair(it[WeaponTable.id], mapToWeapon(it)) }
                result.complete(weapons)
            }
        }
        return result
    }

    fun saveWeaponActions(actionIdList: List<EntityID<Long>>, savedWeaponId: EntityID<Long>?) {
        actionIdList.forEach { action ->
            WeaponActionTable.insert {
                it[weaponId] = savedWeaponId!!.value
                it[actionId] = action.value
            }
        }
    }

    fun saveWeaponProperties(propertyIdList: List<EntityID<Long>>, savedWeaponId: EntityID<Long>?) {
        propertyIdList.forEach { property ->
            WeaponPropertyTable.insert {
                it[weaponId] = savedWeaponId!!.value
                it[propertyId] = property.value
            }
        }
    }

    fun saveWeaponDamages(damageIdList: List<EntityID<Long>>, savedWeaponId: EntityID<Long>?) {
        damageIdList.forEach { damage ->
            WeaponDamageTable.insert {
                it[weaponId] = savedWeaponId!!.value
                it[damageId] = damage.value
            }
        }
    }

    fun getWeaponDamagesIds(weaponId: EntityID<Long>): CompletableFuture<List<EntityID<Long>>> {
        val result = CompletableFuture<List<EntityID<Long>>>()
        CoroutineScope(Dispatchers.IO).launch {
            transaction {
                val damageIds = WeaponDamageTable
                    .selectAll()
                    .where { WeaponDamageTable.weaponId eq weaponId.value }
                    .map { it[WeaponDamageTable.damageId] }

                if (damageIds.isEmpty()) {
                    result.complete(listOf())
                }
                result.complete(damageIds)
            }
        }
        return result
    }

    fun getWeaponPropertiesIds(weaponId: EntityID<Long>): CompletableFuture<List<EntityID<Long>>> {
        val result = CompletableFuture<List<EntityID<Long>>>()
        CoroutineScope(Dispatchers.IO).launch {
            transaction {
                val propertyIds = WeaponPropertyTable
                    .selectAll()
                    .where { WeaponPropertyTable.weaponId eq weaponId.value }
                    .map { it[WeaponPropertyTable.propertyId] }

                if (propertyIds.isEmpty()) {
                    result.complete(listOf())
                }
                result.complete(propertyIds)
            }
        }
        return result
    }

    fun getWeaponActionsIds(weaponId: EntityID<Long>): CompletableFuture<List<EntityID<Long>>> {
        val result = CompletableFuture<List<EntityID<Long>>>()
        CoroutineScope(Dispatchers.IO).launch {
            transaction {
                val actionIds = WeaponActionTable
                    .selectAll()
                    .where { WeaponActionTable.weaponId eq weaponId.value }
                    .map { it[WeaponActionTable.actionId] }

                if (actionIds.isEmpty()) {
                    result.complete(listOf())
                }
                result.complete(actionIds)
            }
        }
        return result
    }

    private fun mapToWeapon(it: ResultRow): WeaponCommand.Builder {
        val rarity: Int = it[WeaponTable.rarity]
        val weaponClass: Int = it[WeaponTable.weaponClass]
        val weaponType: Int = it[WeaponTable.type]
        val handType: Int = it[WeaponTable.handType]
        val proficiency: Int = it[WeaponTable.proficiency]
        val range = it[WeaponTable.range]

        return WeaponCommand.Builder()
            .commonData(mapCommonItemData(it, rarity))
            .weaponClass(WeaponClass.entries[weaponClass])
            .type(WeaponType.entries[weaponType])
            .handType(HandType.entries[handType])
            .proficiency(WeaponProficiency.entries[proficiency])
            .range(range)
    }

    private fun mapCommonItemData(
        it: ResultRow,
        rarity: Int
    ) = CommonItemData(
        name = it[WeaponTable.name],
        rarity = ItemRarity.entries[rarity],
        value = it[WeaponTable.value],
        weight = it[WeaponTable.weight],
        description = it[WeaponTable.description]
    )
}