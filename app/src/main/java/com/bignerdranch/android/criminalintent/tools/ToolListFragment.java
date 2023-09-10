package com.bignerdranch.android.criminalintent.tools;

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
import com.bignerdranch.android.criminalintent.R;

import java.util.List;

public class ToolListFragment extends Fragment {
    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";

    private RecyclerView mToolRecyclerView;
    private ToolAdapter mAdapter;
    private boolean mSubtitleVisible;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tool_list, container, false);
        mToolRecyclerView = (RecyclerView) view.findViewById(R.id.tool_recycler_view);
        mToolRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstanceState != null){
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        updateUI();
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    private void updateUI(){
        ToolLab toolLab = ToolLab.get(getActivity());
        List<Tool> tools = toolLab.getTools();

        if(mAdapter == null){
            mAdapter = new ToolAdapter(tools);
            mToolRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setTools(tools);
            mAdapter.notifyDataSetChanged();
        }

        updateSubtitle();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list, menu);

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
                Tool tool = new Tool();
                ToolLab.get(getActivity()).addTool(tool);
                Intent intent = ToolPagerActivity.newIntent(getActivity(), tool.getId());
                startActivity(intent);
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
        ToolLab toolLab = ToolLab.get(getActivity());
        int toolCount = toolLab.getTools().size();
        String subtitle = getString(R.string.subtitle_format, toolCount);

        if (!mSubtitleVisible) {
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    //заполняет макет
    /*
    В конструкторе CrimeHolder происходит заполнение list_item_crime.xml.
    Вызов на-прямую передается super(…), конструктору ViewHolder.
    Базовый класс ViewHolder хранит иерархию представлений fragment_crime_list.xml.
    Если вам понадобится эта иерархия представлений,
    вы можете взять ее из поля itemView класса ViewHolder.
     */
    private class ToolHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mNameTool;
        private TextView mToolCount;

        private Tool mTool;

        //Самоделки
        private static final String ARG_TOOL_ID = "tool_id";

        public ToolHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_tool, parent, false));
            itemView.setOnClickListener(this);

            mNameTool = (TextView) itemView.findViewById(R.id.tool_name);
            mToolCount = (TextView) itemView.findViewById(R.id.tool_count);
        }

        public void bind(Tool tool){
            mTool = tool;
            mNameTool.setText(mTool.getToolName());
            //mToolCount.setText(mTool.getCount());
        }

        @Override
        public void onClick(View v) {
            Intent intent = ToolPagerActivity.newIntent(getActivity(), mTool.getId());
            startActivity(intent);
        }
    }

    /*
    Класс RecyclerView взаимодействует  с адаптером,  когда  требуется  создать
    новый объект ViewHolder или связать существующий объект ViewHolder с объектом
    Crime.  Он  обращается  за  помощью  к адаптеру,  вызывая  его  метод.  Сам  виджет
    RecyclerView ничего не знает об объекте Crime, но адаптер располагает
    полной информацией о Crime.
     */
    private class ToolAdapter extends RecyclerView.Adapter<ToolHolder>{
        private List<Tool> mTools;

        public ToolAdapter(List<Tool> tools){
            mTools = tools;
        }

        /*
        Метод onCreateViewHolder вызывается виджетом RecyclerView,
        когда ему требуется новое представление для отображения элемента.
        В этом методе мы создаем объект LayoutInflater и используем его для
        создания нового объекта CrimeHolder.
         */
        @NonNull
        @Override
        public ToolHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ToolHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ToolHolder holder, int position) {
            Tool tool = mTools.get(position);
            holder.bind(tool);
        }

        @Override
        public int getItemCount() {
            return mTools.size();
        }

        public void setTools(List<Tool> tools){
            mTools = tools;
        }
    }
}
