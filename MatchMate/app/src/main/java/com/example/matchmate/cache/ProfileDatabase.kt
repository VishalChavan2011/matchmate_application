package com.example.matchmate.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.matchmate.model.ProfileData

/**
 * Database to store data locally using [ProfileDatabase]
 */
@Database(entities = [ProfileData::class], version = 1)
abstract class ProfileDatabase: RoomDatabase() {

    /**
     * Method to create instance of [ProfileDao] which will be used for Data operations
     * @return instance of [ProfileDao]
     */
    abstract fun getProfileDao(): ProfileDao
}