package com.example.tresenraya;




public class Partida {

    private final int dificultad;
    private int jugadores;

    public Partida(int dificultad) {
        this.dificultad = dificultad;
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
}
