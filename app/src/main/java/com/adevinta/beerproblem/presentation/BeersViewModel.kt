package com.adevinta.beerproblem.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adevinta.beerproblem.data.BeerRepository
import com.adevinta.beerproblem.domain.Beer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel class BeersViewModel @Inject constructor(
    private val beerRepository: BeerRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<BeersUiState> =
        MutableStateFlow(BeersUiState.Success(emptyList()))
    val uiState: StateFlow<BeersUiState> = _uiState

    init {
        _uiState.value = BeersUiState.Loading
        viewModelScope.launch {
            beerRepository.getBeers()
                .onSuccess { _uiState.value = BeersUiState.Success(it) }
                .onFailure {
                    Log.e("ivkil", "Error while loading beers", it)
                    _uiState.value = BeersUiState.Error
                }
        }
    }

    sealed class BeersUiState {
        object Loading : BeersUiState()
        data class Success(val beers: List<Beer>) : BeersUiState()
        object Error : BeersUiState()
    }
}
