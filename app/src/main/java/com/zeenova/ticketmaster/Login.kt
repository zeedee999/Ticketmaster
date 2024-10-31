package com.zeenova.washpapa.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.zeenova.ticketmaster.MainActivity
import com.zeenova.ticketmaster.R
import com.zeenova.ticketmaster.Welcome
import com.zeenova.ticketmaster.databinding.ActivityLoginBinding
import www.sanju.motiontoast.MotionToast

class Login : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        loginBinding.backToWelcome.setOnClickListener {
            startActivity(Intent(this, Welcome::class.java))
            overridePendingTransition(
                android.R.anim.fade_in,
                android.R.anim.fade_in
            )
            finishAfterTransition()
        }

        loginBinding.btnPasswordReset.setOnClickListener {
            startActivity(Intent(this, ResetPassword::class.java))
            overridePendingTransition(
                android.R.anim.fade_in,
                android.R.anim.fade_in
            )
            finishAfterTransition()
        }


        loginBinding.btnLoginAccount.setOnClickListener {

            loginBinding.btnLoginAccount.visibility = View.INVISIBLE
            loginBinding.constrntBtnCreateAccount.setBackgroundColor(resources.getColor(R.color.ticketmaster_color))
            loginBinding.progressLoginAccount.visibility = View.VISIBLE

            when {
                loginBinding.etEmail.text.toString().isEmpty() -> {

                    loginBinding.btnLoginAccount.visibility = View.VISIBLE
                    loginBinding.progressLoginAccount.visibility = View.GONE
                    loginBinding.constrntBtnCreateAccount.setBackgroundColor(resources.getColor(R.color.white))

                    Toast.makeText(this, "Please fill in your email address", Toast.LENGTH_SHORT)
                        .show()
                }
                loginBinding.etPassword.text.toString().isEmpty() -> {

                    loginBinding.btnLoginAccount.visibility = View.VISIBLE
                    loginBinding.progressLoginAccount.visibility = View.GONE
                    loginBinding.constrntBtnCreateAccount.setBackgroundColor(resources.getColor(R.color.white))
                    Toast.makeText(this, "Please fill in your password", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    signInUser(
                        loginBinding.etEmail.text.toString(),
                        loginBinding.etPassword.text.toString()
                    )
                }


            }
        }
    }

    private fun signInUser(email: String, password: String) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    updateUser(Firebase.auth.currentUser!!)
                    loginBinding.btnLoginAccount.visibility = View.VISIBLE
                    loginBinding.progressLoginAccount.visibility = View.GONE
                    loginBinding.constrntBtnCreateAccount.setBackgroundColor(resources.getColor(R.color.white))
                } else {
                    MotionToast.createColorToast(
                        this, task.exception!!.localizedMessage!!,
                        MotionToast.TOAST_ERROR,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(this, R.font.nunito_semibold)
                    )

                    loginBinding.btnLoginAccount.visibility = View.VISIBLE
                    loginBinding.progressLoginAccount.visibility = View.GONE
                    loginBinding.constrntBtnCreateAccount.setBackgroundColor(resources.getColor(R.color.white))

                }
            }
    }


    private fun updateUser(user: FirebaseUser?) {
        if (user != null) {
            if (user.isEmailVerified) {

                Toast.makeText(
                    this,
                    "Welcome " + Firebase.auth.currentUser!!.email!!,
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(this, MainActivity::class.java))
                overridePendingTransition(
                    android.R.anim.fade_in,
                    android.R.anim.fade_in
                )


                loginBinding.btnLoginAccount.visibility = View.VISIBLE
                loginBinding.information.visibility = View.INVISIBLE
                loginBinding.spamText.visibility = View.INVISIBLE
                loginBinding.progressLoginAccount.visibility = View.GONE
                loginBinding.constrntBtnCreateAccount.setBackgroundColor(resources.getColor(R.color.white))
                finishAfterTransition()
            } else {

                loginBinding.information.visibility = View.VISIBLE

                loginBinding.information.setOnClickListener {
                    if (loginBinding.spamText.visibility == View.INVISIBLE) {
                        loginBinding.spamText.visibility = View.VISIBLE
                    } else {
                        loginBinding.spamText.visibility = View.INVISIBLE
                    }
                }
                loginBinding.btnLoginAccount.visibility = View.VISIBLE
                loginBinding.progressLoginAccount.visibility = View.GONE
                loginBinding.constrntBtnCreateAccount.setBackgroundColor(resources.getColor(R.color.white))

                Toast.makeText(
                    this,
                    "Please verify your email using the verification link we sent to you.",
                    Toast.LENGTH_LONG
                ).show()
                Firebase.auth.currentUser!!.sendEmailVerification()
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        startActivity(Intent(this, Welcome::class.java))
        overridePendingTransition(
            android.R.anim.fade_in,
            android.R.anim.fade_in
        )
        finishAfterTransition()

    }

}