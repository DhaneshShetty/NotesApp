package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.ToggleButton;

import com.example.notes.Data.Note;

import java.util.Objects;

public class NewNoteActivity extends AppCompatActivity {
    int mProtect;
    public static final String EXTRA_REPLY = "com.example.android.notelistsql.REPLY";
    com.google.android.material.textfield.TextInputEditText textInputEditText;
    com.google.android.material.textfield.TextInputEditText titleInputEditText;
int id_note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        textInputEditText=findViewById(R.id.text_note);
        titleInputEditText=findViewById(R.id.title);
        textInputEditText.setText(getIntent().getStringExtra("Text"));
        titleInputEditText.setText(getIntent().getStringExtra("Title"));
        id_note=getIntent().getIntExtra("id",1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.save_button) {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(titleInputEditText.getText())) {
                Log.d("Hello","Bole toh apun idhar hey");
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String title = titleInputEditText.getText().toString();
                String text = Objects.requireNonNull(textInputEditText.getText()).toString();
                if (getIntent().getIntExtra("request_code",0) == MainActivity.NEW_REQUEST_CODE) {
                    replyIntent.putExtra(EXTRA_REPLY, title);
                    replyIntent.putExtra("Text", text);
                    replyIntent.putExtra("Protect", mProtect);
                    setResult(RESULT_OK, replyIntent);
                }
                if (getIntent().getIntExtra("request_code",0) == MainActivity.UPDATE_REQUEST_CODE) {
                    replyIntent.putExtra("Title", title);
                    replyIntent.putExtra("Text", text);
                    replyIntent.putExtra("Protect", mProtect);
                    replyIntent.putExtra("id", id_note);
                    Log.d("Hello","hey man i came till here not my mistake...");
                    setResult(RESULT_OK, replyIntent);
                }
                else
                {
                    Log.d("Hello","Arey Mujhe toh andhar le lo");

                }

                finish();
                return true;
            }
        }
        else if(item.getItemId()==R.id.protection_switch)
        {
            mProtect=1;
            return true;
        }

            return false;
        }
    }



