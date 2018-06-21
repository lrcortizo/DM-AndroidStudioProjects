package com.example.luisr.dadomatematico.core;

import android.app.Application;

/**
 * Created by luisr on 12/12/2016.
 */

public class App extends Application {
    private SQLite db;
    @Override
    public void onCreate() {
        super.onCreate();
        this.db = new SQLite( this.getApplicationContext() );
    }
    /** @return the database open */
    public SQLite getDb() {
        return this.db;
    }
}
