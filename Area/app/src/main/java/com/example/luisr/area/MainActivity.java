package com.example.luisr.area;

        import android.content.Context;
        import android.content.SharedPreferences;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText edLado1 = (EditText) this.findViewById( R.id.edLado1 );
        final EditText edLado2 = (EditText) this.findViewById( R.id.edLado2 );
        final EditText edResultado = (EditText) this.findViewById( R.id.edResultado );
        Button btCalcula = (Button) this.findViewById( R.id.btCalcula );

        btCalcula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double l1 = Double.parseDouble( edLado1.getText().toString() );
                double l2 = Double.parseDouble( edLado2.getText().toString() );
                String res = Double.toString( l1 * l2 );
                edResultado.setText( res );
            }
        });


    }
    @Override
    public void onResume(){
        super.onResume();

        final EditText edLado1 = (EditText) this.findViewById( R.id.edLado1 );
        final EditText edLado2 = (EditText) this.findViewById( R.id.edLado2 );
        final EditText edResultado = (EditText) this.findViewById( R.id.edResultado );


        SharedPreferences prefs = this.getPreferences(Context.MODE_PRIVATE);
        double lado1 = prefs.getFloat("Lado1", (float)0.0);
        double lado2 = prefs.getFloat("Lado2", (float)0.0);

        edLado1.setText(Double.toString(lado1));
        edLado1.setText(Double.toString(lado2));

        edResultado.setText(Double.toString(lado1*lado2));

    }
    public void onPause(){
        super.onPause();

        final EditText edLado1 = (EditText) this.findViewById( R.id.edLado1 );
        final EditText edLado2 = (EditText) this.findViewById( R.id.edLado2 );
        SharedPreferences prefs = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor saver = prefs.edit();
        saver.putFloat("Lado1", Float.parseFloat( edLado1.getText().toString()));
        saver.putFloat("Lado2", Float.parseFloat( edLado2.getText().toString()));
        saver.apply();

    }
}
