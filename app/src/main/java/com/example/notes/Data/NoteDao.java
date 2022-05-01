package com.example.notes.Data;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert(onConflict =  OnConflictStrategy.IGNORE)
    void insert(Note note);
    @Query("SELECT * FROM notes_table")
    LiveData<List<Note>> getAllNotes();
    @Query("DELETE from notes_table")
    void deleteAll();
    @Query("UPDATE notes_table SET title=:title ,text=:text, protected=:pro WHERE _id=:id")
    void update(String title,String text,int pro,int id);
}
