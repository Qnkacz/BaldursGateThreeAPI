package wasik.api.service.mapper.item

import wasik.api.model.DieType
import domain.model.misc.DieType as DomainDIe

interface DieMapper {
    suspend fun mapDie(apiDie: DieType): DomainDIe
}