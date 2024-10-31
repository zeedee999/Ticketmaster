package com.zeenova.ticketmaster

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zeenova.ticketmaster.databinding.ActivityWelcomeBinding
import com.zeenova.washpapa.activities.Login

class Welcome : AppCompatActivity() {
    private lateinit var welcomeBinding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        welcomeBinding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(welcomeBinding.root)

        welcomeBinding.btnCreateAccount.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
            overridePendingTransition(
                android.R.anim.fade_in,
                android.R.anim.fade_in
            )
            finishAfterTransition()
        }
        welcomeBinding.btnLogin.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            overridePendingTransition(
                android.R.anim.fade_in, android.R.anim.fade_in
            )
            finishAfterTransition()
        }


    }



    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        val builder = android.app.AlertDialog.Builder(this)
        builder.apply {
            setTitle("Exit App")
            setMessage("Are you sure you want to leave the application?")
            setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            setPositiveButton("Exit") { _, _ ->
                finish()
            }
            setCancelable(false)

        }
        val dialog = builder.create()
        dialog.setOnShowListener {
            dialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE)
                .setTextColor(resources.getColor(R.color.purple_700))
            dialog.getButton(android.app.AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(resources.getColor(R.color.black))

        }
        dialog.show()

    }
}