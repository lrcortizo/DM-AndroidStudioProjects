package com.example.luisr.dadomatematico.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.luisr.dadomatematico.R;

public class InstructionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        //-------------------------------WIDGETS AND TEXT FIELDS-------------------
        final TextView tvInstructions = (TextView) this.findViewById(R.id.tvInstructions);
        tvInstructions.setText(this.getString(R.string.instrucciones));
    }
}
