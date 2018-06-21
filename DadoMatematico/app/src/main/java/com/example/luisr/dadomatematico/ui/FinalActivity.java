package com.example.luisr.dadomatematico.ui;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.luisr.dadomatematico.R;
import com.example.luisr.dadomatematico.core.Partida;
import com.example.luisr.dadomatematico.core.SQLite;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;


public class FinalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        //-------------------------------WIDGETS AND TEXT FIELDS------------------
        final Partida partida = (Partida)getIntent().getExtras().getSerializable("partida");
        final TextView tvObjetivo = (TextView) findViewById(R.id.tvObjetivo);
        final TextView tvResultado1 = (TextView) findViewById(R.id.tvResultado1);
        final TextView tvResultado2 = (TextView) findViewById(R.id.tvResultado2);
        final TextView tvGanador = (TextView) findViewById(R.id.tvGanador);
        final Button btFin = (Button) findViewById(R.id.btFin);
        tvObjetivo.setText(this.getString(R.string.objetivo)+" "+partida.getObjetivo());
        tvResultado1.setText(this.getString(R.string.resultado)+" "+partida.getJugador1()+": "+partida.getResultado1());
        tvResultado2.setText(this.getString(R.string.resultado)+" "+partida.getJugador2()+": "+partida.getResultado2());
        //-------------------------------MOSTRAR GANADOR---------------------
        if (partida.ganador() == "Empate") {
            tvGanador.setText(this.getString(R.string.empate)+" "+partida.getJugador1()+" "+this.getString(R.string.y)+" "+partida.getJugador2());
        }else if(partida.ganador() == partida.getJugador1()){
            tvGanador.setText(partida.getJugador1()+" "+this.getString(R.string.ganar)+" "+partida.getJugador2());
        }else{
            tvGanador.setText(partida.getJugador2()+" "+this.getString(R.string.ganar)+" "+partida.getJugador1());
        }
        //------------------------------OBTENER FECHA Y HORA-----------------------
        Date d = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        String fecha = hourdateFormat.format(d);
        //-----------------------------ACTUALIZAR BASE DE DATOS----------------------------
        Queue<String> cola = new LinkedList<>();
        SQLite sqlDb = new SQLite(this.getApplicationContext());
        SQLiteDatabase db = sqlDb.getReadableDatabase();
        Cursor cursor = db.rawQuery( "SELECT fecha FROM historial", null );
        if ( cursor.moveToFirst() ) {
            do {
                String date = cursor.getString( 0 );
                cola.add(date);
            } while ( cursor.moveToNext() );
            cursor.close();
        }
        if(cola.size()==10){
            db = sqlDb.getWritableDatabase();
            db.execSQL( "DELETE FROM historial WHERE fecha = ?", new String[]{ cola.remove() } );
        }
        db = sqlDb.getWritableDatabase();
        db.execSQL( "INSERT OR IGNORE INTO historial(fecha, resultado) VALUES( ?, ? )",
                new String[]{ fecha, partida.ganador() } );
        //------------------------------------------BUTTON LISTENER-----------------------------
        btFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Activity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Activity);
                finish();
            }
        });
    }
}
