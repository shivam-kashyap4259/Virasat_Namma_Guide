package com.virasat.nammaguide.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.virasat.nammaguide.data.AppDatabase
import com.virasat.nammaguide.data.CheckIn
import com.virasat.nammaguide.data.HeritageSite
import com.virasat.nammaguide.repository.AppRepository
import kotlinx.coroutines.launch

class AppViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AppRepository
    val allSites: LiveData<List<HeritageSite>>
    val allCheckIns: LiveData<List<CheckIn>>

    init {
        val db = AppDatabase.getDatabase(application, viewModelScope)
        repository = AppRepository(db.heritageSiteDao(), db.checkInDao())
        allSites = repository.getAllSites().asLiveData()
        allCheckIns = repository.getAllCheckIns().asLiveData()
    }

    suspend fun getSiteById(siteId: String): HeritageSite? = repository.getSiteById(siteId)

    suspend fun getCheckInBySiteId(siteId: String): CheckIn? =
        repository.getCheckInBySiteId(siteId)

    fun insertCheckIn(checkIn: CheckIn) = viewModelScope.launch {
        repository.insertCheckIn(checkIn)
    }
}
