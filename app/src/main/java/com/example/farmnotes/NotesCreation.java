package com.example.farmnotes;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.farmnotes.database.DbCreater;

public class NotesCreation extends AppCompatActivity {
    EditText etTitle,etContent;
    Button btn_create;
    private DbCreater dbCreater = new DbCreater(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_creation);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etTitle = findViewById(R.id.et_add_title);
        etContent = findViewById(R.id.et_add_content);
        btn_create = findViewById(R.id.btn_create_note);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        String time = intent.getStringExtra("date");
        final Integer note_id = intent.getIntExtra("note_id", 0);

        if(title!=null){
            etTitle.setText(title);
            etContent.setText(content);
            btn_create.setText("Update");

        }





        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotesModel notesModel = new NotesModel(etTitle.getText().toString(),etContent.getText().toString(),"11/12/2019");
                if(note_id==0){
                    dbCreater.createNotes(notesModel);
                }
                else {
                    notesModel.setId(note_id);
                    dbCreater.updateNotes(notesModel);
                }

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);

            }
        });



    }
}
