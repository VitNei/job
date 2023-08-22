package com.bignerdranch.android.criminalintent.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.bignerdranch.android.criminalintent.CrimeDbSchema.TableCompleted;
import com.bignerdranch.android.criminalintent.CrimeDbSchema.CrimeTable;

public class CrimeBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "crimeBase.db";

    public CrimeBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + CrimeTable.NAME_JOB + "(" +
                " _id integer primary key autoincrement, " +
                CrimeTable.Cols.UUID + ", " +
                CrimeTable.Cols.TITLE + ", " +
                CrimeTable.Cols.NOTE + ", " +
                CrimeTable.Cols.DATE + ", " +
                CrimeTable.Cols.DATE_CHANGE + ", " +
                CrimeTable.Cols.SOLVED + ", " +
                CrimeTable.Cols.SUSPECT + ", " +
                CrimeTable.Cols.PRIORITET + ", " +
                CrimeTable.Cols.PROGRESS +
                ")"
        );

        //suspect = подозреваемый. Нигде не используется, но пока не удалён, чтобы не ломать готовую бд. Потом почистить

        db.execSQL("create table " + TableCompleted.NAME_TABLE_COMPLETED + "(" +
                " _id integer primary key autoincrement, " +
                TableCompleted.Cols.UUID_TABLE_COMPLETED + ", " +
                TableCompleted.Cols.TITLE_TABLE_COMPLETED + ", " +
                TableCompleted.Cols.NOTE_TABLE_COMPLETED + ", " +
                TableCompleted.Cols.DATE_CREATE_TABLE_COMPLETED + ", " +
                TableCompleted.Cols.DATE_FINISH_TABLE_COMPLETED +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
