package com.fluruh.zblockwars.Eventos;

import com.fluruh.zblockwars.Main;
import com.fluruh.zblockwars.Managers.UbicacionManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class JugadorListener implements Listener {
    private final Main plugin;

    public JugadorListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void clickearEvento(PlayerInteractEvent clickEvento) {
        Player jugador = clickEvento.getPlayer();
        ItemStack itemEnMano = jugador.getInventory().getItemInMainHand();
        if (clickEvento.getClickedBlock() != null && itemEnMano.getItemMeta() != null) {
            String tomarNombreItem = itemEnMano.getItemMeta().getDisplayName();
            List<String> loreItem = itemEnMano.getItemMeta().getLore();
            Material itemEnManoTipo = itemEnMano.getType();
            String nombreItem = plugin.getArchivosManager().traducir("&fVarita de muralla");
            if (itemEnManoTipo == Material.CARROT && tomarNombreItem.equals(nombreItem) && loreItem != null && loreItem.size() >= 3) {
                if (clickEvento.getAction() == Action.LEFT_CLICK_BLOCK) {
                    String nombreArena = loreItem.get(2).replaceFirst("Estás configurando la arena ", "").replaceAll("§[0-9a-fk-or]", "");
                    plugin.getArchivosManager().getArenas().set("Arenas." + nombreArena + ".Muralla.esquinaUno", UbicacionManager.getIns().locationToString(clickEvento.getClickedBlock().getLocation()));
                    plugin.getArchivosManager().guardarArenas();
                    jugador.sendMessage(plugin.getArchivosManager().traducir("&fPosición &a1 &fde la muralla ubicada para la arena &a" + nombreArena + "&f."));
                    clickEvento.setCancelled(true);
                }
                if (clickEvento.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    String nombreArena = loreItem.get(2).replaceFirst("Estás configurando la arena ", "").replaceAll("§[0-9a-fk-or]", "");
                    plugin.getArchivosManager().getArenas().set("Arenas." + nombreArena + ".Muralla.esquinaDos", UbicacionManager.getIns().locationToString(clickEvento.getClickedBlock().getLocation()));
                    plugin.getArchivosManager().guardarArenas();
                    jugador.sendMessage(plugin.getArchivosManager().traducir("&fPosición &a2 &fde la muralla ubicada para la arena &a" + nombreArena + "&f."));
                    clickEvento.setCancelled(true);
                }
            }
        }
    }
}

