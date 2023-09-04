package com.bignerdranch.android.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.bignerdranch.android.criminalintent.completeTask.CompleteTaskListActivity;
import com.bignerdranch.android.criminalintent.tools.ToolListActivity;

public class CrimeListActivity extends AppCompatActivity {
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
                Intent intent = new Intent(CrimeListActivity.this, TaskActivity.class);
                startActivity(intent);
            }
        });

        mButtonCompleteTasks = (Button) findViewById(R.id.completeTasks);
        mButtonCompleteTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrimeListActivity.this, CompleteTaskListActivity.class);
                startActivity(intent);
            }
        });

        mButtonTools = (Button) findViewById(R.id.tools);
        mButtonTools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrimeListActivity.this, ToolListActivity.class);
                startActivity(intent);
            }
        });

        mButtonPlastins = (Button) findViewById(R.id.plastin);
        mButtonPlastins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
