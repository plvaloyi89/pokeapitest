package com.phillv.mypokeapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Summary : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        val passedValues = intent
        val sharedPref =getPreferences(Context.MODE_PRIVATE) ?: return
        val email = sharedPref.getString("email", "pvaloyi@yahoo.com")

        findViewById<TextView>(R.id.pName).apply {
            text = passedValues.getStringExtra("name")
        }
        findViewById<TextView>(R.id.pPrice).apply {
            text = passedValues.getStringExtra("Cost")
        }

        findViewById<TextView>(R.id.email).apply {
            text = email
        }

    }
}