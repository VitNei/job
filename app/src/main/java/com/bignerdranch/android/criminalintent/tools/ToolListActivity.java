package com.bignerdranch.android.criminalintent.tools;

import android.support.v4.app.Fragment;
import com.bignerdranch.android.criminalintent.completeTask.CompleteTaskListFragment;

public class ToolListActivity extends SingleToolsActivity {
    @Override
    protected Fragment createTool() {
        return new ToolListFragment();
    }
}