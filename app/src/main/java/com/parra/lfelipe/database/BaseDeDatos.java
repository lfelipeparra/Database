package com.parra.lfelipe.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by felipe on 23/09/17.
 */

public class BaseDeDatos {

    public BaseDeDatos(Context cont){
        context=cont;
    }

    private Context context;

    public class FeedReaderDbHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "Cuentenos.db";

        public FeedReaderDbHelper() {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        public void onCreate(SQLiteDatabase db) {
            //db.execSQL(query);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            //db.execSQL(query);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }

    FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper();
    //SQLiteDatabase db = mDbHelper.getWritableDatabase();

    /*public void consultar(){
        String[] projection = {
                "id",
                "nombre",
                "cuidad"
        };


        String selection = "cuidad" + " = ?";
        String[] selectionArgs = {"Medellin"};

        String sortOrder =
                "cuidad" + " DESC";

        Cursor cursor = db.query(
                "Cuentenos",                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        List itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            String itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow("nombre"));
            itemIds.add(itemId);
            Log.i("nombre",itemId);
        }
        cursor.close();
    }*/
}
