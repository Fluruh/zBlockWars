package com.fluruh.zblockwars.Jugador;

import org.bukkit.GameMode;
import org.bukkit.inventory.ItemStack;

public class ElementosGuardados {

    private ItemStack[] inventarioGuardado;
    private ItemStack[] equipamientoGuardado;
    private GameMode gameModeGuardado;
    private float experienciaGuardada;
    private int nivelGuardado;
    private int hambreGuardada;
    private double vidaGuardada;
    private double maximaVidaGuardada;
    private boolean permitidoVolar;
    private boolean estaVolando;

    public ElementosGuardados(ItemStack[] inventarioGuardado, ItemStack[] equipamientoGuardado,
                              GameMode gameModeGuardado, float experienciaGuardada, int nivelGuardado,
                              int hambreGuardada, double vidaGuardada, double maximaVidaGuardada,
                              boolean permitidoVolar, boolean estaVolando) {
        this.inventarioGuardado = inventarioGuardado;
        this.equipamientoGuardado = equipamientoGuardado;
        this.gameModeGuardado = gameModeGuardado;
        this.experienciaGuardada = experienciaGuardada;
        this.nivelGuardado = nivelGuardado;
        this.hambreGuardada = hambreGuardada;
        this.vidaGuardada = vidaGuardada;
        this.maximaVidaGuardada = maximaVidaGuardada;
        this.permitidoVolar = permitidoVolar;
        this.estaVolando = estaVolando;
    }

    public ItemStack[] getInventarioGuardado() {
        return inventarioGuardado;
    }

    public void setInventarioGuardado(ItemStack[] inventarioGuardado) {
        this.inventarioGuardado = inventarioGuardado;
    }

    public ItemStack[] getEquipamientoGuardado() {
        return equipamientoGuardado;
    }

    public void setEquipamientoGuardado(ItemStack[] equipamientoGuardado) {
        this.equipamientoGuardado = equipamientoGuardado;
    }

    public GameMode getGameModeGuardado() {
        return gameModeGuardado;
    }

    public void setGameModeGuardado(GameMode gameModeGuardado) {
        this.gameModeGuardado = gameModeGuardado;
    }

    public float getExperienciaGuardada() {
        return experienciaGuardada;
    }

    public void setExperienciaGuardada(float experienciaGuardada) {
        this.experienciaGuardada = experienciaGuardada;
    }

    public int getNivelGuardado() {
        return nivelGuardado;
    }

    public void setNivelGuardado(int nivelGuardado) {
        this.nivelGuardado = nivelGuardado;
    }

    public int getHambreGuardada() {
        return hambreGuardada;
    }

    public void setHambreGuardada(int hambreGuardada) {
        this.hambreGuardada = hambreGuardada;
    }

    public double getVidaGuardada() {
        return vidaGuardada;
    }

    public void setVidaGuardada(double vidaGuardada) {
        this.vidaGuardada = vidaGuardada;
    }

    public double getMaximaVidaGuardada() {
        return maximaVidaGuardada;
    }

    public void setMaximaVidaGuardada(double maximaVidaGuardada) {
        this.maximaVidaGuardada = maximaVidaGuardada;
    }

    public boolean isPermitidoVolar() {
        return permitidoVolar;
    }

    public void setPermitidoVolar(boolean permitidoVolar) {
        this.permitidoVolar = permitidoVolar;
    }

    public boolean isEstaVolando() {
        return estaVolando;
    }

    public void setEstaVolando(boolean estaVolando) {
        this.estaVolando = estaVolando;
    }
}
