package hr.factory.app_home.common.data_models

data class DatesResponse(val data: List<Date>)

data class Date(
    val date: String,
    val flag: String
)

data class LoginResponse(val data: LoginData)
data class LoginData(val access_token: String, val token_type: String, val expires_at: String)
