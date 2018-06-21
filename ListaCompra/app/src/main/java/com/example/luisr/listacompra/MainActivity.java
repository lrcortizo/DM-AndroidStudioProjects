package com.example.luisr.listacompra;

        import android.content.Context;
        import android.content.SharedPreferences;
        import android.graphics.Color;
        import android.support.v7.app.AppCompatActivity;
        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.os.Bundle;
        import android.view.ContextMenu;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.*;

        import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        this.items = new ArrayList<String>();

        Button btAdd = (Button) this.findViewById( R.id.btAdd );
        ListView lvItems = (ListView) this.findViewById( R.id.lvItems );

        lvItems.setClickable( true );
        this.itemsAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_selectable_list_item,
                this.items
        );
        lvItems.setAdapter( this.itemsAdapter );

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view,final int pos, long l ){
                    if(pos >=0){
                        AlertDialog.Builder alerta_dialogo;
                        alerta_dialogo = new AlertDialog.Builder(MainActivity.this);
                        final EditText editText = new EditText(MainActivity.this);

                        alerta_dialogo.setTitle("Nuevo Valor");
                        alerta_dialogo.setMessage("Introduce el nuevo valor");
                        alerta_dialogo.setView(editText);
                        alerta_dialogo.setPositiveButton("Modificar", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which){
                                final String texto = editText.getText().toString();

                                MainActivity.this.items.set(pos, texto);
                            }

                        });
                        alerta_dialogo.setNegativeButton("Cancel", null);
                        alerta_dialogo.create().show();

                    }
                }
            }
        );

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.onAdd();
            }
        });
        this.registerForContextMenu(lvItems);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        this.getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        boolean toret = false;

        switch (menuItem.getItemId()) {
            case R.id.main_op_inserta:
                toret = true;
                onAdd();
                break;
            case R.id.main_op_elimina_ultimo:
                if (this.items.size() > 0) {
                    this.items.remove(this.items.size());
                    this.itemsAdapter.notifyDataSetChanged();
                    toret = true;

                }
                break;
        }
        return toret;
    }
        @Override
        public boolean onContextItemSelected(MenuItem menuItem){
            boolean toret = false;

            int pos= ((AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo()).position;

            switch(menuItem.getItemId()){
                case R.id.context_op_elimina:
                    this.elimina(pos);
                    toret=true;
                    break;
            }
            return toret;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo cmi){
        super.onCreateContextMenu(menu,view,cmi);
        if(view.getId()==R.id.lvItems){
            this.getMenuInflater().inflate(R.menu.contextual_menu, menu);
            menu.setHeaderTitle(R.string.app_name);
        }
    }

    private void onAdd() {
        final EditText edText = new EditText( this );

        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle("A comprar...");
        builder.setMessage( "Nombre" );
        builder.setView( edText );
        builder.setPositiveButton( "+", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final String text = edText.getText().toString();

                MainActivity.this.itemsAdapter.add( text );
                MainActivity.this.updateStatus();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }

    private void elimina(int pos)
    {
        if(pos>=0){
            MainActivity.this.items.remove(pos);
            MainActivity.this.itemsAdapter.notifyDataSetChanged();
            MainActivity.this.updateStatus();
        }
    }

    private void updateStatus() {
        TextView txtNum = (TextView) this.findViewById( R.id.lblNum );
        txtNum.setText( Integer.toString( this.itemsAdapter.getCount() ) );
    }

    private ArrayAdapter<String> itemsAdapter;
    private ArrayList<String> items;


}