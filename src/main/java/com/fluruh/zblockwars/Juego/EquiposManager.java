package com.fluruh.zblockwars.Juego;

import com.fluruh.zblockwars.Jugador.Jugador;
import org.bukkit.Location;

import java.util.ArrayList;

public class EquiposManager {

    private ArrayList<Jugador> jugadores;
    private String colorEquipo;
    private Location spawnEquipos;

    public EquiposManager(String colorEquipo) {
        this.jugadores = new ArrayList<Jugador>();
        this.colorEquipo = colorEquipo;
        this.spawnEquipos = spawnEquipos;
    }

    public void setColorEquipo(String colorEquipo) {
        this.colorEquipo = colorEquipo;
    }
    public String getColorEquipo() {
        return this.colorEquipo;
    }
    public boolean contieneJugador(String nombreJugador) {
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).getJugador().getName().equals(nombreJugador)) {
                return true;
            }
        }
        return false;
    }
    public boolean agregarJugador(Jugador jugador) {
        if (!contieneJugador(jugador.getJugador().getName())) {
            jugadores.add(jugador);
            return true;
        }
        return false;
    }
    public boolean removerJugador(Jugador jugador) {
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).getJugador().getName().equals(jugador)) {
                jugadores.remove(jugador);
                return true;
            }
        }
        return false;
    }
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
    public Location getSpawnEquipos() {
        return spawnEquipos;
    }
    public void setSpawnEquipos(Location spawnEquipos) {
        this.spawnEquipos = spawnEquipos;
    }
    public int getCantidadJugadores() {
        return this.jugadores.size();
    }
    public int getBanderasCapturadas() {
        int banderasCapturadas = 0;
        for (Jugador j : this.jugadores) {
            banderasCapturadas = banderasCapturadas + j.getBanderasCapturadas();
        }
        return banderasCapturadas;
    }
}
