package com.fluruh.zblockwars.Managers;

import com.fluruh.zblockwars.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class ArchivosManager {

    private Main plugin;
    private FileConfiguration config;
    private File archivoConfig;
    private FileConfiguration mensajes;
    private File archivoMensajes;
    private FileConfiguration arenas;
    private File archivoArenas;
    private static ArchivosManager instance;

    public String traducir(String texto) {
        String prefix = ChatColor.translateAlternateColorCodes('&', getMensajes().getString("Prefix") + " ");
        String traducido = prefix + ChatColor.translateAlternateColorCodes('&', texto);
        return traducido;
    }
    public String traducirSP(String texto) {
        String traducido = ChatColor.translateAlternateColorCodes('&', texto);
        return traducido;
    }
    public ArchivosManager(Main plugin) {
        this.plugin = plugin;
        this.archivoConfig = new File(plugin.getDataFolder(), "config.yml");
        crearConfig();
        this.config = YamlConfiguration.loadConfiguration(archivoConfig);
        this.archivoMensajes = new File(plugin.getDataFolder(), "mensajes.yml");
        crearArchivoMensajes();
        this.mensajes = YamlConfiguration.loadConfiguration(archivoMensajes);
        this.archivoArenas = new File(plugin.getDataFolder(), "arenas.yml");
        crearArchivoArenas();
        this.arenas = YamlConfiguration.loadConfiguration(archivoArenas);

    }
    private void crearConfig() {
        if (!archivoConfig.exists()) {
            archivoConfig.getParentFile().mkdirs(); // Crear directorios si no existen
            try {
                plugin.saveResource("config.yml", false); // Copiar config.yml desde recursos
            } catch (Exception e) {
                plugin.getLogger().log(Level.SEVERE, "Error al crear el archivo config.yml", e);
            }
        }
    }
    public FileConfiguration getConfig() {
        return config;
    }
    public void guardarConfig() {
        try {
            config.save(archivoConfig);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Error al guardar el archivo config.yml", e);
        }
    }
    private void crearArchivoMensajes() {
        if (!archivoMensajes.exists()) {
            archivoMensajes.getParentFile().mkdirs();
            try {
                plugin.saveResource("mensajes.yml", false);
            } catch (Exception e) {
                plugin.getLogger().log(Level.SEVERE, "Error al crear el archivo mensajes.yml", e);
            }
        }
    }
    public FileConfiguration getMensajes() {
        return mensajes;
    }
    public void guardarArchivoMensajes() {
        try {
            mensajes.save(archivoMensajes);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Error al guardar el archivo mensajes.yml", e);
        }
    }
    private void crearArchivoArenas() {
        if (!archivoArenas.exists()) {
            archivoArenas.getParentFile().mkdirs();
            try {
                plugin.saveResource("arenas.yml", false);
            } catch (Exception e) {
                plugin.getLogger().log(Level.SEVERE, "Error al crear el archivo arenas.yml", e);
            }
        }
    }
    public FileConfiguration getArenas() {
        return arenas;
    }
    public void guardarArchivoArenas() {
        try {
            arenas.save(archivoArenas);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Error al guardar el archivo arenas.yml", e);
        }
    }
}

