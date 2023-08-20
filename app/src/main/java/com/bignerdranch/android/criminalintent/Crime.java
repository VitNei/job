package com.bignerdranch.android.criminalintent;

import java.util.Date;
import java.util.UUID;

public class Crime {
    private UUID mId;
    private String mTitle;
    private String mNote;
    private Date mDate;
    private Date mDateChange;
    private boolean mSolved;
    private String mSuspect;
    private int mPrioritet;
    private int mProgress;


    public Crime() {
        this(UUID.randomUUID());
    }

    public Crime(UUID id){
        mId = id;
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

	public String getNote()
	{
		return mNote;
	}

	public void setNote(String note) {
		this.mNote = note;
	}

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

	public Date getDateChange()
	{
		return mDateChange;
	}

	public void setDateChange(Date dateChange) {
		this.mDateChange = dateChange;
	}

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }

    public String getSuspect(){
        return mSuspect;
    }

    public void setSuspect(String suspect){
        mSuspect = suspect;
    }

    public String getPhotoFilename(){
        return "IMG_" + getId().toString() + ".jpg";
    }

	public int getPrioritet()
	{
		return mPrioritet;
	}

	public void setPrioritet(int prioritet) {
		this.mPrioritet = prioritet;
	}

	public int getProgress()
	{
		return mProgress;
	}

	public void setProgress(int progress) {
		this.mProgress = progress;
	}
}
