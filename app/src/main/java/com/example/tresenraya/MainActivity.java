package com.example.tresenraya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.sql.Array;

public class MainActivity extends AppCompatActivity {

    private Button buttonOnePlayer;
    private Button buttonTwoPlayer;
    private RadioGroup dificultad;
    private int numeroJugadores = 0;
    private ImageView[] CasillasImagen = new ImageView[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonOnePlayer = findViewById(R.id.btn_unJugador);
        buttonTwoPlayer = findViewById(R.id.btn_dosJugadores);
        dificultad = findViewById(R.id.radioGroup);
        dificultad.clearCheck();

        for (int i = 1; i<CasillasImagen.length; i++ ){
            String casilla = "a"+i;
            int getCasillaResource = getResources().getIdentifier(casilla,"id",getPackageName());
            CasillasImagen[i] = ((ImageView) findViewById(getCasillaResource));
        }
        radioGroupButtonClick(dificultad);
        start();
        aJugar();
    }

    private void start(){
        buttonOnePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumeroJugadores(1);
                Log.d("Pruebas", "El numero de jugadores es: " + String.valueOf(getNumeroJugadores()));
            }
        });

        buttonTwoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNumeroJugadores(2);
                Log.d("Pruebas", "El numero de jugadores es: " + String.valueOf(getNumeroJugadores()));
            }
        });
    }

    private void aJugar(){
        //Drawable casilla = getResources().getDrawable(R.drawable.casilla);

        for(ImageView cadaCasilla:CasillasImagen){
            cadaCasilla.setImageResource(R.drawable.casilla);
        }
    }

    private void radioGroupButtonClick(View view){
        Log.d("Click","Pulsado en el Grupo");
    }

    public int getNumeroJugadores() {
        return numeroJugadores;
    }

    public void setNumeroJugadores(int numeroJugadores) {
        this.numeroJugadores = numeroJugadores;
    }
}