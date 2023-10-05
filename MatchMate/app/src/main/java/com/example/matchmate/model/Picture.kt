package com.example.matchmate.model

/**
 * Data class to hold Picture/Image information of the Profile.
 * @property large the user image with  large dimension.
 * @property medium the user image with medium dimension.
 * @property thumbnail the user image with small dimension which can be used as thumbnail.
 */
data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)