package com.virasat.nammaguide.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HeritageSiteDao {
    @Query("SELECT * FROM heritage_sites")
    fun getAllSites(): Flow<List<HeritageSite>>

    @Query("SELECT * FROM heritage_sites WHERE id = :siteId")
    suspend fun getSiteById(siteId: String): HeritageSite?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSite(site: HeritageSite)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSites(sites: List<HeritageSite>)
}
