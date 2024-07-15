package wasik.domain.logic.misc

import domain.model.damage.Damage
import wasik.infrastructure.model.table.DamageEntity

interface DamageService {

    suspend fun postDamage(damage: Damage)
    suspend fun postDamages(damages: Collection<Damage>): List<DamageEntity>
    suspend fun getDamageById(id: Long): Damage
    suspend fun updateDamage(damage: Damage): Damage
}