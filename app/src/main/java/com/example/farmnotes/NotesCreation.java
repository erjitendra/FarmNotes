package com.example.farmnotes;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.farmnotes.database.DbCreater;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesCreation extends AppCompatActivity {
    EditText etTitle, etContent;
    Button btn_create;
    private DbCreater dbCreater = new DbCreater(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_creation);

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setTitle("Create Notes");
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etTitle = findViewById(R.id.edit_text_title);
        etContent = findViewById(R.id.edit_text_content);
        btn_create = findViewById(R.id.button_notes_creation);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        String time = intent.getStringExtra("date");
        final Integer note_id = intent.getIntExtra("note_id", 0);


        //Changing Button text to update if we are updating any note
        if (title != null) {
            etTitle.setText(title);
            etContent.setText(content);
            btn_create.setText("Update");

        }


        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date date = new Date();
                    System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
                    NotesModel notesModel = new NotesModel(etTitle.getText().toString(), etContent.getText().toString(), dateFormat.format(date));
                    if (note_id == 0) {
                        dbCreater.createNotes(notesModel);
                    } else {
                        notesModel.setId(note_id);
                        dbCreater.updateNotes(notesModel);
                    }

                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                }

            }
        });


    }

    // Validating Edit text for Null or Empty
    public boolean validate() {
        boolean validate = true;
        String titleStr = etTitle.getText().toString();
        String contentStr = etContent.getText().toString();
        if (titleStr.isEmpty() || titleStr.matches("")) {
            etTitle.setError("Please Fill Title");
            validate = false;
        }
        if (contentStr.isEmpty() || contentStr.matches("")) {
            etContent.setError("Please Fill Content");
            validate = false;
        }

        return validate;
    }
}
