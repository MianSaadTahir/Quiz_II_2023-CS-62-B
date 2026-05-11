package com.example.quiz2

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quiz2.databinding.ActivityComplaintDetailBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ComplaintDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComplaintDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComplaintDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Complaint Detail"

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val title = intent.getStringExtra("title")
        val studentName = intent.getStringExtra("studentName")
        val rollNumber = intent.getStringExtra("rollNumber")
        val category = intent.getStringExtra("category")
        val priority = intent.getStringExtra("priority")
        val description = intent.getStringExtra("description")
        val status = intent.getStringExtra("status")
        val timestampMillis = intent.getLongExtra("timestamp", 0L)

        binding.tvDetailTitle.text = title
        binding.tvDetailStudentName.text = studentName
        binding.tvDetailRollNumber.text = rollNumber
        binding.tvDetailCategory.text = category
        binding.tvDetailPriority.text = priority
        binding.tvDetailDescription.text = description
        binding.tvDetailStatus.text = status

        if (timestampMillis != 0L) {
            val date = Date(timestampMillis)
            val sdf = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
            binding.tvDetailDate.text = sdf.format(date)
        } else {
            binding.tvDetailDate.text = "N/A"
        }

        val colorHex = when (priority) {
            "Low" -> "#4CAF50"
            "Medium" -> "#2196F3"
            "High" -> "#FF9800"
            "Urgent" -> "#F44336"
            else -> "#000000"
        }
        binding.tvDetailPriority.backgroundTintList = android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor(colorHex))
        binding.tvDetailPriority.setTextColor(android.graphics.Color.WHITE)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}