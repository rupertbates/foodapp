package com.theguardian.foodapp

import android.app.Application
import net.danlew.android.joda.JodaTimeAndroid

class AppClass : Application() {

    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this)
    }

}