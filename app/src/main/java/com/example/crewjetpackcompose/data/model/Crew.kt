package com.example.crewjetpackcompose.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Crew(
    @get:Json(name = "agency") val agency: String,
    @Json(name = "id") val id: String,
    @Json(name = "image") val image: String,
    @Json(name = "launches") val launches: List<String>,
    @Json(name = "name") val name: String,
    @Json(name = "status") val status: String,
    @Json(name = "wikipedia") val wikipedia: String
)