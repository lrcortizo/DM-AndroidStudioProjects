package com.example.luisr.dadomatematico.ui;


import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.*;

import com.example.luisr.dadomatematico.R;
import com.example.luisr.dadomatematico.core.Dado;
import com.example.luisr.dadomatematico.core.Partida;

public class DiceActivity extends AppCompatActivity {
    private boolean label6 = false;
    private boolean label12 = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);
        //-------------------------------WIDGETS AND TEXT FIELDS------------------
        final Partida partida = (Partida)getIntent().getExtras().getSerializable("partida");
        final Button btPlay =(Button) this.findViewById(R.id.btPlay);
        final Button bt6 = (Button) this.findViewById( R.id.button6 );
        final Button bt12 = (Button) this.findViewById( R.id.button12 );
        final TextView tvDados6 = (TextView) this.findViewById(R.id.tvDados6);
        final TextView tvDados12 = (TextView) this.findViewById(R.id.tvDados12);
        final TextView tvDice = (TextView) this.findViewById(R.id.tvDice);
        //------------------------------LANZAR DADOS--------------------------
        final Dado dado6 = new Dado(6);
        final Dado dado12 = new Dado(12);
        dado6.lanzarDado();
        dado12.lanzarDado();
        //------------------------------------------BUTTON LISTENERS-----------------------------
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDados6.setText(DiceActivity.this.getString(R.string.dado6)+" "+dado6.getTirada()[0]+", "+dado6.getTirada()[1]+", "
                        +dado6.getTirada()[2]+", "+dado6.getTirada()[3]+", "+dado6.getTirada()[4]+", "+dado6.getTirada()[5]);
                label6=true;
            }
        });
        bt12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDados12.setText(DiceActivity.this.getString(R.string.dado12)+" "+dado12.getTirada()[0]+", "+dado12.getTirada()[1]);
                tvDice.setText(DiceActivity.this.getString(R.string.objetivo)+" "+(Integer.parseInt(dado12.getTirada()[0])*Integer.parseInt(dado12.getTirada()[1])));
                label12=true;
            }
        });
        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //-----------COMPROBACION DADOS LANZADOS---------------
                if(label6 && label12) {
                    partida.setDados(dado6, Integer.parseInt(dado12.getTirada()[0])*Integer.parseInt(dado12.getTirada()[1]));
                    Intent intent = new Intent(v.getContext(), Turno1Activity.class);
                    intent.putExtra("partida", partida);
                    startActivityForResult(intent, 0);
                    finish();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder( DiceActivity.this );
                    builder.setTitle( DiceActivity.this.getString(R.string.error) );
                    builder.setMessage( DiceActivity.this.getString(R.string.errordice) );
                    builder.create().show();

                }
            }
        });
    }
    //---------------------------OPTIONS MENU--------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        this.getMenuInflater().inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        boolean toret = false;
        switch (menuItem.getItemId()){
            case R.id.opHelp:
                help();
                toret = true;
                break;
            case R.id.opSalir:
                Intent Activity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Activity);
                finish();
                toret = true;
                break;
        }
        return toret;
    }

    public void help(){
        final TextView t = new TextView(this);
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle(this.getString(R.string.help));
        builder.setMessage(this.getString(R.string.helpdados));
        builder.create().show();

    }
}
