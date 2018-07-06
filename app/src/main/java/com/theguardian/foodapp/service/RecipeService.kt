package com.theguardian.foodapp.service

import com.theguardian.foodapp.model.Recipe
import com.theguardian.foodapp.service.StubApi.favourites
import com.theguardian.foodapp.service.StubApi.recipes
import io.reactivex.Observable

class RecipeService {


    fun setFavourite(id: Int, favourite: Boolean): Observable<Recipe?> {
        val recipe = findById(id)
        if (recipe != null) {
            if (favourite && !favourites.contains(id)) {
                favourites.add(id)
                recipe.favCount = recipe.favCount.inc()
            } else {
                favourites.remove(id)
                recipe.favCount = recipe.favCount.dec()
            }
        }
        return Observable.just(recipe)
    }

    private fun findById(id: Int): Recipe? {
        return recipes.find { recipe -> recipe.id == id }
    }

    fun isRecipeAFavourite(id: Int): Boolean {
        return favourites.contains(id)
    }

    fun getRecipes(): Observable<List<Recipe>> {
        return Observable.just(recipes)
    }

    fun getRecipe(id: Int): Observable<Recipe?> {
        return Observable.just(recipes.find { recipe -> recipe.id == id })
    }
}