package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.notes.Data.Note;

import java.util.List;


public class NoteAdapter extends ArrayAdapter<Note> {
    private List<Note> mNotes;
    public NoteAdapter(@NonNull Context context) {
        super(context, 0);
    }

    public void setmNotes(List<Note> mNotes) {
        this.mNotes = mNotes;
        notifyDataSetChanged();
    }

    public List<Note> getmNotes() {
        return mNotes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null)
        {
            listItemView=LayoutInflater.from(getContext()).inflate(R.layout.grid_item,parent,false);
        }
        Note current=mNotes.get(position);
        TextView noteTitle=listItemView.findViewById(R.id.note_title);
        noteTitle.setText(current.getmTitle());
        ImageView protect=listItemView.findViewById(R.id.protection_icon);
        if(current.getmProtection()==1)
        {
            protect.setVisibility(View.VISIBLE);
        }
        else if(current.getmProtection()==0)
        {
            protect.setVisibility(View.GONE);
        }
        return listItemView;
    }

    @Override
    public int getCount() {
        if(mNotes!=null)
        {
            return mNotes.size();
        }
        else
        {
            return 0;
        }
    }
}
