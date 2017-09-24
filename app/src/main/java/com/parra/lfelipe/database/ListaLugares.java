package com.parra.lfelipe.database;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListaLugares extends AppCompatActivity {

    private String consulta;
    private List<objLugar> lugares;
    private List<String> nombres;
    private ListView listview;
    private int posConsulta;

    public static final String TABLE_NAME = "Cuentenos";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_CUIDAD = "cuidad";
    public static final String COLUMN_NAME_NOMBRE = "nombre";
    public static final String COLUMN_NAME_TELEFONO = "telefono";
    public static final String COLUMN_NAME_DIRECCION = "direccion";
    public static final String COLUMN_NAME_TIPO = "tipo";


    public class objLugar{
        private String cuidad, nombre, telefono, direccion, tipo;
        public objLugar(String cuidad, String nombre, String telefono, String direccion, String tipo){
            this.cuidad = cuidad;
            this.nombre = nombre;
            this.telefono = telefono;
            this.direccion = direccion;
            this.tipo = tipo;
        }

    }

    public class FeedReaderDbHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "Cuentenos.db";

        public FeedReaderDbHelper(Context context) {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_lugares);
        Bundle extras = getIntent().getExtras();
        consulta = extras.getString("consulta");
        lugares = new ArrayList<objLugar>();
        nombres = new ArrayList<String>();
        listview = (ListView) findViewById(R.id.listView);
        Log.i("consulta",consulta);

        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplication());
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String[] projection = {
                COLUMN_NAME_ID ,
                COLUMN_NAME_CUIDAD,
                COLUMN_NAME_NOMBRE,
                COLUMN_NAME_TELEFONO,
                COLUMN_NAME_DIRECCION,
                COLUMN_NAME_CUIDAD,
                COLUMN_NAME_TIPO
        };


        String selection = COLUMN_NAME_TIPO + " = ?";
        String[] selectionArgs = {consulta};

        String sortOrder =
                COLUMN_NAME_CUIDAD + " DESC";

        Cursor cursor = db.query(
                TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );


        while(cursor.moveToNext()) {
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
            String cuidad = cursor.getString(cursor.getColumnIndexOrThrow("cuidad"));
            String telefono = cursor.getString(cursor.getColumnIndexOrThrow("telefono"));
            String direccion = cursor.getString(cursor.getColumnIndexOrThrow("direccion"));
            String tipo = cursor.getString(cursor.getColumnIndexOrThrow("tipo"));
            lugares.add(new objLugar(cuidad, nombre, telefono, direccion,tipo));
            nombres.add(nombre);
        }
        cursor.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombres);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String nombre = lugares.get(i).nombre;
                String cuidad = lugares.get(i).cuidad;
                String telefono = lugares.get(i).telefono;
                String direccion = lugares.get(i).direccion;
                Intent intent = new Intent(ListaLugares.this,infoLugar.class);
                intent.putExtra("nombre",nombre);
                intent.putExtra("cuidad",cuidad);
                intent.putExtra("telefono",telefono);
                intent.putExtra("direccion",direccion);
                startActivity(intent);
            }
        });
    }
}
