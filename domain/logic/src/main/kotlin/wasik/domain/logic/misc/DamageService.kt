package wasik.domain.logic.misc

import kotlinx.coroutines.flow.Flow
import domain.model.damage.Damage
import domain.model.misc.Action
import wasik.infrastructure.model.entity.DamageEntity

interface DamageService {

    suspend fun postDamage(damage: Damage)
    suspend fun postDamages(damages: Collection<Damage>): Flow<DamageEntity>
    suspend fun getDamage(name: String): Action
    suspend fun updateDamage(damage: Damage): Damage
}