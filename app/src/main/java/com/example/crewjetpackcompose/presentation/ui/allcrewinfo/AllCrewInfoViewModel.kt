package com.example.crewjetpackcompose.presentation.ui.allcrewinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crewjetpackcompose.data.model.Crew
import com.example.crewjetpackcompose.domain.usecase.GetAllCrewInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllCrewInfoViewModel @Inject constructor(
    private val getAllCrewInfoUseCase: GetAllCrewInfoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(mutableListOf<Crew>())
    val state: StateFlow<List<Crew>> = _state

    init {
        callApi()
    }

    private fun callApi() {
        viewModelScope.launch {
            getAllCrewInfoUseCase().collect { crews ->
                _state.update {
                    crews.toMutableList()
                }
            }
        }
    }
}
