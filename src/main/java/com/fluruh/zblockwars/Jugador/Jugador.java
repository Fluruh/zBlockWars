package com.fluruh.zblockwars.Jugador;

import org.bukkit.entity.Player;

public class Jugador {

    private final Player jugador;
    private int asesinatosJugador;
    private int muertesJugador;
    private int banderasCapturadas;
    private boolean asesinadoRecientemente;
    private final ElementosGuardados elementosGuardados;

    @Deprecated
    public Jugador(Player jugador) {
        this.jugador = jugador;
        this.elementosGuardados = new ElementosGuardados(jugador.getInventory().getContents(), jugador.getInventory().getArmorContents(),
                jugador.getGameMode(), jugador.getExp(), jugador.getLevel(), jugador.getFoodLevel(), jugador.getHealth(), jugador.getMaxHealth(),
                jugador.getAllowFlight(), jugador.isFlying());
    }

    public Player getJugador() {
        return this.jugador;
    }
    public void aumentarAsesinatos() {
        this.asesinatosJugador++;
    }
    public int getAsesinatosJugador() {
        return this.asesinatosJugador;
    }
    public void aumentarMuertes() {
        this.muertesJugador++;
    }
    public int getMuertesJugador() {
        return this.muertesJugador;
    }
    public int getBanderasCapturadas() {
        return banderasCapturadas;
    }
    public void setBanderasCapturadas(int banderasCapturadas) {
        this.banderasCapturadas = banderasCapturadas;
    }
    public boolean isAsesinadoRecientemente() {
        return asesinadoRecientemente;
    }
    public void setAsesinadoRecientemente(boolean asesinadoRecientemente) {
        this.asesinadoRecientemente = asesinadoRecientemente;
    }
    public ElementosGuardados getElementosGuardados() {
        return this.elementosGuardados;
    }
}
