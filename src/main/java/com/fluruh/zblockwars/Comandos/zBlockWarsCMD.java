package com.fluruh.zblockwars.Comandos;

import com.fluruh.zblockwars.Managers.ArchivosManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class zBlockWarsCMD implements CommandExecutor {

    private ArchivosManager archivosManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration mensajes = archivosManager.getMensajes();
        String prefix = ChatColor.translateAlternateColorCodes('&', mensajes.getString("Prefix" + " "));
        if (!(sender instanceof Player)) {

        }
        Player jugador = (Player) sender;
        if (args.length >= 1) {
            if(args[0].equalsIgnoreCase("crearArena")) {
                if (jugador.isOp() || jugador.hasPermission("zblockwars.admin")) {
                }
            }
        }
        return false;
    }
}
