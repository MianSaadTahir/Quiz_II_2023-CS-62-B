package com.example.quiz2

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quiz2.adapter.ComplaintAdapter
import com.example.quiz2.databinding.ActivityComplaintListBinding
import com.example.quiz2.model.Complaint
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class ComplaintListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComplaintListBinding
    private val db = FirebaseFirestore.getInstance()
    private var complaints = mutableListOf<Complaint>()
    private var cachedComplaints: List<Complaint> = emptyList()
    private lateinit var adapter: ComplaintAdapter
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComplaintListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Complaints"

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading complaints...")
        progressDialog.setCancelable(false)

        setupRecyclerView()

        binding.fabRegister.setOnClickListener {
            startActivityForResult(Intent(this, MainActivity::class.java), 101)
        }

        loadComplaints()
    }

    override fun onResume() {
        super.onResume()
        loadComplaints()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == RESULT_OK) {
            cachedComplaints = emptyList()
            loadComplaints()
        }
    }

    private fun setupRecyclerView() {
        adapter = ComplaintAdapter(complaints) { complaint ->
            val intent = Intent(this, ComplaintDetailActivity::class.java).apply {
                putExtra("title", complaint.title)
                putExtra("studentName", complaint.studentName)
                putExtra("rollNumber", complaint.rollNumber)
                putExtra("category", complaint.category)
                putExtra("priority", complaint.priority)
                putExtra("description", complaint.description)
                putExtra("status", complaint.status)
                putExtra("timestamp", complaint.timestamp?.toDate()?.time ?: 0L)
            }
            startActivity(intent)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun loadComplaints() {
        if (cachedComplaints.isNotEmpty()) {
            complaints.clear()
            complaints.addAll(cachedComplaints)
            adapter.notifyDataSetChanged()
            updateVisibility()
            return
        }

        progressDialog.show()
        db.collection("complaints")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { documents ->
                progressDialog.dismiss()
                val tempList = mutableListOf<Complaint>()
                for (document in documents) {
                    val complaint = document.toObject(Complaint::class.java)
                    complaint.id = document.id
                    tempList.add(complaint)
                }
                cachedComplaints = tempList
                complaints.clear()
                complaints.addAll(cachedComplaints)
                adapter.notifyDataSetChanged()
                updateVisibility()
            }
            .addOnFailureListener { e ->
                progressDialog.dismiss()
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateVisibility() {
        if (complaints.isEmpty()) {
            binding.emptyText.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        } else {
            binding.emptyText.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        }
    }
}