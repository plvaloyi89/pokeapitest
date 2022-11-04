package com.phillv.mypokeapp.ViewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import com.phillv.mypokeapp.Data.PokeInstance
import com.phillv.mypokeapp.Data.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonViewModel : ViewModel() {

    val pokemonName = MutableLiveData<String>()
    val pokemonbaseEx = MutableLiveData<Double>()


    @RequiresApi(Build.VERSION_CODES.O)
    fun search(name:String){
        viewModelScope.launch(Dispatchers.IO) {
            val lower = name.lowercase()
            val response =PokeInstance.process("${lower}")
            val gson = GsonBuilder().create()

            if(response != null){
                try {
                    val info = gson.fromJson(response, Pokemon::class.java)
                    withContext(Dispatchers.Main){
                        val calculatevalue = info.base_experience.toDouble()
                        pokemonbaseEx.value = calculatePrice(calculatevalue)
                    }
                }catch (err: Error) {
                    print("Error when parsing JSON: " + err.localizedMessage)
                }
            }else {

            }

        }
    }

    fun calculatePrice(calculate:Double): Double {
        val base_exp = calculate.toDouble()
        val rawResult= 0.01 * base_exp *6
        val solution = String.format("%.2f",rawResult).toDouble()

        return solution
    }



}