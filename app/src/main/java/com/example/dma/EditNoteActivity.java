package com.example.dma;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;
import io.realm.RealmResults;

public class EditNoteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);


        EditText titleInput = findViewById(R.id.titleinput);
        EditText descriptionInput = findViewById(R.id.descriptioninput);
        EditText timeInput = (EditText) findViewById(R.id.timenotetext);
        EditText placeInput = findViewById(R.id.placeinworldinput);


        Bundle arguments = getIntent().getExtras();
        //if(arguments!=null){
            String title = arguments.get("title").toString();
            String description = arguments.getString("description");
            String timeNote = arguments.get("timeNote").toString();
            String placeInWorld = arguments.get("placeInWorld").toString();
        //int position = arguments.getInt("position");
            titleInput.setText(title);
            descriptionInput.setText(description);
            timeInput.setText(timeNote);
            placeInput.setText(placeInWorld);
            
            /*
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            Note note_old = realm.createObject(Note.class);
            note_old.setTitle(title);
            note_old.setDescription(description);
            note_old.setTimeNote(timeNote);
            note_old.setPlaceInWorld(placeInWorld);
            note_old.deleteFromRealm();
            realm.commitTransaction();
            */

            //Realm realm = Realm.getDefaultInstance();
            //Note note_old = realm.createObject(Note.class);
            //note_old.setTitle(title);
            //note_old.setDescription(description);
            //note_old.setTimeNote(timeNote);
            //note_old.setPlaceInWorld(placeInWorld);
        //Log.v("note_old", "note_old");



       //}


        MaterialButton updateBtn = findViewById(R.id.savebtn);


        Realm.init(getApplicationContext());

        //RealmResults<Note> notesList;
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Note note = notesList.get(position);
                /*
                realm.executeTransaction(r -> {
                    // Get a turtle named "Tony".
                    Note note_old = r.where(Note.class).equalTo("title", title).findFirst();
                    note_old.deleteFromRealm();
                    // discard the reference
                    note_old = null;
                });
                */
                Realm realm = Realm.getDefaultInstance();




                String title_new = titleInput.getText().toString();
                String description_new = descriptionInput.getText().toString();
                String timeNote_new = timeInput.getText().toString();
                String placeInWorld_new = placeInput.getText().toString();

                realm.beginTransaction();
                Note note = realm.createObject(Note.class);
                note.setTitle(title_new);
                note.setDescription(description_new);
                note.setTimeNote(timeNote_new);
                note.setPlaceInWorld(placeInWorld_new);
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(), "Note saved", Toast.LENGTH_SHORT).show();

                /*
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                note_old.deleteFromRealm();
                realm.commitTransaction(); */

                realm.beginTransaction();
                Note note_old = realm.where(Note.class).equalTo("title", title).findFirst();
                note_old.deleteFromRealm();
                realm.commitTransaction();
                finish();


            }
        });

        /*
        Realm realm = Realm.getDefaultInstance();

// Получение текущей заметки из Realm (если она уже существует)
Note note = realm.where(Note.class).
        */

    }



}
