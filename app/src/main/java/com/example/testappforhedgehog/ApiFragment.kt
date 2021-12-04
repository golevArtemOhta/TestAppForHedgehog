package com.example.testappforhedgehog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.testappforhedgehog.databinding.FragmentApiBinding
import com.example.testappforhedgehog.databinding.FragmentJokesBinding


class ApiFragment : Fragment() {
    lateinit var binding: FragmentApiBinding
    private var webviewstate: Bundle? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentApiBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            loadUrl()
        }
        else binding.wbV.restoreState(webviewstate!!)
    /*старый вариант неработающий
        if (savedInstanceState = null){
            binding.wbV.restoreState(savedInstanceState)
        }
        else loadUrl()*/
  }

    override fun onPause() {
        super.onPause()
        webviewstate = Bundle()
        binding.wbV.saveState(webviewstate!!)
    }

    /*старый неработающий вариант
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.wbV.saveState(outState)
    }*/


    companion object {
        @JvmStatic
        fun newInstance() = ApiFragment()
    }

    fun loadUrl() {
        binding.wbV.setWebChromeClient(object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                binding.progressBar.setProgress(progress)
                if (progress == 100) {
                    binding.progressBar.setVisibility(View.GONE)
                } else {
                    binding.progressBar.setVisibility(View.VISIBLE)
                }
            }
        })

        binding.wbV.settings.javaScriptEnabled = true
        binding.wbV.settings.useWideViewPort = true
        binding.wbV.loadUrl("http://www.icndb.com/api/")
        binding.wbV.webViewClient = WebViewClient()
    }


}