package com.theguardian.foodapp.view

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.theguardian.foodapp.R
import com.theguardian.foodapp.model.Recipe
import com.theguardian.foodapp.service.RecipeService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.content_detail.*
import org.ocpsoft.prettytime.PrettyTime

class DetailActivity : AppCompatActivity() {

    private val recipeService = RecipeService() //Could inject this
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = this.intent.getIntExtra("recipeId", -1)

        disposables.add(recipeService.getRecipe(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(::bindViews, ::onError))
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    private fun bindViews(recipe: Recipe?) {
        if (recipe != null) {
            supportPostponeEnterTransition()
            Glide.with(this)
                    .load(recipe.profileImageUrl)
                    .into(profileImage)
            Glide.with(this)
                    .load(recipe.heroImageUrl)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, isFirstResource: Boolean): Boolean {
                            supportStartPostponedEnterTransition()
                            return false
                        }

                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            mainImage.setImageDrawable(resource)
                            supportStartPostponedEnterTransition()
                            return true
                        }
                    }).submit()
            name.text = recipe.name
            bodyText.text = recipe.bodyText
            date.text = PrettyTime().format(recipe.publishDate.toDate())
            favCount.text = recipe.favCount.toString()
            val isFavourite = recipeService.isRecipeAFavourite(recipe.id)
            setFavouriteIcon(isFavourite)
            fab.setOnClickListener {
                disposables.add(recipeService.setFavourite(recipe.id, !isFavourite)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(::bindViews, ::onError))
            }
        }
    }

    private fun setFavouriteIcon(isFavourite: Boolean) {
        if (isFavourite) {
            fab.setImageResource(R.drawable.ic_baseline_favorite_24px)
            fab.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        } else {
            fab.setImageResource(R.drawable.ic_baseline_favorite_border_24px)
            fab.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.textLight))
        }
    }

    private fun onError(throwable: Throwable) {
        //Show some error UI
    }

}
