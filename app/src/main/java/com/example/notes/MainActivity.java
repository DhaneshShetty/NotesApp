package com.example.notes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.notes.Data.Note;
import com.example.notes.Data.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    NotesViewModel mNoteViewModel;
    static final int  NEW_REQUEST_CODE=1;static final int  UPDATE_REQUEST_CODE=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView=findViewById(R.id.grid_view);
        NoteAdapter noteAdapter=new NoteAdapter(this);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent viewIntent=new Intent(MainActivity.this,NewNoteActivity.class);
                viewIntent.putExtra("Title",noteAdapter.getmNotes().get(position).getmTitle());
                viewIntent.putExtra("Text",noteAdapter.getmNotes().get(position).getmText());
                viewIntent.putExtra("Protection",noteAdapter.getmNotes().get(position).getmProtection());
                viewIntent.putExtra("id",noteAdapter.getmNotes().get(position).get_id());
                viewIntent.putExtra("request_code",UPDATE_REQUEST_CODE);
                startActivityForResult(viewIntent,UPDATE_REQUEST_CODE);
            }
        });
        gridView.setAdapter(noteAdapter);
        mNoteViewModel=new ViewModelProvider(this).get(NotesViewModel.class);
        mNoteViewModel.getNotesAll().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                noteAdapter.setmNotes(notes);
            }
        });

        FloatingActionButton floatingActionButton=findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,NewNoteActivity.class);
                intent.putExtra("request_code",NEW_REQUEST_CODE);
                startActivityForResult(intent,NEW_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==NEW_REQUEST_CODE && resultCode==RESULT_OK)
        {
            assert data != null;
            Note note=new Note(data.getStringExtra(NewNoteActivity.EXTRA_REPLY),data.getStringExtra("Text"),data.getIntExtra("Protect",0));
            mNoteViewModel.insert(note);
        }
        else if(requestCode==UPDATE_REQUEST_CODE && resultCode==RESULT_OK){
            assert data != null;
            Note note=new Note(data.getStringExtra(NewNoteActivity.EXTRA_REPLY),data.getStringExtra("Text"),data.getIntExtra("Protect",0));
            mNoteViewModel.update(data.getStringExtra("Title"),data.getStringExtra("Text"),data.getIntExtra("Protect",0),data.getIntExtra("id",0));
        }
        else{
            Toast.makeText(
                    getApplicationContext(),
                    "Empty Title Not Saved",
                    Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_page_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.delete_all_button:
                mNoteViewModel.deleteAll();
                return true;
        }
        return false;
    }
    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        intent.putExtra("requestcode",requestCode);
    }
}
