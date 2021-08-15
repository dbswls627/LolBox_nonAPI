package com.lolbox.lolbox

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(User::class,Box::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun boxDao(): BoxDao
    }
    