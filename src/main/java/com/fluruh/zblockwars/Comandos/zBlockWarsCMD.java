package com.fluruh.zblockwars.Comandos;

import com.fluruh.zblockwars.Juego.Arena;
import com.fluruh.zblockwars.Main;
import com.fluruh.zblockwars.Managers.MurallaManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            } else if (args[0].equalsIgnoreCase("crearArena")) {
                if (jugador.isOp() || jugador.hasPermission("zblockwars.admin")) {
                    if (args.length >= 2) {
                        String nombreArena = args[1];
                        ItemStack varitaMuralla = new ItemStack(Material.CARROT);
                        ItemMeta metaItem = varitaMuralla.getItemMeta();
                        if (metaItem != null) {
                            metaItem.setDisplayName(plugin.getArchivosManager().traducir("&fVarita de muralla"));
                            List<String> loreItem = new ArrayList<>();
                            loreItem.add(plugin.getArchivosManager().traducirSP("&8Herramienta de configuración"));
                            loreItem.add(plugin.getArchivosManager().traducirSP("&r"));
                            loreItem.add(plugin.getArchivosManager().traducirSP("&7Estás configurando la arena &a" + nombreArena));
                            loreItem.add(plugin.getArchivosManager().traducirSP("&r"));
                            metaItem.setLore(loreItem);
                            varitaMuralla.setItemMeta(metaItem);
                            jugador.getInventory().setItem(0, varitaMuralla);
                            jugador.sendMessage(plugin.getArchivosManager().traducir(mensajes.getString("usoVaritaMuralla").replace("%nombreArena%", args[1])));
                        }
                    } else {
                        jugador.sendMessage(plugin.getArchivosManager().traducir(mensajes.getString("errorUsoCrearArena")));
                    }
                } else {
                    jugador.sendMessage(plugin.getArchivosManager().traducir(mensajes.getString("sinPermisos")));
                }
            }
        }
        return false;
    }
}
