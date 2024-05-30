package com.fluruh.zblockwars.Comandos;

import com.fluruh.zblockwars.Juego.Arena;
import com.fluruh.zblockwars.Main;
import com.fluruh.zblockwars.Managers.MurallaManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class zBlockWarsCMD implements CommandExecutor {

    private Arena arena;
    private MurallaManager ubicacionMuralla;
    private Main plugin;

    public zBlockWarsCMD(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration mensajes = plugin.getArchivosManager().getMensajes();
        if (!(sender instanceof Player)) {
        }
        Player jugador = (Player) sender;
        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("recargar")) {
                if (jugador.isOp() || jugador.hasPermission("zblockwars.admin")) {
                    plugin.getArchivosManager().recargarConfig();
                    plugin.getArchivosManager().recargarMensajes();
                    plugin.getArchivosManager().recargarArenas();
                    jugador.sendMessage(plugin.getArchivosManager().traducir(mensajes.getString("configuraciónRecargada")));
                } else {
                    jugador.sendMessage(plugin.getArchivosManager().traducir(mensajes.getString("sinPermisos")));
                }
            } else if (args[0].equalsIgnoreCase("setLobbyPrincipal")) {
                if (jugador.isOp() || jugador.hasPermission("zblockwars.admin")) {

                } else {
                    jugador.sendMessage(plugin.getArchivosManager().traducir(mensajes.getString("sinPermisos")));
                }
            } else if (args[0].equalsIgnoreCase("fijarSpawn")) {
                if (jugador.isOp() || jugador.hasPermission("zblockwars.admin")) {
                    if (args.length >= 4) {
                        String nombreArena = args[4];
                        Arena arena = plugin.getArenaManager().getArena(nombreArena);
                        if (arena != null) {
                            if (args[1].equalsIgnoreCase("Arena")) {
                                arena.setSpawnArena(jugador.getLocation().clone());
                                jugador.sendMessage(plugin.getArchivosManager().traducir(mensajes.getString("spawnArenaFijadoCorrectamente").replace("%nombreArena%", args[1])));
                            } else if (args[1].equalsIgnoreCase("EquipoUno")) {
                                arena.getEquipoUno().setSpawnEquipo(jugador.getLocation().clone());
                                jugador.sendMessage(plugin.getArchivosManager().traducir(mensajes.getString("spawnEquipoUnoArenaFijadoCorrectamente").replace("%nombreArena%", args[1])));
                            } else if (args[1].equalsIgnoreCase("EquipoDos")) {
                                arena.getEquipoDos().setSpawnEquipo(jugador.getLocation().clone());
                                jugador.sendMessage(plugin.getArchivosManager().traducir(mensajes.getString("spawnEquipoDosArenaFijadoCorrectamente").replace("%nombreArena%", args[1])));
                            } else {
                                jugador.sendMessage(plugin.getArchivosManager().traducir(mensajes.getString("errorUsoFijarLobbyArena")));
                            }
                        } else {
                            jugador.sendMessage(plugin.getArchivosManager().traducir(mensajes.getString("arenaNoExistente").replace("%nombreArena%", args[1])));
                        }
                    } else {
                        jugador.sendMessage(plugin.getArchivosManager().traducir(mensajes.getString("errorUsoFijarLobbyArena")));
                    }
                } else {
                    jugador.sendMessage(plugin.getArchivosManager().traducir(mensajes.getString("sinPermisos")));
                }
            } else if (args[0].equalsIgnoreCase("crearArena")) {
                if (jugador.isOp() || jugador.hasPermission("zblockwars.admin")) {
                    if (args.length >= 2) {
                        String nombreArena = args[1];
                        String nombreEquipoUno = args[2];
                        String nombreEquipoDos = args[3];
                        if (plugin.getArenaManager().getArena(nombreArena) == null) {
                            if (plugin.getArchivosManager().getConfig().contains("lobbyArena")) {
                                Arena arena = new Arena(nombreArena);
                                plugin.getArenaManager().agregarArena(arena);
                                jugador.sendMessage(plugin.getArchivosManager().traducir(mensajes.getString("arenaCreadaCorrectamente").replace("%nombreArena%", args[1])));
                            } else {
                                jugador.sendMessage(plugin.getArchivosManager().traducir(mensajes.getString("noLobbyArena")));
                                return true;
                            }
                        } else {
                            jugador.sendMessage(plugin.getArchivosManager().traducir(mensajes.getString("arenaExistente").replace("%nombreArena%", args[1])));
                        }
                    } else {
                        jugador.sendMessage(plugin.getArchivosManager().traducir(mensajes.getString("errorUsoCrearArena")));
                    }
                } else {
                    jugador.sendMessage(plugin.getArchivosManager().traducir(mensajes.getString("sinPermisos")));
                }
            } else if (args[0].equalsIgnoreCase("borrarArena")) {
                if (jugador.isOp() || jugador.hasPermission("zblockwars.admin")) {
                    if (args.length >= 2) {
                        String nombreArena = args[1];
                        if (plugin.getArenaManager().getArena(nombreArena) != null) {
                            plugin.getArenaManager().removerArena(nombreArena);
                            jugador.sendMessage(plugin.getArchivosManager().traducir(mensajes.getString("arenaBorradaCorrectamente").replace("%nombreArena%", args[1])));
                        } else {
                            jugador.sendMessage(plugin.getArchivosManager().traducir(mensajes.getString("arenaNoExistente").replace("%nombreArena%", args[1])));
                        }
                    } else {
                        jugador.sendMessage(plugin.getArchivosManager().traducir(mensajes.getString("errorUsoBorrarArena")));
                    }
                } else {
                    jugador.sendMessage(plugin.getArchivosManager().traducir(mensajes.getString("sinPermisos")));
                }
            }
        }
        return false;
    }
}
