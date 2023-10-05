package com.example.matchmate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.matchmate.repository.ProfileRepository

/**
 * ViewModelFactory which creates the [ProfileViewModel] object.
 */
class ProfileViewModelFactory(private val profileRepository: ProfileRepository) :ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(profileRepository) as T
    }
}