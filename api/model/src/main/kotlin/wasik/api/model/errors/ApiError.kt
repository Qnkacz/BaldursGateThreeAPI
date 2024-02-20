package wasik.api.model.model.errors

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Error type used to determine what exactly went wrong")
data class ApiError(val code: String, val message: String)
