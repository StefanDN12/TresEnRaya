package com.example.tresenraya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button buttonOnePlayer;
    private Button buttonTwoPlayer;
    private Button btnReinciar;
    private GridLayout panel;
    private TextView ganador;
    private int dificultad = 0;
    private int numeroJugadores = 0;
    Partida partida;
    private ImageView[] CasillasImagen = new ImageView[9];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ganador = findViewById(R.id.txt_ganador);
        ganador.setEnabled(false);
        ganador.setAlpha(0);
        btnReinciar = findViewById(R.id.btn_reiniciar);
        btnReinciar.setEnabled(false);
        btnReinciar.setAlpha(0);


        panel = findViewById(R.id.gridLayout);

        reiniciarPartida();

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
            partida.setJugadores(numeroJugadores);
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
            for(int i = 0; i<CasillasImagen.length; i++){
                int a = i;
                CasillasImagen[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(partida != null){
                            if(partida.getJugadores() == 2){
                                if(partida.comprueba_casilla(a)){
                                    if(partida.getInterruptorJugadores() == 1){
                                        CasillasImagen[a].setImageResource(R.drawable.aspa);
                                        comprobarGanador();
                                    }else{
                                        CasillasImagen[a].setImageResource(R.drawable.circulo);
                                        comprobarGanador();
                                    }
                                }
                            }else{
                                if (partida.getDificultad() != 0){
                                    if(partida.comprueba_casilla(a)){
                                        CasillasImagen[a].setImageResource(R.drawable.aspa);
                                        comprobarGanador();
                                        InteligenciaArtificial();
                                    }
                                }
                            }
                        }
                    }
                });
            }
    }

    public void InteligenciaArtificial(){
        for (int i = 0; i<CasillasImagen.length; i++) {
            int aleatoriedad = partida.IA();
            if (partida.comprueba_casilla(aleatoriedad)) {
                CasillasImagen[aleatoriedad].setImageResource(R.drawable.circulo);
                comprobarGanador();
                break;
            }
        }
    }

    public void comprobarGanador(){
        switch (partida.turno()){
            case 1:
                panel.setEnabled(false);
                panel.setAlpha(0);
                ganador.setEnabled(true);
                btnReinciar.setEnabled(true);
                ganador.setAlpha(1);
                btnReinciar.setAlpha(1);
                ganador.setText("Jugador 1 Ganador");
                break;
            case 2:
                panel.setEnabled(false);
                ganador.setEnabled(true);
                panel.setAlpha(0);
                btnReinciar.setEnabled(true);
                ganador.setAlpha(1);
                btnReinciar.setAlpha(1);
                ganador.setText("Jugador 2 Ganador");
                break;
                case 3:
                panel.setEnabled(false);
                ganador.setEnabled(true);
                panel.setAlpha(0);
                btnReinciar.setEnabled(true);
                ganador.setAlpha(1);
                btnReinciar.setAlpha(1);
                ganador.setText("Empate");
                break;
        }
    }
    public void reiniciarPartida(){

        btnReinciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panel.setEnabled(true);
                ganador.setEnabled(false);
                panel.setAlpha(1);
                btnReinciar.setEnabled(false);
                ganador.setAlpha(0);
                btnReinciar.setAlpha(0);

                ((Button) findViewById(R.id.btn_unJugador)).setEnabled(true);
                ((Button) findViewById(R.id.btn_dosJugadores)).setEnabled(true);
                ((RadioGroup) findViewById(R.id.radioGroup)).setAlpha(1);

                for(ImageView cadaCasilla:CasillasImagen){
                    cadaCasilla.setImageResource(R.drawable.casilla);
                }

                for (int i = 0; i<CasillasImagen.length; i++ ){
                    String casilla = "a"+(i+1);
                    int getCasillaResource = getResources().getIdentifier(casilla,"id",getPackageName());
                    CasillasImagen[i] = ((ImageView) findViewById(getCasillaResource));
                }
            }

        });


    }
}