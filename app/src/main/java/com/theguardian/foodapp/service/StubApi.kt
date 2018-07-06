package com.theguardian.foodapp.service

import com.theguardian.foodapp.model.Recipe
import org.joda.time.DateTime

object StubApi {
    val loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
            "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in " +
            "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in " +
            "culpa qui officia deserunt mollit anim id est laborum."

    val recipes = arrayListOf(
            Recipe(1, DateTime(2018, 7, 2, 12, 15),
                    "Alicia Scott",
                    "https://mir-s3-cdn-cf.behance.net/project_modules/1400/4e044a57601403.59dc89fd47aa3.png",
                    loremIpsum,
                    "https://drop.ndtv.com/albums/COOKS/chicken-dinner/chickendinner_640x480.jpg",
                    1327
            ),
            Recipe(2, DateTime(2018, 7, 2, 14, 22),

                    "Selina Cooper",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQvSEzCrsztb_Pk2V-gGrADAlRhH24qotxDgcMjyEUT7fRxWqff",
                    loremIpsum,
                    "https://cdn.pixabay.com/photo/2015/04/08/13/13/food-712665_960_720.jpg",
                    8544
            ),

            Recipe(3, DateTime(2018, 7, 3, 9, 40),
                    "Harriet Jones",
                    "http://www.missingcloud.com/mc_images2014/MC_CircularProfile_Lilla_.png",
                    loremIpsum,
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQxIfsAFbtBRHUCDJDdrOw8Y0pWya-eZu9ZFdusOm9TxVAWzYvnNw",
                    4567
            )
    )

    // Represents the users favourites - store this somewhere locally and also sync to server
    val favourites: MutableList<Int> = mutableListOf()
}