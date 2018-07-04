package com.theguardian.foodapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.theguardian.foodapp.model.Recipe
import kotlinx.android.synthetic.main.card_layout.view.*
import org.ocpsoft.prettytime.PrettyTime
import java.util.*


class FoodAdapter(val items: ArrayList<Recipe>, val activity: Activity) : RecyclerView.Adapter<ViewHolder>() {
    val pretty = PrettyTime()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(activity).inflate(R.layout.card_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.name.text = item.name
        holder.bodyText.text = item.bodyText
        holder.dateText.text = pretty.format(item.publishDate.toDate())
        holder.favCount.text = item.favCount.toString()
        Glide.with(activity)
                .load(item.profileImageUrl)
                .into(holder.profileImage)
        Glide.with(activity)
                .load(item.heroImageUrl)
                .into(holder.heroImage)
        holder.root.setOnClickListener { v -> onItemClick(holder, item) }

    }

    fun onItemClick(holder: ViewHolder, item: Recipe) {
        val p1 = Pair.create(holder.heroImage as View, ViewCompat.getTransitionName(holder.heroImage))
        val p2 = Pair.create(holder.bodyText as View, ViewCompat.getTransitionName(holder.bodyText))
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, p1, p2)

        val bundle = Bundle()
        bundle.putSerializable("item", item)
        val i = Intent(activity, DetailActivity::class.java)
        i.putExtras(bundle)
        activity.startActivity(i, options.toBundle())
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val root = view
    val name = view.name
    val profileImage = view.profileImage
    val dateText = view.date
    val bodyText = view.bodyText
    val heroImage = view.heroImage
    val favCount = view.favCount
}