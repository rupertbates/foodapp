package com.theguardian.foodapp

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bumptech.glide.Glide
import com.theguardian.foodapp.model.Recipe

import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import org.ocpsoft.prettytime.PrettyTime

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        bindViews(this.intent.extras["item"])

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    fun bindViews(bundle: Any) {
        if(bundle is Recipe) {
            Glide.with(this)
                    .load(bundle.profileImageUrl)
                    .into(profileImage)
//            Glide.with(this)
//                    .load(bundle.heroImageUrl)
//                    .into(mainImage)
            name.text = bundle.name
            bodyText.text = bundle.bodyText
            date.text = PrettyTime().format(bundle.publishDate.toDate())
            favCount.text = bundle.favCount.toString()
        }
    }

}
