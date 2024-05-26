package com.fluruh.zblockwars.Managers;

import org.bukkit.Location;

public class MurallaManager {

    private Location esquinaUno;
    private Location esquinaDos;

    public Location getEsquinaUno() {
        return esquinaUno;
    }

    public Location getEsquinaDos() {
        return esquinaDos;
    }

    public MurallaManager(Location ubicacion1, Location ubicacion2) {
        this.esquinaUno = ubicacion1;
        this.esquinaDos = ubicacion2;
    }

}
