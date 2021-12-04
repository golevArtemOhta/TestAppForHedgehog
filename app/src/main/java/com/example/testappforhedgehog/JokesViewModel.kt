package com.example.testappforhedgehog

import android.provider.Settings
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

class JokesViewModel: ViewModel() {

    val items = MutableLiveData<MutableList<String>>()
    var thread: Thread? = null


    fun request(etCount: String){
        val api = Retrofit.Builder()
            .baseUrl("https://api.icndb.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)

        GlobalScope.launch(Dispatchers.IO){
            val response = api.getJokeRandom(etCount).awaitResponse()
            if (response.isSuccessful){
                val data = response.body()!!

                withContext(Dispatchers.Main){
                    val jokes = data.value
                    val jokesList = mutableListOf<String>()
                    for (i in jokes){
                        jokesList.add(i.joke)
                    }
                     items.postValue(jokesList)
                }
            }
        }

    }

   /* fun request(etCount: Int){

        val url: String = "https://api.icndb.com/jokes/random/${etCount}"

       thread = object: Thread(){
            override fun run() {
                try {
                    val apiResponse = URL(url).readText()
                    val value = JSONObject(apiResponse).getJSONArray("value")
                    val jokes = mutableListOf<String>()
                    for (i in 1..etCount) {
                        val joke = value.getJSONObject(i - 1).getString("joke")
                        jokes.add(joke)
                    }
                    items.postValue(jokes)
                }catch (e: Throwable){
                    e.toString()
                }
            }
        }
        thread?.start()

    }*/
}