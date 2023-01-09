package com.example.crewjetpackcompose.data.remote.api

import com.example.crewjetpackcompose.data.model.Crew
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/crew")
    suspend fun getAllCrewInfo(): Response<List<Crew>>

    @GET("/crew/{id}")
    suspend fun getCrewById(
        @Path("id") id: String
    ): Response<Crew>
}