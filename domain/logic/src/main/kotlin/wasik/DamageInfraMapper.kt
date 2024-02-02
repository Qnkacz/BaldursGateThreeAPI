package wasik

import model.damage.Damage
import wasik.entity.DamageEntity

interface DamageInfraMapper {
    suspend fun mapToDamageEntity(damage: Damage) : DamageEntity
}