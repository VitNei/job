package com.bignerdranch.android.criminalintent.tools;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.bignerdranch.android.criminalintent.R;

import java.util.UUID;

public class ToolFragment extends Fragment {
    private static final String ARG_TOOL_ID = "tool_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_CONTACT = 1;
    private static final int REQUEST_PHOTO= 2;

    private Tool mTool;

    //private File mPhotoFile;

    private EditText mToolNameField;
    private TextView mToolCount;
    private Button mReportButton;

    //private SQLiteDatabase mDatabase;

    /*
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    private Button mSuspectButton;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;*/

    public static ToolFragment newInstance(UUID toolId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_TOOL_ID, toolId);

        ToolFragment fragment = new ToolFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID toolId = (UUID) getArguments().getSerializable(ARG_TOOL_ID);
        mTool = ToolLab.get(getActivity()).getTool(toolId);
        //mPhotoFile = CrimeLab.get(getActivity()).getPhotoFile(mCrime);
    }

    @Override
    public void onPause(){
        super.onPause();

        ToolLab.get(getActivity()).updateTool(mTool);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tool, container, false);

        mToolNameField = (EditText) v.findViewById(R.id.tool_title);
        mToolNameField.setText(mTool.getToolName());
        mToolNameField.addTextChangedListener(new TextWatcher() {
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

        mToolCount = (TextView) v.findViewById(R.id.tool_count);
        //mToolCount.setText("0");

        /*mCompletedButton = (Button) v.findViewById(R.id.finsh_button);
        mCompletedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCrime.setDateChange(new Date());
                OldTaskLab.get(getActivity()).addCompleteTask(mCrime);

                CrimeLab.get(getActivity()).deleteCrime(mCrime);
                getActivity().finish();
            }
        });*/

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

        /*final Intent pickContact = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        //pickContact.addCategory(Intent.CATEGORY_HOME);//делает кнопку не активной
        mSuspectButton = (Button) v.findViewById(R.id.crime_suspect);
        mSuspectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(pickContact, REQUEST_CONTACT);
            }
        });*/

        /*if(mCrime.getSuspect() != null){
            mSuspectButton.setText(mCrime.getSuspect());
        }

        PackageManager packageManager = getActivity().getPackageManager();
        if (packageManager.resolveActivity(pickContact, PackageManager.MATCH_DEFAULT_ONLY) == null){
            mSuspectButton.setEnabled(false);
        }*/

        /*mPhotoButton = (ImageButton) v.findViewById(R.id.crime_camera);
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        boolean canTakePhoto = mPhotoFile != null &&
                captureImage.resolveActivity(packageManager) != null;
        mPhotoButton.setEnabled(canTakePhoto);
        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = FileProvider.getUriForFile(getActivity(),
                        "com.bignerdranch.android.criminalintent.fileprovider",
                        mPhotoFile);
                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                List<ResolveInfo> cameraActivities = getActivity()
                        .getPackageManager().queryIntentActivities(captureImage,
                                PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo activity : cameraActivities) {
                    getActivity().grantUriPermission(activity.activityInfo.packageName,
                            uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }
                startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        });
        mPhotoView = (ImageView) v.findViewById(R.id.crime_photo);
        updatePhotoView();*/

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode != Activity.RESULT_OK){
            return;
        }

        /*if (requestCode == REQUEST_CONTACT && data != null){
            Uri contactUri = data.getData();
            //Определение полей, значения которых должны быть возвращены запросом
            String[] queryFields = new String[] {
                    ContactsContract.Contacts.DISPLAY_NAME
            };
            //Выполнение запроса - contactUri здесь выполняет функции условия where
            //
            Cursor c = getActivity().getContentResolver().query(contactUri, queryFields, null, null, null);
            try {
                //Проверка получения результата
                if (c.getCount() == 0){
                    return;
                }
                //извлечение первого столбца данных - имени подозреваемого
                c.moveToFirst();
                /*String suspect = c.getString(0);
                mCrime.setSuspect(suspect);
                mSuspectButton.setText(suspect);
            } finally {
                c.close();
            }
        } else if (requestCode == REQUEST_PHOTO) {
            Uri uri = FileProvider.getUriForFile(getActivity(),
                    "com.bignerdranch.android.criminalintent.fileprovider",
                    mPhotoFile);
            getActivity().revokeUriPermission(uri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            updatePhotoView();
        }*/
    }

    /*private void updateChangeDate() {
        mCrime.setDate(new Date());
    }*/

    /*private String getCrimeReport() {
        String solvedString = null;
        if (mCrime.isSolved()) {
            solvedString = getString(R.string.crime_report_solved);
        } else {
            solvedString = getString(R.string.crime_report_unsolved);
        }
        String dateFormat = "EEE, MMM dd";
        String dateString = DateFormat.format(dateFormat,
                mCrime.getDate()).toString();
        String suspect = mCrime.getSuspect();
        if (suspect == null) {
            suspect = getString(R.string.crime_report_no_suspect);
        } else {
            suspect = getString(R.string.crime_report_suspect, suspect);
        }
        String report = getString(R.string.crime_report,
                mCrime.getTitle(), dateString, solvedString, suspect);
        return report;
    }*/

    /*private void updatePhotoView() {
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(
                    mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(bitmap);
        }
    }*/

    /*public void addCompleteTask(Crime c){
        ContentValues values = getContentValuesForCompletedTable(c);

        mDatabase.insert(CrimeDbSchema.TableCompleted.NAME_TABLE_COMPLETED, null, values);
    }

    public static ContentValues getContentValuesForCompletedTable(Crime crime){
        ContentValues values = new ContentValues();
        values.put(CrimeDbSchema.TableCompleted.Cols.UUID_TABLE_COMPLETED, crime.getId().toString());
        values.put(CrimeDbSchema.TableCompleted.Cols.TITLE_TABLE_COMPLETED, crime.getTitle());
        values.put(CrimeDbSchema.TableCompleted.Cols.NOTE_TABLE_COMPLETED, crime.getNote());
        values.put(CrimeDbSchema.TableCompleted.Cols.DATE_CREATE_TABLE_COMPLETED, crime.getDate().getTime());
        values.put(CrimeDbSchema.TableCompleted.Cols.DATE_FINISH_TABLE_COMPLETED, crime.getDateChange().getTime());

        return values;
    }*/
}