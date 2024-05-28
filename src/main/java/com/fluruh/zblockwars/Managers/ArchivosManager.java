package com.fluruh.zblockwars.Managers;

import com.fluruh.zblockwars.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ArchivosManager {
    // Variables definidas.
    private Main plugin;
    private FileConfiguration config;
    private File archivoConfig;
    private FileConfiguration mensajes;
    private File archivoMensajes;
    private FileConfiguration arenas;
    private File archivoArenas;
    // Constructor
    public ArchivosManager(Main plugin) {
        this.plugin = plugin;
        registrarConfig();
        registrarMensajes();
        registrarArenas();
    }
    // Codigo configuración.
    public void registrarConfig() {
        archivoConfig  = new File(plugin.getDataFolder(), "config.yml");
        if (!archivoConfig.exists()) {
            getConfig().options().copyDefaults(true);
            guardarConfig();
        }
    }

    public void guardarConfig() {
        try {
            config.save(archivoConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig() {
        if (config == null) {
            recargarConfig();
        }
        return config;
    }

    public void recargarConfig() {
        if (config == null) {
            archivoConfig = new File(plugin.getDataFolder(), "config.yml");
        }
        config = YamlConfiguration.loadConfiguration(archivoConfig);
        Reader defConfigStream;
        defConfigStream = new InputStreamReader(plugin.getResource("config.yml"), StandardCharsets.UTF_8);
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            config.setDefaults(defConfig);
        }
    }
    // Codigo mensajes.
    public void registrarMensajes() {
        archivoMensajes  = new File(plugin.getDataFolder(), "mensajes.yml");
        if (!archivoMensajes.exists()) {
            getMensajes().options().copyDefaults(true);
            guardarMensajes();
        }
    }

    public void guardarMensajes() {
        try {
            mensajes.save(archivoMensajes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getMensajes() {
        if (mensajes == null) {
            recargarMensajes();
        }
        return mensajes;
    }

    public void recargarMensajes() {
        if (mensajes == null) {
            archivoMensajes = new File(plugin.getDataFolder(), "mensajes.yml");
        }
        mensajes = YamlConfiguration.loadConfiguration(archivoMensajes);
        Reader defMensajesStream;
        defMensajesStream = new InputStreamReader(plugin.getResource("mensajes.yml"), StandardCharsets.UTF_8);
        if (defMensajesStream != null) {
            YamlConfiguration defMensajes = YamlConfiguration.loadConfiguration(defMensajesStream);
            mensajes.setDefaults(defMensajes);
        }
    }
    // Codigo arenas.
    public void registrarArenas() {
        archivoArenas  = new File(plugin.getDataFolder(), "arenas.yml");
        if (!archivoArenas.exists()) {
            getArenas().options().copyDefaults(true);
            guardarArenas();
        }
    }

    public void guardarArenas() {
        try {
            arenas.save(archivoArenas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getArenas() {
        if (arenas == null) {
            recargarArenas();
        }
        return arenas;
    }

    public void recargarArenas() {
        if (arenas == null) {
            archivoArenas = new File(plugin.getDataFolder(), "arenas.yml");
        }
        arenas = YamlConfiguration.loadConfiguration(archivoArenas);
        Reader defArenasStream;
        defArenasStream = new InputStreamReader(plugin.getResource("arenas.yml"), StandardCharsets.UTF_8);
        if (defArenasStream != null) {
            YamlConfiguration defArenas = YamlConfiguration.loadConfiguration(defArenasStream);
            arenas.setDefaults(defArenas);
        }
    }
    // Codigo traductores.
    public String traducir(String texto) {
        String prefix = ChatColor.translateAlternateColorCodes('&', getMensajes().getString("Prefix") + " ");
        String traducido = prefix + ChatColor.translateAlternateColorCodes('&', texto);
        return traducido;
    }

    public String traducirSP(String texto) {
        String traducidoSP = ChatColor.translateAlternateColorCodes('&', texto);
        return traducidoSP;
    }

}