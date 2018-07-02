package com.theguardian.foodapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.theguardian.foodapp.model.Recipe
import kotlinx.android.synthetic.main.card_layout.view.*
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat

//import kotlinx.android.synthetic.main.animal_list_item.view.*

class FoodAdapter(val items : ArrayList<Recipe>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    val pretty = PrettyTime()

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_layout, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.name.text = item.name
        holder.bodyText.text = item.bodyText
        holder.dateText.text = pretty.format(item.publishDate)
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val name = view.name
    val profileImage = view.profileImage
    val dateText = view.date
    val bodyText = view.bodyText
    val heroImage = view.heroImage
}