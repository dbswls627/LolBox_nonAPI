package com.example.lolbox

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getAll():List<User>
    @Insert(onConflict = REPLACE)
    fun insert(users: User)
    @Delete
    fun delete(user: User)
    @Update
    fun upadte(user: User)
    }
    