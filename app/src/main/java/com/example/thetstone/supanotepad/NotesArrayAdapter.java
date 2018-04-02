package com.example.thetstone.supanotepad;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by TheTStone on 2/5/2018.
 */

public class NotesArrayAdapter extends ArrayAdapter<NoteModel> {
    private ArrayList<NoteModel> noteSet;
    Context nContext;
    private LayoutInflater inflater;

    public NotesArrayAdapter(Context context, int resources, ArrayList<NoteModel> notes) {
        super(context,resources, notes);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v==null) {
            inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.notepad_item_layout, null);
        }

        NoteModel currentNoteModel = getItem(position);
        //Toast.makeText(getContext(), "!!!!!!!!!!!!!!!" + currentNoteModel.toString(), Toast.LENGTH_SHORT).show();

        if(currentNoteModel != null)
        {
            TextView titleView = (TextView) v.findViewById(R.id.titleView);
            TextView dateupdate = (TextView) v.findViewById(R.id.dateUpdated);

            TextView datecrea = (TextView) v.findViewById(R.id.dateCreated);
            TextView tags = (TextView) v.findViewById(R.id.tagsView);
            if(titleView != null) {
                titleView.setText(currentNoteModel.getTitle().toCharArray(), 0, currentNoteModel.getTitle().toCharArray().length);
            }
            if(dateupdate != null)
                dateupdate.setText(currentNoteModel.getUPdate().toCharArray(), 0, currentNoteModel.getUPdate().toCharArray().length);

            if(datecrea != null)
            datecrea.setText(currentNoteModel.getCREATEdate().toCharArray(), 0, currentNoteModel.getCREATEdate().length());

           // if (tags!= null)
            //tags.setText(currentNoteModel.getTags().toCharArray(), 0, currentNoteModel.getTags().length());

        }



        return v;
    }

    /*
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        NoteModel note = noteSet.get(position);
        CurrView currentView;
        final View finalProduct;

            currentView = new CurrView();
            inflater = LayoutInflater.from(getContext());
            conView = inflater.inflate(R.layout.notepad_item_layout, parent, false);
            currentView.titleView = (TextView) conView.findViewById(R.id.titleView);
            currentView.tagsView = (TextView) conView.findViewById(R.id.dateUpdated);
            currentView.UpDateView = (TextView) conView.findViewById(R.id.dateCreated);
            currentView.CreDateView = (TextView) conView.findViewById(R.id.tagsView);

            finalProduct = conView;
            conView.setTag(currentView);
        }
        else
        {
            currentView = (CurrView) conView.getTag();
            finalProduct = conView;
        }

        currentView.titleView.setText(note.getText());
        currentView.tagsView.setText(note.getTags());
        currentView.UpDateView.setText(note.getUPdate());
        currentView.CreDateView.setText(note.getCREATEdate());
        return conView;

    }

*/

}