package com.example.farmnotes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.farmnotes.NotesModel;

import java.util.ArrayList;

public class DbCreater extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "farmnotes.db";
    public static final int DATABASE_VERSION = 1;
    private String DROP_NOTES_TABLE = "DROP TABLE IF EXISTS " + FarmNotesTable.FARM_TABLE_NAME;


    public DbCreater(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_FARM_NOTES_TABLE = "CREATE TABLE IF NOT EXISTS " + FarmNotesTable.FARM_TABLE_NAME + " ("
                + FarmNotesTable.FARM_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + FarmNotesTable.FARM_COLUMN_TITLE + " TEXT NOT NULL, "
                + FarmNotesTable.FARM_COLUMN_CONTENT + " TEXT NOT NULL, "
                + FarmNotesTable.FARM_COLUMN_NOTES_TIME + " TEXT NOT NULL);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_FARM_NOTES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);

    }

    public void createNotes(NotesModel note) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FarmNotesTable.FARM_COLUMN_TITLE, note.getTitle());
        values.put(FarmNotesTable.FARM_COLUMN_CONTENT, note.getContent());
        values.put(FarmNotesTable.FARM_COLUMN_NOTES_TIME, note.getDate());

        // Inserting Row
        db.insert(FarmNotesTable.FARM_TABLE_NAME, null, values);
        db.close();
    }

    // Used for updating notes in sqlite db
    public void updateNotes(NotesModel note) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FarmNotesTable.FARM_COLUMN_TITLE, note.getTitle());
        values.put(FarmNotesTable.FARM_COLUMN_CONTENT, note.getContent());
        values.put(FarmNotesTable.FARM_COLUMN_NOTES_TIME, note.getDate());

        // Updating Row
        db.update(FarmNotesTable.FARM_TABLE_NAME, values, "_id=" + note.getId(), null);
        db.close();
    }

    // Used for getting notes from sqlite db
    public ArrayList<NotesModel> getNotes() {
        ArrayList<NotesModel> notesList = new ArrayList<>();

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String selectQuerySQLCommand = "SELECT * FROM " + FarmNotesTable.FARM_TABLE_NAME + " ORDER BY _id DESC";
            Cursor cursor = db.rawQuery(selectQuerySQLCommand, null);
            while (cursor.moveToNext()) {

                String title = cursor.getString(cursor.getColumnIndex(FarmNotesTable.FARM_COLUMN_TITLE));
                String content = cursor.getString(cursor.getColumnIndex(FarmNotesTable.FARM_COLUMN_CONTENT));
                String creation_time = cursor.getString(cursor.getColumnIndex(FarmNotesTable.FARM_COLUMN_NOTES_TIME));
                Integer notes_id = cursor.getInt(cursor.getColumnIndex(FarmNotesTable.FARM_COLUMN_ID));
                NotesModel note = new NotesModel(title, content, creation_time);
                note.setId(notes_id);
                notesList.add(note);
            }
            cursor.close();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return notesList;

    }

}
