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

    private static final String ARG_CRIME_ID = "task_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_CONTACT = 1;
    private static final int REQUEST_PHOTO= 2;

    private Task mTask;

    //private File mPhotoFile;

    private TextView mTitleField;
    private TextView mNoteField;
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
    }

    @Override
    public void onPause(){
        super.onPause();
        //OldTaskLab.get(getActivity()).updateCrime(mTask);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_completed, container, false);

        mTitleField = (TextView) v.findViewById(R.id.f_title);
        mTitleField.setText(mTask.getTitle());

        mNoteField = (TextView) v.findViewById(R.id.f_note);
        mNoteField.setText(mTask.getNote());

        mDateCreate = (TextView) v.findViewById(R.id.f_date_create);
        mDateCreate.setText("Создано: " + mTask.getDate().toString());

        mDateChange = (TextView) v.findViewById(R.id.f_delete_date);
        mDateChange.setText("Удалено: " + mTask.getDateChange().toString());

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
}