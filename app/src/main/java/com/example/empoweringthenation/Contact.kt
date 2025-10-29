package com.example.empoweringthenation

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class Contact : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        val phoneNumber: TextView = findViewById(R.id.phoneNumber)
        val emailAddress: TextView = findViewById(R.id.emailAddress)
        val backHomeBtn: Button = findViewById(R.id.btnBackHome)

        // Phone click opens dialer
        phoneNumber.setOnClickListener {
            val uri = Uri.parse("tel:0115824127")
            val intent = Intent(Intent.ACTION_DIAL, uri)
            startActivity(intent)
        }

        // Email click opens email client safely
        emailAddress.setOnClickListener {
            val uri = Uri.parse("mailto:EmpoweringNation@gmail.com")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            try {
                startActivity(intent)
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(this, "No email app found on device", Toast.LENGTH_SHORT).show()
            }
        }

        // Back to home (MainActivity) — change class if your home activity is named differently
        backHomeBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            // clear top to avoid duplicates in stack
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
            finish()
        }
    }
}
