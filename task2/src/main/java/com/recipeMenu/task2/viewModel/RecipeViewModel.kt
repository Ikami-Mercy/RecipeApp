package com.recipeMenu.task2.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.recipeMenu.task2.api.NetworkResponse
import com.recipeMenu.task2.model.responses.Recipe
import com.recipeMenu.task2.repository.RecipeRepository
import com.recipeMenu.task2.utils.ErrorUtils
import kotlinx.coroutines.launch

class RecipeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RecipeRepository()
    var recipeLiveData: MutableLiveData<NetworkResponse<List<Recipe>>> = MutableLiveData()

    //Pass the different UI states as data is fetching using the NetworkResponse class to the UI
    fun getRecipes() {
        recipeLiveData.value = NetworkResponse.Loading
        viewModelScope.launch {
            var recipesResponse = repository.getRecipes()
            if (recipesResponse.wasSuccessful){
                recipeLiveData.value = NetworkResponse.Success(data = recipesResponse.body)
            }
            else{
                recipeLiveData.value = NetworkResponse.Error(message = recipesResponse.exception?.let {
                    ErrorUtils.getErrorMessage(
                        it,
                    )
                }.toString())
            }
        }
    }

}