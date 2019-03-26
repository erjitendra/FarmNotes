package com.example.farmnotes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.farmnotes.database.DbCreater;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button activity_btn;
    Context context =this;
    private NotesModel notesModel;
    private NotesAdapter notesAdapter;
    private RecyclerView recyclerView;
    private ArrayList<NotesModel> notesModels = new ArrayList<>();
    private DbCreater dbCreater = new DbCreater(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        recyclerView = findViewById(R.id.recyclerViewNotesList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        notesModels = dbCreater.getNotes();


        notesAdapter = new NotesAdapter(notesModels, context);
        recyclerView.setAdapter(notesAdapter);


        activity_btn = findViewById(R.id.add_new_note);
        activity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), NotesCreation.class);
                startActivity(intent);



            }
        });

    }
}
