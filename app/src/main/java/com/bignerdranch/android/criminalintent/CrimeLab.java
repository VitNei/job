package com.bignerdranch.android.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.bignerdranch.android.criminalintent.completeTask.OldTaskLab;
import com.bignerdranch.android.criminalintent.database.CrimeBaseHelper;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.bignerdranch.android.criminalintent.CrimeDbSchema.*;

public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
    }

    public void addCrime(Crime c){
        ContentValues values = getContentValues(c);

        mDatabase.insert(CrimeTable.NAME_JOB, null, values);
    }

    public void addCompleteTask(Crime c){
        ContentValues values = OldTaskLab.getContentValuesForCompletedTable(c);

        mDatabase.insert(CrimeDbSchema.TableCompleted.NAME_TABLE_COMPLETED, null, values);
    }

    public void deleteCrime(Crime crime){
        mDatabase.delete(CrimeTable.NAME_JOB, CrimeTable.Cols.DATE + "=" + crime.getDate().getTime(), null);
    }

    public List<Crime> getCrimes(){
        List<Crime> crimes = new ArrayList<>();

        CrimeCursorWrapper cursor = queryCrimes(null,null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return crimes;
    }

    public Crime getCrime(UUID id){
        CrimeCursorWrapper cursor = queryCrimes(CrimeTable.Cols.UUID + " = ?", new String[] {id.toString()} );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCrime();
        } finally {
            cursor.close();
        }
    }

    public File getPhotoFile(Crime crime){
        File filesDir = mContext.getFilesDir();
        return new File(filesDir, crime.getPhotoFilename());
        //if (filesDir == null){}
    }

    public void updateCrime(Crime crime){
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        mDatabase.update(CrimeTable.NAME_JOB, values, CrimeTable.Cols.UUID + " = ?", new String[]{uuidString});
    }

    private CrimeCursorWrapper queryCrimes(String whereClase, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                CrimeTable.NAME_JOB,
                null,
                whereClase,
                whereArgs,
                null,
                null,
                null
        );
        return new CrimeCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Crime crime){
        ContentValues values = new ContentValues();
        values.put(CrimeTable.Cols.UUID, crime.getId().toString());
        values.put(CrimeTable.Cols.TITLE, crime.getTitle());
        values.put(CrimeTable.Cols.NOTE, crime.getNote());
        values.put(CrimeTable.Cols.DATE, crime.getDate().getTime());
        values.put(CrimeTable.Cols.DATE_CHANGE, crime.getDateChange().getTime());
        values.put(CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);
        values.put(CrimeTable.Cols.SUSPECT, crime.getSuspect());
        values.put(CrimeTable.Cols.PRIORITET, crime.getPrioritet());
        values.put(CrimeTable.Cols.PROGRESS, crime.getProgress());

        return values;
    }


}
