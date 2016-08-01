package com.alonsodiez.miscontactos;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contacto> contactos;
    private TextInputEditText tvNombre;
    int request_code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*        Bundle parametros = getIntent().getExtras();
        String nombre = parametros.getString(getResources().getString(R.string.pnombre_contacto));
        tvNombre = (TextInputEditText) findViewById(R.id.campo_nombre);
        tvNombre.setText(nombre);*/
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(getResources().getString(R.string.pnombre_contacto), tvNombre.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        tvNombre.setText(savedInstanceState.getString(getResources().getString(R.string.pnombre_contacto)));
    }

    public void verValores(View v){
        EditText nombre = (EditText) findViewById(R.id.campo_nombre);
        //EditText fecha = (EditText) findViewById(R.id.campo_fecha);
        EditText telefono = (EditText) findViewById(R.id.campo_telefono);
        EditText email = (EditText) findViewById(R.id.campo_correo);
        EditText descripcion = (EditText) findViewById(R.id.campo_detalle);
        DatePicker dpFecha = (DatePicker) findViewById(R.id.dpFecha);
        int day = dpFecha.getDayOfMonth();
        int month = dpFecha.getMonth();
        int year =  dpFecha.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        String sFecha = Integer.toString(day).concat(" / ").concat(Integer.toString(month+1)).concat(" / ").concat(Integer.toString(year));

        Contacto contacto = new Contacto(nombre.getText().toString(), sFecha, telefono.getText().toString(), email.getText().toString(), descripcion.getText().toString());

        Intent intent = new Intent (MainActivity.this, DetalleContacto.class);
        intent.putExtra(getResources().getString(R.string.pnombre_contacto), contacto.getNombre());
        intent.putExtra(getResources().getString(R.string.pfecha), contacto.getFecha());
        intent.putExtra(getResources().getString(R.string.ptelefono), contacto.getTelefono());
        intent.putExtra(getResources().getString(R.string.pemail), contacto.getEmail());
        intent.putExtra(getResources().getString(R.string.pdescripcion), contacto.getDescripcion());

        startActivity(intent);

        finish();
    }
}