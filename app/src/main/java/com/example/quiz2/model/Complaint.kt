package com.example.quiz2.model

import com.google.firebase.Timestamp

data class Complaint(
    var id: String = "",
    val studentName: String = "",
    val rollNumber: String = "",
    val title: String = "",
    val category: String = "",
    val priority: String = "",
    val description: String = "",
    val status: String = "Pending",
    val timestamp: Timestamp? = null
)