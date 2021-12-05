package com.example.testappforhedgehog.jokes

import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class JokesViewModel : ViewModel() {

    val items = MutableLiveData<List<String>>()
    private val api = RetrofitFactory.new()
    private var job: Job? = null

    fun request(etCount: String) {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            val joke = api.getJokeRandom(etCount)
                items.postValue(joke.jokes.map { fromHtml(it.joke).toString() })
        }
    }

    private fun fromHtml(html: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // FROM_HTML_MODE_LEGACY is the behaviour that was used for versions below android N
            // we are using this flag to give a consistent behaviour
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html)
        }
    }
}