package com.example.farmnotes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.farmnotes.database.DbCreater;
import com.example.farmnotes.socialIntegration.GoogleLoginActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button activity_btn;
    Context context = this;
    private NotesAdapter notesAdapter;
    private RecyclerView recyclerView;
    private TextView userName;
    private ArrayList<NotesModel> notesModels = new ArrayList<>();
    private DbCreater dbCreater = new DbCreater(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Setting User Name
        userName = findViewById(R.id.tv_user_name);
        if(SaveSharedPreference.getUserName(this)==null || SaveSharedPreference.getUserName(this).matches("")){
            userName.setVisibility(View.GONE);
        }
        else {
            userName.setVisibility(View.VISIBLE);
        userName.setText("Hi "+SaveSharedPreference.getUserName(this));}


        //Recycler View Settings
        recyclerView = findViewById(R.id.recyclerViewNotesList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Getting notes from device databse
        notesModels = dbCreater.getNotes();

        //setting notes in the adapter
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_notes_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_logout:

                SaveSharedPreference.setSignedIn(context,false);
                SaveSharedPreference.clearAll(context);
                Intent intent = new Intent(context, GoogleLoginActivity.class);
                startActivity(intent);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
