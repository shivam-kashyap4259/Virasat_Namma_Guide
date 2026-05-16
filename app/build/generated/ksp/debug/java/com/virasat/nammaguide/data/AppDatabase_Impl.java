package com.virasat.nammaguide.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile HeritageSiteDao _heritageSiteDao;

  private volatile CheckInDao _checkInDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `heritage_sites` (`id` TEXT NOT NULL, `nameEn` TEXT NOT NULL, `nameKn` TEXT NOT NULL, `descriptionEn` TEXT NOT NULL, `descriptionKn` TEXT NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `hiddenFactEn` TEXT NOT NULL, `hiddenFactKn` TEXT NOT NULL, `audioResId` INTEGER, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `check_ins` (`siteId` TEXT NOT NULL, `checkInTime` INTEGER NOT NULL, PRIMARY KEY(`siteId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '25c81e7b28aa06a0b74c47dcaba0912f')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `heritage_sites`");
        db.execSQL("DROP TABLE IF EXISTS `check_ins`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsHeritageSites = new HashMap<String, TableInfo.Column>(10);
        _columnsHeritageSites.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHeritageSites.put("nameEn", new TableInfo.Column("nameEn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHeritageSites.put("nameKn", new TableInfo.Column("nameKn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHeritageSites.put("descriptionEn", new TableInfo.Column("descriptionEn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHeritageSites.put("descriptionKn", new TableInfo.Column("descriptionKn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHeritageSites.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHeritageSites.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHeritageSites.put("hiddenFactEn", new TableInfo.Column("hiddenFactEn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHeritageSites.put("hiddenFactKn", new TableInfo.Column("hiddenFactKn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHeritageSites.put("audioResId", new TableInfo.Column("audioResId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysHeritageSites = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesHeritageSites = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoHeritageSites = new TableInfo("heritage_sites", _columnsHeritageSites, _foreignKeysHeritageSites, _indicesHeritageSites);
        final TableInfo _existingHeritageSites = TableInfo.read(db, "heritage_sites");
        if (!_infoHeritageSites.equals(_existingHeritageSites)) {
          return new RoomOpenHelper.ValidationResult(false, "heritage_sites(com.virasat.nammaguide.data.HeritageSite).\n"
                  + " Expected:\n" + _infoHeritageSites + "\n"
                  + " Found:\n" + _existingHeritageSites);
        }
        final HashMap<String, TableInfo.Column> _columnsCheckIns = new HashMap<String, TableInfo.Column>(2);
        _columnsCheckIns.put("siteId", new TableInfo.Column("siteId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCheckIns.put("checkInTime", new TableInfo.Column("checkInTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCheckIns = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCheckIns = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCheckIns = new TableInfo("check_ins", _columnsCheckIns, _foreignKeysCheckIns, _indicesCheckIns);
        final TableInfo _existingCheckIns = TableInfo.read(db, "check_ins");
        if (!_infoCheckIns.equals(_existingCheckIns)) {
          return new RoomOpenHelper.ValidationResult(false, "check_ins(com.virasat.nammaguide.data.CheckIn).\n"
                  + " Expected:\n" + _infoCheckIns + "\n"
                  + " Found:\n" + _existingCheckIns);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "25c81e7b28aa06a0b74c47dcaba0912f", "ff7f049461402ccf792143af72ad8488");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "heritage_sites","check_ins");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `heritage_sites`");
      _db.execSQL("DELETE FROM `check_ins`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(HeritageSiteDao.class, HeritageSiteDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CheckInDao.class, CheckInDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public HeritageSiteDao heritageSiteDao() {
    if (_heritageSiteDao != null) {
      return _heritageSiteDao;
    } else {
      synchronized(this) {
        if(_heritageSiteDao == null) {
          _heritageSiteDao = new HeritageSiteDao_Impl(this);
        }
        return _heritageSiteDao;
      }
    }
  }

  @Override
  public CheckInDao checkInDao() {
    if (_checkInDao != null) {
      return _checkInDao;
    } else {
      synchronized(this) {
        if(_checkInDao == null) {
          _checkInDao = new CheckInDao_Impl(this);
        }
        return _checkInDao;
      }
    }
  }
}
