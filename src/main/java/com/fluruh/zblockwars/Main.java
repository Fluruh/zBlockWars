package com.fluruh.zblockwars;

import com.fluruh.zblockwars.Comandos.zBlockWarsCMD;
import com.fluruh.zblockwars.Eventos.JugadorListener;
import com.fluruh.zblockwars.Managers.ArchivosManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private ArchivosManager archivosManager;

    @Override
    public void onEnable() {
        registrarManagers();
        registrarComandos();
        registrarEventos();
    }

    @Override
    public void onDisable() {
    }

    public void registrarManagers() {
        archivosManager = new ArchivosManager(this);
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

}
