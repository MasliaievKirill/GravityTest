package com.masliaiev.gravitytest.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.masliaiev.gravitytest.R
import com.masliaiev.gravitytest.databinding.FragmentLoadBinding
import com.masliaiev.gravitytest.presentation.GravityTestApp
import com.masliaiev.gravitytest.presentation.view_models.LoadFragmentViewModel
import com.masliaiev.gravitytest.presentation.view_models.ViewModelFactory
import javax.inject.Inject

class LoadFragment : Fragment() {

    private val component by lazy {
        (requireActivity().application as GravityTestApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: LoadFragmentViewModel

    private var _binding: FragmentLoadBinding? = null
    private val binding: FragmentLoadBinding
        get() = _binding ?: throw RuntimeException("FragmentLoadBinding is null")

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[LoadFragmentViewModel::class.java]
        viewModel.loadResult.observe(viewLifecycleOwner) {
            if (it.first && it.second) {
                launchWebViewFragment()
            }
//            Server download error. Loading data from database.
            else if (!it.first && it.second) {
                Toast.makeText(
                    requireActivity(),
                    getString(R.string.server_download_error_warning),
                    Toast.LENGTH_SHORT
                ).show()
                launchWebViewFragment()
            }
//            Server download error. Database is empty. No data to download.
            else {
                Toast.makeText(
                    requireActivity(),
                    getString(R.string.no_data_to_download_warning),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchWebViewFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, WebViewFragment.newInstance())
            .commit()
    }
}