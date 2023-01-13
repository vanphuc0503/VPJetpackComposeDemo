package com.example.crewjetpackcompose.presentation.ui.allcrewinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.crewjetpackcompose.data.model.Crew
import com.example.crewjetpackcompose.domain.usecase.GetAllCrewInfoUseCase
import com.example.crewjetpackcompose.domain.usecase.GetAllCrewQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllCrewInfoViewModel @Inject constructor(
    private val getAllCrewInfoUseCase: GetAllCrewInfoUseCase,
    private val getAllCrewQueryUseCase: GetAllCrewQueryUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(mutableListOf<Crew>())
    val state: StateFlow<List<Crew>> = _state

    private var _crews: Flow<PagingData<Crew>>? = null
    var crews = _crews

    init {
//        callApi()
        callApiPaging()
    }

    private fun callApiPaging() {
        viewModelScope.launch {
            _crews = getAllCrewQueryUseCase().cachedIn(viewModelScope)
        }
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
