package Clases;

import Interfaces.HuellaCarbono;

/**
 * Clase Carro.
 * Calcula la huella de carbono basada en consumo de combustible.
 * No tiene relación de herencia con Edificio ni Bicicleta.
 */
public class Carro implements HuellaCarbono {

    private static final double FACTOR_GASOLINA = 2.31;
    private static final double FACTOR_DIESEL   = 2.68;

    private String modelo;
    private String tipoCombustible;
    private double consumoCombustible;
    private double kilometrosAnio;

    public Carro(String modelo, String tipoCombustible,
                 double consumoCombustible, double kilometrosAnio) {
        this.modelo             = modelo;
        this.tipoCombustible    = tipoCombustible;
        this.consumoCombustible = consumoCombustible;
        this.kilometrosAnio     = kilometrosAnio;
    }

    public double getEficiencia() {
        if (consumoCombustible <= 0) return 0;
        return 100.0 / consumoCombustible;
    }

    @Override
    public double getHuellaCarbono() {
        double litros = (kilometrosAnio / 100.0) * consumoCombustible;
        double factor = "diesel".equalsIgnoreCase(tipoCombustible)
                ? FACTOR_DIESEL : FACTOR_GASOLINA;
        return litros * factor;
    }

    public String getModelo()                    { return modelo; }
    public void   setModelo(String m)            { this.modelo = m; }
    public String getTipoCombustible()           { return tipoCombustible; }
    public void   setTipoCombustible(String t)   { this.tipoCombustible = t; }
    public double getConsumoCombustible()        { return consumoCombustible; }
    public void   setConsumoCombustible(double c){ this.consumoCombustible = c; }
    public double getKilometrosAnio()            { return kilometrosAnio; }
    public void   setKilometrosAnio(double k)    { this.kilometrosAnio = k; }

    @Override
    public String toString() {
        return String.format("Carro | %s | %s | %.0f km/año | Huella: %.2f kg CO2/año",
                modelo, tipoCombustible, kilometrosAnio, getHuellaCarbono());
    }
}
