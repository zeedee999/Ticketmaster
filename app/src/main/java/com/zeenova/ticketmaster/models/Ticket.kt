package com.zeenova.ticketmaster.models

data class Ticket(
    val imageUrl: String,
    val showName: String,
    val date: String,
    val month: String,
    val year: String,
    val dayOfWeek: String,
    val timeOfEvent: String,
    val showLocation: String,
    val showOrderID: String,
    val section: String,
    val row: String,
    val seat: Int,
    val numberOfTickets: Int,
    val isItGA: Boolean,
    val timeStamp: MutableMap<String, String>
    )

