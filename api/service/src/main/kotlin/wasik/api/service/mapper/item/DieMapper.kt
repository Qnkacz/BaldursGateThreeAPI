package wasik.api.service.mapper.item

import org.springframework.stereotype.Component
import wasik.api.model.DieType
import wasik.api.model.exception.ApiException
import wasik.api.model.exception.ApiExceptionType
import domain.model.misc.DieType as DomainDie

@Component
class DieMapper {
    suspend fun mapDie(apiDie: DieType): DomainDie {
        return when (apiDie) {
            DieType.D4 -> DomainDie.D4
            DieType.D6 -> DomainDie.D6
            DieType.D8 -> DomainDie.D8
            DieType.D10 -> DomainDie.D10
            DieType.D12 -> DomainDie.D12
            DieType.D20 -> DomainDie.D20
            DieType.D100 -> DomainDie.D100
            else -> throw ApiException(type = ApiExceptionType.MAPPING_ERROR, message = "Could not map Die type")
        }
    }
}