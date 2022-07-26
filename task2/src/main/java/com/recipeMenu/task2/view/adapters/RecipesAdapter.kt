package com.recipeMenu.task2.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.recipeMenu.task2.R
import com.recipeMenu.task2.model.responses.Recipe
import kotlinx.android.synthetic.main.recipe_item.view.*

class RecipesAdapter(
    private var recipeItems: List<Recipe>,
) : RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {
    private lateinit var context: Context


    interface ItemClickListener {
        fun navigateToARecipe(recipe: Recipe)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.populateRecipes(recipeItems[position])

    }

    override fun getItemCount(): Int {
        return recipeItems.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun populateRecipes(recipe: Recipe) {
            Glide.with(context)
                .load(recipe.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemView.roundedRecipeImageView)
            itemView.tvRecipeTittle.text = recipe.name
            itemView.tvRecipeHeadline.text = recipe.headline

        }


    }
}