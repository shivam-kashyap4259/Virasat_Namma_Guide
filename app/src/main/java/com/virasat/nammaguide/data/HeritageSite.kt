package com.virasat.nammaguide.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "heritage_sites")
data class HeritageSite(
    @PrimaryKey val id: String,
    val nameEn: String,
    val nameKn: String,
    val descriptionEn: String,
    val descriptionKn: String,
    val latitude: Double,
    val longitude: Double,
    val hiddenFactEn: String,
    val hiddenFactKn: String,
    val audioResId: Int? = null
)
