package com.fluruh.zblockwars.Managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Locale;

public class UbicacionManager {

    private static UbicacionManager instance;

    public static UbicacionManager getIns() {
        if (instance == null) {
            instance = new UbicacionManager();
        }
        return instance;
    }
    public String locationToString(Location location) {
        if (location == null || location.getWorld() == null) {
            throw new IllegalArgumentException("La ubicación o el mundo no pueden ser nulos.");
        }
        return String.format(Locale.US, "%s %.2f %.2f %.2f %.2f %.2f",
                location.getWorld().getName(),
                location.getX(), location.getY(), location.getZ(),
                location.getYaw(), location.getPitch());
    }
    public Location stringToLocation(String locationString) {
        String[] parts = locationString.split(" ");
        if (parts.length != 6) {
            throw new IllegalArgumentException("Formato de cadena de ubicación inválido.");
        }
        World world = Bukkit.getWorld(parts[0]);
        if (world == null) {
            throw new IllegalArgumentException("Mundo no encontrado: " + parts[0]);
        }
        double x = Double.parseDouble(parts[1]);
        double y = Double.parseDouble(parts[2]);
        double z = Double.parseDouble(parts[3]);
        float yaw = Float.parseFloat(parts[4]);
        float pitch = Float.parseFloat(parts[5]);

        return new Location(world, x, y, z, yaw, pitch);
    }
}

