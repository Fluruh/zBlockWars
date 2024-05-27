package com.fluruh.zblockwars.Eventos;

import com.fluruh.zblockwars.Managers.ArchivosManager;
import com.fluruh.zblockwars.Managers.UbicacionManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.IOException;

public class JugadorListener implements Listener {
    private ArchivosManager am;

    public String traducir(String texto) {
        String prefix = ChatColor.translateAlternateColorCodes('&', "&f[&6zB&eW&f] ");
        String traducido = prefix + ChatColor.translateAlternateColorCodes('&', texto);
        return traducido;
    }

    @EventHandler
    public void clickearEvento(PlayerInteractEvent clickEventoJugador) {
        Player jugador = clickEventoJugador.getPlayer();
        Material tipoItem = jugador.getItemInUse().getType();
        String  nombreItem = jugador.getItemInUse().getItemMeta().getDisplayName();
        if (clickEventoJugador.getAction().equals(Action.LEFT_CLICK_BLOCK) && tipoItem == Material.IRON_AXE && nombreItem.equals(traducir("&eVaritas de muralla"))) {
            String lore = jugador.getItemInUse().getItemMeta().getLore().getFirst();
            am.getArenas().set("Arenas." + lore + ".Muralla.esquinaUno", UbicacionManager.getIns().locationToString(clickEventoJugador.getClickedBlock().getLocation()));
            am.guardarArchivoArenas();
            jugador.sendMessage(traducir("&7La posición &e1 de la muralla fue ubicada correctamente para la arena: &e " + lore + "&7."));
            clickEventoJugador.setCancelled(true);
        }
        if (clickEventoJugador.getAction().equals(Action.RIGHT_CLICK_BLOCK) && tipoItem == Material.IRON_AXE && nombreItem.equals(traducir("&eVaritas de muralla"))) {
            String lore = jugador.getItemInUse().getItemMeta().getLore().getFirst();
            am.getArenas().set("Arenas." + lore + ".Muralla.esquinaDos", UbicacionManager.getIns().locationToString(clickEventoJugador.getClickedBlock().getLocation()));
            am.guardarArchivoArenas();
            jugador.sendMessage(traducir("&7La posición &e2 de la muralla fue ubicada correctamente para la arena: &e " + lore + "&7."));
            clickEventoJugador.setCancelled(true);
        }
    }
}
