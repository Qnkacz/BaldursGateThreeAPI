package wasik.repository.weapon

import kotlinx.coroutines.coroutineScope
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import wasik.entity.item.weapon.WeaponDamageEntity

@Repository
open class WeaponDamageRepositoryImpl(private val databaseClient: DatabaseClient) : WeaponDamageRepository {
    override suspend fun saveWeaponDamage(weaponDamage: WeaponDamageEntity): Unit = coroutineScope {
        databaseClient.sql("INSERT INTO weapon_damage (weapon_id, damage_id) VALUES (:weaponId, :damageId)")
            .bind("weaponId", weaponDamage.weaponId)
            .bind("damageId", weaponDamage.damageId)
            .fetch()
            .rowsUpdated()
            .subscribe()
        //TODO for future self, maybe this will work? https://www.detroitlabs.com/blog/introduction-to-spring-data-r2dbc-with-kotlin/
    }

    override suspend fun getWeaponDamage(weaponId: Int, damageId: Int): WeaponDamageEntity? {
        TODO("Not yet implemented")
    }
}