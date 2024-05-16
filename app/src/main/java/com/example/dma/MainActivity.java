package com.example.dma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.yandex.mapkit.MapKitFactory;

import java.io.IOException;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    RealmResults<Note> notesList; // глобальная переменная
    RadioButton sortByTitle, sortByTime;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton mapBtn = findViewById(R.id.iconMap);
        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapActivity.class));
            }
        });

        ImageButton apiBtn = findViewById(R.id.apiBtn);
        apiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BoredActivity.class));
            }
        });



        MaterialButton addNoteBtn = findViewById(R.id.addnewnotebtn);
        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddNoteActivity.class));

            }
        });


        Realm.init(getApplicationContext());

        sortByTitle = (RadioButton) findViewById(R.id.titleSort);
        sortByTime = (RadioButton) findViewById(R.id.timeSort);
        submit = (Button) findViewById(R.id.sortBtn);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Realm realm = Realm.getDefaultInstance();
        notesList = realm.where(Note.class).findAll();
        MyAdapter myAdapter = new MyAdapter(getApplicationContext(), notesList);
        recyclerView.setAdapter(myAdapter);
        notesList.addChangeListener(new RealmChangeListener<RealmResults<Note>>() {
            @Override
            public void onChange(RealmResults<Note> notes) {

                myAdapter.notifyDataSetChanged();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sortByTitle.isChecked()) {
                    Realm realm = Realm.getDefaultInstance();
                    notesList = realm.where(Note.class).findAll().sort("title");
                    MyAdapter myAdapter = new MyAdapter(getApplicationContext(), notesList);
                    recyclerView.setAdapter(myAdapter);
                    notesList.addChangeListener(new RealmChangeListener<RealmResults<Note>>() {
                        @Override
                        public void onChange(RealmResults<Note> notes) {

                            myAdapter.notifyDataSetChanged();
                        }
                    });
                } else if (sortByTime.isChecked()) {
                    Realm realm = Realm.getDefaultInstance();
                    notesList = realm.where(Note.class).findAll();
                    MyAdapter myAdapter = new MyAdapter(getApplicationContext(), notesList);
                    recyclerView.setAdapter(myAdapter);
                    notesList.addChangeListener(new RealmChangeListener<RealmResults<Note>>() {
                        @Override
                        public void onChange(RealmResults<Note> notes) {

                            myAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
    }


    private RealmResults<Note> sortAndReturnNotes() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        RadioGroup sortGr = findViewById(R.id.rgSort);
        sortGr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.titleSort) {
                    notesList = realm.where(Note.class).findAll().sort("title");

                }
                else {
                    notesList = realm.where(Note.class).findAll();
                }
            }
        });
        return notesList;
    }
}