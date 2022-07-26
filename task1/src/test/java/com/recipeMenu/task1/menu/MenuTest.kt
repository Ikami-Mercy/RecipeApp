package com.recipeMenu.task1.menu

import com.recipeMenu.task1.model.Recipe
import com.recipeMenu.task1.model.Subscription
import junit.framework.Assert.assertEquals
import org.junit.Test


class MenuTest {
    var familySubscription: Subscription = Subscription(1, "7-03-2022", true)
    var nonFamilySubscription: Subscription = Subscription(2, "11-03-2022", false)
    private var availableRecipe = listOf(
        Recipe(1, "Cake", listOf("baking")),
        Recipe(2, "Beef steak", listOf("hot", "spicy")),
        Recipe(3, "Fried chicken", listOf("hot", "spicy")),
        Recipe(4, "Fried curry", listOf("hot", "spicy")),
        Recipe(5, "Beans", listOf("hot", "spicy")),
        Recipe(6, "Soya", listOf("low calories"))
    )

    @Test
    fun getAllSelectedRecipesCountAndNonIsSelectedShouldReturnZero() {
        var menu = Menu(emptyList(), familySubscription)
        var selectedCount = menu.countSelectedRecipes()
        assertEquals(0, selectedCount)
    }

    @Test
    fun getAllSelectedRecipesCountAndOneIsSelectedShouldReturnOne() {
        var menu = Menu(availableRecipe, familySubscription)
        menu.selectRecipes(2)
        var selectedCount = menu.countSelectedRecipes()
        assertEquals(1, selectedCount)
    }

    @Test
    fun selectTwoRecipesSelectedCountShouldReturnTwo() {
        var menu = Menu(availableRecipe, familySubscription)
        menu.selectRecipes(2, 3)
        var selectedCount = menu.countSelectedRecipes()
        assertEquals(2, selectedCount)
    }

    @Test(expected = Exception::class)
    fun ifSubscriptionIsNotForFamilyAndSelectsMoreThanThreeShouldthrowSubscriptionException() {
        var menu = Menu(availableRecipe, nonFamilySubscription)
        menu.selectRecipes(2, 3,1,4)

    }
    @Test(expected = Exception::class)
    fun ifSubscriptionIsForFamilyAndSelectsMoreThanFiveShouldthrowSubscriptionException() {
        var menu = Menu(availableRecipe, familySubscription)
        menu.selectRecipes(2, 3,1,4,5,6)

    }
    @Test
    fun ifSubscriptionIsForFamilyAndSelectsLessThanFiveSelectedCountShouldBeNumberOfSelectedRecipes() {
        var menu = Menu(availableRecipe, familySubscription)
        menu.selectRecipes(2, 3,1)
        var selectedCount = menu.countSelectedRecipes()
        assertEquals(3, selectedCount)
    }
    @Test
    fun ifSubscriptionIsNotForFamilyAndSelectsLessThanThreeSelectedCountShouldBeNumberOfSelectedRecipes() {
        var menu = Menu(availableRecipe, nonFamilySubscription)
        menu.selectRecipes(2, 3)
        var selectedCount = menu.countSelectedRecipes()
        assertEquals(2, selectedCount)
    }
    @Test(expected = Exception::class)
    fun ifSubscriptionIsNotForFamilyAndSelectsTwoRecipesWhenTheNumberOfSelectedRecipesIsMoreThanOneShouldThrowException() {
        var menu = Menu(availableRecipe, nonFamilySubscription)
        menu.selectRecipes(2, 3)
        menu.selectRecipes(1, 6)
    }
    @Test
    fun ifRecipeIsSelectedItShouldNotBeReselected() {
        var menu = Menu(availableRecipe, nonFamilySubscription)
        menu.selectRecipes(2, 3)
        menu.selectRecipes(2)
        var selectedCount = menu.countSelectedRecipes()
        assertEquals(2, selectedCount)
    }

    @Test
    fun getAllRecipesWithATagReturnsAListOfRecipesWithThePassedTag(){
        var menu = Menu(availableRecipe, nonFamilySubscription)
        var list=menu.getATagSelectedRecipes("hot")
        assertEquals(4, list.size)
    }
    @Test
    fun getAllRecipesWithAnUnavailableTagShouldReturnZero(){
        var menu = Menu(availableRecipe, nonFamilySubscription)
        var list = menu.getATagSelectedRecipes("high calories")
        assertEquals(0, list.size)
    }
    @Test
    fun getSelectedRecipesShouldReturnAListOfTheSameSizeAsTheSelectedRecipesSize(){
        var menu = Menu(availableRecipe, familySubscription)
        menu.selectRecipes(1,6)
        var list =menu.getSelectedRecipes()
        assertEquals(2, list.size)
    }
    @Test
    fun getSelectedRecipesShouldReturnZeroIfThereAreNoSelectedRecipes(){
        var menu = Menu(availableRecipe, familySubscription)
        var list =menu.getSelectedRecipes()
        assertEquals(0, list.size)
    }
    @Test
    fun getSelectedRecipesShouldReturnSelectedRecipesLessTheNumberOfUnselectedRecipes(){
        var menu = Menu(availableRecipe, familySubscription)
        menu.selectRecipes(1,2,3,4)
        menu.unSelectRecipes(4)
        var list = menu.getSelectedRecipes()
        assertEquals(3, list.size)
    }
    @Test
    fun unselectedRecipesShouldNotUnselectARecipeThatIsNotSelected(){
        var menu = Menu(availableRecipe, familySubscription)
        menu.selectRecipes(1,2,3,4)
        menu.unSelectRecipes(6)
        var list = menu.getSelectedRecipes()
        assertEquals(4, list.size)
    }
}