package com.example.dma;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;
import io.realm.RealmResults;

public class EditNoteActivity extends AppCompatActivity {
    CalendarView calender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        EditText timeInput = (EditText) findViewById(R.id.timenotetext);

        /*Календарь*/
        calender = (CalendarView) findViewById(R.id.calendarView1);


        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // TODO Auto-generated method stub
                timeInput.setText(dayOfMonth + "." + (month + 1) + "." + year);
            }

        });

        EditText titleInput = findViewById(R.id.titleinput);
        EditText descriptionInput = findViewById(R.id.descriptioninput);
        MaterialButton updateBtn = findViewById(R.id.savebtn);

        Bundle arguments = getIntent().getExtras();

        String title = arguments.get("title").toString();
        String description = arguments.getString("description");
        String timeNote = arguments.get("timeNote").toString();

        titleInput.setText(title);
        descriptionInput.setText(description);
        timeInput.setText(timeNote);


        Realm.init(getApplicationContext());

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm realm = Realm.getDefaultInstance();

                String title_new = titleInput.getText().toString();
                String description_new = descriptionInput.getText().toString();
                String timeNote_new = timeInput.getText().toString();

                realm.beginTransaction();
                Note note = realm.createObject(Note.class);
                note.setTitle(title_new);
                note.setDescription(description_new);
                note.setTimeNote(timeNote_new);
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(), "Заметка сохранена", Toast.LENGTH_SHORT).show();

                realm.beginTransaction();
                Note note_old = realm.where(Note.class).equalTo("title", title).findFirst();
                note_old.deleteFromRealm();
                realm.commitTransaction();
                finish();


            }
        });

    }



}
