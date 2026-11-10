public class Main {
    public static void main(String[] args) throws Exception {
        String ruta = "programa.txt";            // archivo que se va a analizar

        LectorArchivo archivo = new LectorArchivo();
        Analizador analizador = new Analizador();

        archivo.abrir(ruta);
        int numLinea = 0;
        while (archivo.hayLinea()) {             // se lee línea por línea
            numLinea++;
            analizador.analizarLinea(archivo.leerLinea(), numLinea);
        }
        archivo.cerrar();

        analizador.imprimirTablas();
    }
}
