package com.example.farmnotes.database;

import android.provider.BaseColumns;

public class FarmNotesTable implements BaseColumns {
    public final static String FARM_TABLE_NAME = "farm_notes";
    public final static String FARM_COLUMN_ID = BaseColumns._ID;
    public final static String FARM_COLUMN_TITLE = "title";
    public final static String FARM_COLUMN_CONTENT = "content";
    public final static String FARM_COLUMN_NOTES_TIME = "time";

    public FarmNotesTable() {
    }
}
