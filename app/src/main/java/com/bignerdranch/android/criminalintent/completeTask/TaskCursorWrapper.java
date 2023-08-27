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
        String uuidString = getString(getColumnIndex(CrimeDbSchema.TableCompleted.Cols.UUID_TABLE_COMPLETED));
        String title = getString(getColumnIndex(CrimeDbSchema.TableCompleted.Cols.TITLE_TABLE_COMPLETED));
        String note = getString(getColumnIndex(CrimeDbSchema.TableCompleted.Cols.NOTE_TABLE_COMPLETED));
        long date = getLong(getColumnIndex(CrimeDbSchema.TableCompleted.Cols.DATE_CREATE_TABLE_COMPLETED));
        long dateChange = getLong(getColumnIndex(CrimeDbSchema.TableCompleted.Cols.DATE_FINISH_TABLE_COMPLETED));


        Task task = new Task(UUID.fromString(uuidString));
        task.setTitle(title);
        task.setNote(note);
        task.setDate(new Date(date));
        task.setDateChange(new Date(dateChange));


        return task;
    }
}
