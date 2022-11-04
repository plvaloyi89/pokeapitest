package com.phillv.mypokeapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.phillv.mypokeapp.ViewModel.PokemonViewModel
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class MainActivity : AppCompatActivity() {

    lateinit var viewModel : PokemonViewModel
    private val cBalance = "PokemonAccountBalance"
    val balance = 10.00


    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setBalance()

        val pokemonName = findViewById<EditText>(R.id.editTextPokemonName).text
        viewModel = ViewModelProvider(this)[PokemonViewModel::class.java]
        val sharedPref =getPreferences(Context.MODE_PRIVATE) ?: return
        val balance = sharedPref.getInt("Balance", balance.toInt()).toString()


         findViewById<Button>(R.id.button).setOnClickListener {
             viewModel.search("${pokemonName}")
         }
        findViewById<TextView>(R.id.acnumber).apply {

            text ="$ ${balance}"
        }

        viewModel.pokemonbaseEx.observe(this, Observer {
            val result = it
            val sid = balance.toDouble()
            if (sid > result) {
                findViewById<Button>(R.id.purchase).isEnabled = true
                findViewById<TextView>(R.id.result).text = "you can purchase this Pokemon for ${it}"

                findViewById<Button>(R.id.purchase).setOnClickListener {
                    val summaryIntent = Intent (this, Summary::class.java)
                    summaryIntent.putExtra("Cost", "${result}")
                    summaryIntent.putExtra("name","${pokemonName}")
                    startActivity(summaryIntent)
                }
            }else{
                findViewById<TextView>(R.id.result).text = "This costs ${it}, you have a low balance"
                findViewById<Button>(R.id.purchase).isEnabled = false
            }
        })

    }

    fun setBalance(){
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(cBalance, Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()

        edit.putInt("Balance", balance.toInt())
        edit.putString("email", "pvaloyi@yahoo.com")
    }

}