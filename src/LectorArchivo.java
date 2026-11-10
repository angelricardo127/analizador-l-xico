import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * Lector de archivos
 * Se encarga de abrir el archivo de texto, darlo línea por línea y cerrarlo.
 */
public class LectorArchivo {
    private Scanner lector;

    // Metodo que abre el archivo que está en la ruta indicada
    public void abrir(String ruta) throws FileNotFoundException { lector = new Scanner(new File(ruta));}

    // Dice si todavía quedan líneas por leer
    public boolean hayLinea() { return lector.hasNextLine(); }

    // Regresa la siguiente línea completa del archivo
    public String leerLinea() { return lector.nextLine(); }

    // Cierra el archivo al terminar
    public void cerrar() {
        if (lector != null) {
            lector.close();
        }
    }
}
