package com.example.testappforhedgehog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONObject
import java.net.URL

class JokesViewModel: ViewModel() {

    val items = MutableLiveData<MutableList<String>>()
    var thread: Thread? = null



    fun request(etCount: Int){

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

    }
}