import Clases.ArchivoService;
import Clases.Bicicleta;
import Clases.Carro;
import Clases.Edificio;
import Interfaces.HuellaCarbono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal HuellaCarbonoApp.
 *
 * Demuestra:
 *  - Polimorfismo: ArrayList<HuellaCarbono> almacena Edificio, Carro y Bicicleta.
 *  - Modularidad: carpetas Clases e Interfaces con responsabilidades separadas.
 *  - Manejo de archivos: ArchivoService guarda y lee el reporte en .txt.
 *  - Reutilización: la interfaz HuellaCarbono es el contrato común sin herencia entre clases.
 */
public class HuellaCarbonoApp {

    private ArrayList<HuellaCarbono> listaHuella;

    public HuellaCarbonoApp() {
        listaHuella = new ArrayList<>();
    }

    /**
     * Agrega un objeto a la lista polimórfica.
     * @param objeto implementación de HuellaCarbono a agregar
     */
    public void agregar(HuellaCarbono objeto) {
        listaHuella.add(objeto);
    }

    /**
     * Itera polimórficamente la lista e imprime la huella de cada objeto.
     */
    public void displayFootprints() {
        System.out.println("=== REPORTE DE HUELLA DE CARBONO ===\n");
        double total = 0.0;
        for (HuellaCarbono objeto : listaHuella) {
            System.out.println(objeto.toString());
            total += objeto.getHuellaCarbono();
        }
        System.out.printf("HUELLA TOTAL: %.2f kg CO2/año%n", total);
    }

    /**
     * Retorna la lista polimórfica de objetos HuellaCarbono.
     * @return ArrayList con todos los objetos registrados
     */
    public ArrayList<HuellaCarbono> getListaHuella() {
        return listaHuella;
    }

    public static void main(String[] args) {
        HuellaCarbonoApp app = new HuellaCarbonoApp();

        app.agregar(new Edificio("Av. Caracas 45-67", 8500.0, 250.0, "electrica"));
        app.agregar(new Edificio("Cra. 7 #80-20",    6200.0, 180.0, "gas"));

        app.agregar(new Carro("Corolla",  "gasolina", 6.5, 15000));
        app.agregar(new Carro("Logan",    "diesel",   5.8, 20000));

        app.agregar(new Bicicleta("montana", 12.0, 5.0, 4, 2500));
        app.agregar(new Bicicleta("ruta",    8.5,  4.5, 3, 3500));

        app.displayFootprints();

        ArchivoService archivoService = new ArchivoService();
        String ruta = "reporte_huella_carbono.txt";
        try {
            archivoService.guardaEnArchivo(app.getListaHuella(), ruta);
            System.out.println("\nArchivo guardado: " + ruta);
            System.out.println("\n--- Contenido del archivo ---");
            archivoService.leerArchivo(ruta).forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Error al manejar el archivo: " + e.getMessage());
        }
    }
}
