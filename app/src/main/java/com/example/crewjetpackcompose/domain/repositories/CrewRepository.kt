package com.example.crewjetpackcompose.domain.repositories

import com.example.crewjetpackcompose.data.model.Crew
import kotlinx.coroutines.flow.Flow

interface CrewRepository {
    suspend fun getAllCrewInfo(): Flow<List<Crew>>

    suspend fun getCrewById(id: String): Flow<Crew>
}
