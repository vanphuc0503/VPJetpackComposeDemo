package com.example.crewjetpackcompose.data.remote.api

import com.example.crewjetpackcompose.data.model.Crew
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("crew")
    suspend fun getAllCrewInfo(): List<Crew>

    @POST("crew/query")
    suspend fun getAllCrewQuery(
        @Body crewOption: CrewOption
    ): List<Crew>

    @GET("crew/{id}")
    suspend fun getCrewById(
        @Path("id") id: String
    ): Crew
}