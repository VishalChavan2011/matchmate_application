package com.example.matchmate.model


/**
 * Data class to hold the Login information such as username, password, sha1 key and uuid.
 */
data class Login(
    val md5: String,
    val password: String,
    val salt: String,
    val sha1: String,
    val sha256: String,
    val username: String,
    val uuid: String
)