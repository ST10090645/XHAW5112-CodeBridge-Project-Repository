package com.example.empoweringthenation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.net.Uri

class FeeCalculation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fee_calculation)


        val checkBoxes = listOf(
            findViewById<CheckBox>(R.id.cbFirstAid),
            findViewById<CheckBox>(R.id.cbLandscaping),
            findViewById<CheckBox>(R.id.cbSewing),
            findViewById<CheckBox>(R.id.cbLifeSkills),
            findViewById<CheckBox>(R.id.cbChildMinding),
            findViewById<CheckBox>(R.id.cbCooking),
            findViewById<CheckBox>(R.id.cbGardenMaintenance)
        )

        val etPhoneNumber: EditText = findViewById(R.id.etPhone)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val txtTotal = findViewById<TextView>(R.id.txtTotal)
        val home = findViewById<Button>(R.id.btnBackHome)
        val btnConfirmSend: Button = findViewById(R.id.btnConfirmSend)


        btnCalculate.setOnClickListener {
            var total = 0
            var count = 0

            for (cb in checkBoxes) {
                if (cb.isChecked) {
                    count++
                    total += if (cb.text.contains("R1500")) 1500 else 750
                }
            }

            val discountPercent = when (count) {
                2 -> 5
                3 -> 10
                else -> if (count > 3) 15 else 0
            }

            val discountAmount = total * discountPercent / 100
            val finalTotal = total - discountAmount

            txtTotal.text = """
                Courses selected: $count
                Subtotal: R$total
                Discount: $discountPercent% (-R$discountAmount)
                Total: R$finalTotal
            """.trimIndent()


            //home.setOnClickListener{
               // val intent = Intent(this, MainActivity::class.java)
                //startActivity(intent)
            }

        home.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnConfirmSend.setOnClickListener {
            val phone = etPhoneNumber.text.toString().trim()

            // For demonstration: hardcode example details or replace with actual data later
            val name = "Example User"
            val courses = "First Aid, Cooking"
            val total = "R2250"

            if (phone.isEmpty()) {
                Toast.makeText(this, "Please enter a phone number", Toast.LENGTH_SHORT).show()
            } else {
                val smsBody = "Name: $name\nCourses: $courses\nTotal Fees: $total"

                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("sms:$phone")
                intent.putExtra("sms_body", smsBody)

                try {
                    startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(this, "No messaging app found", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
    }





