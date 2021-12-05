package com.example.testappforhedgehog.apiInfo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.testappforhedgehog.databinding.FragmentApiBinding


class ApiInfoFragment : Fragment() {
    lateinit var binding: FragmentApiBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentApiBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            loadUrl()
        }
        else binding.wbV.restoreState(savedInstanceState)

        binding.wbV.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                binding.progressBar.progress = progress
                if (progress == 100) {
                    binding.progressBar.visibility = View.GONE
                } else {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }

        binding.wbV.settings.javaScriptEnabled = true
        binding.wbV.settings.useWideViewPort = true
  }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.wbV.saveState(outState)
    }

    companion object {
        fun newInstance() = ApiInfoFragment()
    }

    fun canGoBack(): Boolean {
        return binding.wbV.canGoBack()
    }

    fun goBack() {
        binding.wbV.goBack()
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun loadUrl() {
        binding.wbV.loadUrl("http://www.icndb.com/api/")
        binding.wbV.webViewClient = WebViewClient()
    }
}