package com.example.crewjetpackcompose.domain.usecase

import com.example.crewjetpackcompose.data.model.Crew
import com.example.crewjetpackcompose.domain.base.SuspendUseCase
import com.example.crewjetpackcompose.domain.repositories.CrewRepository
import com.example.crewjetpackcompose.presentation.di.MainDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllCrewInfoUseCase @Inject constructor(
    @MainDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val crewRepository: CrewRepository,
) : SuspendUseCase<Unit, Flow<List<Crew>>>(ioDispatcher) {

    override suspend fun execute(params: Unit?): Flow<List<Crew>> {
        return crewRepository.getAllCrewInfo()
    }
}
