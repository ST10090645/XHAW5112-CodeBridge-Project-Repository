package com.example.empoweringthenation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GardenMaintence : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_garden_maintence)

        // Navigate to Calculate Fees
        findViewById<Button>(R.id.btnCalculateFees).setOnClickListener {
            startActivity(Intent(this, FeeCalculation::class.java))
        }

        // Back to Courses List
        findViewById<Button>(R.id.btnBackToCourses).setOnClickListener {
            finish() // just closes and returns to SixMonthCoursesActivity
        }

        // Navigate to Contact Info
        findViewById<Button>(R.id.btnContactInfo).setOnClickListener {
            startActivity(Intent(this, Contact::class.java))
        }

    }

    }
