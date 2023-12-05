package com.example.matchmate.repository

/**
 * Class to hold response of the profile data request from the server and database.
 */
sealed class NetworkResult (
) {
    object Loading : NetworkResult()
    object Success : NetworkResult()
    class Error(val errorCode: Int? = null, val errorMessage: String? = null) : NetworkResult()
}

