package com.fluruh.zblockwars;

import com.fluruh.zblockwars.Comandos.zBlockWarsCMD;
import com.fluruh.zblockwars.Eventos.JugadorListener;
import com.fluruh.zblockwars.Juego.ArenaManager;
import com.fluruh.zblockwars.Managers.ArchivosManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    ArchivosManager archivosManager;
    ArenaManager arenaManager;

    @Override
    public void onEnable() {
        archivosManager = new ArchivosManager(this);
        registrarManagers();
        registrarEventos();
        registrarComandos();
        arenaManager.cargarArenas();
    }

    @Override
    public void onDisable() {
        arenaManager.guardarArenas();
    }

    public void registrarManagers() {
        archivosManager = new ArchivosManager(this);
        arenaManager = new ArenaManager(this);
    }

    public void registrarComandos() {
        getCommand("zblockwars").setExecutor(new zBlockWarsCMD(this));
    }

    public void registrarEventos() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new JugadorListener(this), this);
    }
    public ArchivosManager getArchivosManager() {
        return archivosManager;
    }
    public ArenaManager getArenaManager() {
        return arenaManager;
    }
}
