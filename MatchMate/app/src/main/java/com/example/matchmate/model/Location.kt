package com.example.matchmate.model

/**
 * Data class to hold the Location of the profile.
 * @property city the city of the profile.
 * @property coordinates the [Coordinates] of the profile.
 * @property country the country the profile reside.
 * @property postcode the postcode of the profile.
 * @property state the state the profile resided.
 * @property street the street the profile resides.
 * @property timezone the [Timezone] of the profile location
 */
data class Location(
    val city: String,
    val coordinates: Coordinates,
    val country: String,
    val postcode: String,
    val state: String,
    val street: Street,
    val timezone: Timezone
)