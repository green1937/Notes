package com.example.dma;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;

public class AddNoteActivity extends AppCompatActivity {
    CalendarView calender;
    EditText eTxt;
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        calender = (CalendarView) findViewById(R.id.calendarView1);
        eTxt = (EditText) findViewById(R.id.timenotetext);

        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // TODO Auto-generated method stub
                eTxt.setText(dayOfMonth + "." + (month + 1) + "." + year);
            }

        });

        //MaterialButton mapBtn = findViewById(R.id.mapbtn);
        //EditText placeInput = findViewById(R.id.placeinworldinput);

        /*mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddNoteActivity.this, MapActivity.class));
            }
        });*/


        EditText titleInput = findViewById(R.id.titleinput);
        EditText descriptionInput = findViewById(R.id.descriptioninput);
        EditText timeInput = eTxt;

        MaterialButton saveBtn = findViewById(R.id.savebtn);


        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleInput.getText().toString();
                String description = descriptionInput.getText().toString();
                String timeNote = timeInput.getText().toString();
                //String placeInWorld = placeInput.getText().toString();

                realm.beginTransaction();
                Note note = realm.createObject(Note.class);
                note.setTitle(title);
                note.setDescription(description);
                note.setTimeNote(timeNote);
                //note.setPlaceInWorld(placeInWorld);
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(), "Note saved", Toast.LENGTH_SHORT).show();
                finish();


            }
        });


    }
}
