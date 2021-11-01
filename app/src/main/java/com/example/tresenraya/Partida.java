package com.example.tresenraya;


import java.util.Random;

public class Partida {

    private final int dificultad;
    private int jugadores;
    private int[] arrayOcupaciones = new int[9];
    private final int[][] COMBINACIONES_GANADORAS = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    private int interruptorJugadores;

    public Partida(int dificultad) {
        this.dificultad = dificultad;
        interruptorJugadores = 1;

        for (int i = 0;i< arrayOcupaciones.length; i++){
            arrayOcupaciones[i] = 0;
        }
    }


    public int getDificultad() {
        return dificultad;
    }

    public int getJugadores() {
        return jugadores;
    }

    public void setJugadores(int jugadores) {
        this.jugadores = jugadores;
    }

    public int IA(){

        int casilla = -1;

        casilla = dosEnRaya(2);

        if (casilla == -1) return casilla;

        if(dificultad > 1){
            casilla = dosEnRaya(1);

            if(casilla != -1) return casilla;
        }

        if(dificultad == 3){
            if(arrayOcupaciones[0] == 0) return 0;
            if(arrayOcupaciones[2] == 0) return 3;
            if(arrayOcupaciones[4] == 0) return 4;
            if(arrayOcupaciones[6] == 0) return 6;
        }

        int numAleatorio = new Random().nextInt(9);

        return numAleatorio;
    }

    public int getInterruptorJugadores() {
        return interruptorJugadores;
    }

    public boolean comprueba_casilla(int casilla){
        boolean returnador = false;
        if (arrayOcupaciones[casilla] == 0){
            arrayOcupaciones[casilla] = interruptorJugadores;
            returnador = true;
        }

        return returnador;
    }

    public int turno(){
        int estadoPartida = 0;
        boolean empate = true;
        boolean ultimo_movimiento = true;

        for(int i= 0;i<COMBINACIONES_GANADORAS.length;i++){
            for(int pos:COMBINACIONES_GANADORAS[i]){

                if(arrayOcupaciones[pos] != interruptorJugadores){
                    ultimo_movimiento = false;

                }

                if (arrayOcupaciones[pos] == 0){
                    empate = false;
                }
            }
            if(ultimo_movimiento) return interruptorJugadores;
            ultimo_movimiento = true;
        }
        if(empate){
            return 3;
        }

        interruptorJugadores++;
        if(interruptorJugadores > 2){
            interruptorJugadores = 1;
        }

        return estadoPartida;
    }

    public int dosEnRaya(int jugador_casilla){

        int casilla = -1;
        int cuantaslleva = 0;

        for(int i= 0;i<COMBINACIONES_GANADORAS.length;i++){
            for(int pos:COMBINACIONES_GANADORAS[i]){
                if(arrayOcupaciones[pos] == jugador_casilla) cuantaslleva += 1;
                if(arrayOcupaciones[0] == 0) casilla = pos;
            }
            if(cuantaslleva == 2 && casilla != -1) return casilla;

            casilla =-1;
            cuantaslleva = 0;
        }

        return casilla;
    }

}
