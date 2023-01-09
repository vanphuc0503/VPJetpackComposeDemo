package com.example.crewjetpackcompose.data.di

import com.example.crewjetpackcompose.data.remote.api.ApiService
import com.example.crewjetpackcompose.data.repositories.CrewRepositoryImpl
import com.example.crewjetpackcompose.domain.repositories.CrewRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideCrewRepository(
        apiService: ApiService
    ): CrewRepository = CrewRepositoryImpl(apiService)
}
