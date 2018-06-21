package com.example.luisr.coversortemperatura;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText ed_celsius = (EditText) this.findViewById( R.id.ed_celsius );
        final EditText ed_fahrenheit = (EditText) this.findViewById( R.id.ed_fahrenheit );

        ed_celsius.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(ed_celsius.isFocused()){
                    try {
                        double value = Double.parseDouble(ed_celsius.getText().toString());
                        ed_fahrenheit.setText("" + Math.round(value * 1.8 + 32));
                    }catch(NumberFormatException exc){ed_fahrenheit.setText("Error");}

                }
            }
        });
        ed_fahrenheit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(ed_fahrenheit.isFocused()){
                    try {
                        double value = Double.parseDouble(ed_fahrenheit.getText().toString());
                        ed_celsius.setText("" + Math.round((value-32)/1.8));
                    }catch(NumberFormatException exc){ed_celsius.setText("Error");}

                }
            }
        });
    }

}
