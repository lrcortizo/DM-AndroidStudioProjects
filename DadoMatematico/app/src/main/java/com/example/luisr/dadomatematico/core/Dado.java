package com.example.luisr.dadomatematico.core;
import java.io.Serializable;
import java.math.*;

/**
 * Created by luisr on 08/11/2016.
 */

public class Dado implements Serializable{
    private int numCaras;
    private String [] tirada;

    public Dado(int n){
        numCaras=n;
        tirada = new String[n];
    }

    public void lanzarDado(){
        if(numCaras==6){
            for(int i=0;i<6;i++) {
                int resultado = 0;
                resultado = (int) (Math.random() * numCaras + 1);
                StringBuilder sb = new StringBuilder();
                sb.append(resultado);
                tirada[i]=sb.toString();
            }
        }else {
            for (int i = 0; i < 2; i++) {
                int resultado = 0;
                resultado = (int) (Math.random() * numCaras + 1);
                StringBuilder sb = new StringBuilder();
                sb.append(resultado);
                tirada[i] = sb.toString();
            }
        }
    }

    public String[] getTirada() {
        return tirada;
    }
}
