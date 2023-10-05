package com.example.matchmate.model

/**
 * Data class to hold the profile information.
 * @property dob the date of birth information in [Dob] data class.
 * @property email the email address of the profile.
 * @property gender the gender of the profile.
 * @property id the [Id] information of the profile.
 * @property location the [Location] of the profile.
 * @property login the login details.
 * @property name the [Name] of the profile.
 * @property phone the phone number of the profile.
 * @property picture the [Picture] of the profile.
 * @property registered the [Registered] details.
 */
data class Profile(
    val cell: String,
    val dob: Dob,
    val email: String,
    val gender: String,
    val id: Id,
    val location: Location,
    val login: Login,
    val name: Name,
    val nat: String,
    val phone: String,
    val picture: Picture,
    val registered: Registered
)