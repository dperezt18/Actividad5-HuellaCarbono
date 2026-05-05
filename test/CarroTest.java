package test;

import Clases.Carro;
import Interfaces.HuellaCarbono;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarroTest {

    private Carro carroGasolina;
    private Carro carroDiesel;

    @BeforeEach
    void setUp() {
        carroGasolina = new Carro("Corolla", "gasolina", 7.0, 10000);
        carroDiesel   = new Carro("Logan",   "diesel",   6.0, 15000);
    }

    @Test
    @DisplayName("Debe calcular correctamente la huella con gasolina")
    void testHuellaGasolina() {
        double esperado = (10000.0 / 100.0) * 7.0 * 2.31;
        assertEquals(esperado, carroGasolina.getHuellaCarbono(), 0.01);
    }

    @Test
    @DisplayName("Debe calcular correctamente la huella con diesel")
    void testHuellaDiesel() {
        double esperado = (15000.0 / 100.0) * 6.0 * 2.68;
        assertEquals(esperado, carroDiesel.getHuellaCarbono(), 0.01);
    }

    @Test
    @DisplayName("Diesel debe generar más huella que gasolina con el mismo consumo")
    void testDieselMayorQueGasolina() {
        Carro g = new Carro("X", "gasolina", 7.0, 10000);
        Carro d = new Carro("X", "diesel",   7.0, 10000);
        assertTrue(d.getHuellaCarbono() > g.getHuellaCarbono());
    }

    @Test
    @DisplayName("Debe calcular correctamente la eficiencia en km/L")
    void testEficiencia() {
        assertEquals(100.0 / 7.0, carroGasolina.getEficiencia(), 0.01);
    }

    @Test
    @DisplayName("Eficiencia debe ser 0 si consumo es 0")
    void testEficienciaCero() {
        Carro inv = new Carro("X", "gasolina", 0.0, 10000);
        assertEquals(0.0, inv.getEficiencia());
    }

    @Test
    @DisplayName("Carro debe implementar HuellaCarbono")
    void testImplementaInterfaz() {
        assertTrue(carroGasolina instanceof HuellaCarbono);
    }
}
