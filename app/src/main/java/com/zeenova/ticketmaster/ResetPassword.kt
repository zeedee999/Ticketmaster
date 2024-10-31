package com.zeenova.washpapa.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.zeenova.washpapa.R
import com.zeenova.washpapa.databinding.ActivityResetPasswordBinding
import www.sanju.motiontoast.MotionToast

class ResetPassword : AppCompatActivity() {
    private lateinit var fpBinding: ActivityResetPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fpBinding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(fpBinding.root)

        fpBinding.backToWelcome.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            overridePendingTransition(
                android.R.anim.fade_in, android.R.anim.fade_in
            )
            finishAfterTransition()
        }

        fpBinding.btnReset.setOnClickListener {
            fpBinding.btnReset.visibility = View.GONE
            fpBinding.constrntBtnResetAccount.setBackgroundColor(resources.getColor(R.color.purple_700))
            fpBinding.progressReset.visibility = View.VISIBLE
            if (fpBinding.etEmail.text.toString().isEmpty()) {
                fpBinding.progressReset.visibility = View.GONE
                fpBinding.constrntBtnResetAccount.setBackgroundColor(resources.getColor(R.color.backgroundColor))
                fpBinding.btnReset.visibility = View.VISIBLE
                MotionToast.createColorToast(
                    this, "Please fill in your email address!",
                    MotionToast.TOAST_WARNING,
                    MotionToast.GRAVITY_TOP,
                    MotionToast.SHORT_DURATION,
                    ResourcesCompat.getFont(this, R.font.nunito_regular)
                )
            } else {

                val email = fpBinding.etEmail.text.toString()
                Firebase.auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(
                                this,
                                "Your email reset request was successful, Check the reset link we just sent to $email",
                                Toast.LENGTH_SHORT
                            ).show()
                            val i = Intent(this, Login::class.java)
                            startActivity(i)
                            overridePendingTransition(
                                android.R.anim.fade_in, android.R.anim.fade_in
                            )
                            finish()
                        } else {

                            fpBinding.progressReset.visibility = View.GONE
                            fpBinding.constrntBtnResetAccount.setBackgroundColor(
                                resources.getColor(
                                    R.color.backgroundColor
                                )
                            )
                            fpBinding.btnReset.visibility = View.VISIBLE

                            MotionToast.createColorToast(
                                this, it.exception!!.localizedMessage!!,
                                MotionToast.TOAST_ERROR,
                                MotionToast.GRAVITY_BOTTOM,
                                MotionToast.LONG_DURATION,
                                ResourcesCompat.getFont(this, R.font.nunito_semibold)
                            )

                        }
                    }
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        startActivity(Intent(this, Login::class.java))
        overridePendingTransition(
            android.R.anim.fade_in, android.R.anim.fade_in
        )
        finishAfterTransition()
    }
}