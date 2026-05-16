package com.virasat.nammaguide.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
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
public final class CheckInDao_Impl implements CheckInDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CheckIn> __insertionAdapterOfCheckIn;

  private final SharedSQLiteStatement __preparedStmtOfDeleteCheckIn;

  public CheckInDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCheckIn = new EntityInsertionAdapter<CheckIn>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `check_ins` (`siteId`,`checkInTime`) VALUES (?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CheckIn entity) {
        statement.bindString(1, entity.getSiteId());
        statement.bindLong(2, entity.getCheckInTime());
      }
    };
    this.__preparedStmtOfDeleteCheckIn = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM check_ins WHERE siteId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertCheckIn(final CheckIn checkIn, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCheckIn.insert(checkIn);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteCheckIn(final String siteId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteCheckIn.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, siteId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteCheckIn.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<CheckIn>> getAllCheckIns() {
    final String _sql = "SELECT * FROM check_ins";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"check_ins"}, new Callable<List<CheckIn>>() {
      @Override
      @NonNull
      public List<CheckIn> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfSiteId = CursorUtil.getColumnIndexOrThrow(_cursor, "siteId");
          final int _cursorIndexOfCheckInTime = CursorUtil.getColumnIndexOrThrow(_cursor, "checkInTime");
          final List<CheckIn> _result = new ArrayList<CheckIn>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CheckIn _item;
            final String _tmpSiteId;
            _tmpSiteId = _cursor.getString(_cursorIndexOfSiteId);
            final long _tmpCheckInTime;
            _tmpCheckInTime = _cursor.getLong(_cursorIndexOfCheckInTime);
            _item = new CheckIn(_tmpSiteId,_tmpCheckInTime);
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
  public Object getCheckInBySiteId(final String siteId,
      final Continuation<? super CheckIn> $completion) {
    final String _sql = "SELECT * FROM check_ins WHERE siteId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, siteId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<CheckIn>() {
      @Override
      @Nullable
      public CheckIn call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfSiteId = CursorUtil.getColumnIndexOrThrow(_cursor, "siteId");
          final int _cursorIndexOfCheckInTime = CursorUtil.getColumnIndexOrThrow(_cursor, "checkInTime");
          final CheckIn _result;
          if (_cursor.moveToFirst()) {
            final String _tmpSiteId;
            _tmpSiteId = _cursor.getString(_cursorIndexOfSiteId);
            final long _tmpCheckInTime;
            _tmpCheckInTime = _cursor.getLong(_cursorIndexOfCheckInTime);
            _result = new CheckIn(_tmpSiteId,_tmpCheckInTime);
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
