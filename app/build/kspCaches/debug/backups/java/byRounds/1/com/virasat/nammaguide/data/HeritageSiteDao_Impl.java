package com.virasat.nammaguide.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class HeritageSiteDao_Impl implements HeritageSiteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<HeritageSite> __insertionAdapterOfHeritageSite;

  public HeritageSiteDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHeritageSite = new EntityInsertionAdapter<HeritageSite>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `heritage_sites` (`id`,`nameEn`,`nameKn`,`descriptionEn`,`descriptionKn`,`latitude`,`longitude`,`hiddenFactEn`,`hiddenFactKn`,`audioResId`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final HeritageSite entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getNameEn());
        statement.bindString(3, entity.getNameKn());
        statement.bindString(4, entity.getDescriptionEn());
        statement.bindString(5, entity.getDescriptionKn());
        statement.bindDouble(6, entity.getLatitude());
        statement.bindDouble(7, entity.getLongitude());
        statement.bindString(8, entity.getHiddenFactEn());
        statement.bindString(9, entity.getHiddenFactKn());
        if (entity.getAudioResId() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getAudioResId());
        }
      }
    };
  }

  @Override
  public Object insertSite(final HeritageSite site, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfHeritageSite.insert(site);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAllSites(final List<HeritageSite> sites,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfHeritageSite.insert(sites);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<HeritageSite>> getAllSites() {
    final String _sql = "SELECT * FROM heritage_sites";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"heritage_sites"}, new Callable<List<HeritageSite>>() {
      @Override
      @NonNull
      public List<HeritageSite> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNameEn = CursorUtil.getColumnIndexOrThrow(_cursor, "nameEn");
          final int _cursorIndexOfNameKn = CursorUtil.getColumnIndexOrThrow(_cursor, "nameKn");
          final int _cursorIndexOfDescriptionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionEn");
          final int _cursorIndexOfDescriptionKn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionKn");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfHiddenFactEn = CursorUtil.getColumnIndexOrThrow(_cursor, "hiddenFactEn");
          final int _cursorIndexOfHiddenFactKn = CursorUtil.getColumnIndexOrThrow(_cursor, "hiddenFactKn");
          final int _cursorIndexOfAudioResId = CursorUtil.getColumnIndexOrThrow(_cursor, "audioResId");
          final List<HeritageSite> _result = new ArrayList<HeritageSite>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HeritageSite _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpNameEn;
            _tmpNameEn = _cursor.getString(_cursorIndexOfNameEn);
            final String _tmpNameKn;
            _tmpNameKn = _cursor.getString(_cursorIndexOfNameKn);
            final String _tmpDescriptionEn;
            _tmpDescriptionEn = _cursor.getString(_cursorIndexOfDescriptionEn);
            final String _tmpDescriptionKn;
            _tmpDescriptionKn = _cursor.getString(_cursorIndexOfDescriptionKn);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final String _tmpHiddenFactEn;
            _tmpHiddenFactEn = _cursor.getString(_cursorIndexOfHiddenFactEn);
            final String _tmpHiddenFactKn;
            _tmpHiddenFactKn = _cursor.getString(_cursorIndexOfHiddenFactKn);
            final Integer _tmpAudioResId;
            if (_cursor.isNull(_cursorIndexOfAudioResId)) {
              _tmpAudioResId = null;
            } else {
              _tmpAudioResId = _cursor.getInt(_cursorIndexOfAudioResId);
            }
            _item = new HeritageSite(_tmpId,_tmpNameEn,_tmpNameKn,_tmpDescriptionEn,_tmpDescriptionKn,_tmpLatitude,_tmpLongitude,_tmpHiddenFactEn,_tmpHiddenFactKn,_tmpAudioResId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getSiteById(final String siteId,
      final Continuation<? super HeritageSite> $completion) {
    final String _sql = "SELECT * FROM heritage_sites WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, siteId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<HeritageSite>() {
      @Override
      @Nullable
      public HeritageSite call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNameEn = CursorUtil.getColumnIndexOrThrow(_cursor, "nameEn");
          final int _cursorIndexOfNameKn = CursorUtil.getColumnIndexOrThrow(_cursor, "nameKn");
          final int _cursorIndexOfDescriptionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionEn");
          final int _cursorIndexOfDescriptionKn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionKn");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfHiddenFactEn = CursorUtil.getColumnIndexOrThrow(_cursor, "hiddenFactEn");
          final int _cursorIndexOfHiddenFactKn = CursorUtil.getColumnIndexOrThrow(_cursor, "hiddenFactKn");
          final int _cursorIndexOfAudioResId = CursorUtil.getColumnIndexOrThrow(_cursor, "audioResId");
          final HeritageSite _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpNameEn;
            _tmpNameEn = _cursor.getString(_cursorIndexOfNameEn);
            final String _tmpNameKn;
            _tmpNameKn = _cursor.getString(_cursorIndexOfNameKn);
            final String _tmpDescriptionEn;
            _tmpDescriptionEn = _cursor.getString(_cursorIndexOfDescriptionEn);
            final String _tmpDescriptionKn;
            _tmpDescriptionKn = _cursor.getString(_cursorIndexOfDescriptionKn);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final String _tmpHiddenFactEn;
            _tmpHiddenFactEn = _cursor.getString(_cursorIndexOfHiddenFactEn);
            final String _tmpHiddenFactKn;
            _tmpHiddenFactKn = _cursor.getString(_cursorIndexOfHiddenFactKn);
            final Integer _tmpAudioResId;
            if (_cursor.isNull(_cursorIndexOfAudioResId)) {
              _tmpAudioResId = null;
            } else {
              _tmpAudioResId = _cursor.getInt(_cursorIndexOfAudioResId);
            }
            _result = new HeritageSite(_tmpId,_tmpNameEn,_tmpNameKn,_tmpDescriptionEn,_tmpDescriptionKn,_tmpLatitude,_tmpLongitude,_tmpHiddenFactEn,_tmpHiddenFactKn,_tmpAudioResId);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
