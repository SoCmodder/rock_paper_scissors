package com.socmodder.android.rps.Database;

import android.R;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Miller
 * Date: 1/6/14
 * Time: 3:52 PM
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "rps.db";
    private static final int DATABASE_VERSION = 1;

    private RuntimeExceptionDao<Game, Integer> gameRuntimeDao = null;
    private Dao<Game, Integer> gameDao;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Game.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to create datbases", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {
        try {
            TableUtils.dropTable(connectionSource, Game.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to upgrade database from version " + oldVer + " to new "
                    + newVer, e);
        }
    }

    public Dao<Game, Integer> getDao() throws SQLException {
        if(gameDao == null){
            gameDao = getDao(Game.class);
        }
        return gameDao;
    }

    public RuntimeExceptionDao<Game, Integer> getGameDao(){
        if(gameRuntimeDao == null){
            gameRuntimeDao = getRuntimeExceptionDao(Game.class);
        }
        return gameRuntimeDao;
    }

    /*
        Close the connection to the database.
     */
    @Override
    public void close(){
        super.close();
        gameDao = null;
        gameRuntimeDao = null;
    }
}
