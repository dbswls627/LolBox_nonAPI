package com.lolbox.lolbox

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
    data class Box(
        @PrimaryKey
        var p:Int,
        var n:Int,
        var time:Long?,
        var b:Boolean
    )
    