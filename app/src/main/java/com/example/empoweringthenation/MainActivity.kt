package com.example.empoweringthenation

import android.content.Intent
import android.net.Uri
import android.widget.TextView
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Navigate to Six-Month Courses
        findViewById<Button>(R.id.btnSixMonth).setOnClickListener {
            val intent = Intent(this, SixMonthCoursesActivity::class.java)
            startActivity(intent)
        }

        val facebookBtn: ImageButton = findViewById(R.id.btnFacebook)
        val instagramBtn: ImageButton = findViewById(R.id.btnInstagram)
        val xBtn: ImageButton = findViewById(R.id.btnX)
        val youtubeBtn: ImageButton = findViewById(R.id.btnYoutube)

        facebookBtn.setOnClickListener {
            val uri = Uri.parse("https://www.facebook.com/profile.php?id=61581489944224")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        instagramBtn.setOnClickListener {
            val uri = Uri.parse("https://www.instagram.com/empowering._the.nation/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }


        xBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://x.com/EmpowerNation22?t=2nMZVBxivO8A-w6UbsI7CQ&s=09"))
            startActivity(intent)
        }

        youtubeBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCMPi5QHY3Fj3K7bIvzYLXmw"))
            startActivity(intent)
        }


        // Navigate to Six-Week Courses
        //findViewById<Button>(R.id.btnSixWeek).setOnClickListener {
          //  val intent = Intent(this, SixWeekCoursesActivity::class.java)
            //startActivity(intent)
        //}

        // Navigate to Contact Info
        findViewById<Button>(R.id.btnContactInfo).setOnClickListener {
            val intent = Intent(this, Contact::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnSixWeek).setOnClickListener {
            val intent = Intent(this, sixWeekCourses::class.java)
            startActivity(intent)
        }

        // Navigate to Fees
        findViewById<Button>(R.id.btnCalculateFees).setOnClickListener {
            val intent = Intent(this, FeeCalculation::class.java)
            startActivity(intent)
        }


    }
}


