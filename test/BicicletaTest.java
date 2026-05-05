package test;

import Clases.Bicicleta;
import Clases.Carro;
import Interfaces.HuellaCarbono;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BicicletaTest {

    private Bicicleta bicicleta;

    @BeforeEach
    void setUp() {
        bicicleta = new Bicicleta("montana", 12.0, 5.0, 4, 2000);
    }

    @Test
    @DisplayName("Debe calcular correctamente la huella de carbono")
    void testHuellaCarbono() {
        double esperado = 5.0 + (2000 * 0.005);
        assertEquals(esperado, bicicleta.getHuellaCarbono(), 0.01);
    }

    @Test
    @DisplayName("Debe calcular correctamente los kilómetros totales")
    void testGetKilometros() {
        assertEquals(8000.0, bicicleta.getKilometros(), 0.01);
    }

    @Test
    @DisplayName("Huella de bicicleta sin uso solo tiene emisiones de fabricación")
    void testHuellaSinUso() {
        Bicicleta b = new Bicicleta("urbana", 10.0, 5.0, 1, 0);
        assertEquals(5.0, b.getHuellaCarbono(), 0.01);
    }

    @Test
    @DisplayName("La huella de una bicicleta debe ser menor que la de un carro")
    void testBicicletaMenorQueCarro() {
        Carro carro = new Carro("X", "gasolina", 7.0, 2000);
        assertTrue(bicicleta.getHuellaCarbono() < carro.getHuellaCarbono());
    }

    @Test
    @DisplayName("Bicicleta debe implementar HuellaCarbono")
    void testImplementaInterfaz() {
        assertTrue(bicicleta instanceof HuellaCarbono);
    }

    @Test
    @DisplayName("getKilometros debe aumentar con más años de uso")
    void testKilometrosAumentanConAnios() {
        Bicicleta b1 = new Bicicleta("ruta", 8.0, 4.0, 2, 3000);
        Bicicleta b2 = new Bicicleta("ruta", 8.0, 4.0, 5, 3000);
        assertTrue(b2.getKilometros() > b1.getKilometros());
    }
}
