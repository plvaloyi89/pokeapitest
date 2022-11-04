package com.phillv.mypokeapp.Data

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

object PokeInstance {

    fun process(category:String): String? {
        var result: String? = null
        try {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://pokeapi.co/api/v2/pokemon/${category}")
                .build()
            val response = client.newCall(request).execute()
            result = response.body()?.string();
            //println(result)
        } catch (e: IOException) {
            e.printStackTrace();
        }
        return result
    }


}