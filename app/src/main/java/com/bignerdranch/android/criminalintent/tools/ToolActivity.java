package com.bignerdranch.android.criminalintent.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.bignerdranch.android.criminalintent.R;

import java.util.UUID;


public class ToolActivity extends AppCompatActivity {
    /*private static final String EXTRA_CRIME_ID = "com.bignerdranch.android.criminalintent.crime_id";

    private TextView mTitle;
    private TextView mToolCount;

    private Tool mTool;

    public static Intent newIntent(Context packageContext, UUID crimeId){
        Intent intent = new Intent(packageContext, ToolActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tool_activity);

        //это важный кусок
        //не удалять

        /*mTool = ToolLab.get(this).getTools();
        mTitle = (TextView) findViewById(R.id.tool_name);
        mTitle.setText(mTool.getToolName());
        mToolCount = (TextView) findViewById(R.id.tool_count);
        mToolCount.setText(mTool.getCount());
    }*/
}
