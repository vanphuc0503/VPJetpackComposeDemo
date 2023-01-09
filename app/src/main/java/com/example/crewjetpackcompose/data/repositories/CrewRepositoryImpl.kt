package com.example.crewjetpackcompose.data.repositories

import com.example.crewjetpackcompose.data.model.Crew
import com.example.crewjetpackcompose.data.remote.api.ApiService
import com.example.crewjetpackcompose.domain.repositories.CrewRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CrewRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CrewRepository {

    override suspend fun getAllCrewInfo(): Flow<List<Crew>> {
        return flow {
            apiService.getAllCrewInfo().body()?.let {
                emit(it)
            }
        }
    }

    override suspend fun getCrewById(id: String): Flow<Crew> = flow {
        apiService.getCrewById(id).body()?.let {
            emit(it)
        }
    }
}
