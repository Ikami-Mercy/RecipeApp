package com.recipeMenu.task2.utils

import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ErrorUtils {
    private  val NOT_CONNECTED_TO_INTERNET =
        "You are not connected to the internet, please check you connection and try again."
    private val GENERIC_ERROR = "Something went wrong. Please try again."
    private  val ERROR_CONNECTING_TO_SERVER ="We are currently unable to connect to our servers.Please try again in a few minutes."
    private  val ERROR_400_GENERIC = "We can not process your request as is. Please contact the team."
    private  val ERROR_403_GENERIC ="Unauthorized user.Please contact the team."


    fun getErrorMessage(exception: Exception): String {
        return when (exception) {
            is SocketTimeoutException -> ERROR_CONNECTING_TO_SERVER
            is UnknownHostException -> NOT_CONNECTED_TO_INTERNET
            is ConnectException -> ERROR_CONNECTING_TO_SERVER
            is HttpException -> processHttpException(exception)
            else -> {
                GENERIC_ERROR
            }
        }

    }

    private fun processHttpException(exception: HttpException): String{
        return when (exception.code()) {
            400,409 -> getErrorMessageFromJson(getErrorBodyFromThrowable(exception))
            403 -> ERROR_403_GENERIC
            500, 503, 502 -> ERROR_CONNECTING_TO_SERVER
            else -> GENERIC_ERROR
        }
    }

    private fun getErrorBodyFromThrowable(throwable: HttpException): JSONObject {
        return try {
            JSONObject(throwable.response()!!.errorBody()!!.string())
        } catch (e: Exception) {
            val jsonObject = JSONObject()
            when (e) {
                is JSONException -> {
                    jsonObject.put(
                        "end_point_message",
                        throwable.response()!!.errorBody()!!.string()
                    )
                }
            }
            jsonObject
        }
    }

    private fun getErrorMessageFromJson(jsonObject: JSONObject): String {
        return when {
            //Assumption here is that the endpoint will have an error message in 'message' json key
            jsonObject.has("message") -> {
                jsonObject.getString("message")
            }
            jsonObject.has("end_point_message") -> {
                jsonObject.getString("message")
            }
            else -> {
                ERROR_400_GENERIC
            }
        }
    }

}