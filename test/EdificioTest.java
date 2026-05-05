package test;

import Clases.Edificio;
import Interfaces.HuellaCarbono;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdificioTest {

    private Edificio edificioElectrico;
    private Edificio edificioGas;

    @BeforeEach
    void setUp() {
        edificioElectrico = new Edificio("Calle 1 # 2-3", 5000.0, 200.0, "electrica");
        edificioGas       = new Edificio("Calle 4 # 5-6", 4000.0, 150.0, "gas");
    }

    @Test
    @DisplayName("Debe calcular correctamente la huella con calefacción eléctrica")
    void testHuellaElectrico() {
        double esperado = 5000.0 * 0.385;
        assertEquals(esperado, edificioElectrico.getHuellaCarbono(), 0.01);
    }

    @Test
    @DisplayName("Debe calcular correctamente la huella con calefacción a gas")
    void testHuellaGas() {
        double esperado = 4000.0 * 0.385 * 1.20;
        assertEquals(esperado, edificioGas.getHuellaCarbono(), 0.01);
    }

    @Test
    @DisplayName("La huella con gas debe ser mayor que con electricidad para igual consumo")
    void testGasMayorQueElectrico() {
        Edificio e1 = new Edificio("A", 5000.0, 200.0, "electrica");
        Edificio e2 = new Edificio("B", 5000.0, 200.0, "gas");
        assertTrue(e2.getHuellaCarbono() > e1.getHuellaCarbono());
    }

    @Test
    @DisplayName("Debe calcular correctamente la eficiencia energética")
    void testEficiencia() {
        assertEquals(25.0, edificioElectrico.getEficiencia(), 0.01);
    }

    @Test
    @DisplayName("Eficiencia debe ser 0 si metrosCuadrados es 0")
    void testEficienciaConAreaCero() {
        Edificio inv = new Edificio("X", 1000.0, 0.0, "electrica");
        assertEquals(0.0, inv.getEficiencia());
    }

    @Test
    @DisplayName("Edificio debe implementar HuellaCarbono")
    void testImplementaInterfaz() {
        assertTrue(edificioElectrico instanceof HuellaCarbono);
    }
}
