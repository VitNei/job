package com.bignerdranch.android.criminalintent.completeTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.bignerdranch.android.criminalintent.R;

import java.util.Date;
import java.util.UUID;

public class CompleteTaskFragment extends Fragment {

    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_CONTACT = 1;
    private static final int REQUEST_PHOTO= 2;

    private Task mTask;

    //private File mPhotoFile;

    private EditText mTitleField;
    private EditText mNoteField;
    private TextView mDateCreate;
    private TextView mDateChange;


    /*
    private ImageView mPhotoView;*/

    public static CompleteTaskFragment newInstance(UUID taskId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, taskId);

        CompleteTaskFragment fragment = new CompleteTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mTask = OldTaskLab.get(getActivity()).getTask(crimeId);
        //mPhotoFile = CrimeLab.get(getActivity()).getPhotoFile(mCrime);
    }

    @Override
    public void onPause(){
        super.onPause();

        //OldTaskLab.get(getActivity()).updateCrime(mTask);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        mTitleField = (EditText) v.findViewById(R.id.title);
        mTitleField.setText(mTask.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTask.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mNoteField = (EditText) v.findViewById(R.id.note);
        mNoteField.setText(mTask.getNote());
        mNoteField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTask.setNote(s.toString());
                mTask.setDateChange(new Date());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mDateCreate = (TextView) v.findViewById(R.id.date_create);
        mDateCreate.setText("Создано: " + mTask.getDate().toString());

        mDateChange = (TextView) v.findViewById(R.id.date_change);
        mDateChange.setText("Изменено: " + mTask.getDateChange().toString());

        /*mReportButton = (Button) v.findViewById(R.id.crime_report);
        mReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, getCrimeReport());
                i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.crime_report_subject));
                i = Intent.createChooser(i, getString(R.string.send_report));
                startActivity(i);
            }
        });*/


        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode != Activity.RESULT_OK){
            return;
        }


    }

    private void updateChangeDate() {
        mTask.setDate(new Date());
    }


}