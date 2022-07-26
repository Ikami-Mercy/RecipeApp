package com.recipeMenu.task2.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.recipeMenu.task2.R
import com.recipeMenu.task2.api.NetworkResponse
import com.recipeMenu.task2.model.responses.Recipe
import com.recipeMenu.task2.utils.ProgressDialog
import com.recipeMenu.task2.utils.Utils
import com.recipeMenu.task2.view.adapters.RecipesAdapter
import com.recipeMenu.task2.viewModel.RecipeViewModel
import kotlinx.android.synthetic.main.activity_recipe.*


class RecipeActivity : AppCompatActivity(), RecipesAdapter.ItemClickListener {
    private var adapter: RecipesAdapter? = null
    private val progressDialog = ProgressDialog()
    private var viewModel: RecipeViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_recipe)
        viewModel = ViewModelProvider(this)[RecipeViewModel::class.java]
        tvDate.text = Utils.getToDate()
        observeRecipes()
        viewModel?.getRecipes()
    }
    //Set data to the adapter
    private fun processViewToShow(recipeItems: List<Recipe>) {
        adapter = RecipesAdapter( recipeItems)
        rvRecipeItems.adapter = adapter
        rvRecipeItems.layoutManager = LinearLayoutManager(this)

    }

    //Observe view model's recipe live data to update the UI as the data changes
    private fun observeRecipes() {
        viewModel?.recipeLiveData?.observe(this, Observer {
            when (it) {
                is NetworkResponse.Loading -> progressDialog.show(this)
                is NetworkResponse.Success ->{
                    progressDialog.dismiss()
                    processViewToShow(it.data)
                }
                is NetworkResponse.Error -> Snackbar.make(this,activityRecipe,it.message,
                    Snackbar.LENGTH_SHORT).show()

            }
        })
    }

    override fun navigateToARecipe(recipe: Recipe) {
        //Add functionality to navigate a user to a single clicked recipe
    }

}