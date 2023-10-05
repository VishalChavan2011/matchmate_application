package com.example.matchmate.model

/**
 * Data class representing the Profile information retrieved from the server.
 * @property results the list of the [Profile]
 */
data class ProfileInformation(
    val info: Info,
    val results: List<Profile>
)