package com.zeenova.ticketmaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ServerValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.zeenova.ticketmaster.databinding.ActivityTicketBinding
import com.zeenova.ticketmaster.models.Ticket
import java.text.SimpleDateFormat
import java.util.*

class TicketAC : AppCompatActivity() {

    private lateinit var ticketBinding: ActivityTicketBinding

    var ticketID: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ticketBinding = ActivityTicketBinding.inflate(layoutInflater)
        setContentView(ticketBinding.root)

        fixVisibility(ticketBinding.radBtnGeneralTickets.isChecked)
        when(ticketBinding.radioGrp.checkedRadioButtonId){
            R.id.radBtnRegularTickets -> {
                fixVisibility(ticketBinding.radBtnGeneralTickets.isChecked)
            }
            R.id.radBtnGeneralTickets -> {
                fixVisibility(ticketBinding.radBtnGeneralTickets.isChecked)
            }

        }

        ticketBinding.backToWelcome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(
                android.R.anim.fade_in,
                android.R.anim.fade_in
            )
            finishAfterTransition()
        }
        ticketBinding.btnAddTicket.setOnClickListener {
            if (!ticketBinding.radBtnGeneralTickets.isChecked){

                when {
                    ticketBinding.etImageUrl.text.toString().isEmpty() -> {

                        Toast.makeText(
                            this, "Fill in all fields!", Toast.LENGTH_SHORT
                        ).show()
                    }
                    ticketBinding.etShowName.text.toString().isEmpty() -> {

                        Toast.makeText(
                            this, "Fill in all fields!", Toast.LENGTH_SHORT
                        ).show()
                    }
                    ticketBinding.date.text.toString().isEmpty() -> {

                        Toast.makeText(
                            this, "Fill in all fields!", Toast.LENGTH_SHORT
                        ).show()
                    }
                    ticketBinding.month.text.toString().isEmpty() -> {
                        Toast.makeText(
                            this, "Fill in all fields!", Toast.LENGTH_SHORT
                        ).show()
                    }
                    ticketBinding.year.text.toString().isEmpty() -> {
                        Toast.makeText(
                            this, "Fill in all fields!", Toast.LENGTH_SHORT
                        ).show()
                    }
                    ticketBinding.dayOfWeek.text.toString().isEmpty() -> {
                        Toast.makeText(
                            this, "Fill in all fields!", Toast.LENGTH_SHORT
                        ).show()
                    }
                    ticketBinding.timeInText.text.toString().isEmpty() -> {
                        Toast.makeText(
                            this, "Fill in all fields!", Toast.LENGTH_SHORT
                        ).show()
                    }
                    ticketBinding.location.text.toString().isEmpty() -> {
                        Toast.makeText(
                            this, "Fill in all fields!", Toast.LENGTH_SHORT
                        ).show()
                    }

                    ticketBinding.showOrder.text.toString().isEmpty() -> {
                        Toast.makeText(
                            this, "Fill in all fields!", Toast.LENGTH_SHORT
                        ).show()
                    }
                    ticketBinding.section.text.toString().isEmpty() -> {
                        Toast.makeText(
                            this, "Fill in all fields!", Toast.LENGTH_SHORT
                        ).show()
                    }
                    ticketBinding.row.text.toString().isEmpty() -> {
                        Toast.makeText(
                            this, "Fill in all fields!", Toast.LENGTH_SHORT
                        ).show()
                    }
                    ticketBinding.Seat.text.toString().isEmpty() -> {
                        Toast.makeText(
                            this, "Fill in all fields!", Toast.LENGTH_SHORT
                        ).show()
                    }
                    ticketBinding.ticketsNumber.text.toString().isEmpty() -> {
                        Toast.makeText(
                            this, "Fill in all fields!", Toast.LENGTH_SHORT
                        ).show()
                    }
                    else -> {


                    }
                }
            }
            else{
                when {
                    ticketBinding.etImageUrl.text.toString().isEmpty() -> {

                        Toast.makeText(
                            this, "Fill in all fields!", Toast.LENGTH_SHORT
                        ).show()
                    }
                    ticketBinding.etShowName.text.toString().isEmpty() -> {

                        Toast.makeText(
                            this, "Fill in all fields!", Toast.LENGTH_SHORT
                        ).show()
                    }
                    ticketBinding.date.text.toString().isEmpty() -> {

                        Toast.makeText(
                            this, "Fill in all fields!", Toast.LENGTH_SHORT
                        ).show()
                    }
                    ticketBinding.month.text.toString().isEmpty() -> {
                        Toast.makeText(
                            this, "Fill in all fields!", Toast.LENGTH_SHORT
                        ).show()
                    }
                    ticketBinding.year.text.toString().isEmpty() -> {
                        Toast.makeText(
                            this, "Fill in all fields!", Toast.LENGTH_SHORT
                        ).show()
                    }
                    ticketBinding.dayOfWeek.text.toString().isEmpty() -> {
                        Toast.makeText(
                            this, "Fill in all fields!", Toast.LENGTH_SHORT
                        ).show()
                    }
                    ticketBinding.timeInText.text.toString().isEmpty() -> {
                        Toast.makeText(
                            this, "Fill in all fields!", Toast.LENGTH_SHORT
                        ).show()
                    }
                    ticketBinding.location.text.toString().isEmpty() -> {
                        Toast.makeText(
                            this, "Fill in all fields!", Toast.LENGTH_SHORT
                        ).show()
                    }

                    ticketBinding.showOrder.text.toString().isEmpty() -> {
                        Toast.makeText(
                            this, "Fill in all fields!", Toast.LENGTH_SHORT
                        ).show()
                    }
                    ticketBinding.section2.text.toString().isEmpty() -> {
                        Toast.makeText(
                            this, "Fill in all fields!", Toast.LENGTH_SHORT
                        ).show()
                    }
                    ticketBinding.ticketsNumber.text.toString().isEmpty() -> {
                        Toast.makeText(
                            this, "Fill in all fields!", Toast.LENGTH_SHORT
                        ).show()
                    }
                    else -> {
                        addGATicketToDataBase2(){

                        }
                    }
                }
            }


        }
    }

    private fun addGATicketToDataBase2(function: () -> Unit) {



    }

    private fun fixVisibility(gA:Boolean){
        when{
            gA -> {
                ticketBinding.genAdmissionTixLayout.visibility = View.VISIBLE
                ticketBinding.regularTixLayout.visibility = View.GONE

            }
            else -> {
                ticketBinding.genAdmissionTixLayout.visibility = View.GONE
                ticketBinding.regularTixLayout.visibility = View.VISIBLE

            }
        }
    }
    private fun addTicketToDatabase(
        imgUrl: String,
        showName: String,
        date: Int,
        month: Int,
        year: Int,
        dayOfWeek: String,
        timeOfEvent: String,
        showLocation: String,
        showOrderID: String,
        section: String,
        row: String,
        seat: Int,
        numberOfSeats: Int,
        isItGA: Boolean
    ) {

        val nValue = randomCode()
        ticketID = nValue



        when (numberOfSeats){
            1 -> {

            }
            2 -> {

            }
            3 -> {

            }
            4 -> {

            }
            5 -> {

            }
            6 -> {

            }

        }
        Firebase.database.reference
            .child("Tickets")
            .child(ticketID!!)
            .setValue("ticket")
            .addOnSuccessListener {
                Toast.makeText(this, "Ticket Added", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, ActiveTicks::class.java))
                overridePendingTransition(
                    android.R.anim.fade_in,
                    android.R.anim.fade_in
                )
                finishAfterTransition()
            }

    }

    private fun randomCode(): String {
        var uMail = Firebase.auth.currentUser!!.email!!.take(3)
        var date = Date()
        var newDate = Date(date.time + 604800000L * 2 + 24 * 60 * 60)
        var dt = SimpleDateFormat("dd-MM-yyyy")
        var stringdate = dt.format(date)

        return "WP-$uMail-$stringdate-${randomID()}"
    }

    private fun randomID(): String = List(10) {
        (('a'..'z') + ('A'..'Z') + ('0'..'9')).random()
    }.joinToString("")

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(
            android.R.anim.fade_in,
            android.R.anim.fade_in
        )
        finishAfterTransition()
    }
}



