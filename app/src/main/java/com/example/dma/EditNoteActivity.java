package com.example.dma;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class EditNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);


        EditText titleInput = findViewById(R.id.titleinput);
        EditText descriptionInput = findViewById(R.id.descriptioninput);
        EditText timeInput = (EditText) findViewById(R.id.timenotetext);
        EditText placeInput = findViewById(R.id.placeinworldinput);
        MaterialButton updateBtn = findViewById(R.id.savebtn);

        Bundle arguments = getIntent().getExtras();
        if(arguments!=null){
            String title = arguments.get("title").toString();
            String description = arguments.getString("description");
            String timeNote = arguments.get("timeNote").toString();
            String placeInWorld = arguments.get("placeInWorld").toString();

            titleInput.setText(title);
            descriptionInput.setText(description);
            timeInput.setText(timeNote);
            placeInput.setText(placeInWorld);
        }

    }


}
