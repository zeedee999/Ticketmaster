package com.zeenova.ticketmaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zeenova.ticketmaster.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.activeTicks.setOnClickListener {
            startActivity(Intent(this, ActiveTicks::class.java))
            overridePendingTransition(
                android.R.anim.fade_in,
                android.R.anim.fade_in
            )
            finishAfterTransition()
        }
        mainBinding.myAccount.setOnClickListener {
            startActivity(Intent(this, Settings::class.java))
            overridePendingTransition(
                android.R.anim.fade_in,
                android.R.anim.fade_in
            )
            finishAfterTransition()
        }

        mainBinding.btnAddTicket.setOnClickListener {
            startActivity(Intent(this, TicketAC::class.java))
            overridePendingTransition(
                android.R.anim.fade_in,
                android.R.anim.fade_in
            )
            finishAfterTransition()
        }

    }
}