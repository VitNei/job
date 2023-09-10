package com.bignerdranch.android.criminalintent.tools;

import android.support.v4.app.Fragment;
import com.bignerdranch.android.criminalintent.SingleFragmentActivity;

public class ToolListActivity extends SingleToolFragmentActivity {

    @Override
    protected Fragment createTool() {
        return new ToolListFragment();
    }
}