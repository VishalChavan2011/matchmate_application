package com.example.matchmate.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.matchmate.model.ProfileData

/**
 * Interface to perform the data access operation such as insertion of [ProfileData], retrieving
 * profile information from the server and updating the profile on button click action.
 */
@Dao
interface ProfileDao {
    /**
     * Insert the list of [ProfileData] into the Database.
     * @param profileList list of the [ProfileData] to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProfileList(profileList: List<ProfileData>)

    /**
     * Retrieve all the profile entries from the database.
     * @return List of [ProfileData] from the Database.
     */
    @Query("SELECT * FROM profile")
    suspend fun getProfileData(): List<ProfileData>

    /**
     * Update the profile information as per the user action.
     * @param profileId id of the profile which should be updated.
     * @param isLiked boolean value indicating if the user has accepted or declined.
     * @param profileStatus boolean value indicating that the profile is either accepted or declined.
     */
    @Query("UPDATE profile SET isProfileLiked = :isLiked, profileStatusDecided = :profileStatus WHERE profileId = :profileId")
    fun updateProfile(profileId: Int, isLiked: Boolean, profileStatus: Boolean)
}
