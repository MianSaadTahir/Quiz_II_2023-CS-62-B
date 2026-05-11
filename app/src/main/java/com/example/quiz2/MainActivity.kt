package com.example.quiz2

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quiz2.databinding.ActivityMainBinding
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val db = FirebaseFirestore.getInstance()
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Saving complaint...")
        progressDialog.setCancelable(false)

        setupSpinners()

        binding.btnSubmit.setOnClickListener {
            submitComplaint()
        }
    }

    private fun setupSpinners() {
        val categories = arrayOf(
            "Select Category", "IT", "Library", "Transport", "Hostel",
            "Accounts", "Examination", "Cafeteria", "Administration"
        )
        val priorities = arrayOf("Select Priority", "Low", "Medium", "High", "Urgent")

        val categoryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.categorySpinner.adapter = categoryAdapter

        val priorityAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, priorities)
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.prioritySpinner.adapter = priorityAdapter
    }

    private fun submitComplaint() {
        val name = binding.etStudentName.text.toString().trim()
        val rollNo = binding.etRollNumber.text.toString().trim()
        val title = binding.etComplaintTitle.text.toString().trim()
        val category = binding.categorySpinner.selectedItem.toString()
        val priority = binding.prioritySpinner.selectedItem.toString()
        val description = binding.etDescription.text.toString().trim()

        if (name.isEmpty() || rollNo.isEmpty() || title.isEmpty() || description.isEmpty() ||
            binding.categorySpinner.selectedItemPosition == 0 ||
            binding.prioritySpinner.selectedItemPosition == 0
        ) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        progressDialog.show()
        binding.btnSubmit.isEnabled = false

        val complaint = hashMapOf(
            "studentName" to name,
            "rollNumber" to rollNo,
            "title" to title,
            "category" to category,
            "priority" to priority,
            "description" to description,
            "status" to "Pending",
            "timestamp" to FieldValue.serverTimestamp()
        )

        db.collection("complaints")
            .add(complaint)
            .addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(this, "Complaint submitted successfully", Toast.LENGTH_SHORT).show()
                clearFields()
                setResult(RESULT_OK)
                finish()
            }
            .addOnFailureListener { e ->
                progressDialog.dismiss()
                binding.btnSubmit.isEnabled = true
                Toast.makeText(this, "Failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun clearFields() {
        binding.etStudentName.setText("")
        binding.etRollNumber.setText("")
        binding.etComplaintTitle.setText("")
        binding.etDescription.setText("")
        binding.categorySpinner.setSelection(0)
        binding.prioritySpinner.setSelection(0)
    }
}