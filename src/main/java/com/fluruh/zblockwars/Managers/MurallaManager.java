package com.fluruh.zblockwars.Managers;

import org.bukkit.Location;

public class MurallaManager {
    // Variables definidas.
    private Location esquinaUno;
    private Location esquinaDos;

    public MurallaManager(Location esquinaUno, Location esquinaDos) {
        this.esquinaUno = esquinaUno;
        this.esquinaDos = esquinaDos;
    }
    // Getters
    public Location getEsquinaUno() {
        return esquinaUno;
    }
    public Location getEsquinaDos() {
        return esquinaDos;
    }
    // Setters
    public void setEsquinaUno(Location esquinaUno) {
        this.esquinaUno = esquinaUno;
    }
    public void setEsquinaDos(Location esquinaDos) {
        this.esquinaDos = esquinaDos;
    }

}
