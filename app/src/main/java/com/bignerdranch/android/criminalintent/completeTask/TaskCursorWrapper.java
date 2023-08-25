package com.bignerdranch.android.criminalintent.completeTask;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.bignerdranch.android.criminalintent.CrimeDbSchema;

import java.util.Date;
import java.util.UUID;

public class TaskCursorWrapper extends CursorWrapper {

    public TaskCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Task getTask() {
        String uuidSring = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.UUID));
        String title = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.TITLE));
        String note = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.NOTE));
        long date = getLong(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.DATE));
        long dateChange = getLong(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.DATE_CHANGE));


        Task crime = new Task(UUID.fromString(uuidSring));
        crime.setTitle(title);
        crime.setNote(note);
        crime.setDate(new Date(date));
        crime.setDateChange(new Date(dateChange));


        return crime;
    }
}
