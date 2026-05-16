package com.virasat.nammaguide.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CheckInDao {
    @Query("SELECT * FROM check_ins")
    fun getAllCheckIns(): Flow<List<CheckIn>>

    @Query("SELECT * FROM check_ins WHERE siteId = :siteId")
    suspend fun getCheckInBySiteId(siteId: String): CheckIn?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCheckIn(checkIn: CheckIn)

    @Query("DELETE FROM check_ins WHERE siteId = :siteId")
    suspend fun deleteCheckIn(siteId: String)
}
