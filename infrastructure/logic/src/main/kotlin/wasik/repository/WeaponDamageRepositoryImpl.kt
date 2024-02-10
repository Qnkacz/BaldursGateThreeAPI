package wasik.repository

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import wasik.entity.item.weapon.WeaponDamageEntity

@Repository
open class WeaponDamageRepositoryImpl(private val databaseClient: DatabaseClient) : WeaponDamageRepository {
    override suspend fun saveWeaponDamage(weaponDamage: WeaponDamageEntity): Flux<MutableMap<String, Any>> = coroutineScope {
        databaseClient.sql("INSERT INTO weapon_damage (weapon_id, damage_id) VALUES (:weaponId, :damageId)")
            .bind("weaponId", weaponDamage.weaponId)
            .bind("damageId", weaponDamage.damageId)
            .fetch()
            .all()
            //TODO for future self, maybe this will work? https://www.detroitlabs.com/blog/introduction-to-spring-data-r2dbc-with-kotlin/
    }

    override suspend fun getWeaponDamage(weaponId: Int, damageId: Int): WeaponDamageEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun count(): Long {
        TODO("Not yet implemented")
    }

    override suspend fun delete(entity: WeaponDamageEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll(entities: Iterable<WeaponDamageEntity>) {
        TODO("Not yet implemented")
    }

    override suspend fun <S : WeaponDamageEntity> deleteAll(entityStream: Flow<S>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllById(ids: Iterable<Pair<Int, Int>>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteById(id: Pair<Int, Int>) {
        TODO("Not yet implemented")
    }

    override suspend fun existsById(id: Pair<Int, Int>): Boolean {
        TODO("Not yet implemented")
    }

    override fun findAll(): Flow<WeaponDamageEntity> {
        TODO("Not yet implemented")
    }

    override fun findAllById(ids: Iterable<Pair<Int, Int>>): Flow<WeaponDamageEntity> {
        TODO("Not yet implemented")
    }

    override fun findAllById(ids: Flow<Pair<Int, Int>>): Flow<WeaponDamageEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun findById(id: Pair<Int, Int>): WeaponDamageEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun <S : WeaponDamageEntity> save(entity: S): WeaponDamageEntity {
        TODO("Not yet implemented")
    }

    override fun <S : WeaponDamageEntity> saveAll(entities: Iterable<S>): Flow<S> {
        TODO("Not yet implemented")
    }

    override fun <S : WeaponDamageEntity> saveAll(entityStream: Flow<S>): Flow<S> {
        TODO("Not yet implemented")
    }
}