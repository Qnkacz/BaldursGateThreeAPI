package wasik.api.service.mapper.item

import org.springframework.stereotype.Component
import wasik.api.model.exception.ApiException
import wasik.api.model.exception.ApiExceptionType
import wasik.api.model.item.ItemRarity
import domain.model.item.ItemRarity as DomainRarity

@Component
class RarityMapper {
    fun mapToDomainRarity(apiRarity: ItemRarity): DomainRarity {
        return when (apiRarity) {
            ItemRarity.COMMON -> DomainRarity.COMMON
            ItemRarity.UNCOMMON -> DomainRarity.UNCOMMON
            ItemRarity.RARE -> DomainRarity.RARE
            ItemRarity.VERY_RARE -> DomainRarity.VERY_RARE
            ItemRarity.LEGENDARY -> DomainRarity.LEGENDARY
            else -> throw ApiException(type = ApiExceptionType.MAPPING_ERROR, message = MAPPING_ERROR_MSG)
        }
    }

    fun mapToApiRarity(domainRarity: DomainRarity): ItemRarity {
        return when (domainRarity) {
            DomainRarity.COMMON -> ItemRarity.COMMON
            DomainRarity.UNCOMMON -> ItemRarity.UNCOMMON
            DomainRarity.RARE -> ItemRarity.RARE
            DomainRarity.VERY_RARE -> ItemRarity.VERY_RARE
            DomainRarity.LEGENDARY -> ItemRarity.LEGENDARY
            else -> throw ApiException(type = ApiExceptionType.MAPPING_ERROR, message = MAPPING_ERROR_MSG)
        }
    }

    private companion object {
        const val MAPPING_ERROR_MSG = "Mapping error"
    }
}