package com.example.matchmate.repository

import androidx.lifecycle.LiveData
import com.example.matchmate.model.ProfileData

/**
 * Repository to fetch profile information and to update the profile information.
 */
interface ProfileRepository {

    /**
     * [LiveData] which contains the list of [ProfileData].
     */
    val profileLiveData: LiveData<List<ProfileData>>

    /**
     * [LiveData] which contains [NetworkResult] information.
     */
    val networkResultLiveData: LiveData<NetworkResult>

    /**
     * Function the retrieve profile information from the database or server.
     */
    suspend fun getProfileInformation()

    /**
     * Function to update the profile information as per the user action.
     * @param profileId id of the profile which should be updated.
     * @param isLiked boolean value indicating if the user has accepted or declined.
     * @param profileStatus boolean value indicating that the profile is either accepted or declined.
     */
    suspend fun updateProfile(profileId: Int, isLiked: Boolean, profileStatus: Boolean)

}