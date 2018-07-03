package com.theguardian.foodapp

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.theguardian.foodapp.model.Recipe
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.joda.time.DateTime
import java.util.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    // Initializing an empty ArrayList to be filled with animals
    val items: ArrayList<Recipe> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = FoodAdapter(fillItems(), this)
    }

    fun fillItems(): ArrayList<Recipe> {
        return arrayListOf(
                Recipe(DateTime(2018, 7, 2, 12, 15),
                        "Alicia Scott",
                        "https://mir-s3-cdn-cf.behance.net/project_modules/1400/4e044a57601403.59dc89fd47aa3.png",
                        this.resources.getString(R.string.lorem_ipsum),
                        "https://drop.ndtv.com/albums/COOKS/chicken-dinner/chickendinner_640x480.jpg",
                        1327
                ),
                Recipe(DateTime(2018, 7, 2, 14, 22),

                        "Selina Cooper",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQvSEzCrsztb_Pk2V-gGrADAlRhH24qotxDgcMjyEUT7fRxWqff",
                        this.resources.getString(R.string.lorem_ipsum),
                        "https://cdn.pixabay.com/photo/2015/04/08/13/13/food-712665_960_720.jpg",
                        8544
                ),

                Recipe(DateTime(2018, 7, 3, 9, 40),
                        "Harriet Jones",
                        "http://www.missingcloud.com/mc_images2014/MC_CircularProfile_Lilla_.png",
                        this.resources.getString(R.string.lorem_ipsum),
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQxIfsAFbtBRHUCDJDdrOw8Y0pWya-eZu9ZFdusOm9TxVAWzYvnNw",
                        4567
                )
        )


    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
