package com.example.empoweringthenation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SixMonthCoursesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_six_month_courses)

        val contact = findViewById<Button>(R.id.btnContactInfo)

        // Example: Navigate to Life Skills detail screen
        findViewById<Button>(R.id.btnLifeSkills).setOnClickListener {
            startActivity(Intent(this, CourseDetailActivity::class.java))
        }

        findViewById<Button>(R.id.btnFirstAid).setOnClickListener {
            startActivity(Intent(this, firstAid::class.java))
        }

        findViewById<Button>(R.id.sewingBtn).setOnClickListener {
            startActivity(Intent(this, sewing::class.java))
        }

        findViewById<Button>(R.id.landBtn).setOnClickListener {
            startActivity(Intent(this, landScaping::class.java))
        }

        contact.setOnClickListener(){
            val intent = Intent(this, Contact::class.java)
            startActivity(intent)
        }
    }
}
