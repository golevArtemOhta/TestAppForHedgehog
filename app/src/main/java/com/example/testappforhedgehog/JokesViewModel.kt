package com.example.testappforhedgehog

import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testappforhedgehog.api.RetrofitFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JokesViewModel : ViewModel() {

    val items = MutableLiveData<MutableList<String>>()
    private val api = RetrofitFactory.new()

    fun request(etCount: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val joke = api.getJokeRandom(etCount)
                val jokesList = mutableListOf<String>()
                for (i in joke.value) {
                    jokesList.add(fromHtml(i.joke).toString())
                }
                items.postValue(jokesList)
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