package com.example.luisr.dadomatematico.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.luisr.dadomatematico.R;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {
    private Locale locale;
    private Configuration config = new Configuration();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        //-------------------------------WIDGETS AND TEXT FIELDS-------------------
        final Button btEnglish = (Button) findViewById(R.id.btEnglish);
        final Button btSpanish = (Button) findViewById(R.id.btSpanish);
        final Button btGalician = (Button) findViewById(R.id.btGalician);
        //--------------------------------------BUTTON LISTENERS--------------------
        btEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locale = new Locale("en");
                config.locale = locale;
                getResources().updateConfiguration(config, null);
                Intent refresh = new Intent(v.getContext(), MainActivity.class);
                startActivity(refresh);
                finish();
            }
        });
        btSpanish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locale = new Locale("es");
                config.locale = locale;
                getResources().updateConfiguration(config, null);
                Intent refresh = new Intent(v.getContext(), MainActivity.class);
                startActivity(refresh);
                finish();
            }
        });
        btGalician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locale = new Locale("gl");
                config.locale = locale;
                getResources().updateConfiguration(config, null);
                Intent refresh = new Intent(v.getContext(), MainActivity.class);
                startActivity(refresh);
                finish();
            }
        });
    }
}
