package com.example.testappforhedgehog


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.testappforhedgehog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val jokesViewModel: JokesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        openFrag(JokesFragment.newInstance(), R.id.place_holder)

        binding.btnWeb.setOnClickListener {
            setTitle("Api info")
            binding.btnWeb.setImageResource(R.drawable.web_blue)
            binding.btnJoke.setImageResource(R.drawable.joke_grey)
            binding.tvWeb.setTextColor(ContextCompat.getColor(this, R.color.blue))
            binding.tvJokes.setTextColor(ContextCompat.getColor(this, R.color.gray))

            openFrag(ApiFragment.newInstance(), R.id.place_holder)
        }

        binding.btnJoke.setOnClickListener {
            setTitle("Jokes")
            binding.btnWeb.setImageResource(R.drawable.web_grey)
            binding.btnJoke.setImageResource(R.drawable.joke_blue)
            binding.tvWeb.setTextColor(ContextCompat.getColor(this, R.color.gray))
            binding.tvJokes.setTextColor(ContextCompat.getColor(this, R.color.blue))

            openFrag(JokesFragment.newInstance(), R.id.place_holder)
        }

    }
    private fun openFrag(f: Fragment, idHolder: Int){
        supportFragmentManager
            .beginTransaction()
            .replace(idHolder, f)
            .commit()
    }

}