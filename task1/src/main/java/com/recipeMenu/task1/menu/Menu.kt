package com.recipeMenu.task1.menu

import com.recipeMenu.task1.model.Recipe
import com.recipeMenu.task1.model.Subscription


class Menu(var availableRecipes: List<Recipe>, var subscription: Subscription) {

    var selectedRecipeIdList: MutableList<Int> = mutableListOf()

    //mark multiple recipes as selected.
      fun selectRecipes(vararg selectedRecipeId: Int) {
        val recipesLimit = if (subscription.isForFamily) FAMILY_RECIPES_LIMIT else REGULAR_RECIPES_LIMIT
        val isAdditionNotAllowed = canAddRecipes(recipesLimit, selectedRecipeId).not()
        if (isAdditionNotAllowed) {
            throw ExceedLimitException("You can only select $recipesLimit recipes!")
        }
        selectedRecipeId.forEach {
            if (!selectedRecipeIdList.contains(it))
                selectedRecipeIdList.add(it)
        }
    }

    data class ExceedLimitException(val customMessage: String) : Exception()

    private fun canAddRecipes(recipesLimit: Int, selectedRecipeId: IntArray): Boolean {
        return countSelectedRecipes() + selectedRecipeId.size <= recipesLimit
    }

    //count the number of recipes that have been selected
    fun countSelectedRecipes(): Int {
        return selectedRecipeIdList.size
    }


    //unselect multiple selected recipes
    fun unSelectRecipes(vararg selectedRecipeIds: Int) {
        selectedRecipeIds.forEach {
            if (selectedRecipeIdList.contains(it))
                selectedRecipeIdList.remove(it)
        }
    }

    //request a list of selected recipes
    fun getSelectedRecipes(): List<Recipe> {
        return availableRecipes.filter { selectedRecipeIdList.contains(it.id) }
    }

    //request a list of recipes which have a certain tag.
    fun getATagSelectedRecipes(
        tag: String,
    ): List<Recipe> {
        return availableRecipes.filter { it.tags.contains(tag) }
    }


    companion object {

        private const val FAMILY_RECIPES_LIMIT = 5
        private const val REGULAR_RECIPES_LIMIT = 3
    }

}
