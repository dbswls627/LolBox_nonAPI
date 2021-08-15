package com.lolbox.lolbox

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
    data class User(
    var img: Int,
    @PrimaryKey
    var name: String,
    var box: Boolean?,
    var save: Boolean
    )
    