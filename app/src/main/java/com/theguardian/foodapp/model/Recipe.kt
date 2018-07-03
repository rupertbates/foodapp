package com.theguardian.foodapp.model

import org.joda.time.DateTime
import java.io.Serializable
import java.util.*

data class Recipe(val publishDate: DateTime, val name: String, val profileImageUrl: String, val bodyText: String, val heroImageUrl: String, val favCount: Int) : Serializable