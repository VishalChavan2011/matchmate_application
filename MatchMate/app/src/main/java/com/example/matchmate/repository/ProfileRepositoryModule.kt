package com.example.matchmate.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module that returns the [Singleton] object of the [ProfileRepository].
 */
@InstallIn(SingletonComponent::class)
@Module
object ProfileRepositoryModule {

    @Provides
    @Singleton
    fun provideProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl) : ProfileRepository {
        return  profileRepositoryImpl
    }
}