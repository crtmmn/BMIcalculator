package com.example.bmicalculator
import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val uSwitch: Switch = findViewById(R.id.units_switch)
        val calcButton: Button = findViewById(R.id.calc_button)
        val summary: TextView = findViewById(R.id.summary_textView)
        val ustext: TextView = findViewById(R.id.us_textView)
        val metrictext: TextView = findViewById(R.id.metric_textView)
        metrictext.setBackgroundResource(R.drawable.edittextstyle)

        fun checkBMI(bmi: Float): String {
            if(bmi < 18.5) {
                summary.setTextColor(Color.rgb(188, 32, 32))
                return "Under Weight"
            }
            else if(bmi >= 18.5 && bmi <= 24.9) {
                summary.setTextColor(Color.rgb(0, 129, 55))
                return "Normal"
            }
            else if(bmi >= 25 && bmi <= 29.9) {
                summary.setTextColor(Color.rgb(255, 228, 0))
                return "Over Weight"
            }
            else if(bmi >= 30 && bmi <= 34.9) {
                summary.setTextColor(Color.rgb(211, 136, 136))
                return "Obesity (Class I)"
            }
            else if(bmi >= 35 && bmi <= 39.9) {
                summary.setTextColor(Color.rgb(188, 32, 32))
                return "Obesity (Class II)"
            }
            else if(bmi > 40) {
                summary.setTextColor(Color.rgb(138, 1, 1))
                return "Extreme Obesity"
            }
            return "Error"
        }

        uSwitch.setOnClickListener {
            ustext.background = null
            metrictext.background = null
            if(uSwitch.isChecked) {
                ustext.setBackgroundResource(R.drawable.edittextstyle)
            }
            else {
                metrictext.setBackgroundResource(R.drawable.edittextstyle)
            }
        }

        calcButton.setOnClickListener {
            if(uSwitch.isChecked) {
                val height: TextView = findViewById(R.id.height_editTextNumber)
                val weight: TextView = findViewById(R.id.weight_editTextNumber)
                if(height.text.isNotBlank() && weight.text.isNotBlank()) {
                    val heightFloat = (height.text.toString().toFloat())
                    val heightInches = heightFloat/100
                    val weightInt = weight.text.toString().toInt()
                    val bmi: Float = weightInt/(heightInches*heightInches)*703
                    val roundedBMI = String.format("%.2f", bmi / 10000).toFloat()
                    val result: TextView = findViewById(R.id.result_textView)
                    result.text = "Your BMI is: ${roundedBMI.toString()}"
                    summary.text = checkBMI(roundedBMI)
                }
                else {
                    val result: TextView = findViewById(R.id.result_textView)
                    result.text = null
                    summary.text = null
                    val toast: Toast = Toast.makeText(this, "You didn't entered all required data!", Toast.LENGTH_LONG)
                    toast.show()
                }
            }
            else {
                val height: TextView = findViewById(R.id.height_editTextNumber)
                val weight: TextView = findViewById(R.id.weight_editTextNumber)
                if(height.text.isNotBlank() && weight.text.isNotBlank()) {
                    val heightFloat = (height.text.toString().toFloat())
                    val heightMeters = heightFloat/100
                    val weightInt = weight.text.toString().toInt()
                    val bmi: Float = weightInt/(heightMeters*heightMeters)
                    val roundedBMI = String.format("%.2f", bmi).toFloat()
                    val result: TextView = findViewById(R.id.result_textView)
                    result.text = "Your BMI is: ${roundedBMI.toString()}"
                    summary.text = checkBMI(roundedBMI)
                }
                else {
                    val result: TextView = findViewById(R.id.result_textView)
                    result.text = null
                    summary.text = null
                    val toast: Toast = Toast.makeText(this, "You didn't entered all data!", Toast.LENGTH_LONG)
                    toast.show()
                }
            }
        }
    }
}