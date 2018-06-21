package com.example.luisr.dadomatematico.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.luisr.dadomatematico.R;
import com.example.luisr.dadomatematico.core.Partida;

import org.mozilla.javascript.*;

public class Turno2Activity extends AppCompatActivity {
    private boolean label = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turno2);
        //-------------------------------WIDGETS AND TEXT FIELDS---------------
        final Partida partida = (Partida)getIntent().getExtras().getSerializable("partida");

        final TextView tvJugador2 = (TextView) this.findViewById(R.id.tvJugador2);
        tvJugador2.setText("Jugador: "+partida.getJugador2());

        final TextView tvObjetivo = (TextView) this.findViewById(R.id.tvObjetivo2);
        final TextView tvCifras = (TextView) this.findViewById(R.id.tvCifras2);
        final EditText etExpresion = (EditText) this.findViewById(R.id.etExpresion2);
        final Button btTerminar = (Button) this.findViewById(R.id.btTerminar);
        final TextView tvTemporizador = (TextView) this.findViewById(R.id.tvTemporizador2);
        tvObjetivo.setText(this.getString(R.string.objetivo)+" "+partida.getObjetivo());
        tvCifras.setText(this.getString(R.string.numeros)+" "+partida.getDado6().getTirada()[0]+", "+partida.getDado6().getTirada()[1]+", "
                +partida.getDado6().getTirada()[2]+", "+partida.getDado6().getTirada()[3]+", "
                +partida.getDado6().getTirada()[4]+", "+partida.getDado6().getTirada()[5]);
        //-------------------------------------TEMPORIZADOR------------------------------------
        final CountDownTimer temporizador = new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                tvTemporizador.setText(Turno2Activity.this.getString(R.string.tiempo)+" " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                if(label==true) {
                    partida.setResultado1(null);
                    Intent intent = new Intent(Turno2Activity.this, Turno2Activity.class);
                    intent.putExtra("partida", partida);
                    startActivityForResult(intent, 0);
                    finish();
                }
            }
        };
        temporizador.start();
        //------------------------------------------BUTTON LISTENER----------------------------
        btTerminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String resultado2 = "";
            //---------------COMPROBACION CAMPO VACÍO Y CARACTERES VÁLIDOS-----------------
            if(etExpresion.getText().toString().isEmpty()){
                AlertDialog.Builder builder = new AlertDialog.Builder( Turno2Activity.this );
                builder.setTitle( Turno2Activity.this.getString(R.string.error) );
                builder.setMessage( Turno2Activity.this.getString(R.string.errorturno1) );
                builder.create().show();
            }else if(etExpresion.getText().toString().length()==1){
                AlertDialog.Builder builder = new AlertDialog.Builder( Turno2Activity.this );
                builder.setTitle( Turno2Activity.this.getString(R.string.error) );
                builder.setMessage( Turno2Activity.this.getString(R.string.errorturno2) );
                builder.create().show();
            }else{
                try {
                    //---------EVALUAR EXPRESION MATEMATICA----------------
                    resultado2 = calc(etExpresion.getText().toString(), partida);
                }catch (Exception e){
                    AlertDialog.Builder builder = new AlertDialog.Builder( Turno2Activity.this );
                    builder.setTitle( Turno2Activity.this.getString(R.string.error) );
                    builder.setMessage( e.getMessage());
                    builder.create().show();
                }
                if(!resultado2.equals("")) {
                    partida.setResultado2(resultado2);
                    Intent intent = new Intent(v.getContext(), FinalActivity.class);
                    intent.putExtra("partida", partida);
                    startActivityForResult(intent, 0);
                    finish();
                    temporizador.cancel();
                }
            }
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        label = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        label = true;
    }
    //-------------------------EVALUATE MATH EXPRESION----------------------
    public String calc(String expresion, Partida partida) throws Exception{
        Context rhino = Context.enter();
        boolean label0=false;
        boolean label1=false;
        boolean label2=false;
        boolean label3=false;
        boolean label4=false;
        boolean label5=false;
        rhino.setOptimizationLevel(-1);
        //----------------COMPROBACIÓN CARACTERES PERMITIDOS-----------------------------
        for(int i=1;i<expresion.length()+1;i++){
            if (!(expresion.substring((i-1),i).equals("+")) &&
                    !(expresion.substring((i-1),i).equals("*")) &&
                    !(expresion.substring((i-1),i).equals("-")) &&
                    !(expresion.substring((i-1),i).equals("/")) &&
                    !(expresion.substring((i-1),i).equals("(")) &&
                    !(expresion.substring((i-1),i).equals(")")) &&
                    !(expresion.substring((i-1),i).equals(partida.getDado6().getTirada()[0])) &&
                    !(expresion.substring((i-1),i).equals(partida.getDado6().getTirada()[1])) &&
                    !(expresion.substring((i-1),i).equals(partida.getDado6().getTirada()[2])) &&
                    !(expresion.substring((i-1),i).equals(partida.getDado6().getTirada()[3])) &&
                    !(expresion.substring((i-1),i).equals(partida.getDado6().getTirada()[4])) &&
                    !(expresion.substring((i-1),i).equals(partida.getDado6().getTirada()[5])))
            {
                throw new Exception(this.getString(R.string.errorturno3));
            }else if(expresion.substring((i-1),i).equals(partida.getDado6().getTirada()[0]) && label0==false){
                label0=true;
            } else if(expresion.substring((i-1),i).equals(partida.getDado6().getTirada()[1]) && label1==false){
                label1=true;
            } else if(expresion.substring((i-1),i).equals(partida.getDado6().getTirada()[2]) && label2==false){
                label2=true;
            } else if(expresion.substring((i-1),i).equals(partida.getDado6().getTirada()[3]) && label3==false){
                label3=true;
            } else if(expresion.substring((i-1),i).equals(partida.getDado6().getTirada()[4]) && label4==false){
                label4=true;
            } else if(expresion.substring((i-1),i).equals(partida.getDado6().getTirada()[5]) && label5==false){
                label5=true;
            } else if(!(expresion.substring((i-1),i).equals("+")) &&
                    !(expresion.substring((i-1),i).equals("*")) &&
                    !(expresion.substring((i-1),i).equals("-")) &&
                    !(expresion.substring((i-1),i).equals("/")) &&
                    !(expresion.substring((i-1),i).equals("(")) &&
                    !(expresion.substring((i-1),i).equals(")"))){
                throw new Exception(this.getString(R.string.errorturno3));
            }
        }
        Scriptable scope = rhino.initStandardObjects();

        String toRet = rhino.evaluateString(scope, expresion, "JavaScript", 1, null).toString();

        return toRet;
    }

    //---------------------------------OPTIONS MENU-----------------------------------
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
        builder.setMessage( this.getString(R.string.helpturno) );
        builder.create().show();
    }
}
