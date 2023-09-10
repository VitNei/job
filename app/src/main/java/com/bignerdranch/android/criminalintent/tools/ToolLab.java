package com.bignerdranch.android.criminalintent.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.bignerdranch.android.criminalintent.CrimeCursorWrapper;
import com.bignerdranch.android.criminalintent.CrimeDbSchema;
import com.bignerdranch.android.criminalintent.database.CrimeBaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ToolLab {
    private static ToolLab sToolLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static ToolLab get(Context context) {
        if (sToolLab == null){
            sToolLab = new ToolLab(context);
        }
        return sToolLab;
    }

    private ToolLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
    }

    public void addTool(Tool c){
        ContentValues values = getContentValues(c);
        mDatabase.insert(CrimeDbSchema.ToolTable.TOOL, null, values);
    }

    /*public void deleteCrime(Crime crime){
        mDatabase.delete(CrimeTable.NAME_JOB, CrimeTable.Cols.DATE + "=" + crime.getDate().getTime(), null);
    }*/

    public List<Tool> getTools(){
        List<Tool> tools = new ArrayList<>();

        ToolCursorWrapper cursor = queryTools(null,null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                tools.add(cursor.getTool());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return tools;
    }

    public Tool getTool(UUID id){
        ToolCursorWrapper cursor = queryTools(CrimeDbSchema.ToolTable.Cols.UUID_TOOL + " = ?", new String[] {id.toString()} );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getTool();
        } finally {
            cursor.close();
        }
    }

    /*public File getPhotoFile(Crime crime){
        File filesDir = mContext.getFilesDir();
        return new File(filesDir, crime.getPhotoFilename());
        //if (filesDir == null){}
    }*/

    public void updateTool(Tool tool){
        String uuidString = tool.getId().toString();
        ContentValues values = getContentValues(tool);

        mDatabase.update(CrimeDbSchema.ToolTable.TOOL, values, CrimeDbSchema.ToolTable.Cols.UUID_TOOL + " = ?", new String[]{uuidString});
    }

    private ToolCursorWrapper queryTools(String whereClase, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                CrimeDbSchema.ToolTable.TOOL,
                null,
                whereClase,
                whereArgs,
                null,
                null,
                null
        );
        return new ToolCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Tool tool){
        ContentValues values = new ContentValues();
        values.put(CrimeDbSchema.ToolTable.Cols.UUID_TOOL, tool.getId().toString());
        values.put(CrimeDbSchema.ToolTable.Cols.TOOL_NAME, tool.getToolName());
        values.put(CrimeDbSchema.ToolTable.Cols.TOOL_COUNT, tool.getCount());

        return values;
    }
}
