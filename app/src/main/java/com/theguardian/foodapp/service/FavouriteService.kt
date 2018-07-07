package com.theguardian.foodapp.service

import com.theguardian.foodapp.model.Recipe
import io.reactivex.Observable
import java.security.InvalidParameterException

class FavouriteService(private val recipeService: RecipeService) {

    companion object {
        // Represents the users favourites - store this somewhere locally and also sync to server
        private val favourites: MutableList<Int> = mutableListOf()
    }

    fun setFavourite(id: Int, favourite: Boolean): Observable<Recipe> {
        if (favourite && favourites.contains(id) || !favourite && !favourites.contains(id))
            return Observable.error(InvalidParameterException("Recipe with Id $id ${if (favourite) "is already" else "is not"} a favourite"))

        return if (favourite) {
            favourites.add(id)
            recipeService.incrementFavouriteCount(id)
        } else {
            favourites.remove(id)
            recipeService.decrementFavouriteCount(id)
        }

    }

    fun isRecipeAFavourite(id: Int): Boolean {
        return favourites.contains(id)
    }
}