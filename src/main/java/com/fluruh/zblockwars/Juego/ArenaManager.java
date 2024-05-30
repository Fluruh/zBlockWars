package com.fluruh.zblockwars.Juego;

import com.fluruh.zblockwars.Jugador.Jugador;
import com.fluruh.zblockwars.Main;
import com.fluruh.zblockwars.Managers.UbicacionManager;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;

public class ArenaManager {

    private final Main plugin;
    private ArrayList<Arena> listaArenas;

    public ArenaManager(Main plugin) {
        this.plugin = plugin;
    }

    public void agregarArena(Arena nombreArena) {
        listaArenas.add(nombreArena);
    }

    public void removerArena(String nombreArena) {
        for (int i = 0; i < listaArenas.size(); i++) {
            if (listaArenas.get(i).getNombreArena().equals(nombreArena)) {
                listaArenas.remove(i);
                break;
            }
        }
    }

    public ArrayList<Arena> getListaArenas() {
        return listaArenas;
    }

    public Arena getArena(String nombreArena) {
        for (Arena a : listaArenas) {
            if (a.getNombreArena().equals(nombreArena)) {
                return a;
            }
        }
        return null;
    }

    public Arena getArenaJugador(String nombreJugador) {
        for (Arena listaArena : listaArenas) {
            ArrayList<Jugador> jugadores = listaArena.getJugadoresArena();
            for (Jugador j : jugadores) {
                if (j.getJugador().getName().equals(nombreJugador)) {
                    return listaArena;
                }
            }
        }
        return null;
    }

    public void cargarArenas() {
        listaArenas = new ArrayList<>();
        FileConfiguration arenas = plugin.getArchivosManager().getArenas();
        ConfigurationSection arenasSection = arenas.getConfigurationSection("Arenas");
        if (arenasSection != null) {
            for (String key : arenasSection.getKeys(false)) {
                int minJugadores = arenas.getInt("Arenas." + key + ".minJugadores");
                int maxJugadores = arenas.getInt(("Arenas." + key + ".maxJugadores"));
                int tiempoMaximo = arenas.getInt(("Arenas." + key + ".tiempoMaximo"));
                Location lobbyArena = null;
                String lobbyArenaString = arenas.getString("Arenas." + key + ".lobbyArena");
                if (lobbyArenaString != null) {
                    lobbyArena = UbicacionManager.getIns().stringToLocation(lobbyArenaString);
                }
                String colorEquipoUno = arenas.getString("Arenas." + key + ".equipoUno.Color");
                Location spawnEquipoUno = null;
                String spawnEquipoUnoString = arenas.getString("Arenas." + key + ".equipoUno.Spawn");
                if (spawnEquipoUnoString != null) {
                    spawnEquipoUno = UbicacionManager.getIns().stringToLocation(spawnEquipoUnoString);
                }
                String colorEquipoDos = arenas.getString("Arenas." + key + ".equipoDos.Color");
                Location spawnEquipoDos = null;
                String spawnEquipoDosString = arenas.getString("Arenas." + key + ".equipoDos.Spawn");
                if (spawnEquipoDosString != null) {
                    spawnEquipoDos = UbicacionManager.getIns().stringToLocation(spawnEquipoDosString);
                }
                Location murallaEsquinaUno = null;
                String murallaEsquinaUnoString = arenas.getString("Arenas." + key + ".Muralla.esquinaUno");
                if (murallaEsquinaUnoString != null) {
                    murallaEsquinaUno = UbicacionManager.getIns().stringToLocation(murallaEsquinaUnoString);
                }
                Location murallaEsquinaDos = null;
                String murallaEsquinaDosString = arenas.getString("Arenas." + key + ".Muralla.esquinaDos");
                if (murallaEsquinaDosString != null) {
                    murallaEsquinaDos = UbicacionManager.getIns().stringToLocation(murallaEsquinaDosString);
                }
                Arena arena = new Arena(key);
                arena.setCantidadMinimaJugadores(minJugadores);
                arena.setCantidadMaximaJugadores(maxJugadores);
                arena.setTiempoMaximo(tiempoMaximo);
                arena.setSpawnArena(lobbyArena);
                arena.getEquipoUno().setColorEquipo(colorEquipoUno);
                arena.getEquipoUno().setSpawnEquipo(spawnEquipoUno);
                arena.getEquipoDos().setColorEquipo(colorEquipoDos);
                arena.getEquipoDos().setSpawnEquipo(spawnEquipoDos);
                arena.getUbicacionMuralla().setEsquinaUno(murallaEsquinaUno);
                arena.getUbicacionMuralla().setEsquinaDos(murallaEsquinaDos);
                boolean estaActivada = arenas.getBoolean("Arenas." + key + ".Activada", false);
                arena.setEstado(estaActivada ? EstadoArena.ESPERANDO : EstadoArena.DESACTIVADA);
                listaArenas.add(arena);
            }
        }
    }

    public void guardarArenas() {
        FileConfiguration arenas = plugin.getArchivosManager().getArenas();
        for (Arena arena : listaArenas) {
            String nombreArena = arena.getNombreArena();
            Location lobbyArena = arena.getSpawnArena();
            Location spawnEquipoUno = arena.getEquipoUno().getSpawnEquipo();
            Location spawnEquipoDos = arena.getEquipoDos().getSpawnEquipo();
            Location murallaEsquinaUno = arena.getUbicacionMuralla().getEsquinaUno();
            Location murallaEsquinaDos = arena.getUbicacionMuralla().getEsquinaDos();
            arenas.set("Arenas." + nombreArena + ".minJugadores", arena.getCantidadMinimaJugadores());
            arenas.set("Arenas." + nombreArena + ".maxJugadores", arena.getCantidadMaximaJugadores());
            arenas.set("Arenas." + nombreArena + ".maxTiempo", arena.getTiempoMaximo());
            if (murallaEsquinaUno != null && murallaEsquinaDos != null) {
                arenas.set("Arenas." + nombreArena + ".Muralla.esquinaUno", UbicacionManager.getIns().locationToString(murallaEsquinaUno));
                arenas.set("Arenas." + nombreArena + ".Muralla.esquinaDos", UbicacionManager.getIns().locationToString(murallaEsquinaDos));
            }
            if (lobbyArena != null) {
                arenas.set("Arenas." + nombreArena + ".lobbyArena", UbicacionManager.getIns().locationToString(lobbyArena));
            }
            if (spawnEquipoUno != null && spawnEquipoDos != null) {
                arenas.set("Arenas." + nombreArena + ".equipoUno.Spawn", UbicacionManager.getIns().locationToString(spawnEquipoUno));
                arenas.set("Arenas." + nombreArena + ".equipoDos.Spawn", UbicacionManager.getIns().locationToString(spawnEquipoDos));
            }
            if (arena.getEstadoArena().equals(EstadoArena.DESACTIVADA)) {
                arenas.set("Arenas." + nombreArena + ".Activada", false);
            } else {
                arenas.set("Arenas." + nombreArena + ".Activada", true);
            }
        }
        plugin.getArchivosManager().guardarArenas();
    }
}
