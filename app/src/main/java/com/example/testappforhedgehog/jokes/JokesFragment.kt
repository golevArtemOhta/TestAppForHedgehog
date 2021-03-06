package com.example.testappforhedgehog.jokes

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.example.testappforhedgehog.databinding.FragmentJokesBinding


class JokesFragment : Fragment() {
    lateinit var binding: FragmentJokesBinding
    lateinit var jokesViewModel: JokesViewModel

    var dataItems = MutableList(0) { "" }
    lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        jokesViewModel = ViewModelProvider(requireActivity()).get(JokesViewModel::class.java)
        binding = FragmentJokesBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = ArrayAdapter<String>(requireActivity(),
            R.layout.simple_list_item_1, dataItems)
        binding.listViewJokes.adapter = adapter

        binding.btnReload.setOnClickListener {
            jokesViewModel.request(binding.etCount.text.toString())
        }

        jokesViewModel.items.observe(activity as LifecycleOwner, {
            adapter.clear()
            dataItems.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() =  JokesFragment()
    }
}