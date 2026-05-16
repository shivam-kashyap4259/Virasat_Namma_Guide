package com.virasat.nammaguide.repository

import com.virasat.nammaguide.data.CheckIn
import com.virasat.nammaguide.data.CheckInDao
import com.virasat.nammaguide.data.HeritageSite
import com.virasat.nammaguide.data.HeritageSiteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class AppRepository(
    private val heritageSiteDao: HeritageSiteDao,
    private val checkInDao: CheckInDao
) {

    fun getAllSites(): Flow<List<HeritageSite>> = heritageSiteDao.getAllSites()

    suspend fun getSiteById(siteId: String): HeritageSite? =
        withContext(Dispatchers.IO) {
            heritageSiteDao.getSiteById(siteId)
        }

    fun getAllCheckIns(): Flow<List<CheckIn>> = checkInDao.getAllCheckIns()

    suspend fun getCheckInBySiteId(siteId: String): CheckIn? =
        withContext(Dispatchers.IO) {
            checkInDao.getCheckInBySiteId(siteId)
        }

    suspend fun insertCheckIn(checkIn: CheckIn) =
        withContext(Dispatchers.IO) {
            checkInDao.insertCheckIn(checkIn)
        }
}
