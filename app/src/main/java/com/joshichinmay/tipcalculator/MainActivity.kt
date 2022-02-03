package com.joshichinmay.tipcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.joshichinmay.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val stringInTextField =
            binding.costOfService.text.toString() //cost of service in string format
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            binding.tipAmount.text = ""
            binding.finalAmount.text = ""
            return
        }
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
        if (binding.roundUpTip.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        val finalCost = cost + tip
        val indiaLocale = Locale("en", "IN")
        val formattedTip = NumberFormat.getCurrencyInstance(indiaLocale).format(tip)
        val formattedCost = NumberFormat.getCurrencyInstance(indiaLocale).format(finalCost)
        binding.tipAmount.text = getString(R.string.tip_amount, formattedTip)
        binding.finalAmount.text = getString(R.string.final_amount, formattedCost)

    }
}