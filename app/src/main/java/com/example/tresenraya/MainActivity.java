package com.example.tresenraya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {


    private Button buttonOnePlayer;
    private Button buttonTwoPlayer;
    private int dificultad = 0;
    private int numeroJugadores = 0;
    Partida partida;
    private int interruptorJugadores=0;
    private ImageView[] CasillasImagen = new ImageView[9];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonOnePlayer = findViewById(R.id.btn_unJugador);
        buttonTwoPlayer = findViewById(R.id.btn_dosJugadores);

        for (int i = 0; i<CasillasImagen.length; i++ ){
            String casilla = "a"+(i+1);
            int getCasillaResource = getResources().getIdentifier(casilla,"id",getPackageName());
            CasillasImagen[i] = ((ImageView) findViewById(getCasillaResource));
        }
        aJugar();
        toque();
    }

    private void aJugar(){

        buttonOnePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroJugadores = 1;
                incializarPartida();
            }
        });
        buttonTwoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroJugadores = 2;
                incializarPartida();
            }
        });
    }

    public void incializarPartida(){
        for(ImageView cadaCasilla:CasillasImagen){
            cadaCasilla.setImageResource(R.drawable.casilla);
        }
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        int id = radioGroup.getCheckedRadioButtonId();

        if (id >= 0){
            dificultad = 1;
            if(id == R.id.btn_medio){
                dificultad = 2;
            }else if (id == R.id.btn_dificil){
                dificultad = 3;
            }
            partida = new Partida(dificultad);
            ((Button) findViewById(R.id.btn_unJugador)).setEnabled(false);
            ((Button) findViewById(R.id.btn_dosJugadores)).setEnabled(false);
            ((RadioGroup) findViewById(R.id.radioGroup)).setAlpha(0);
        }else{
            if(numeroJugadores == 2){
                partida = new Partida(dificultad);
                partida.setJugadores(numeroJugadores);
                ((Button) findViewById(R.id.btn_unJugador)).setEnabled(false);
                ((Button) findViewById(R.id.btn_dosJugadores)).setEnabled(false);
                ((RadioGroup) findViewById(R.id.radioGroup)).setAlpha(0);
            }
        }
    }

    public void toque(){
        int asp = R.drawable.aspa;
        int cir = R.drawable.circulo;

        for (ImageView casillas:CasillasImagen){
            casillas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(partida.getJugadores() == 2 && partida != null){
                        if (casillas.getTag() == null){
                            if(interruptorJugadores == 0){
                                casillas.setImageResource(R.drawable.aspa);
                                casillas.setTag(R.drawable.aspa);
                                interruptorJugadores +=1;
                                if(asp == (int)casillas.getTag()){
                                    casillas.setImageResource(R.drawable.aspa);
                                }
                            }else{
                                casillas.setImageResource(R.drawable.circulo);
                                casillas.setTag(R.drawable.circulo);
                                if(cir == (int)casillas.getTag()){
                                    casillas.setImageResource(R.drawable.circulo);
                                }
                                interruptorJugadores -=1;
                            }
                        }
                    }else{
                        casillas.setImageResource(R.drawable.aspa);
                    }
                }
            });
        }
    }



}