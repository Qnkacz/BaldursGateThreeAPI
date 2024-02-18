package wasik.infrastructure.logic.repository.weapon

import kotlinx.coroutines.coroutineScope
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import wasik.infrastructure.model.entity.item.weapon.WeaponDamageEntity

@Repository
open class WeaponDamageRepositoryImpl(private val databaseClient: DatabaseClient) : WeaponDamageRepository {
    override suspend fun saveWeaponDamage(weaponDamage: WeaponDamageEntity): Unit = coroutineScope {
        databaseClient.sql("INSERT INTO weapon_damage (weapon_id, damage_id) VALUES (:weaponId, :damageId)")
            .bind("weaponId", weaponDamage.weaponId)
            .bind("damageId", weaponDamage.damageId)
            .fetch()
            .rowsUpdated()
            .subscribe()
    }

    override suspend fun getWeaponDamage(weaponId: Int, damageId: Int): WeaponDamageEntity? {
        TODO("Not yet implemented")
    }
}