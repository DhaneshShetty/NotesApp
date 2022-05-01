package com.example.notes.Data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table")
public class Note
{
    @PrimaryKey(autoGenerate = true)
    public int _id;

    @ColumnInfo(name="title")
    public String mTitle;

    @ColumnInfo(name="text")
    public String mText;

    @ColumnInfo(name= "protected")
    public int mProtection;
    public Note(String mTitle, String mText, int mProtection)
    {
        this.mTitle=mTitle;
        this.mProtection=mProtection;
        this.mText=mText;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmProtection(int mProtection) {
        this.mProtection = mProtection;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmText() {
        return mText;
    }

    public int getmProtection() {
        return mProtection;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
