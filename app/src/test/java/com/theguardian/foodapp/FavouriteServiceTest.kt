package com.theguardian.foodapp

import com.theguardian.foodapp.service.FavouriteService
import com.theguardian.foodapp.service.RecipeService
import org.junit.Assert.*
import org.junit.Test


class FavouriteServiceTest {

    @Test
    fun setFavourite_should_add_or_remove_from_favourites() {
        val service = FavouriteService(RecipeService())
        val id = 1

        service.setFavourite(id, true).blockingFirst()
        assertTrue(service.isRecipeAFavourite(id))

        service.setFavourite(id, false).blockingFirst()
        assertFalse(service.isRecipeAFavourite(id))
    }

    @Test
    fun setFavorite_should_increment_and_decrement_favCount() {
        val recipeService = RecipeService()
        val service = FavouriteService(recipeService)
        val id = 1

        val favCount = recipeService.getRecipe(id).blockingFirst().favCount

        val incremented = service.setFavourite(id, true).blockingFirst()
        assertEquals(favCount + 1, incremented.favCount)

        val decremented = service.setFavourite(id, false).blockingFirst()
        assertEquals(favCount, decremented.favCount)
    }
}
