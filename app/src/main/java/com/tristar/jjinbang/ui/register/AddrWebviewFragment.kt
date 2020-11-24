package com.tristar.jjinbang.ui.register

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.tristar.jjinbang.R
import kotlinx.android.synthetic.main.addr_webview_fragment.*


class AddrWebviewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.addr_webview_fragment, container, false)
    }
    lateinit var handler: Handler

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init_webView()
        handler = Handler(Looper.getMainLooper())
    }

    fun init_webView(){
        webview.settings.javaScriptEnabled = true
        webview.settings.javaScriptCanOpenWindowsAutomatically = true
        webview.settings.setSupportMultipleWindows(true)
        val bridge = AndroidBridge()
        webview.addJavascriptInterface(bridge, "JJinbang")
        webview.webChromeClient = WebChromeClient()
        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                return false
            }
        }
        webview.loadUrl("http://gch04721.ivyro.net/addr.php")
    }

    inner class AndroidBridge {
        @JavascriptInterface
        fun setAddress(arg1: String, arg2: String, arg3: String){
            handler.post {
                val text: String = String.format("(%s), %s, %s", arg1, arg2, arg3)
                Log.d("TEST", "run: $text")
                result.text = text
                init_webView()
            }
        }

        @JavascriptInterface
        fun setAlert(){
            handler.post{
                Toast.makeText(context, "지번주소를 선택해주세요", Toast.LENGTH_LONG).show()
                init_webView()
            }
        }
    }
}