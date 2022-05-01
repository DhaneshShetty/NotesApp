package com.example.notes.Data;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import java.util.List;

public class NotesRepository {
    private NoteDao mNoteDao;
    private LiveData<List<Note>> mAllNotes;

    public NotesRepository(Application application) {
        NoteRoomDatabase db = NoteRoomDatabase.getDatabase(application);
        mNoteDao = db.noteDao();
        mAllNotes = mNoteDao.getAllNotes();
    }

    public LiveData<List<Note>> getmAllNotes() {
        return mAllNotes;
    }

    public void insert(final Note note) {
        NoteRoomDatabase.databaseWriteExecutor.execute(() -> {
            mNoteDao.insert(note);
        });
    }

    public void delete() {
        NoteRoomDatabase.databaseWriteExecutor.execute(() -> {
            mNoteDao.deleteAll();
        });

    }

    public void update(String title, String text, int protect, int id) {
        {
            NoteRoomDatabase.databaseWriteExecutor.execute(() -> {
                mNoteDao.update(title, text, protect, id);
            });
        }
    }
}
