package com.masliaiev.gravitytest.presentation.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.masliaiev.gravitytest.databinding.FragmentWebViewBinding
import com.masliaiev.gravitytest.presentation.GravityTestApp
import com.masliaiev.gravitytest.presentation.view_models.ViewModelFactory
import com.masliaiev.gravitytest.presentation.view_models.WebViewFragmentViewModel
import javax.inject.Inject

class WebViewFragment : Fragment() {

    private val component by lazy {
        (requireActivity().application as GravityTestApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: WebViewFragmentViewModel

    private lateinit var sharedPreferences: SharedPreferences

    private var _binding: FragmentWebViewBinding? = null
    private val binding: FragmentWebViewBinding
        get() = _binding ?: throw RuntimeException("FragmentWebViewBinding is null")

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences =
            requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("WebViewFragment", "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.javaScriptCanOpenWindowsAutomatically = true
        viewModel = ViewModelProvider(this, viewModelFactory)[WebViewFragmentViewModel::class.java]
        viewModel.response.observe(viewLifecycleOwner) {
//            if (savedInstanceState == null) {
                if (sharedPreferences.getBoolean(FIRST_LAUNCH, true)) {
                    binding.webView.loadUrl(it[0].link)
                    sharedPreferences.edit().putBoolean(FIRST_LAUNCH, false).apply()
                } else {
                    binding.webView.loadUrl(it[0].home)
                }
//            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (binding.webView.canGoBack()) {
                        binding.webView.goBack()
                    } else {
                        isEnabled = false
                        requireActivity().onBackPressed()
                    }
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {

        fun newInstance(): WebViewFragment {
            return WebViewFragment()
        }

        private const val APP_PREFERENCES = "gravity_test_app"
        private const val FIRST_LAUNCH = "first_launch"

    }
}