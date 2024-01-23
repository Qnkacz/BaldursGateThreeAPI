package wasik.api.restResource.items.weapon

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import wasik.api.model.weapon.Weapon

@RestController
@RequestMapping("/api/weapon")
open class WeaponRestResourceImpl : WeaponRestResource{

    //    @GetMapping("/{name}")
//    fun getWeaponByName(@PathVariable name: String): Weapon? {
//        return null;
//    }
    @GetMapping("/{name}")
    override suspend fun getWeaponByName(@PathVariable name: String): ResponseEntity<Weapon> {
        return ResponseEntity.ok(null);
    }
}