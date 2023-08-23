package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;

public class TaskActivity extends SingleFragmentActivity{

        @Override
        protected Fragment createTask() {
            return new CrimeListFragment();
        }
}
