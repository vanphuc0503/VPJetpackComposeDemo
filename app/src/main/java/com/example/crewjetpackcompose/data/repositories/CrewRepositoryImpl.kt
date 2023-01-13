package com.example.crewjetpackcompose.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.crewjetpackcompose.data.model.Crew
import com.example.crewjetpackcompose.data.remote.api.ApiService
import com.example.crewjetpackcompose.data.remote.api.CrewOption
import com.example.crewjetpackcompose.domain.repositories.CrewRepository
import com.example.crewjetpackcompose.presentation.ui.adapter.CrewPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CrewRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CrewRepository {

    override suspend fun getAllCrewInfo(): Flow<List<Crew>> =
        flow { emit(apiService.getAllCrewInfo()) }

    override suspend fun getCrewQuery() = Pager(
        config = PagingConfig(
            pageSize = 1
        ),
        pagingSourceFactory = {
            CrewPagingSource {
                val data = apiService.getAllCrewQuery(CrewOption(it, 10))
                (data as? List<Crew>) ?: mutableListOf()
            }
        }
    ).flow

    override suspend fun getCrewById(id: String): Flow<Crew> = flow {
        emit(apiService.getCrewById(id))
    }
}
