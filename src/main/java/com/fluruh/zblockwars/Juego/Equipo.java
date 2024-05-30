package com.fluruh.zblockwars.Juego;

import com.fluruh.zblockwars.Jugador.Jugador;
import org.bukkit.Location;

import java.util.ArrayList;

public class Equipo {

    private ArrayList<Jugador> jugadoresEquipo;
    private int maxJugadoresEquipo;
    private String colorEquipo;
    private Location spawnEquipo;

    public Equipo() {
        this.jugadoresEquipo = new ArrayList<>();
    }

    public void setColorEquipo(String colorEquipo) {
        this.colorEquipo = colorEquipo;
    }
    public String getColorEquipo() {
        return this.colorEquipo;
    }
    public boolean contieneJugador(Jugador jugador) {
        return jugadoresEquipo.contains(jugador);
    }
    public boolean agregarJugador(Jugador jugador) {
        if (!contieneJugador(jugador)) {
            jugadoresEquipo.add(jugador);
            return true;
        }
        return false;
    }
    public boolean removerJugador(Jugador jugador) {
        if (contieneJugador(jugador)) {
            jugadoresEquipo.remove(jugador);
            return true;
        }
        return false;
    }
    public boolean estaLleno() {
        return jugadoresEquipo.size() >= getMaxJugadoresEquipo();
    }
    public ArrayList<Jugador> getJugadoresEquipo() {
        return jugadoresEquipo;
    }
    public Location getSpawnEquipo() {
        return spawnEquipo;
    }
    public void setSpawnEquipo(Location spawnEquipo) {
        this.spawnEquipo = spawnEquipo;
    }
    public int getMaxJugadoresEquipo() {
        return maxJugadoresEquipo;
    }
    public void setMaxJugadoresEquipo(int maxJugadoresEquipo) {
        this.maxJugadoresEquipo = maxJugadoresEquipo;
    }
    public int getCantidadJugadores() {
        return this.jugadoresEquipo.size();
    }
    public int getBanderasCapturadas() {
        int banderasCapturadas = 0;
        for (Jugador j : this.jugadoresEquipo) {
            banderasCapturadas = banderasCapturadas + j.getBanderasCapturadas();
        }
        return banderasCapturadas;
    }
}
