package com.example.luisr.dadomatematico.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.luisr.dadomatematico.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
//-------------------------------WIDGETS AND TEXT FIELDS---------------
        final Button btGame = (Button) this.findViewById( R.id.btGame );
        final Button btScore = (Button) this.findViewById( R.id.btScore );
        final Button btExit = (Button) this.findViewById( R.id.btExit );
        final Button btLanguage = (Button) this.findViewById( R.id.btLanguage );
        final Button btInstructions = (Button) this.findViewById( R.id.btInstructions );
//-----------------------------------BUTTON LISTENERS---------------------------------------------
        btGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), PlayersActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        btScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), HistoryActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        btLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), LanguageActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        btInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), InstructionsActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
    }

}