package com.bignerdranch.android.criminalintent.completeTask;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.TextView;
import com.bignerdranch.android.criminalintent.CrimeLab;
import com.bignerdranch.android.criminalintent.CrimePagerActivity;
import com.bignerdranch.android.criminalintent.R;

import java.util.List;

public class CompleteTaskListFragment  extends Fragment {
    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";//сомнительная надобность

    private RecyclerView mTaskRecyclerView;//+
    private TaskAdapter mAdapter;//+
    private boolean mSubtitleVisible;//сомнительная надобность

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);//+
        setHasOptionsMenu(true);//+
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complete_task_list, container, false);//add fragment_complete_task_list
        mTaskRecyclerView = (RecyclerView) view.findViewById(R.id.complete_task_recycler_view);
        mTaskRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();//+
        return view;//+
    }

    @Override
    public void onResume(){
        super.onResume();//+
        updateUI();//+
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);//+
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);//+
    }

    private void updateUI(){
        OldTaskLab oldTaskLab = OldTaskLab.get(getActivity());//may be dont change this
        List<Task> tasks = oldTaskLab.getTasks();//add this method and class Task

        if(mAdapter == null){
            mAdapter = new TaskAdapter(tasks);
            mTaskRecyclerView.setAdapter(mAdapter);//wtf i dont know this yet
        } else {
            mAdapter.setTasks(tasks);//add setTask
            mAdapter.notifyDataSetChanged();//????
        }

        updateSubtitle();//+
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);//+
        inflater.inflate(R.menu.fragment_crime_list, menu);//???

        MenuItem subtitleItem = menu.findItem(R.id.show_subtitle);
        if (mSubtitleVisible){
            subtitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.new_crime:
                //создаёт новую запись, с нам оно здесь не нужно
                /*Task crime = new Task();
                CrimeLab.get(getActivity()).addCrime(crime);
                Intent intent = CrimePagerActivity.newIntent(getActivity(), crime.getId());
                startActivity(intent);*/
                return true;

            case R.id.show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;

            default:
                return  super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle(){
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        int crimeCount = crimeLab.getCrimes().size();
        String subtitle = getString(R.string.subtitle_format, crimeCount);

        if (!mSubtitleVisible) {
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitleTextView;
        private TextView mDateTextView;

        private Task mTask;

        private static final String ARG_TASK_ID = "task_id";//????

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_complete_task, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.complete_note);
            mDateTextView = (TextView) itemView.findViewById(R.id.complete_task_date);
        }

        public void bind(Task task){
            mTask = task;
            mTitleTextView.setText(mTask.getTitle());
            mDateTextView.setText(mTask.getDateChange().toString());
        }

        @Override
        public void onClick(View v) {
            Intent intent = CompleteTaskPagerActivity.newIntent(getActivity(), mTask.getId());//???
            startActivity(intent);//+
        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Task> mTask;

        public TaskAdapter(List<Task> tasks){
            mTask = tasks;
        }


        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            Task task = mTask.get(position);
            holder.bind(task);
        }

        @Override
        public int getItemCount() {
            return mTask.size();
        }

        public void setTasks(List<Task> tasks){
            mTask = tasks;
        }
    }
}
