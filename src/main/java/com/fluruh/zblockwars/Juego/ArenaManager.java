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

    public void cargarPartidas() {
        this.listaArenas = new ArrayList<Arena>();
        FileConfiguration arenas = plugin.getArchivosManager().getArenas();
        if(arenas.contains("Arenas")) {
            for (String key : arenas.getConfigurationSection("Arenas").getKeys(false)) {
                int minJugadores = Integer.valueOf(arenas.getString("Arenas." + key + ".minJugadores"));
                int maxJugadores = Integer.valueOf(arenas.getString("Arenas." + key + ".maxJugadores"));
                int tiempoMaximo = Integer.valueOf(arenas.getString("Arenas." + key + ".tiempoMaximo"));
                Location lobbyArena = null;
                if(arenas.contains("Arenas."+ key + ".lobbyArena")) {
                    Location lobbyArena = UbicacionManager.getIns().stringToLocation(arenas.getString("Arenas." + key + ".lobbyArena"));
                }


                String nombreTeam1 = arenas.getString("Arenas."+key+".Team1.name");

                Location lSpawnTeam1 = null;
                if(arenas.contains("Arenas."+key+".Team1.Spawn")) {
                    double xSpawnTeam1 = Double.valueOf(arenas.getString("Arenas."+key+".Team1.Spawn.x"));
                    double ySpawnTeam1 = Double.valueOf(arenas.getString("Arenas."+key+".Team1.Spawn.y"));
                    double zSpawnTeam1 = Double.valueOf(arenas.getString("Arenas."+key+".Team1.Spawn.z"));
                    String worldSpawnTeam1 = arenas.getString("Arenas."+key+".Team1.Spawn.world");
                    float pitchSpawnTeam1 = Float.valueOf(arenas.getString("Arenas."+key+".Team1.Spawn.pitch"));
                    float yawSpawnTeam1 = Float.valueOf(arenas.getString("Arenas."+key+".Team1.Spawn.yaw"));
                    lSpawnTeam1 = new Location(Bukkit.getWorld(worldSpawnTeam1),xSpawnTeam1,ySpawnTeam1,zSpawnTeam1,yawSpawnTeam1,pitchSpawnTeam1);
                }


                String nombreTeam2 = arenas.getString("Arenas."+key+".Team2.name");
                Location lSpawnTeam2 = null;
                if(arenas.contains("Arenas."+key+".Team2.Spawn")) {
                    double xSpawnTeam2 = Double.valueOf(arenas.getString("Arenas."+key+".Team2.Spawn.x"));
                    double ySpawnTeam2 = Double.valueOf(arenas.getString("Arenas."+key+".Team2.Spawn.y"));
                    double zSpawnTeam2 = Double.valueOf(arenas.getString("Arenas."+key+".Team2.Spawn.z"));
                    String worldSpawnTeam2 = arenas.getString("Arenas."+key+".Team2.Spawn.world");
                    float pitchSpawnTeam2 = Float.valueOf(arenas.getString("Arenas."+key+".Team2.Spawn.pitch"));
                    float yawSpawnTeam2 = Float.valueOf(arenas.getString("Arenas."+key+".Team2.Spawn.yaw"));
                    lSpawnTeam2 = new Location(Bukkit.getWorld(worldSpawnTeam2),xSpawnTeam2,ySpawnTeam2,zSpawnTeam2,yawSpawnTeam2,pitchSpawnTeam2);
                }

                Partida partida = new Partida(key, tiempoMaximo,nombreTeam1,nombreTeam2,vidas);
                if(nombreTeam1.equalsIgnoreCase("random")) {
                    partida.getTeam1().setRandom(true);
                }
                if(nombreTeam2.equalsIgnoreCase("random")) {
                    partida.getTeam2().setRandom(true);
                }
                partida.modificarTeams(getConfig());
                partida.setCantidadMaximaJugadores(max_players);
                partida.setCantidadMinimaJugadores(minJugadores);
                partida.setLobby(lobbyArena);
                partida.getTeam1().setSpawn(lSpawnTeam1);
                partida.getTeam2().setSpawn(lSpawnTeam2);
                String enabled = arenas.getString("Arenas."+key+".enabled");
                if(enabled.equals("true")) {
                    partida.setEstado(EstadoPartida.ESPERANDO);
                }else {
                    partida.setEstado(EstadoPartida.DESACTIVADA);
                }

                this.partidas.add(partida);
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
