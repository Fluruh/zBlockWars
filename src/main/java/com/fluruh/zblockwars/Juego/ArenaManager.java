package com.fluruh.zblockwars.Juego;

import com.fluruh.zblockwars.Jugador.Jugador;
import com.fluruh.zblockwars.Main;
import com.fluruh.zblockwars.Managers.UbicacionManager;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;

public class ArenaManager {

    private Main plugin;
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
            }
        }
    }

    public ArrayList<Arena> getListaArenas() {
        return listaArenas;
    }

    public Arena getArena(String nombreArena) {
        for (int i = 0; i < listaArenas.size(); i++) {
            if (listaArenas.get(i).getNombreArena().equals(nombreArena)) {
                return listaArenas.get(i);
            }
        }
        return null;
    }

    public Arena getArenaJugador(String nombreJugador) {
        for (int i = 0; i < listaArenas.size(); i++) {
            ArrayList<Jugador> jugadores = listaArenas.get(i).getJugadoresArena();
            for (int k = 0; k < jugadores.size(); k++) {
                if (jugadores.get(k).getJugador().getName().equals(nombreJugador)) {
                    return listaArenas.get(i);
                }
            }
        }
        return null;
    }

    public void cargarArenas() {
        listaArenas = new ArrayList<Arena>();
        FileConfiguration arenas = plugin.getArchivosManager().getArenas();
        if (arenas.contains("Arenas")) {
            for (String key : arenas.getConfigurationSection("Arenas").getKeys(false)) {
                int minJugadores = arenas.getInt("Arenas." + key + ".minJugadores");
                int maxJugadores = arenas.getInt(("Arenas." + key + ".maxJugadores"));
                int tiempoMaximo = arenas.getInt(("Arenas." + key + ".tiempoMaximo"));
                Location lobbyArena = null;
                if (arenas.contains("Arenas." + key + ".lobbyArena")) {
                    lobbyArena = UbicacionManager.getIns().stringToLocation(arenas.getString("Arenas." + key + ".lobbyArena"));
                }
                String nombreEquipoUno = arenas.getString("Arenas." + key + ".EquipoUno.Nombre");
                Location spawnEquipoUno = null;
                if (arenas.contains("Arenas." + key + ".equipoUno.Spawn")) {
                    spawnEquipoUno = UbicacionManager.getIns().stringToLocation(arenas.getString("Arenas." + key + ".equipoUno.Spawn"));
                }
                String nombreEquipoDos = arenas.getString("Arenas." + key + ".EquipoDos.Nombre");
                Location spawnEquipoDos = null;
                if (arenas.contains("Arenas." + key + ".equipoDos.Spawn")) {
                    spawnEquipoDos = UbicacionManager.getIns().stringToLocation(arenas.getString("Arenas." + key + ".equipoDos.Spawn"));
                }
                Arena arena = new Arena(key, nombreEquipoUno, nombreEquipoDos, minJugadores, maxJugadores);
                arena.setCantidadMinimaJugadores(minJugadores);
                arena.setCantidadMaximaJugadores(maxJugadores);
                arena.setTiempoMaximo(tiempoMaximo);
                arena.setLobbyArena(lobbyArena);
                arena.getEquipoUno().setSpawnEquipo(spawnEquipoUno);
                arena.getEquipoDos().setSpawnEquipo(spawnEquipoDos);
                String activada = arenas.getString("Arenas." + key + ".Activada");
                if (activada.equals("false")) {
                    arena.setEstado(EstadoArena.DESACTIVADA);
                } else {
                    arena.setEstado(EstadoArena.ESPERANDO);
                }
                listaArenas.add(arena);
            }
        }

    }

    public void guardarArenas() {
        FileConfiguration arenas = plugin.getArchivosManager().getArenas();
        for (Arena arena : listaArenas) {
            String nombreArena = arena.getNombreArena();
            Location lobbyArena = arena.getLobbyArena();
            Location spawnEquipoUno = arena.getEquipoUno().getSpawnEquipo();
            Location spawnEquipoDos = arena.getEquipoDos().getSpawnEquipo();
            Location murallaEsquinaUno = arena.getEsquinaUnoMuralla();
            Location murallaEsquinaDos = arena.getEsquinaDosMuralla();
            arenas.set("Arenas." + nombreArena + ".minJugadores", arena.getCantidadMinimaJugadores());
            arenas.set("Arenas." + nombreArena + ".maxJugadores", arena.getCantidadMinimaJugadores());
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
