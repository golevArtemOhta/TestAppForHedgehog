package com.example.testappforhedgehog


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewFragment
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.testappforhedgehog.apiInfo.ApiInfoFragment
import com.example.testappforhedgehog.databinding.ActivityMainBinding
import com.example.testappforhedgehog.jokes.JokesFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null) {
            openFragment(JokesFragment.newInstance())
        }else{
            val isJokesFragment = supportFragmentManager.findFragmentById(R.id.place_holder) is JokesFragment
            selectMenuItem(isJokesFragment)
        }

        binding.btnWeb.setOnClickListener {
            selectMenuItem(false)
            openFragment(ApiInfoFragment.newInstance())
        }

        binding.btnJoke.setOnClickListener {
            selectMenuItem(true)
            openFragment(JokesFragment.newInstance())
        }
    }

    private fun selectMenuItem(isJokes: Boolean){
        if(isJokes){
            title = "Jokes"
            binding.btnWeb.setImageResource(R.drawable.web_grey)
            binding.btnJoke.setImageResource(R.drawable.joke_blue)
            binding.tvWeb.setTextColor(ContextCompat.getColor(this, R.color.gray))
            binding.tvJokes.setTextColor(ContextCompat.getColor(this, R.color.blue))
        }else{
            title = "Api info"
            binding.btnWeb.setImageResource(R.drawable.web_blue)
            binding.btnJoke.setImageResource(R.drawable.joke_grey)
            binding.tvWeb.setTextColor(ContextCompat.getColor(this, R.color.blue))
            binding.tvJokes.setTextColor(ContextCompat.getColor(this, R.color.gray))
        }
    }

    private fun openFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace( R.id.place_holder, fragment)
            .commit()
    }

    override fun onBackPressed() {
        val fragment =
            supportFragmentManager.findFragmentById(R.id.place_holder) as ApiInfoFragment
        if (fragment.canGoBack()){
            fragment.goBack()
        }else{
            super.onBackPressed()
        }

    }
}