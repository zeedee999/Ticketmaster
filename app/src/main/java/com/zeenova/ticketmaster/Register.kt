package com.zeenova.ticketmaster

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.zeenova.ticketmaster.databinding.ActivityRegisterBinding
import com.zeenova.washpapa.activities.Login
import www.sanju.motiontoast.MotionToast
import java.util.*

class Register : AppCompatActivity() {
    private lateinit var registerBinding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)


        registerBinding.backToWelcome.setOnClickListener {
            startActivity(Intent(this, Welcome::class.java))
            overridePendingTransition(
                android.R.anim.fade_in,
                android.R.anim.fade_in
            )
            finishAfterTransition()
        }
        registerBinding.login.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            overridePendingTransition(
                android.R.anim.fade_in,
                android.R.anim.fade_in
            )
            finishAfterTransition()
        }

        registerBinding.btnCreateAccount.setOnClickListener {
            registerBinding.btnCreateAccount.visibility = View.GONE
            registerBinding.constrntBtnCreateAccount.setBackgroundColor(resources.getColor(R.color.obo_color))
            registerBinding.progressCreateAccount.visibility = View.VISIBLE

            when {
                registerBinding.etEmail.text.toString().isEmpty() -> {

                    registerBinding.btnCreateAccount.visibility = View.VISIBLE
                    registerBinding.progressCreateAccount.visibility = View.GONE
                    registerBinding.constrntBtnCreateAccount.setBackgroundColor(resources.getColor(R.color.backgroundColor))
                    Toast.makeText(
                        this, "Please fill in your email address!", Toast.LENGTH_SHORT
                    ).show()
                }
                registerBinding.etPassword.text.toString().isEmpty() -> {
                    registerBinding.btnCreateAccount.visibility = View.VISIBLE

                    registerBinding.progressCreateAccount.visibility = View.GONE
                    registerBinding.constrntBtnCreateAccount.setBackgroundColor(resources.getColor(R.color.backgroundColor))
                    Toast.makeText(
                        this, "Please fill in your account password!", Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    signUpUser(
                        registerBinding.etEmail.text.toString(),
                        registerBinding.etPassword.text.toString()
                    )
                }
            }




        }


    }

    private fun signUpUser(email: String, password: String) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this, "Registration successful, Verify the email we just sent to you", Toast.LENGTH_LONG
                    ).show()
                    val users = Users(email, Firebase.auth.currentUser!!.uid, registerBinding.etPhoneNumber.text.toString())
                    Firebase.database.reference
                        .child(Constants.CLIENT_REF)
                        .child(Firebase.auth.currentUser!!.uid)
                        .setValue(users)

                    Firebase.auth.currentUser!!.sendEmailVerification()
                    val i = Intent(this, Login::class.java)
                    startActivity(i)
                    finish()
                } else {


                    registerBinding.progressCreateAccount.visibility = View.GONE
                    registerBinding.constrntBtnCreateAccount.setBackgroundColor(resources.getColor(R.color.backgroundColor))
                    registerBinding.btnCreateAccount.visibility = View.VISIBLE
                    MotionToast.createColorToast(this,task.exception!!.localizedMessage!!,
                        MotionToast.TOAST_ERROR,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(this, R.font.nunito_semibold))


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