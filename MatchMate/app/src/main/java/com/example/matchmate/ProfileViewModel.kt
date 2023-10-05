package com.example.matchmate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matchmate.model.ProfileData
import com.example.matchmate.repository.NetworkResult
import com.example.matchmate.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel to hold [ProfileData] and [NetworkResult] data as per the response.
 * @property profileRepository [ProfileRepository] to fetch profile information
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(private val profileRepository: ProfileRepository) :
    ViewModel() {

    private val _profileData: MutableLiveData<List<ProfileData>> =
        profileRepository.profileLiveData as MutableLiveData<List<ProfileData>>
    val profileData: LiveData<List<ProfileData>> = _profileData
    val networkResult: LiveData<NetworkResult> = profileRepository.networkResultLiveData

    init {
        fetchData()
    }

    /**
     * Fetch data from the server and database
     * @param fromDatabase boolean representing if the data should be fetched from database or server.
     */
    fun fetchData(fromDatabase: Boolean = true) {
        viewModelScope.launch(Dispatchers.IO) {
            profileRepository.getProfileInformation(fromDatabase)
        }
    }

    /**
     * Update the profile as per the user interaction.
     * @param profileId the profileId of the Profile to be updated.
     * @param isLiked the boolean representing the user interaction.
     */
    fun profileLiked(profileId: Int, isLiked: Boolean) {
        val profileData = _profileData.value?.toMutableList()
        profileData?.forEachIndexed { index, profile ->
            val updateProfile = if (profile.profileId == profileId) profile else null
            if (updateProfile != null) {
                val replacement =
                    profile.copy(isProfileLiked = isLiked, profileStatusDecided = true)
                profileData[index] = replacement
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            profileRepository.updateProfile(profileId, isLiked, true)
        }
        _profileData.postValue(profileData!!)
    }
}