package com.lolbox.lolbox

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface BoxDao {
 @Query("SELECT time FROM Box")
 fun getTime() : Long
 @Query("SELECT n FROM Box")
 fun getN() : Int
 @Query("SELECT b FROM Box")
 fun getB() : Boolean
 @Insert(onConflict = REPLACE)
 fun insert(box:Box)
 @Delete
 fun delete(box:Box)
 @Update
 fun upadte(box:Box)
}
    