package com.example.pocsharedpreferencespersistence

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.et_text
import kotlinx.android.synthetic.main.activity_main.sw_switch
import kotlinx.android.synthetic.main.activity_main.tv_text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()

        val btbutton = findViewById<Button>(R.id.bt_button)
        btbutton.setOnClickListener{
            saveData()
        }
    }

    private fun saveData(){

        val insertedText = et_text.text.toString()
        tv_text.text = insertedText

        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString("STRING_KEY", insertedText)
            putBoolean("BOOLEAN_KEY", sw_switch.isChecked)
        }.apply()

        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show()
    }

    private fun loadData(){
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString("STRING_KEY", null)
        val savedBoolean = sharedPreferences.getBoolean("BOOLEAN_KEY", false)

        tv_text.text = savedString
        sw_switch.isChecked = savedBoolean
    }
}