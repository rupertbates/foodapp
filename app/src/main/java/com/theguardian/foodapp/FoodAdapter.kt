package com.theguardian.foodapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.theguardian.foodapp.model.Recipe
import kotlinx.android.synthetic.main.card_layout.view.*
import org.ocpsoft.prettytime.PrettyTime
import java.util.*

class FoodAdapter(val items: ArrayList<Recipe>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    val pretty = PrettyTime()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.name.text = item.name
        holder.bodyText.text = item.bodyText
        holder.dateText.text = pretty.format(item.publishDate.toDate())
        holder.favCount.text = item.favCount.toString()
        Glide.with(context)
                .load(item.profileImageUrl)
                .into(holder.profileImage)
        Glide.with(context)
                .load(item.heroImageUrl)
                .into(holder.heroImage)
        holder.root.setOnClickListener { v ->
            val bundle = Bundle()
            bundle.putSerializable("item", item)
            val i = Intent(context, DetailActivity::class.java)
            i.putExtras(bundle)
            context.startActivity(i)
        }
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