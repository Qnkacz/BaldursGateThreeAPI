package wasik.infrastructure.logic.repository.weapon

import kotlinx.coroutines.coroutineScope
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import wasik.infrastructure.model.entity.item.weapon.WeaponPropertyEntity

@Repository
open class WeaponPropertyRepositoryImpl(private val databaseClient: DatabaseClient) : WeaponPropertyRepository {

    override suspend fun saveWeaponProperty(weaponProperty: WeaponPropertyEntity): Unit = coroutineScope {
        databaseClient.sql("INSERT INTO weapon_properties (weapon_id, property_id) VALUES (:weaponId, :propertyId)")
            .bind("weaponId", weaponProperty.weaponId)
            .bind("propertyId", weaponProperty.propertyId)
            .fetch()
            .rowsUpdated()
            .subscribe()
        //TODO for future self, maybe this will work? https://www.detroitlabs.com/blog/introduction-to-spring-data-r2dbc-with-kotlin/
    }
}