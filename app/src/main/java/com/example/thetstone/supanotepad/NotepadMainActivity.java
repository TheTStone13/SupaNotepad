package com.example.thetstone.supanotepad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NotepadMainActivity extends AppCompatActivity {

    ListView notesList;
    ArrayList<NoteModel> notesModels;
    private NotesArrayAdapter adapter;
    private FileReadWrite reWr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //this.deleteFile("saveNotes.txt"); //DELETES ALL TEST FILES//// TODO: 3/31/2018

        notesList = (ListView) findViewById(R.id.main_note_list);
        notesList.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        //Toast.makeText(this, "oncreate pop list", Toast.LENGTH_SHORT).show();
        notesModels = populateList();


        //Toast.makeText(this, "filename = " + notesModels.get(0).getFilename(), Toast.LENGTH_SHORT).show();
        //notesList.setFocusable(false);
        //notesList.setFocusableInTouchMode(false);


        adapter = new NotesArrayAdapter(this, R.layout.notepad_item_layout,notesModels);

        notesList.setAdapter(adapter);
        notesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //parent.getItemAtPosition(position)
                Toast.makeText(NotepadMainActivity.this, "Note Selected", Toast.LENGTH_SHORT).show();
                Intent edit = new Intent(NotepadMainActivity.this, EditNoteActivity.class);
                //Toast.makeText(NotepadMainActivity.this, notesModels.get(0).getTitle(), Toast.LENGTH_SHORT).show();
                edit.putExtra("note_model_filename", notesModels.get(position).getFilename());
                //Toast.makeText(NotepadMainActivity.this, edit.getStringExtra("note_model_filename"), Toast.LENGTH_SHORT).show();
                startActivity(edit);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_page_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.upload_notes://TODO upload notes to server
                Toast.makeText(this, "Upload Notes", Toast.LENGTH_SHORT).show();
                uploadNotes();
                break;
            case R.id.news_notes_menu:
                startActivity(new Intent(NotepadMainActivity.this, NewNoteActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    protected ArrayList<NoteModel> populateList() {
        //Toast.makeText(this, "populate list", Toast.LENGTH_SHORT).show();
        //ArrayList<String> linesArr = new ArrayList<String>();
        String data = "";
        try {
            reWr = new FileReadWrite();
            data = reWr.readFile(this, "saveNotes.txt");
        } catch (Exception e) {
            Toast.makeText(this, "404", Toast.LENGTH_SHORT).show();
        }

        ArrayList<NoteModel> notes = new ArrayList<NoteModel>();
        //Toast.makeText(this, data, Toast.LENGTH_LONG).show();

        //TextView t = (TextView)findViewById(R.id.tempTextView); //used to debug listview
        //char[] c = data.toCharArray();
        //t.setText(c, 0, data.length());

        int index = 0; // index of noteData
        int pos = 3; //position of index for main loop
        NoteModel temp;
        int front = 0;//front of current subString
        int back = 0;//back of current subString
        String[] noteData = new String[5];//note Data
        //Toast.makeText(this, "StartLoop", Toast.LENGTH_SHORT).show();

        while (pos < data.length() + 1) {
            String s = data.substring(pos - 3, pos);
            if (s.compareTo("/?/") == 0) {
                front = pos - 3;
            }
            if (s.compareTo("/!/") == 0) {
                if(data.length() - pos > 10) {
                    String si = data.substring(front, pos);
                    //Toast.makeText(this, si, Toast.LENGTH_SHORT).show();
                    //NoteModel test = new NoteModel(si);
                    notes.add(new NoteModel(si));
                }
                else{
                    String si = data.substring(front);
                    //Toast.makeText(NotepadMainActivity.this, si, Toast.LENGTH_SHORT).show();
                    notes.add(new NoteModel(si));
                }

            }
            pos++;
        }

/*
        while (pos < data.length() + 1) {

            String s = data.substring(pos - 3, pos);
            //Toast.makeText(this, "s=  " + s, Toast.LENGTH_SHORT).show();

            if (s.compareTo("/?/") == 0)//start note
            {
                //Toast.makeText(this, "s = /?/", Toast.LENGTH_SHORT).show();
                front = pos;
                pos++;
            } else if (s.compareTo("/!/") == 0)// end notes
            {
                //Toast.makeText(this, "s = /!/", Toast.LENGTH_SHORT).show();
                back = pos - 3;
                noteData[index] = data.substring(front, back);
                temp = new NoteModel(noteData);
                notes.add(temp);

                pos++;
                index = 0;
                noteData = new String[5];

            } else if (s.compareTo("/~/") == 0)//divs
            {
                //Toast.makeText(this, "s = /~/", Toast.LENGTH_SHORT).show();
                back = pos - 3;
                noteData[index] = data.substring(front, back);
                index++;
                front = pos;
                pos++;

            } else //normal interate
            {
                //Toast.makeText(this, "iterate", Toast.LENGTH_SHORT).show();
                pos++;
            }

        }*/
         //NOTEMODEL CONTINUITY CHECK
        /*
        Toast.makeText(this, "NOTEMODEL LIST", Toast.LENGTH_SHORT).show();
        for (NoteModel n : notes) {
            Toast.makeText(this, n.toString(), Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "NOTEMODEL LIST END", Toast.LENGTH_SHORT).show();
*/
        return notes;

    }

    public void uploadNotes() {

    }


}
