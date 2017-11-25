package de.slg.leoappabi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLiteManager.
 *
 * Verwaltet die lokale Zitat-Datenbank.
 *
 * @author Gianni
 * @version 2017.2411
 * @since 0.0.1
 */

public class SQLiteManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "citations.db";

    private static final String TABLE_ENTRIES = "entries";
    private static final String ENTRIES_ID    = "id";
    private static final String ENTRIES_DATUM = "date";
    private static final String ENTRIES_TEXT  = "content";

    public SQLiteManager(Context c) {
        super(c, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_ENTRIES + " (" +
                ENTRIES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                ENTRIES_TEXT + " TEXT NOT NULL, " +
                ENTRIES_DATUM + " TEXT NOT NULL" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
