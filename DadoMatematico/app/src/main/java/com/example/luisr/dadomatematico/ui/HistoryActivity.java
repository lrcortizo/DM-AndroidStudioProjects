package com.example.luisr.dadomatematico.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.luisr.dadomatematico.R;
import com.example.luisr.dadomatematico.core.SQLite;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    private ArrayAdapter<String> historyAdapter;
    private ArrayList<String> history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        //--------------------MOSTRAR LISTA BASE DE DATOS---------------------
        ListView lvHistory = (ListView) this.findViewById( R.id.lvHistory );
        history = new ArrayList<>();
        SQLite sqlDb = new SQLite(this.getApplicationContext());
        SQLiteDatabase db = sqlDb.getReadableDatabase();
        Cursor cursor = db.rawQuery( "SELECT fecha, resultado FROM historial", null );
        if ( cursor.moveToFirst() ) {
            do {
                String partida = cursor.getString( 0 )+" ► "+cursor.getString( 1 );
                history.add(partida);
            } while ( cursor.moveToNext() );
            cursor.close();
        }
        historyAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,history);
        lvHistory.setAdapter(historyAdapter);
    }
    //---------------------------------OPTIONS MENU-----------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        this.getMenuInflater().inflate(R.menu.history_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        boolean toret = false;
        if (menuItem.getItemId()==R.id.opDelete){
            SQLite sqlDb = new SQLite(this.getApplicationContext());
            SQLiteDatabase db = sqlDb.getWritableDatabase();
            db.execSQL( "DELETE FROM historial" );
            toret = true;
            Intent refresh = new Intent(this, HistoryActivity.class);
            startActivity(refresh);
            finish();
        }
        return toret;
    }
}
