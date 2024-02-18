package wasik.domain.logic.items.mapper

import domain.model.damage.Damage
import wasik.infrastructure.model.entity.DamageEntity

interface DamageInfraMapper {
    suspend fun mapToDamageEntity(damage: Damage) : DamageEntity
}