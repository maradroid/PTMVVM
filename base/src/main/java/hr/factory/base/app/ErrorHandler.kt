package hr.factory.base.app

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.annotations.SerializedName
import retrofit2.HttpException
import retrofit2.Response

data class ErrorResponse(
    @SerializedName("errors")
    val errorObj: ErrorObject
)

data class ErrorObject(
    val errors: List<Error>,
    val message: String
)

data class Error(
    val key: String,
    val message: String
)

const val ERROR_SHOW_SNACK = -1
const val ERROR_SHOW_500 = -2

class ErrorHandler(private val gson: Gson) {

    fun handleError(error: Throwable): AppError {
        error.printStackTrace()
        if (error is HttpException) {
            val response = error.response()
            val code = response.code()
            when (code) {
                in 300..499 -> return parseErrorFromResponse(error)
                in 500..599 -> return AppError(ERROR_SHOW_500)
            }
        } else {
            return AppError()
        }

        return AppError()
    }

    private fun parseErrorFromResponse(error: HttpException): AppError {
        error.response().errorBody()?.let {
            val json = it.string()
            return try {
                val parsedResponse = gson.fromJson(json, ErrorResponse::class.java)
                if (parsedResponse.errorObj.errors != null) {
                    val message = if (parsedResponse.errorObj.errors.isEmpty()) {
                        parsedResponse.errorObj.message
                    } else {
                        parsedResponse.errorObj.errors[0].message
                    }
                    AppError(message = message)
                } else
                    return AppError()
            } catch (e: JsonSyntaxException) {
                e.printStackTrace()
                AppError()
            }
        }
        return AppError()
    }
}

data class AppError(val type: Int = ERROR_SHOW_SNACK, val message: String = "Error :(")