package com.bignerdranch.android.criminalintent.tools;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.bignerdranch.android.criminalintent.CrimeDbSchema;
import com.bignerdranch.android.criminalintent.R;
import com.bignerdranch.android.criminalintent.database.CrimeBaseHelper;

import java.util.UUID;

public class AddToolActivity extends AppCompatActivity {
    /*private static final String EXTRA_TOOL_ID = "com.bignerdranch.android.criminalintent.crime_id";
    private static final String ARG_CRIME_ID = "tool_id";

    private EditText mTitle;
    private TextView mToolCount;
    private Button mSave;

    private Tool mTool;

    public static Intent newIntent(Context packageContext, Tool tool){
        Intent intent = new Intent(packageContext, ToolActivity.class);
        intent.putExtra(EXTRA_TOOL_ID, tool);
        return intent;
    }


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_tool_activity);

        Bundle arguments = getIntent().getExtras();
        mTool = (Tool) arguments.getSerializable(Tool.class.getSimpleName());

        mTitle = (EditText) findViewById(R.id.tool_title);
        mTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTool.setToolName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSave = (Button) findViewById(R.id.save_tool_button);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(CrimeDbSchema.ToolTable.Cols.UUID_TOOL, mTool.getId().toString());
                values.put(CrimeDbSchema.ToolTable.Cols.TOOL_NAME, mTool.getToolName());
                values.put(CrimeDbSchema.ToolTable.Cols.TOOL_COUNT, mTool.getCount());
                String uuidString = mTool.getId().toString();

                SQLiteDatabase mDatabase = new CrimeBaseHelper(getApplicationContext()).getWritableDatabase();
                mDatabase.update(CrimeDbSchema.ToolTable.TOOL, values, CrimeDbSchema.ToolTable.Cols.UUID_TOOL + " = ?", new String[]{uuidString});
                String s = mTool.getToolName();
                Toast.makeText(AddToolActivity.this, s, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }*/

}
