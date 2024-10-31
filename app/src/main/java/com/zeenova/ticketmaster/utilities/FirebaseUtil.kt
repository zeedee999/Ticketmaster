package com.zeenova.ticketmaster.utilities

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.zeenova.ticketmaster.R
import com.zeenova.washpapa.utilities.Constants

object FirebaseUtil {
    fun updateUser(view: View?, updateData: Map<String, Any>) {
        FirebaseDatabase.getInstance().reference
            .child(Constants.CLIENT_REF)
            .child(Firebase.auth.currentUser!!.uid)
            .updateChildren(updateData)
            .addOnFailureListener { e ->
                Snackbar.make(view!!, e.localizedMessage!!, Snackbar.LENGTH_LONG).show()
            }
            .addOnSuccessListener {
                Snackbar.make(view!!, "Your profile has been successfully updated", Snackbar.LENGTH_LONG)
                    .setTextColor(view.resources.getColor(R.color.white))
                    .setBackgroundTint(view.resources.getColor(R.color.purple_700))
                    .show()
            }
    }
}