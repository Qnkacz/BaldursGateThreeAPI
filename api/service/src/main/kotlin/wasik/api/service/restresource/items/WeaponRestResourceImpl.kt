package wasik.api.service.restresource.items

import jakarta.validation.Valid
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import wasik.api.model.model.weapon.Weapon
import wasik.api.service.mapper.item.weapon.WeaponMapper
import wasik.domain.logic.items.WeaponService

@RestController
@RequestMapping("/api/weapon")
open class WeaponRestResourceImpl(private val weaponMapper: WeaponMapper, private val weaponService: WeaponService) :
    WeaponRestResource {
    @GetMapping("/{name}")
    override suspend fun getWeaponByName(@PathVariable name: String): ResponseEntity<List<Weapon>> = coroutineScope {
        val foundWeapons = weaponService.getWeaponByName(name).map {
            async {
                weaponMapper.mapToWeaponResponse(it)
            }
        }
            .awaitAll()
        ResponseEntity.ok(foundWeapons)
    }

    @PostMapping
    override suspend fun postWeapon(@RequestBody @Valid weapon: Weapon): ResponseEntity<Void> = coroutineScope {
        val weaponCommandDeferred = async { weaponMapper.mapToWeaponCommand(weapon) }
        weaponService.postWeapon(weaponCommandDeferred.await())
        ResponseEntity.ok().build()
    }

}