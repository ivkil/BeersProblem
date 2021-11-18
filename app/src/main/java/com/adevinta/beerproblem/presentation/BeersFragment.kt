package com.adevinta.beerproblem.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.adevinta.beerproblem.databinding.FragmentBeersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.adevinta.beerproblem.presentation.BeersViewModel.BeersUiState.Error
import com.adevinta.beerproblem.presentation.BeersViewModel.BeersUiState.Loading
import com.adevinta.beerproblem.presentation.BeersViewModel.BeersUiState.Success
import javax.inject.Inject

@AndroidEntryPoint class BeersFragment : Fragment() {

    private var _binding: FragmentBeersBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BeersViewModel by viewModels()

    @Inject lateinit var beersAdapter: BeersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBeersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.beersRecycler.adapter = beersAdapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    binding.beersProgress.showIf { uiState is Loading }
                    binding.beersRecycler.showIf { uiState is Success }
                    binding.beersErrorText.showIf { uiState is Error }
                    if (uiState is Success) {
                        beersAdapter.submitList(uiState.beers)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
