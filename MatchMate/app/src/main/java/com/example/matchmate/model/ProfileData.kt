package com.example.matchmate.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class to hold the Profile information.
 * @property profileId the primary key for the database.
 * @property firstName the first name of the profile.
 * @property lastName the last name of the profile.
 * @property age the age of the profile.
 * @property location the location city of the profile.
 * @property image the image of the profile.
 * @property profileStatusDecided the boolean representing if the profile interaction status.
 * @property isProfileLiked the boolean representing  if the profile is accepted or declined.
 */
@Entity(tableName = "Profile")
data class ProfileData(
    @PrimaryKey
    val profileId: Int,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val location: String,
    val image: String,
    var profileStatusDecided: Boolean,
    var isProfileLiked: Boolean
)
