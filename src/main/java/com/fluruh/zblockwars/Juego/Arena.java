package com.fluruh.zblockwars.Juego;

import com.fluruh.zblockwars.Jugador.Jugador;
import com.fluruh.zblockwars.Managers.ArchivosManager;
import com.fluruh.zblockwars.Managers.MurallaManager;
import com.fluruh.zblockwars.Managers.UbicacionManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class Arena {
    private EquiposManager equipoUno;
    private EquiposManager equipoDos;
    private String nombreArena;
    private Location lobbyArena;//
    private MurallaManager ubicacionMuralla;
    private EstadoArena estadoArena;
    private int cantidadMaximaJugadores;
    private int cantidadMinimaJugadores;
    private int cantidadActualJugadores;
    private int tiempo;
    private int tiempoMaximo;
    private ArchivosManager am;

    // ... (otros atributos que necesites, como equipos, jugadores, etc.)

    public Arena(String nombreArena, String equipoUno, String equipoDos, int cantidadMinimaJugadores, int cantidadMaximaJugadores) {
        this.equipoUno = new EquiposManager(equipoUno);
        this.equipoDos = new EquiposManager(equipoDos);
        this.nombreArena = nombreArena;
        this.lobbyArena = UbicacionManager.getIns().stringToLocation("Arenas." + nombreArena + ".Lobby");
        this.ubicacionMuralla = new MurallaManager(UbicacionManager.getIns().stringToLocation(am.getArenas().getString("Arenas." + nombreArena + ".Muralla.murallaEsquinaUno")),
                UbicacionManager.getIns().stringToLocation(am.getArenas().getString("Arenas." + nombreArena + ".Muralla.murallaEsquinaDos")));
        this.estadoArena = EstadoArena.DESACTIVADA;
        this.cantidadMinimaJugadores = cantidadMinimaJugadores;
        this.cantidadMaximaJugadores = cantidadMaximaJugadores;
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
                    Block block = mundo.getBlockAt(X, Y, Z);
                    block.setType(Material.AIR);
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

    // Métodos para cambiar el estado de la arena
    public void setEstado(EstadoArena nuevoEstado) {
        this.estadoArena = nuevoEstado;
        // ... (acciones adicionales según el nuevo estado)
    }

    public void agregarJugador(Jugador jugador) {

    }
    // Getters y setters para los atributos
    // ...
    // Otros métodos para gestionar la arena (e.g., añadir/eliminar jugadores, capturar banderas)
    // ...
}

