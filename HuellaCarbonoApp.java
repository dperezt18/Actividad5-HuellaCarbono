import Clases.Bicicleta;
import Clases.Carro;
import Clases.Edificio;
import Interfaces.HuellaCarbono;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal HuellaCarbonoApp.
 *
 * Demuestra:
 *  - Polimorfismo: ArrayList<HuellaCarbono> almacena Edificio, Carro y Bicicleta.
 *  - Modularidad: carpetas Clases e Interfaces con responsabilidades separadas.
 *  - Manejo de archivos: guarda y lee el reporte en .txt.
 *  - Reutilización: la interfaz HuellaCarbono es el contrato común sin herencia entre clases.
 */
public class HuellaCarbonoApp {

    private ArrayList<HuellaCarbono> listaHuella;

    public HuellaCarbonoApp() {
        listaHuella = new ArrayList<>();
    }

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

    public void guardaEnArchivo(String ruta) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            writer.write("=== REPORTE DE HUELLA DE CARBONO ===");
            writer.newLine();
            writer.newLine();
            double total = 0.0;
            int num = 1;
            for (HuellaCarbono objeto : listaHuella) {
                writer.write("Objeto #" + num + ":");
                writer.newLine();
                writer.write("  " + objeto.toString());
                writer.newLine();
                writer.newLine();
                total += objeto.getHuellaCarbono();
                num++;
            }
            writer.write("=====================================");
            writer.newLine();
            writer.write(String.format("HUELLA TOTAL: %.2f kg CO2/año", total));
            writer.newLine();
        }
    }

    public List<String> leerArchivo(String ruta) throws IOException {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                lineas.add(linea);
            }
        }
        return lineas;
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

        String ruta = "reporte_huella_carbono.txt";
        try {
            app.guardaEnArchivo(ruta);
            System.out.println("\nArchivo guardado: " + ruta);
            System.out.println("\n--- Contenido del archivo ---");
            app.leerArchivo(ruta).forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Error al manejar el archivo: " + e.getMessage());
        }
    }
}
