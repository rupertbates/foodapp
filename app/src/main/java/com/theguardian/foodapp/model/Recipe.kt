package com.theguardian.foodapp.model

import org.joda.time.DateTime
import java.io.Serializable

data class Recipe(val id: Int, val publishDate: DateTime, val name: String, val profileImageUrl: String, val bodyText: String, val heroImageUrl: String, var favCount: Int) : Serializable