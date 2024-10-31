package com.zeenova.ticketmaster.utilities

import android.app.Application
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class Psl : Application() {

    override fun onCreate() {
        super.onCreate()

        Firebase.database.setPersistenceEnabled(true)

        val picassoBuilder =  Picasso.Builder(this)
        var lad = OkHttp3Downloader(this, Integer.MAX_VALUE.toLong())
        picassoBuilder.downloader(lad)
        val builtVerse = picassoBuilder.build()
        builtVerse.setIndicatorsEnabled(false)
        builtVerse.isLoggingEnabled = true
        Picasso.setSingletonInstance(builtVerse)

    }

}