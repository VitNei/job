package com.bignerdranch.android.criminalintent;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

public class CrimeCursorWrapper extends CursorWrapper {

    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime() {
        String uuidSring = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.UUID));
        String title = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.TITLE));
        String note = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.NOTE));
        long date = getLong(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.DATE));
        long dateChange = getLong(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.DATE_CHANGE));
        int prioritet = getInt(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.PRIORITET));
        int progress = getInt(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.PROGRESS));

        int isSolved = getInt(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.SOLVED));
        String suspect = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.SUSPECT));

        Crime crime = new Crime(UUID.fromString(uuidSring));
        crime.setTitle(title);
        crime.setNote(note);
        crime.setDate(new Date(date));
        crime.setDateChange(new Date(dateChange));
        crime.setPrioritet(prioritet);
        crime.setProgress(progress);

        crime.setSolved(isSolved != 0);
        crime.setSuspect(suspect);

        return crime;
    }
}
