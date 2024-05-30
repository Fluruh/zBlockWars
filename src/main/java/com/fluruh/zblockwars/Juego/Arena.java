package com.fluruh.zblockwars.Juego;

import com.fluruh.zblockwars.Jugador.Jugador;
import com.fluruh.zblockwars.Managers.MurallaManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import java.util.ArrayList;
import java.util.Random;

public class Arena {
    // Variables definidas.
    private String nombreArena;
    private Location spawnArena;
    private Equipo equipoUno;
    private Equipo equipoDos;
    private MurallaManager ubicacionMuralla;
    private EstadoArena estadoArena;
    private int cantidadMaximaJugadores;
    private int cantidadMinimaJugadores;
    private int cantidadActualJugadores;
    private int tiempo;
    private int tiempoMaximo;
    // Arena
    public Arena(String nombreArena) {
        this.equipoUno = new Equipo();
        this.equipoDos = new Equipo();
        this.nombreArena = nombreArena;
        this.estadoArena = EstadoArena.DESACTIVADA;
        this.cantidadMinimaJugadores = 2;
        this.cantidadMaximaJugadores = 8;
        this.tiempo = 0;
        this.tiempoMaximo = 60;
        // ... (inicializar otros atributos)
    }
    public void destruirMuralla() {
        Location esquinaUno = ubicacionMuralla.getEsquinaUno();
        Location esquinaDos = ubicacionMuralla.getEsquinaDos();
        World mundo = esquinaUno.getWorld(); // Obtener el mundo de las esquinas
        int xMin = Math.min(esquinaUno.getBlockX(), esquinaDos.getBlockX());
        int yMin = Math.min(esquinaUno.getBlockY(), esquinaDos.getBlockY());
        int zMin = Math.min(esquinaUno.getBlockZ(), esquinaDos.getBlockZ());
        int xMax = Math.max(esquinaUno.getBlockX(), esquinaDos.getBlockX());
        int yMax = Math.max(esquinaUno.getBlockY(), esquinaDos.getBlockY());
        int zMax = Math.max(esquinaUno.getBlockZ(), esquinaDos.getBlockZ());
        // Iterar sobre todos los bloques entre las esquinas y destruir la muralla
        for (int X = xMin; X <= xMax; X++) {
            for (int Y = yMin; Y <= yMax; Y++) {
                for (int Z = zMin; Z <= zMax; Z++) {
                    if (mundo != null) {
                        Block block = mundo.getBlockAt(X, Y, Z);
                        block.setType(Material.AIR);
                    }
                }
            }
        }
    }
    public String getNombreArena() {
        return nombreArena;
    }
    public int getTiempo() {
        return tiempo;
    }
    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
    public void aumentarTiempo() {
        this.tiempo++;
    }
    public void disminuirTiempo(){
        this.tiempo--;
    }
    public int getTiempoMaximo() {
        return tiempoMaximo;
    }
    public void setTiempoMaximo(int tiempoMaximo) {
        this.tiempoMaximo = tiempoMaximo;
    }
    public void setEstado(EstadoArena nuevoEstado) {
        this.estadoArena = nuevoEstado;
    }
    public void removerJugadorArena(Jugador jugador) {
        if (equipoUno.removerJugador(jugador) || equipoDos.removerJugador(jugador)) {
            cantidadActualJugadores--;
        }
    }
    public void agregarJugadorArena(Jugador jugador) {
        if (estadoArena == EstadoArena.ESPERANDO || estadoArena == EstadoArena.COMENZANDO) {
            if (cantidadActualJugadores < cantidadMaximaJugadores) {
                if (cantidadActualJugadores == 0) {
                    Random random = new Random();
                    if (random.nextBoolean()) {
                        equipoUno.agregarJugador(jugador);
                    } else {
                        equipoDos.agregarJugador(jugador);
                    }
                } else {
                    if (equipoUno.getCantidadJugadores() <= equipoDos.getCantidadJugadores() && equipoUno.noEstaLleno()) {
                        equipoUno.agregarJugador(jugador);
                    } else if (equipoDos.noEstaLleno()) {
                        equipoDos.agregarJugador(jugador);
                    } else {
                        equipoUno.agregarJugador(jugador);
                    }
                }
                cantidadActualJugadores++;
            }
        }
    }
    public ArrayList<Jugador> getJugadoresArena() {
        ArrayList<Jugador> jugadoresArena = new ArrayList<>();
        jugadoresArena.addAll(equipoUno.getJugadoresEquipo());
        jugadoresArena.addAll(equipoDos.getJugadoresEquipo());
        return jugadoresArena;
    }
    public Jugador getJugadorArena(String jugador) {
        ArrayList<Jugador> jugadores = getJugadoresArena();
        for (Jugador j : jugadores) {
            if (j.getJugador().getName().equals(jugador)) {
                return j;
            }
        }
        return null;
    }
    public Equipo getEquipoJugador(String jugador) {
        ArrayList<Jugador> jugadoresEquipoUno = equipoUno.getJugadoresEquipo();
        ArrayList<Jugador> jugadoresEquipoDos = equipoDos.getJugadoresEquipo();
        for (Jugador j : jugadoresEquipoUno) {
            if (j.getJugador().getName().equals(jugador)) {
                return this.equipoUno;
            }
        }
        for (Jugador j : jugadoresEquipoDos) {
            if (j.getJugador().getName().equals(jugador)) {
                return this.equipoDos;
            }
        }
        return null;
    }
    public Equipo getEquipoUno() {
        return equipoUno;
    }
    public void setEquipoUno(Equipo equipoUno) {
        this.equipoUno = equipoUno;
    }
    public Equipo getEquipoDos() {
        return equipoDos;
    }
    public void setEquipoDos(Equipo equipoDos) {
        this.equipoDos = equipoDos;
    }
    public void setNombreArena(String nombreArena) {
        this.nombreArena = nombreArena;
    }
    public Location getSpawnArena() {
        return spawnArena;
    }
    public void setSpawnArena(Location spawnArena) {
        this.spawnArena = spawnArena;
    }
    public EstadoArena getEstadoArena() {
        return estadoArena;
    }
    public void setEstadoArena(EstadoArena estadoArena) {
        this.estadoArena = estadoArena;
    }
    public int getCantidadMaximaJugadores() {
        return cantidadMaximaJugadores;
    }
    public void setCantidadMaximaJugadores(int cantidadMaximaJugadores) {
        this.cantidadMaximaJugadores = cantidadMaximaJugadores;
    }
    public int getCantidadMinimaJugadores() {
        return cantidadMinimaJugadores;
    }
    public void setCantidadMinimaJugadores(int cantidadMinimaJugadores) {
        this.cantidadMinimaJugadores = cantidadMinimaJugadores;
    }
    public int getCantidadActualJugadores() {
        return cantidadActualJugadores;
    }
    public void setCantidadActualJugadores(int cantidadActualJugadores) {
        this.cantidadActualJugadores = cantidadActualJugadores;
    }
    public boolean estaIniciada() {
        return !estadoArena.equals(EstadoArena.DESACTIVADA) && !estadoArena.equals(EstadoArena.ESPERANDO) && !estadoArena.equals(EstadoArena.COMENZANDO);
    }
    public boolean estaLlena() {
        return cantidadActualJugadores == cantidadMaximaJugadores;
    }
    public boolean estaActivada() {
        return !estadoArena.equals(EstadoArena.DESACTIVADA);
    }
    public Equipo getGanador() {
        int banderasEquipoUno = equipoUno.getBanderasCapturadas();
        int banderasEquipoDos = equipoDos.getBanderasCapturadas();
        if (equipoUno.getJugadoresEquipo().isEmpty()) {
            return equipoDos;
        }
        if (equipoDos.getJugadoresEquipo().isEmpty()) {
            return equipoUno;
        } else if (banderasEquipoUno > banderasEquipoDos) {
            return equipoUno;
        } else {
            return equipoDos;
        }
    }
    public void setUbicacionMuralla(Location esquinaUno, Location esquinaDos) {
        ubicacionMuralla = new MurallaManager(esquinaUno, esquinaDos);
    }

    public MurallaManager getUbicacionMuralla() {
        return ubicacionMuralla;
    }
}
