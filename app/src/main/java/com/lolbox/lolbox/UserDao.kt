package com.lolbox.lolbox

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getAll():List<User>
    @Query("SELECT * FROM User ORDER BY name")
    fun getSort():List<User>
    @Query("SELECT name FROM User WHERE save")
    fun getSave():List<String>
    @Query("SELECT name FROM User")
    fun getName():List<String>
    @Query("select * FROM User WHERE save")
    fun getSaveList():List<User>
    @Query("SELECT * FROM User WHERE name in  (:name)")
    fun getU(name : String):User
    @Insert(onConflict = REPLACE)
    fun insert(users: User)
    @Delete
    fun delete(user: User)
    @Update
    fun upadte(user: User)

    }
    