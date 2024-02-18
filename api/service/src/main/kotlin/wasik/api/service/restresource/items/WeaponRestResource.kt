package wasik.api.service.restresource.items

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import wasik.api.model.model.errors.ApiError
import wasik.api.model.model.weapon.Weapon

@Tag(name = "Items", description = "Items rest resource")
interface WeaponRestResource {

    @Operation(
        operationId = "getWeaponById",
        description = "Gets a weapon by it's name"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Succesfuly found the weapon",
                content = [
                    Content(mediaType = "application/json", schema = Schema(implementation = Weapon::class)),
                ]
            ),
            ApiResponse(
                responseCode = "404", description = "weapon not found",
                content = [
                    Content(mediaType = "application/json", schema = Schema(implementation = ApiError::class)),
                ]
            ),
            ApiResponse(
                responseCode = "500", description = "Internal server error",
                content = [
                    Content(mediaType = "application/json", schema = Schema(implementation = ApiError::class)),
                ]
            ),
        ]
    )
    suspend fun getWeaponByName(@Parameter(description = "Name of the weapon") name: String): ResponseEntity<Weapon>
    suspend fun postWeapon(@Parameter weapon: Weapon): ResponseEntity<Void>
}