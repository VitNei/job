package com.bignerdranch.android.criminalintent.completeTask;

import android.support.v4.app.Fragment;

public class CompleteTaskListActivity extends SingleCompleteFragmentActivity {
    @Override
    protected Fragment createCompleteTask() {
        return new CompleteTaskListFragment();
    }
}
