package com.example.climbingapp.network

import com.example.climbingapp.model.climbs.Gym
import com.example.climbingapp.model.session.Climb
import com.example.climbingapp.model.session.Session
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.POST

private const val BASE_URL =
        ""

private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

interface ClimbApiService {
        // Past Sessions
        @GET("pastSessions")
        suspend fun getPastSessions(): List<Session>
        @GET("pastSession")
        suspend fun getPastSession(sessionCode: String): Session

        // climb
        @GET("climb")
        suspend fun getClimb(): Climb
        @POST("climb")
        suspend fun addClimb(climb: Climb)

        @GET("gym")
        suspend fun getGyms(): List<Gym>

}

object ClimbApi {
        val retrofitService : ClimbApiService by lazy {
                retrofit.create(ClimbApiService::class.java)
        }
}