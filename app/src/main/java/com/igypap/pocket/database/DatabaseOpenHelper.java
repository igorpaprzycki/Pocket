package com.igypap.pocket.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.igypap.pocket.model.Link;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by igypap on 08.01.17.
 */

public class DatabaseOpenHelper extends OrmLiteSqliteOpenHelper {

    public static final String DATABASE_NAME = "links.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,Link.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
