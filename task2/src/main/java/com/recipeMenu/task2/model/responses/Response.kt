package com.recipeMenu.task2.model.responses

import java.lang.Exception


data class Response(
    val wasSuccessful: Boolean,
    val exception: Exception?,
    val body: List<Recipe>
)