package com.bignerdranch.android.criminalintent.completeTask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.bignerdranch.android.criminalintent.Crime;
import com.bignerdranch.android.criminalintent.CrimeDbSchema;
import com.bignerdranch.android.criminalintent.CrimeLab;
import com.bignerdranch.android.criminalintent.database.CrimeBaseHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OldTaskLab {
    private static OldTaskLab sOldTaskLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static OldTaskLab get(Context context) {
        if (sOldTaskLab == null){
            sOldTaskLab = new OldTaskLab(context);
        }
        return sOldTaskLab;
    }

    private OldTaskLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
    }

    public void deleteCrime(Task task){
        mDatabase.delete(CrimeDbSchema.TableCompleted.NAME_TABLE_COMPLETED, CrimeDbSchema.TableCompleted.Cols.UUID_TABLE_COMPLETED + "=" + task.getId(), null);
    }

    //нужны методы addCrime deleteCrime updateCrime CrimeCursorWrapper
    //нужны методы addCrime deleteCrime updateCrime CrimeCursorWrapper
    //нужны методы addCrime deleteCrime updateCrime CrimeCursorWrapper
    //нужны методы addCrime deleteCrime updateCrime CrimeCursorWrapper
    //нужны методы addCrime deleteCrime updateCrime CrimeCursorWrapper
    //нужны методы addCrime deleteCrime updateCrime CrimeCursorWrapper
    //нужны методы addCrime deleteCrime updateCrime CrimeCursorWrapper
    //нужны методы addCrime deleteCrime updateCrime CrimeCursorWrapper
    //нужны методы addCrime deleteCrime updateCrime CrimeCursorWrapper
    //нужны методы addCrime deleteCrime updateCrime CrimeCursorWrapper
    //нужны методы addCrime deleteCrime updateCrime CrimeCursorWrapper
    //нужны методы addCrime deleteCrime updateCrime CrimeCursorWrapper
    //нужны методы addCrime deleteCrime updateCrime CrimeCursorWrapper
    //нужны методы addCrime deleteCrime updateCrime CrimeCursorWrapper
    //для таблицы завершённых дел

    public List<Task> getTasks(){
        List<Task> crimes = new ArrayList<>();

        TaskCursorWrapper cursor = queryTasks(null,null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                crimes.add(cursor.getTask());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return crimes;
    }

    public Task getTask(UUID id){

        TaskCursorWrapper cursor = queryTasks(
                CrimeDbSchema.TableCompleted.Cols.UUID_TABLE_COMPLETED + " = ?",
                new String[] { id.toString() }
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getTask();
        } finally {
            cursor.close();
        }
    }

    public void addCompleteTask(Crime c){
        ContentValues values = getContentValuesForCompletedTable(c);

        mDatabase.insert(CrimeDbSchema.TableCompleted.NAME_TABLE_COMPLETED, null, values);
    }

    private TaskCursorWrapper queryTasks(String whereClase, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                CrimeDbSchema.TableCompleted.NAME_TABLE_COMPLETED,
                null,
                whereClase,
                whereArgs,
                null,
                null,
                null
        );
        return new TaskCursorWrapper(cursor);
    }

    //кто знает кто знает

    public static ContentValues getContentValuesForCompletedTable(Crime crime){
        ContentValues values = new ContentValues();
        values.put(CrimeDbSchema.TableCompleted.Cols.UUID_TABLE_COMPLETED, crime.getId().toString());
        values.put(CrimeDbSchema.TableCompleted.Cols.TITLE_TABLE_COMPLETED, crime.getTitle());
        values.put(CrimeDbSchema.TableCompleted.Cols.NOTE_TABLE_COMPLETED, crime.getNote());
        values.put(CrimeDbSchema.TableCompleted.Cols.DATE_CREATE_TABLE_COMPLETED, crime.getDate().getTime());
        values.put(CrimeDbSchema.TableCompleted.Cols.DATE_FINISH_TABLE_COMPLETED, crime.getDateChange().getTime());

        return values;
    }
}
