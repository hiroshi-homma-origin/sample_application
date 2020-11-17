package com.example.timeline.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.timeline.databinding.FragmentDetailBinding
import timber.log.Timber
import javax.inject.Inject

class DetailFragment @Inject constructor() : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: DetailViewModel by viewModels { factory }
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("check_args:$args")
        viewModel.setTitle(args.title)
        _binding = FragmentDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@DetailFragment
        }
        lifecycle.addObserver(viewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.title.text = viewModel.title.value
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
