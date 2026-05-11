package com.example.quiz2.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz2.databinding.ItemComplaintBinding
import com.example.quiz2.model.Complaint

class ComplaintAdapter(
    private val complaints: List<Complaint>,
    private val onItemClick: (Complaint) -> Unit
) : RecyclerView.Adapter<ComplaintAdapter.ComplaintViewHolder>() {

    class ComplaintViewHolder(val binding: ItemComplaintBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComplaintViewHolder {
        val binding = ItemComplaintBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComplaintViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComplaintViewHolder, position: Int) {
        val complaint = complaints[position]
        holder.binding.apply {
            tvTitle.text = complaint.title
            tvStudentName.text = "Student: ${complaint.studentName}"
            tvRollNumber.text = "Roll No: ${complaint.rollNumber}"
            tvCategory.text = complaint.category
            tvPriority.text = complaint.priority

            val colorHex = when (complaint.priority) {
                "Low" -> "#4CAF50"
                "Medium" -> "#2196F3"
                "High" -> "#FF9800"
                "Urgent" -> "#F44336"
                else -> "#000000"
            }
            tvPriority.backgroundTintList = ColorStateList.valueOf(Color.parseColor(colorHex))
            tvPriority.setTextColor(Color.WHITE)

            root.setOnClickListener {
                onItemClick(complaint)
            }
        }
    }

    override fun getItemCount() = complaints.size
}