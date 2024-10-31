package com.zeenova.ticketmaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zeenova.ticketmaster.databinding.ActivitySettingsBinding

class Settings : AppCompatActivity() {

    private lateinit var settingsBinding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        settingsBinding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(settingsBinding.root)
    }
}