package com.fluruh.zblockwars;

import com.fluruh.zblockwars.Managers.ArchivosManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private ArchivosManager archivosManager;
    @Override
    public void onEnable() {
        registrarManagers();

    }

    @Override
    public void onDisable() {
        archivosManager.guardarConfig();
        archivosManager.guardarArchivoMensajes();
        archivosManager.guardarArchivoArenas();
    }
    public void registrarManagers() {
        archivosManager = new ArchivosManager(this);
    }
}
