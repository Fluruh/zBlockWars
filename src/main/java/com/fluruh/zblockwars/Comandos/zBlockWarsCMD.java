package com.fluruh.zblockwars.Comandos;

import com.fluruh.zblockwars.Juego.Arena;
import com.fluruh.zblockwars.Main;
import com.fluruh.zblockwars.Managers.ArchivosManager;
import com.fluruh.zblockwars.Managers.MurallaManager;
import org.bukkit.ChatColor;
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

public class zBlockWarsCMD implements CommandExecutor {

    private Arena arena;
    private MurallaManager ubicacionMuralla;
    private Main plugin;

    public zBlockWarsCMD(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
        }
        Player jugador = (Player) sender;
        if (args.length >= 2) {
            if(args[0].equalsIgnoreCase("configurarArena")) {
                if (jugador.isOp() || jugador.hasPermission("zblockwars.admin")) {
                    String nombreArena = args[1];
                    ItemStack varitaMuralla = new ItemStack(Material.IRON_AXE); // Crear el varitaMuralla de hierro
                    // Personalizar el nombre y el lore
                    ItemMeta meta = varitaMuralla.getItemMeta();
                    meta.setDisplayName(plugin.getArchivosManager().traducir("&eVaritas de muralla")); // Nombre con color amarillo
                    // Crear el lore (lista de strings)
                    List<String> lore = new ArrayList<>();
                    lore.add(nombreArena); // Agregar el nombre de la arena al lore
                    meta.setLore(lore);
                    varitaMuralla.setItemMeta(meta); // Aplicar los cambios al varitaMuralla
                    jugador.getInventory().setItem(0, varitaMuralla); // Dar el varitaMuralla al jugador
                }
            }
        }
        return false;
    }
}
