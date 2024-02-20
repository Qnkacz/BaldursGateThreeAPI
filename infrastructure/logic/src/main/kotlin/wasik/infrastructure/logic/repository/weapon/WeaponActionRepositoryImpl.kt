package wasik.infrastructure.logic.repository.weapon

import kotlinx.coroutines.coroutineScope
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import wasik.infrastructure.model.entity.item.weapon.WeaponActionEntity

@Repository
open class WeaponActionRepositoryImpl(private val databaseClient: DatabaseClient) : WeaponActionRepository {
    override suspend fun saveWeaponAction(weaponActionEntity: WeaponActionEntity): Unit = coroutineScope {
        databaseClient.sql("INSERT INTO weapon_actions (weapon_id, actions_id) VALUES (:weaponId, :actionsId)")
            .bind("weaponId", weaponActionEntity.weaponId)
            .bind("actionsId", weaponActionEntity.actionId)
            .fetch()
            .rowsUpdated()
            .subscribe()
        //TODO for future self, maybe this will work? https://www.detroitlabs.com/blog/introduction-to-spring-data-r2dbc-with-kotlin/
    }
}