package com.bignerdranch.android.criminalintent.completeTask;
import java.util.Date;
import java.util.UUID;

public class Task {
    private UUID mId;
    private String mTitle;
    private String mNote;
    private Date mCreateDate;
    private Date mCompleteDate;

    public Task(UUID id){
        mId = id;
    }


    public Task(UUID id, String title, String note, Date createDate, Date completeDate){
        mId = id;
        mTitle = title;
        mNote = note;
        mCreateDate = createDate;
        mCompleteDate = completeDate;
    }

    //setter'ы вроде как и не нужны, но мали вдруг пригодятся

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
        return mCreateDate;
    }

    public void setDate(Date mDate) {
        this.mCreateDate = mDate;
    }

    public Date getDateChange()
    {
        return mCompleteDate;
    }

    public void setDateChange(Date completeDate) {
        this.mCompleteDate = completeDate;
    }
}
