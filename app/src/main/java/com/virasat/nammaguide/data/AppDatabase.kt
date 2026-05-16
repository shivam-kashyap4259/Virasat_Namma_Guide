package com.virasat.nammaguide.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [HeritageSite::class, CheckIn::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun heritageSiteDao(): HeritageSiteDao
    abstract fun checkInDao(): CheckInDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "virasat_database"
                )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        scope.launch(Dispatchers.IO) {
                            INSTANCE?.heritageSiteDao()?.let { populateDatabase(it) }
                        }
                    }
                })
                .build()
                INSTANCE = instance
                // Also try populating if it's already created but somehow empty
                scope.launch(Dispatchers.IO) {
                    val dao = instance.heritageSiteDao()
                    if (dao.getSiteById("hampi1") == null) {
                        populateDatabase(dao)
                    }
                }
                instance
            }
        }

        private suspend fun populateDatabase(dao: HeritageSiteDao) {
            val sites = listOf(
                HeritageSite(
                    id = "hampi1",
                    nameEn = "Hampi - Vittala Temple",
                    nameKn = "ಹಂಪಿ - ವಿಟ್ಟಲ ದೇವಸ್ಥಾನ",
                    descriptionEn = "The Vittala Temple is one of the largest and most famous structures in Hampi. It was built in the 15th century during the reign of King Devaraya II.",
                    descriptionKn = "ವಿಟ್ಟಲ ದೇವಸ್ಥಾನವು ಹಂಪಿಯಲ್ಲಿನ ಅತಿದೊಡ್ಡ ಮತ್ತು ಪ್ರಸಿದ್ಧ ಕಟ್ಟಡಗಳಲ್ಲಿ ಒಂದಾಗಿದೆ. ಇದನ್ನು 15ನೇ ಶತಮಾನದಲ್ಲಿ ರಾಜ ದೇವರಾಯ II ನ ಆಡಳಿತದಲ್ಲಿ ನಿರ್ಮಿಸಲಾಗಿದೆ.",
                    latitude = 15.3350,
                    longitude = 76.4600,
                    hiddenFactEn = "The stone chariot in Vittala Temple was originally a shrine dedicated to Garuda, the vehicle of Lord Vishnu.",
                    hiddenFactKn = "ವಿಟ್ಟಲ ದೇವಸ್ಥಾನದ ಕಲ್ಲಿನ ರಥವನ್ನು ಮೂಲತಃ ವಿಷ್ಣು ಭಗವಂತನ ವಾಹನವಾದ ಗರುಡನಿಗೆ ಅರ್ಪಿಸಲಾದ ದೇವಸ್ಥಾನವಾಗಿತ್ತು.",
                    audioResId = null
                ),
                HeritageSite(
                    id = "mysore1",
                    nameEn = "Mysore Palace",
                    nameKn = "ಮೈಸೂರು ಅರಮನೆ",
                    descriptionEn = "The Mysore Palace is a historical palace and a royal residence at Mysore in India. It is the official residence of the Wadiyar dynasty.",
                    descriptionKn = "ಮೈಸೂರು ಅರಮನೆಯು ಭಾರತದ ಮೈಸೂರಿನಲ್ಲಿನ ಐತಿಹಾಸಿಕ ಅರಮನೆ ಮತ್ತು ರಾಯಭಾರದ ನಿವಾಸವಾಗಿದೆ. ಇದು ವಾಡಿಯಾರ ವಂಶದ ಆಧಿಕಾರಿಕ ನಿವಾಸವಾಗಿದೆ.",
                    latitude = 12.3051,
                    longitude = 76.6552,
                    hiddenFactEn = "The current Mysore Palace is the fourth structure to occupy the site.",
                    hiddenFactKn = "ಪ್ರಸ್ತುತ ಮೈಸೂರು ಅರಮನೆಯು ಈ ಸ್ಥಳವನ್ನು ಆಕ್ರಮಿಸಿಕೊಂಡ ನಾಲ್ಕನೆಯ ಕಟ್ಟಡವಾಗಿದೆ.",
                    audioResId = null
                ),
                HeritageSite(
                    id = "belur1",
                    nameEn = "Belur - Chennakeshava Temple",
                    nameKn = "ಬೇಳೂರು - ಚೆನ್ನಕೇಶವ ದೇವಸ್ಥಾನ",
                    descriptionEn = "Chennakeshava Temple is a 12th-century Hindu temple in Hassan, Karnataka, India built by the Hoysala king Vishnuvardhana.",
                    descriptionKn = "ಚೆನ್ನಕೇಶವ ದೇವಸ್ಥಾನವು ಭಾರತದ ಕರ್ನಾಟಕದ ಹಾಸನದಲ್ಲಿನ 12ನೇ ಶತಮಾನದ ಹಿಂದೂ ದೇವಸ್ಥಾನವಾಗಿದ್ದು, ಹೊಯ್ಸಳ ರಾಜ ವಿಷ್ಣುವರ್ಧನನಿಂದ ನಿರ್ಮಿಸಲಾಗಿದೆ.",
                    latitude = 13.1600,
                    longitude = 75.8500,
                    hiddenFactEn = "The temple has 48 pillars, each carved with intricate designs and stories from Hindu mythology.",
                    hiddenFactKn = "ದೇವಸ್ಥಾನವು 48 ಸ್ತಂಭಗಳನ್ನು ಹೊಂದಿದೆ, ಪ್ರತಿಯೊಂದು ಸ್ತಂಭವು ಸಂಕೀರ್ಣ ವಿನ್ಯಾಸಗಳು ಮತ್ತು ಹಿಂದೂ ಪುರಾಣಗಳ ಕಥೆಗಳಿಂದ ಕೆತ್ತಲಾಗಿದೆ.",
                    audioResId = null
                ),
                HeritageSite(
                    id = "halebidu1",
                    nameEn = "Halebidu - Hoysaleswara Temple",
                    nameKn = "ಹಾಳೇಬೀಡು - ಹೊಯ್ಸಳೇಶ್ವರ ದೇವಸ್ಥಾನ",
                    descriptionEn = "Hoysaleswara Temple is a 12th-century Hindu temple dedicated to Shiva. It is located in Halebidu, Karnataka.",
                    descriptionKn = "ಹೊಯ್ಸಳೇಶ್ವರ ದೇವಸ್ಥಾನವು ಶಿವನಿಗೆ ಅರ್ಪಿಸಲಾದ 12ನೇ ಶತಮಾನದ ಹಿಂದೂ ದೇವಸ್ಥಾನವಾಗಿದೆ. ಇದು ಕರ್ನಾಟಕದ ಹಾಳೇಬೀಡುವಲ್ಲಿ ಸ್ಥಿತವಾಗಿದೆ.",
                    latitude = 13.2100,
                    longitude = 75.9800,
                    hiddenFactEn = "The temple walls are covered with more than 30,000 sculptures depicting scenes from epics, animals, and daily life.",
                    hiddenFactKn = "ದೇವಸ್ಥಾನದ ಗೋಡೆಗಳು ಇತಿಹಾಸಗಳು, ಪ್ರಾಣಿಗಳು ಮತ್ತು ದೈನಂದಿನ ಜೀವನದ ದೃಶ್ಯಗಳನ್ನು ಚಿತ್ರಿಸುವ 30,000 ಕ್ಕೂ ಹೆಚ್ಚಿನ ಶಿಲ್ಪಗಳಿಂದ ಆವರಿಸಲ್ಪಟ್ಟಿವೆ.",
                    audioResId = null
                ),
                HeritageSite(
                    id = "pattadakal1",
                    nameEn = "Pattadakal - Group of Monuments",
                    nameKn = "ಪಟ್ಟದಕಲ್ - ಸ್ಮಾರಕಗಳ ಗುಂಪು",
                    descriptionEn = "Pattadakal is a UNESCO World Heritage site and a complex of 7th and 8th-century Hindu and Jain temples.",
                    descriptionKn = "ಪಟ್ಟದಕಲ್ ಯುನೆಸ್ಕೋ ವಿಶ್ವ ಪಾರಂಪರಿಕ ಸ್ಥಳವಾಗಿದೆ ಮತ್ತು 7ನೇ ಮತ್ತು 8ನೇ ಶತಮಾನದ ಹಿಂದೂ ಮತ್ತು ಜೈನ ದೇವಸ್ಥಾನಗಳ ಸಂಕೀರ್ಣವಾಗಿದೆ.",
                    latitude = 15.9470,
                    longitude = 75.8160,
                    hiddenFactEn = "Pattadakal was the place where the coronation of Chalukyan kings took place.",
                    hiddenFactKn = "ಚಾಲುಕ್ಯ ರಾಜರರ ಅಭಿಷೇಕ ನಡೆಯುತ್ತಿದ್ದ ಸ್ಥಳವು ಪಟ್ಟದಕಲ್.",
                    audioResId = null
                )
            )
            dao.insertAllSites(sites)
        }
    }
}
