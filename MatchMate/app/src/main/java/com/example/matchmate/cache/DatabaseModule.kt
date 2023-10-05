package com.example.matchmate.cache

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module to create [Singleton] object [ProfileDatabase] and [ProfileDao]
 */
@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideImageDatabase(context: Application): ProfileDatabase {
        return Room.databaseBuilder(context, ProfileDatabase::class.java, "ProfileDB").build()
    }

    @Singleton
    @Provides
    fun provideDao(db: ProfileDatabase) = db.getProfileDao()
}