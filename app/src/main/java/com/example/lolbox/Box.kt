package com.example.lolbox

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
    data class Box(
        @PrimaryKey
        var p:Int,
        var n:Int,
        var time:Long?
    )
    