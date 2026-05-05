package Clases;

import Interfaces.HuellaCarbono;

/**
 * Clase Bicicleta.
 * Huella de carbono basada en emisiones de fabricación amortizadas y mantenimiento.
 * No tiene relación de herencia con Edificio ni Carro.
 */
public class Bicicleta implements HuellaCarbono {

    private static final double FACTOR_MANTENIMIENTO = 0.005;

    private String tipo;
    private double peso;
    private double emisionesFab;
    private int    aniosUso;
    private double kilometrosAnio;

    public Bicicleta(String tipo, double peso, double emisionesFab,
                     int aniosUso, double kilometrosAnio) {
        this.tipo           = tipo;
        this.peso           = peso;
        this.emisionesFab   = emisionesFab;
        this.aniosUso       = aniosUso;
        this.kilometrosAnio = kilometrosAnio;
    }

    public double getKilometros() {
        return kilometrosAnio * aniosUso;
    }

    @Override
    public double getHuellaCarbono() {
        double huellaMantenimiento = kilometrosAnio * FACTOR_MANTENIMIENTO;
        return emisionesFab + huellaMantenimiento;
    }

    public String getTipo()                    { return tipo; }
    public void   setTipo(String t)            { this.tipo = t; }
    public double getPeso()                    { return peso; }
    public void   setPeso(double p)            { this.peso = p; }
    public double getEmisionesFab()            { return emisionesFab; }
    public void   setEmisionesFab(double e)    { this.emisionesFab = e; }
    public int    getAniosUso()                { return aniosUso; }
    public void   setAniosUso(int a)           { this.aniosUso = a; }
    public double getKilometrosAnio()          { return kilometrosAnio; }
    public void   setKilometrosAnio(double k)  { this.kilometrosAnio = k; }

    @Override
    public String toString() {
        return String.format("Bicicleta | %s | %.1f kg | %.0f km/año | Huella: %.2f kg CO2/año",
                tipo, peso, kilometrosAnio, getHuellaCarbono());
    }
}
