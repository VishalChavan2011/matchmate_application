package com.example.matchmate.repository

/**
 * Class to hold response of the profile data request from the server and database.
 */
sealed class NetworkResult (
) {
    class Loading() : NetworkResult()
    class Success(val dataFromDatabase: Boolean) : NetworkResult()
    class Error(val errorCode: Int? = null, val errorMessage: String? = null) : NetworkResult()
}

