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
    private static final String EXTRA_TOOL_ID = "tool_id";
    private static final String ARG_TOOL_ID = "tool_id";

    public static Intent newIntent(Context packageContext, UUID toolId){
        Intent intent = new Intent(packageContext, AddToolActivity.class);
        intent.putExtra(EXTRA_TOOL_ID, toolId);
        return intent;
    }


    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_CONTACT = 1;
    private static final int REQUEST_PHOTO= 2;

    private Tool mTool;

    //private File mPhotoFile;

    private EditText mToolNameField;
    private TextView mToolCount;
    private Button mSaveButton;
    private Button mReportButton;

    //private SQLiteDatabase mDatabase;

    /*
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    private Button mSuspectButton;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;*/

    private String toolName;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_tool_activity);
        /*Bundle arguments = getIntent().getExtras();
        UUID toolId = (UUID) arguments.get(ARG_TOOL_ID);
        mTool = new Tool(toolId);*/


        mToolNameField = (EditText) findViewById(R.id.add_tool_title);
        mToolNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                toolName = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mToolCount = (TextView) findViewById(R.id.tool_count);
        //mToolCount.setText(mTool.getToolName());

        mSaveButton = (Button) findViewById(R.id.save_tool_button);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTool = new Tool();
                mTool.setToolName(toolName);
                mTool.setCount(0);
                ToolLab.get(getApplicationContext()).addTool(mTool);
                //getContentValues(mTool);
                finish();
            }
        });
    }

    @Override
    public void onPause(){
        super.onPause();
    }
}
