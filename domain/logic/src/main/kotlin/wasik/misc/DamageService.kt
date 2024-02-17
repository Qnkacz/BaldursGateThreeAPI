package wasik.misc

import kotlinx.coroutines.flow.Flow
import model.damage.Damage
import model.misc.Action
import wasik.entity.DamageEntity

interface DamageService {

    suspend fun postDamage(damage: Damage)
    suspend fun postDamages(damages: Collection<Damage>): Flow<DamageEntity>
    suspend fun getDamage(name: String): Action
    suspend fun updateDamage(damage: Damage): Damage
}