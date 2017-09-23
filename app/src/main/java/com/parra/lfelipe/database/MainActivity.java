package com.parra.lfelipe.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout llRestaurantes, llHospitales, llBarberias, llBares, llUniversidades, llVeterinarias, llCompras, llCines, llHoteles;
    private String consulta = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llRestaurantes = (LinearLayout) findViewById(R.id.llRestaurante);
        llHospitales = (LinearLayout) findViewById(R.id.llHospitales);
        llBarberias = (LinearLayout) findViewById(R.id.llBarberias);
        llBares = (LinearLayout) findViewById(R.id.llBares);
        llVeterinarias = (LinearLayout) findViewById(R.id.llVeterinarias);
        llUniversidades = (LinearLayout)findViewById(R.id.llUniversidades);
        llCompras = (LinearLayout) findViewById(R.id.llCompras);
        llCines = (LinearLayout) findViewById(R.id.llCine);
        llHoteles = (LinearLayout) findViewById(R.id.llHoteles);
    }

    public void listarLugares(View view) {
        int id = view.getId();

        switch(id){
            case R.id.llRestaurante:
                consulta = "restaurantes";
                break;
            case R.id.llHospitales:
                consulta = "hospitales";
                break;
            case R.id.llBarberias:
                consulta = "barberias";
                break;
            case R.id.llBares:
                consulta = "bares";
                break;
            case R.id.llVeterinarias:
                consulta = "veterinarias";
                break;
            case R.id.llUniversidades:
                consulta = "universidades";
                break;
            case R.id.llCompras:
                consulta = "compras";
                break;
            case R.id.llCine:
                consulta = "cines";
                break;
            case R.id.llHoteles:
                consulta = "hoteles";
                break;
        }

        Intent intent = new Intent(MainActivity.this,ListaLugares.class);
        intent.putExtra("consulta",consulta);
        startActivity(intent);
    }
}
