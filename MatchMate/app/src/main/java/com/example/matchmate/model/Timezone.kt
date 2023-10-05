package com.example.matchmate.model

/**
 * Data class to represent the Timezone of the profile.
 * @property description the description of the timezone.
 * @property offset the offset of the timezone.
 */
data class Timezone(
    val description: String,
    val offset: String
)