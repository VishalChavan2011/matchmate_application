package com.example.matchmate.model

/**
 * Data class to hold the Info of the server page.
 * @property page the page number.
 * @property results the results count.
 * @property seed the seed property.
 * @property version the current version.
 */
data class Info(
    val page: Int,
    val results: Int,
    val seed: String,
    val version: String
)