package com.example.notes.Data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    private NotesRepository mRepo;
    private LiveData<List<Note>> notesAll;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        mRepo=new NotesRepository(application);
        notesAll=mRepo.getmAllNotes();
    }

    public LiveData<List<Note>> getNotesAll() {
        return notesAll;
    }
    public void insert(Note note)
    {
        mRepo.insert(note);
    }
    public void deleteAll()
    {
        mRepo.delete();
    }
    public void update(String title,String text,int protect,int id)
    {
        mRepo.update(title, text, protect, id);
    }

}
