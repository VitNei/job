package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    private Button mButtonTasks;
    private Button mButtonCompleteTasks;
    private Button mButtonTools;
    private Button mButtonPlastins;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.first_activity);

        mButtonTasks = (Button) findViewById(R.id.tasksList);
        mButtonTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mButtonCompleteTasks = (Button) findViewById(R.id.completeTasks);
        mButtonCompleteTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mButtonTools = (Button) findViewById(R.id.tools);
        mButtonTools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mButtonPlastins = (Button) findViewById(R.id.plastin);
        mButtonPlastins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        /*setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null){
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }*/
    }

    protected abstract Fragment createTask();
}
