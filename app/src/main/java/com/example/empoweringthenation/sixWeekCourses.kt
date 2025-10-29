package com.example.empoweringthenation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class sixWeekCourses : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_six_week_courses)


        val contact = findViewById<Button>(R.id.btnContactInfo)

        // Example: Navigate to Life Skills detail screen
        findViewById<Button>(R.id.btnCooking).setOnClickListener {
            startActivity(Intent(this, cooking::class.java))
        }

        findViewById<Button>(R.id.GardenBtn).setOnClickListener {
            startActivity(Intent(this, GardenMaintence::class.java))
        }

        findViewById<Button>(R.id.btnChild).setOnClickListener {
            startActivity(Intent(this, ChildMinding::class.java))
        }


        contact.setOnClickListener(){
            val intent = Intent(this, Contact::class.java)
            startActivity(intent)
        }
    }
    }
