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

    fun saveWeapon(weapon: Weapon): CompletableFuture<EntityID<Long>> {
        val result = CompletableFuture<EntityID<Long>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                transaction {
                    val insertAndGetId: EntityID<Long> = WeaponTable.insertAndGetId {
                        it[name] = weapon.commonData.name
                        it[rarity] = weapon.commonData.rarity.ordinal
                        it[value] = weapon.commonData.value
                        it[weight] = weapon.commonData.weight
                        it[description] = weapon.commonData.description
                        it[weaponClass] = weapon.weaponClass.ordinal
                        it[proficiency] = weapon.proficiency.ordinal
                        it[handType] = weapon.handType.ordinal
                        it[type] = weapon.type.ordinal
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

    fun findAll(): CompletableFuture<List<Weapon>> {
        val result = CompletableFuture<List<Weapon>>()
        CoroutineScope(Dispatchers.IO).launch {
            transaction {
                val weapons = WeaponTable
                    .selectAll()
                    .map { mapToWeapon(it) }

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

    fun findById(id: Long): CompletableFuture<Weapon> {
        val result = CompletableFuture<Weapon>()
        CoroutineScope(Dispatchers.IO).launch {
            transaction {
                val weapon: Weapon? = WeaponTable.selectAll().where { WeaponTable.id eq id }.firstOrNull()?.let {
                    mapToWeapon(it)
                }
                if (weapon == null) {
                    throw NullPointerException("Damage with id $id not found")
                }
                result.complete(weapon)
            }
        }
        return result
    }

    private fun mapToWeapon(it: ResultRow): Weapon {
        val rarity: Int = it[WeaponTable.rarity]
        val weaponClass: Int = it[WeaponTable.weaponClass]
        val weaponType: Int = it[WeaponTable.type]
        val handType: Int = it[WeaponTable.handType]
        val proficiency: Int = it[WeaponTable.proficiency]
        return Weapon(
            commonData = mapCommonItemData(it, rarity),
            damage = setOf(),
            weaponClass = WeaponClass.entries[weaponClass],
            type = WeaponType.entries[weaponType],
            properties = setOf(),
            handType = HandType.entries[handType],
            proficiency = WeaponProficiency.entries[proficiency],
            actions = setOf()
        )
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