package Interfaces;

/**
 * Interfaz HuellaCarbono
 * Define el contrato que toda clase capaz de calcular huella de carbono debe cumplir.
 * Principio ISP: pequeña y enfocada en una sola responsabilidad.
 */
public interface HuellaCarbono {

    /**
     * Calcula la huella de carbono anual en kg de CO2.
     * @return huella de carbono en kg CO2/año
     */
    double getHuellaCarbono();
}
