import Interfaces.HuellaCarbono;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase ArchivoService.
 * Principio SRP: única responsabilidad — leer y escribir archivos.
 * Principio DIP: depende de la abstracción HuellaCarbono, no de clases concretas.
 */
public class ArchivoService {

    /**
     * Guarda la lista de objetos HuellaCarbono en un archivo de texto.
     * @param lista lista polimórfica de objetos
     * @param ruta  ruta del archivo destino
     * @throws IOException si ocurre un error de escritura
     */
    public void guardaEnArchivo(List<HuellaCarbono> lista, String ruta) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            writer.write("=== REPORTE DE HUELLA DE CARBONO ===");
            writer.newLine();
            writer.newLine();
            double total = 0.0;
            int num = 1;
            for (HuellaCarbono objeto : lista) {
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

    /**
     * Lee el contenido de un archivo y lo retorna como lista de líneas.
     * @param ruta ruta del archivo a leer
     * @return lista de líneas del archivo
     * @throws IOException si ocurre un error de lectura
     */
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
}
