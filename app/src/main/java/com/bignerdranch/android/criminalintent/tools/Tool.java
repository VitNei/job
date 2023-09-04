package com.bignerdranch.android.criminalintent.tools;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


public class Tool implements Serializable {
    private UUID mId;
    private String mToolName;
    private String mWhereUse;
    private int mCount;
    //private boolean mIsEmpty;

    //private int mPhoto;

    //private Date mDateOfReceiving;
    //private Date mDateOfIssue;

    public Tool() {
        this(UUID.randomUUID());
    }

    public Tool(UUID id){
        mId = id;
        mToolName =  "";
        mWhereUse = "";
        mCount = 0;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        this.mId = id;
    }

    public String getToolName() {
        return mToolName;
    }

    public void setToolName(String toolName) {
        this.mToolName = toolName;
    }

    public String getWhereUse() {
        return mWhereUse;
    }

    public void setWhereUse(String whereUse) {
        this.mWhereUse = whereUse;
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        this.mCount = count;
    }

    /*public boolean isIsEmpty() {
        return mIsEmpty;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.mIsEmpty = isEmpty;
    }*/
}
