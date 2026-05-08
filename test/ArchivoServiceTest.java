package test;

import Clases.ArchivoService;
import Clases.Carro;
import Clases.Edificio;
import Interfaces.HuellaCarbono;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArchivoServiceTest {

    private ArchivoService archivoService;
    private List<HuellaCarbono> lista;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        archivoService = new ArchivoService();
        lista = new ArrayList<>();
        lista.add(new Edificio("Calle 1", 5000.0, 200.0, "electrica"));
        lista.add(new Carro("Corolla", "gasolina", 6.5, 15000));
    }

    @Test
    @DisplayName("El archivo guardado debe existir tras guardaEnArchivo")
    void testArchivoCreado() throws IOException {
        String ruta = tempDir.resolve("reporte.txt").toString();
        archivoService.guardaEnArchivo(lista, ruta);
        assertTrue(new java.io.File(ruta).exists());
    }

    @Test
    @DisplayName("leerArchivo debe retornar al menos una línea")
    void testLeerArchivoContenido() throws IOException {
        String ruta = tempDir.resolve("reporte.txt").toString();
        archivoService.guardaEnArchivo(lista, ruta);
        List<String> lineas = archivoService.leerArchivo(ruta);
        assertFalse(lineas.isEmpty());
    }

    @Test
    @DisplayName("El archivo debe contener la línea HUELLA TOTAL")
    void testContieneHuellaTotal() throws IOException {
        String ruta = tempDir.resolve("reporte.txt").toString();
        archivoService.guardaEnArchivo(lista, ruta);
        List<String> lineas = archivoService.leerArchivo(ruta);
        boolean contiene = lineas.stream().anyMatch(l -> l.contains("HUELLA TOTAL"));
        assertTrue(contiene);
    }

    @Test
    @DisplayName("El archivo debe contener tantos objetos como la lista")
    void testCantidadObjetos() throws IOException {
        String ruta = tempDir.resolve("reporte.txt").toString();
        archivoService.guardaEnArchivo(lista, ruta);
        List<String> lineas = archivoService.leerArchivo(ruta);
        long count = lineas.stream().filter(l -> l.startsWith("Objeto #")).count();
        assertEquals(lista.size(), count);
    }

    @Test
    @DisplayName("guardaEnArchivo con lista vacía no debe lanzar excepción")
    void testListaVacia() {
        String ruta = tempDir.resolve("vacio.txt").toString();
        assertDoesNotThrow(() -> archivoService.guardaEnArchivo(new ArrayList<>(), ruta));
    }

    @Test
    @DisplayName("leerArchivo debe lanzar IOException si el archivo no existe")
    void testLeerArchivoNoExiste() {
        String ruta = tempDir.resolve("no_existe.txt").toString();
        assertThrows(IOException.class, () -> archivoService.leerArchivo(ruta));
    }
}
