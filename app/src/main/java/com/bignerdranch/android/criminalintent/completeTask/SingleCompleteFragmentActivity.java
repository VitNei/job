package com.bignerdranch.android.criminalintent.completeTask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.bignerdranch.android.criminalintent.R;


public abstract class SingleCompleteFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createCompleteTask();//+

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//+

        setContentView(R.layout.activity_fragment);//добавить complete_activity_fragment

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);//?????

        if(fragment == null){
            fragment = createCompleteTask();//+
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit(); //????
        }
    }
}
