package com.bignerdranch.android.criminalintent.tools;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.bignerdranch.android.criminalintent.CrimeDbSchema;

import java.util.UUID;

public class ToolCursorWrapper extends CursorWrapper {

    public ToolCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Tool getTool() {
        String uuidSring = getString(getColumnIndex(CrimeDbSchema.ToolTable.Cols.UUID_TOOL));
        String toolName = getString(getColumnIndex(CrimeDbSchema.ToolTable.Cols.TOOL_NAME));
        int toolCount = getInt(getColumnIndex(CrimeDbSchema.ToolTable.Cols.TOOL_COUNT));


        Tool tool = new Tool(UUID.fromString(uuidSring));
        //Tool tool = new Tool(UUID.randomUUID());
        tool.setToolName(toolName);
        tool.setCount(toolCount);

        return tool;
    }
}
