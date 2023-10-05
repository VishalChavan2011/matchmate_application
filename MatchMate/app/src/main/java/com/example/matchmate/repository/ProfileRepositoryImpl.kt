package com.example.matchmate.repository

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.matchmate.Constants
import com.example.matchmate.api.ProfileService
import com.example.matchmate.cache.ProfileDao
import com.example.matchmate.cache.ProfileDatabase
import com.example.matchmate.model.Profile
import com.example.matchmate.model.ProfileData
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONObject
import javax.inject.Inject

/**
 * The implementation of the [ProfileRepository].
 * @property profileService instance of [ProfileService] to fetch data from server.
 * @property profileDatabase instance of [ProfileDatabase] to fetch data from local cache.
 * @property context instance of [ApplicationContext]
 */

class ProfileRepositoryImpl @Inject constructor(
    private val profileService: ProfileService,
    private val profileDatabase: ProfileDatabase,
    @ApplicationContext val context: Context
) : ProfileRepository {

    private val _profileLiveData = MutableLiveData<List<ProfileData>>()
    override val profileLiveData: LiveData<List<ProfileData>> = _profileLiveData

    private val _networkResultLiveData = MutableLiveData<NetworkResult>()
    override val networkResultLiveData: LiveData<NetworkResult> = _networkResultLiveData

    private var profileID: Int = 0
    private val profileDao: ProfileDao by lazy {
        profileDatabase.getProfileDao()
    }

    override suspend fun getProfileInformation(fromDatabase: Boolean) {
        _networkResultLiveData.postValue(NetworkResult.Loading())
        if(fromDatabase) {
            val profileData = profileDao.getProfileData()
            if(profileData != null) {
                _networkResultLiveData.postValue(NetworkResult.Success(dataFromDatabase = true))
                _profileLiveData.postValue(profileData)
            }
            else {
                getDataFromNetwork()
            }
        } else {
            getDataFromNetwork()
        }
    }

    override suspend fun updateProfile(profileId: Int, isLiked: Boolean, profileStatus: Boolean) {
        profileDao.updateProfile(profileId, isLiked, profileStatus)
    }

     private suspend fun getDataFromNetwork() {
         if(isInternetConnected()) {
             try {
                 val result = profileService.getProfileInformation()
                 if (result.isSuccessful) {
                     result.body()?.let {
                         val results = getProfileData(it.results)
                         val updatedList = profileLiveData.value?.plus(results)
                         if (updatedList != null) {
                             profileDao.addProfileList(updatedList)
                             _profileLiveData.postValue(updatedList!!)
                             _networkResultLiveData.postValue(NetworkResult.Success(dataFromDatabase = false))
                         }
                         else {
                             _networkResultLiveData.postValue(NetworkResult.Error())
                         }
                     }
                 } else if (result.errorBody() != null) {
                     val errorMessage =
                         JSONObject(result.errorBody()!!.charStream().readText()).toString()
                     _networkResultLiveData.postValue(
                         NetworkResult.Error(
                             errorCode = result.code(),
                             errorMessage = errorMessage
                         )
                     )
                 }
             } catch (e: Exception) {
                 _networkResultLiveData.postValue(NetworkResult.Error(errorMessage = e.message))
             }
         }
         else {
             _networkResultLiveData.postValue(NetworkResult.Error(errorMessage = "No, Internet Connectivity"))
         }

    }

    private fun getProfileData(results: List<Profile>): List<ProfileData> {
        val profileList: MutableList<ProfileData> = mutableListOf()

        results.forEach { result ->
            profileList.add(
                ProfileData(
                    profileId = getProfileId(),
                    firstName = result.name.first,
                    lastName = result.name.last,
                    age = result.dob.age,
                    image = result.picture.large,
                    location = result.location.city,
                    isProfileLiked = false,
                    profileStatusDecided = false
                )
            )
        }
        return profileList
    }

    private fun getProfileId(): Int {
        profileID = if (profileID != 0)
            ++profileID

        else {
            val sharedPreferences = context.getSharedPreferences("ProfileID", Context.MODE_PRIVATE)
            profileID = sharedPreferences.getInt(Constants.PROFILE_ID, 1)
            profileID
        }
        storeProfileIdInSP()
        return profileID
    }

    /**
     * Store the ProfileID into the SharedPreferences.
     */
    private fun storeProfileIdInSP() {
        val sharedPreferences = context.getSharedPreferences("ProfileID", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(Constants.PROFILE_ID, profileID).apply()
    }

    /**
     * Check if the internet connection is available or not.
     * @return true or false depending upon the internet connection.
     */
    private fun isInternetConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetwork != null && cm.getNetworkCapabilities(cm.activeNetwork) != null
    }
}