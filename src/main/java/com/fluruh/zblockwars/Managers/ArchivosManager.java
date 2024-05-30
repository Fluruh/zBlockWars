package com.fluruh.zblockwars.Managers;

import com.fluruh.zblockwars.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArchivosManager {
    // Variables definidas.
    private final Main plugin;
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
    // Registrar
    public void registrarConfig() {
        archivoConfig  = new File(plugin.getDataFolder(), "config.yml");
        if (!archivoConfig.exists()) {
            getConfig().options().copyDefaults(true);
            guardarConfig();
        }
    }
    public void registrarMensajes() {
        archivoMensajes  = new File(plugin.getDataFolder(), "mensajes.yml");
        if (!archivoMensajes.exists()) {
            getMensajes().options().copyDefaults(true);
            guardarMensajes();
        }
    }
    public void registrarArenas() {
        archivoArenas  = new File(plugin.getDataFolder(), "arenas.yml");
        if (!archivoArenas.exists()) {
            getArenas().options().copyDefaults(true);
            guardarArenas();
        }
    }
    // Guardar
    public void guardarConfig() {
        try {
            config.save(archivoConfig);
        } catch (IOException e) {
            Logger logger = plugin.getLogger(); // Obtiene el logger del plugin
            logger.log(Level.WARNING, "Error al guardar la configuración en {0}: {1}",
                    new Object[] { archivoConfig.getAbsolutePath(), e.getMessage() }); // Loguea con información útil
        }
    }
    public void guardarMensajes() {
        try {
            mensajes.save(archivoMensajes);
        } catch (IOException e) {
            Logger logger = plugin.getLogger(); // Obtiene el logger del plugin
            logger.log(Level.WARNING, "Error al guardar los mensajes en {0}: {1}",
                    new Object[] { archivoMensajes.getAbsolutePath(), e.getMessage() }); // Registra el error con detalles
        }
    }
    public void guardarArenas() {
        try {
            arenas.save(archivoArenas);
        } catch (IOException e) {
            Logger logger = plugin.getLogger();
            logger.log(Level.WARNING, "Error al guardar las arenas en {0}: {1}",
                    new Object[] { archivoArenas.getAbsolutePath(), e.getMessage() });
        }
    }
    // Getters
    public FileConfiguration getConfig() {
        if (config == null) {
            recargarConfig();
        }
        return config;
    }
    public FileConfiguration getMensajes() {
        if (mensajes == null) {
            recargarMensajes();
        }
        return mensajes;
    }
    public FileConfiguration getArenas() {
        if (arenas == null) {
            recargarArenas();
        }
        return arenas;
    }
    // Recargar
    public void recargarConfig() {
        if (config == null) {
            archivoConfig = new File(plugin.getDataFolder(), "config.yml");
        }
        config = YamlConfiguration.loadConfiguration(archivoConfig);
        InputStream defConfigStream = plugin.getResource("config.yml"); // Obtener InputStream directamente
        if (defConfigStream != null) { // Verificar si el recurso existe
            try (InputStreamReader reader = new InputStreamReader(defConfigStream, StandardCharsets.UTF_8)) { // Usar try-with-resources para cerrar automáticamente
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(reader);
                config.setDefaults(defConfig);
            } catch (IOException e) {
                plugin.getLogger().warning("Error al cargar/cerrar el archivo de configuración predeterminado: " + e.getMessage());
            }
        }
    }
    public void recargarMensajes() {
        if (mensajes == null) {
            archivoMensajes = new File(plugin.getDataFolder(), "mensajes.yml");
        }
        mensajes = YamlConfiguration.loadConfiguration(archivoMensajes);
        InputStream defMensajesStream = plugin.getResource("mensajes.yml");
        if (defMensajesStream != null) {
            try (InputStreamReader reader = new InputStreamReader(defMensajesStream, StandardCharsets.UTF_8)) {
                YamlConfiguration defMensajes = YamlConfiguration.loadConfiguration(reader);
                mensajes.setDefaults(defMensajes);
            } catch (IOException e) {
                plugin.getLogger().warning("Error al cargar/cerrar el archivo de mensajes predeterminado: " + e.getMessage());
            }
        }
    }
    public void recargarArenas() {
        if (arenas == null) {
            archivoArenas = new File(plugin.getDataFolder(), "arenas.yml");
        }
        arenas = YamlConfiguration.loadConfiguration(archivoArenas);
        InputStream defArenasStream = plugin.getResource("arenas.yml");
        if (defArenasStream != null) {
            try (InputStreamReader reader = new InputStreamReader(defArenasStream, StandardCharsets.UTF_8)) {
                YamlConfiguration defArenas = YamlConfiguration.loadConfiguration(reader);
                arenas.setDefaults(defArenas);
            } catch (IOException e) { // Capturar la IOException del cierre
                plugin.getLogger().warning("Error al cargar/cerrar el archivo de arenas predeterminado: " + e.getMessage());
            }
        }
    }
    // Codigo traductores.
    public String traducir(String texto) {
        String prefix = ChatColor.translateAlternateColorCodes('&', getMensajes().getString("Prefix") + " ");
        return prefix + ChatColor.translateAlternateColorCodes('&', texto);
    }
    public String traducirSP(String texto) {
        return ChatColor.translateAlternateColorCodes('&', texto);
    }
}