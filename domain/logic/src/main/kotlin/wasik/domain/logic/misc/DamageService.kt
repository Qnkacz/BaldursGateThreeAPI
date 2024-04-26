package wasik.domain.logic.misc

import domain.model.damage.Damage
import org.jetbrains.exposed.dao.id.EntityID

interface DamageService {

    suspend fun postDamage(damage: Damage)
    suspend fun postDamages(damages: Collection<Damage>): List<EntityID<Long>>
    suspend fun getDamageById(id: Long): Damage
    suspend fun updateDamage(damage: Damage): Damage
}