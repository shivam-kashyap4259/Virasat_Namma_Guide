package com.virasat.nammaguide.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "check_ins")
data class CheckIn(
    @PrimaryKey val siteId: String,
    val checkInTime: Long = System.currentTimeMillis()
)
