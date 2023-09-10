package com.bignerdranch.android.criminalintent.tools;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.bignerdranch.android.criminalintent.R;

import java.util.List;
import java.util.UUID;

//навигация пальцем влево пальцем в право

public class ToolPagerActivity extends AppCompatActivity {
    private static final String EXTRA_TOOL_ID = "com.bignerdranch.android.criminalintent.tools.tool_id";

    private ViewPager mViewPager;
    private List<Tool> mTools;

    public static Intent newIntent(Context packageContext, UUID crimeId){
        Intent intent = new Intent(packageContext, ToolPagerActivity.class);
        intent.putExtra(EXTRA_TOOL_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID toolId = (UUID) getIntent().getSerializableExtra(EXTRA_TOOL_ID);

        mViewPager = (ViewPager) findViewById(R.id.crime_view_pager);

        mTools = ToolLab.get(this).getTools();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Tool tool = mTools.get(position);
                return ToolFragment.newInstance(tool.getId());
            }

            @Override
            public int getCount() {
                return mTools.size();
            }
        });

        for (int i = 0; i < mTools.size(); i++){
            if (mTools.get(i).getId().equals(toolId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}