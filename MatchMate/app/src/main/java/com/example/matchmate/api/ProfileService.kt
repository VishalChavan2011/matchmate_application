package com.example.matchmate.api

import com.example.matchmate.model.ProfileInformation
import retrofit2.Response
import retrofit2.http.GET

/**
 * Interface to get the Profile information from the server.
 */
interface ProfileService {

    /**
     * Suspending function to get Profile information from the server using Retrofit.
     * @return [Response] depending upon the success and failure scenario.
     */
    @GET("api/?results=10")
    suspend fun getProfileInformation(): Response<ProfileInformation>

}