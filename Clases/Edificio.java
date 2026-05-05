package Clases;

import Interfaces.HuellaCarbono;

/**
 * Clase Edificio.
 * Calcula la huella de carbono basada en consumo energético.
 * No tiene relación de herencia con Carro ni Bicicleta.
 */
public class Edificio implements HuellaCarbono {

    private static final double FACTOR_ELECTRICIDAD = 0.385;
    private static final double FACTOR_GAS          = 1.20;

    private String nombre;
    private double consumoEnergia;
    private double metrosCuadrados;
    private String tipoCalefaccion;

    /**
     * @param nombre          nombre o dirección del edificio
     * @param consumoEnergia  consumo energético anual en kWh
     * @param metrosCuadrados área del edificio en m²
     * @param tipoCalefaccion tipo de calefacción: "electrica" o "gas"
     */
    public Edificio(String nombre, double consumoEnergia,
                    double metrosCuadrados, String tipoCalefaccion) {
        this.nombre          = nombre;
        this.consumoEnergia  = consumoEnergia;
        this.metrosCuadrados = metrosCuadrados;
        this.tipoCalefaccion = tipoCalefaccion;
    }

    /**
     * Comportamiento único de Edificio: eficiencia energética por m².
     * @return kWh consumidos por metro cuadrado al año
     */
    public double getEficiencia() {
        if (metrosCuadrados <= 0) return 0;
        return consumoEnergia / metrosCuadrados;
    }

    @Override
    public double getHuellaCarbono() {
        double factor = FACTOR_ELECTRICIDAD;
        if ("gas".equalsIgnoreCase(tipoCalefaccion)) {
            factor *= FACTOR_GAS;
        }
        return consumoEnergia * factor;
    }

    public String getNombre()                    { return nombre; }
    public void   setNombre(String n)            { this.nombre = n; }
    public double getConsumoEnergia()            { return consumoEnergia; }
    public void   setConsumoEnergia(double c)    { this.consumoEnergia = c; }
    public double getMetrosCuadrados()           { return metrosCuadrados; }
    public void   setMetrosCuadrados(double m)   { this.metrosCuadrados = m; }
    public String getTipoCalefaccion()           { return tipoCalefaccion; }
    public void   setTipoCalefaccion(String t)   { this.tipoCalefaccion = t; }

    @Override
    public String toString() {
        return String.format("Edificio | %s | %.0f m² | Calefacción: %s | Huella: %.2f kg CO2/año",
                nombre, metrosCuadrados, tipoCalefaccion, getHuellaCarbono());
    }
}
