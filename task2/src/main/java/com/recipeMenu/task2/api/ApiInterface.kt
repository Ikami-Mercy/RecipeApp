package com.recipeMenu.task2.api

import com.recipeMenu.task2.model.responses.Recipe
import com.recipeMenu.task2.utils.Constants.RECIPES_URL
import retrofit2.http.GET

interface ApiInterface {
    @GET(RECIPES_URL)
    suspend fun getRecipes(): List<Recipe>


}