package com.recipeMenu.task2.repository

import com.recipeMenu.task2.BuildConfig
import com.recipeMenu.task2.api.ApiClient
import com.recipeMenu.task2.model.responses.Response

class RecipeRepository() {

    suspend fun getRecipes(): Response {
        return try {
            val response = ApiClient().getApiService(BuildConfig.DEBUG).getRecipes()
            Response(true, null, response)
        } catch (e: Exception) {

            Response(false, e, emptyList())
        }
    }
}