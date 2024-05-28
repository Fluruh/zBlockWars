package com.fluruh.zblockwars.Eventos;

import com.fluruh.zblockwars.Main;
import com.fluruh.zblockwars.Managers.UbicacionManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class JugadorListener implements Listener {
    private Main plugin;

    public JugadorListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void clickearEvento(PlayerInteractEvent clickEventoJugador) {
        Player jugador = clickEventoJugador.getPlayer();
        Material itemEnMano = jugador.getInventory().getItemInMainHand().getType();
        String  nombreItem = jugador.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
        if (itemEnMano != null && !itemEnMano.isAir()) {
            if (clickEventoJugador.getAction().equals(Action.LEFT_CLICK_BLOCK) && itemEnMano == Material.CARROT && nombreItem.equals(plugin.getArchivosManager().traducir("&7Varitas de muralla"))) {
                String lore = jugador.getInventory().getItemInMainHand().getItemMeta().getLore().getFirst();
                plugin.getArchivosManager().getArenas().set("Arenas." + lore + ".Muralla.esquinaUno", UbicacionManager.getIns().locationToString(clickEventoJugador.getClickedBlock().getLocation()));
                plugin.getArchivosManager().guardarArchivoArenas();
                jugador.sendMessage(plugin.getArchivosManager().traducir("&7La posición &e1 &7de la muralla fue ubicada correctamente para la arena: &e" + lore + "&7."));
                clickEventoJugador.setCancelled(true);
            }
            if (clickEventoJugador.getAction().equals(Action.RIGHT_CLICK_BLOCK) && itemEnMano == Material.CARROT  && nombreItem.equals(plugin.getArchivosManager().traducir("&7Varitas de muralla"))) {
                String lore = jugador.getInventory().getItemInMainHand().getItemMeta().getLore().getFirst();
                plugin.getArchivosManager().getArenas().set("Arenas." + lore + ".Muralla.esquinaDos", UbicacionManager.getIns().locationToString(clickEventoJugador.getClickedBlock().getLocation()));
                plugin.getArchivosManager().guardarArchivoArenas();
                jugador.sendMessage(plugin.getArchivosManager().traducir("&7La posición &e2 &7de la muralla fue ubicada correctamente para la arena: &e" + lore + "&7."));
                clickEventoJugador.setCancelled(true);
            }
        }
    }
}
