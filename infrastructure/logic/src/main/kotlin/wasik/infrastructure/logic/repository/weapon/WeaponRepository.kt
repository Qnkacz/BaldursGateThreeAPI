package wasik.infrastructure.logic.repository.weapon

import domain.model.item.CommonItemData
import domain.model.item.ItemRarity
import domain.model.item.weapon.*
import io.klogging.Klogging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SizedCollection
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import wasik.infrastructure.model.exception.InfrastructureException
import wasik.infrastructure.model.exception.InfrastructureExceptionType
import wasik.infrastructure.model.table.ActionEntity
import wasik.infrastructure.model.table.DamageEntity
import wasik.infrastructure.model.table.PropertyEntity
import wasik.infrastructure.model.table.item.weapon.*
import java.util.concurrent.CompletableFuture

@Repository
open class WeaponRepository : Klogging {

    fun saveWeapon(weaponCommand: WeaponCommand): CompletableFuture<WeaponEntity> {
        val savedWeaponEntity = CompletableFuture<WeaponEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                saveWeaponEntity(weaponCommand, savedWeaponEntity)
            } catch (ex: Exception) {
                throw InfrastructureException(
                    "Cannot insert property",
                    InfrastructureExceptionType.DATABASE_CONNECTION_ISSUE
                )
            }
        }
        return savedWeaponEntity
    }

    fun addDamageToWeapon(weaponEntity: WeaponEntity, damageEntityList: List<DamageEntity>) {
        transaction {
            weaponEntity.damageEntityList = SizedCollection(damageEntityList)
        }
    }

    fun addPropertiesToWeapon(weaponEntity: WeaponEntity, properties: List<PropertyEntity>) {
        transaction {
            weaponEntity.properties = SizedCollection(properties)
        }
    }

    fun addActionsToWeapon(weaponEntity: WeaponEntity, actions: List<ActionEntity>) {
        transaction {
            weaponEntity.actions = SizedCollection(actions)
        }
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

    private fun saveWeaponEntity(
        weaponCommand: WeaponCommand,
        savedWeaponEntity: CompletableFuture<WeaponEntity>
    ): WeaponEntity = transaction {
        val weaponEntity: WeaponEntity = WeaponEntity.new {
            name = weaponCommand.commonData.name
            rarity = weaponCommand.commonData.rarity.ordinal
            value = weaponCommand.commonData.value
            weight = weaponCommand.commonData.weight
            description = weaponCommand.commonData.description
            weaponClass = weaponCommand.weaponClass.ordinal
            proficiency = weaponCommand.proficiency.ordinal
            handType = weaponCommand.handType.ordinal
            type = weaponCommand.type.ordinal
            range = weaponCommand.range
            upgrade = 69
        }
        savedWeaponEntity.complete(weaponEntity)
        return@transaction weaponEntity
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