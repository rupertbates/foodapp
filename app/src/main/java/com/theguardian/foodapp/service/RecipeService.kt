package com.theguardian.foodapp.service

import com.theguardian.foodapp.model.NotFoundException
import com.theguardian.foodapp.model.Recipe
import com.theguardian.foodapp.service.StubApi.recipes
import io.reactivex.Observable

class RecipeService {

    fun getRecipes(): Observable<List<Recipe>> {
        return Observable.just(recipes)
    }

    fun getRecipe(id: Int): Observable<Recipe> {
        val recipe = recipes.find { recipe -> recipe.id == id }
        if (recipe != null) {
            return Observable.just(recipe)
        }
        return Observable.error(NotFoundException("Couldn't find a recipe with Id $id"))
    }

    fun incrementFavouriteCount(id: Int): Observable<Recipe> {
        return getRecipe(id).map { r ->
            r.favCount = r.favCount.inc() //Using mutable state here to mimic an API
            return@map r
        }
    }

    fun decrementFavouriteCount(id: Int): Observable<Recipe> {
        return getRecipe(id).map { r ->
            r.favCount = r.favCount.dec() //Using mutable state here to mimic an API
            return@map r
        }
    }
}