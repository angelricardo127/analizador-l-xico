/*
* Tecnológico Nacional de México, Instituto Tecnológico de León
* Ingeniería en Sistemas Computacionales
* Lenguajes y Autómatas I
*
* @author Herrera Hermosillo Uriel Sebastián
* @author Herrández Nava Angel Ricardo
*
* Analizador Léxico
* @version 1.0
*
* La clase Analizador implementa el funcionamiento de un analizador léxico
 * mediante la simulación de un Autómata Finito No Determinista.
 * Su objetivo principal es procesar el código fuente de un programa de
 * entrada, agrupando secuencias de caracteres para formar componentes
 * léxicos válidos (tokens) según las reglas de un lenguaje de programación
 * definido previamente.
* */
import java.util.ArrayList;

public class Analizador {

    /**
    * Tabla de palabras reservadas que se codifica desde el inicio ya que las palabras reservadas
    * son categorias lexicas que ya estan implicitas desde el inicio. Mediante un arreglo de Stings
    * definimos las palabras reservadas para esa categoria léxica. Definimos sus respectivos atributos
    * en un arreglo de enteros estos atributos son asignados por nostros los programadores.
    * */
    String[] reservadas = {"Programa", "Real", "Entero", "Leer", "Escribir",
            "Si", "Entonces", "Sino", "Inicio", "Fin"};
    int[] atributosReservadas = {300, 301, 302, 303, 304, 305, 306, 307, 308, 309};

    /**
    Tablas que se llenan en tiempo de ejecucion, todos son arryalist ya que no sabemos el tamaño exacto
    de la tabla, lo que la hace una estructura de datos dinamica.
     */
    // Tabla de símbolos: lexema, atributo
    ArrayList<String> simbolosLexema = new ArrayList<>();
    ArrayList<Integer> simbolosAtributo = new ArrayList<>();

    // Tabla de tokens válidos: lexema, clasificación léxica, atributo
    ArrayList<String> tokensLexema = new ArrayList<>();
    ArrayList<String> tokensClasificacion = new ArrayList<>();
    ArrayList<Integer> tokensAtributo = new ArrayList<>();

    // Tabla de errores: lexema, línea, descripción
    ArrayList<String> erroresLexema = new ArrayList<>();
    ArrayList<Integer> erroresLinea = new ArrayList<>();
    ArrayList<String> erroresDescripcion = new ArrayList<>();

    int numLinea;   // variable de tipo entera para almacenar la línea que se está analizando

    /**
     1. Para separar las palabras se recorre la línea carácter por carácter.
        La palabra termina con un espacio o con un carácter simple.
    */
    /**
    * Metodo publico, que no retorna un resultado. Recibe como parametos un String llamado linea
    * y el entero antes declarado numLinea. Este metodo analiza linea por linea usando un ciclo
    * for.
    * */
    public void analizarLinea(String linea, int numLinea) {
        this.numLinea = numLinea;
        String palabra = "";

        for (int i = 0; i < linea.length(); i++) {
            char c = linea.charAt(i); //se extrae la letra que esta en la posicion de i y se guarda en c

            if (c == ' ' || c == '\t') { //si c es un espacio en blanco o un tabulador
                // Espacio: la palabra terminó y se manda al autómata
                if (!palabra.equals("")) {
                    automata(palabra);
                    palabra = "";
                }

            } else if (esCaracterSimple(c)) {
                // Carácter simple: primero se manda la palabra que venía
                if (!palabra.equals("")) {
                    automata(palabra);
                    palabra = "";
                }
                // y luego el símbolo. Si es == o <> se mandan los dos juntos.
                if (c == '=' && i + 1 < linea.length() && linea.charAt(i + 1) == '=') {
                    automata("==");
                    i++;                // brincamos el segundo '='
                } else if (c == '<' && i + 1 < linea.length() && linea.charAt(i + 1) == '>') {
                    automata("<>");
                    i++;                // brincamos el '>'
                } else {
                    automata("" + c);
                }

            } else {
                // Cualquier otro carácter se pega a la palabra
                palabra = palabra + c;
            }
        }

        // La última palabra de la línea
        if (!palabra.equals("")) {
            automata(palabra);
        }
    }


    public void automata(String palabra) {
        int estado = 0; //siempre iniciamos en el estado inicial cero
        String descripcion = "";

        for (int i = 0; i < palabra.length(); i++) { //este ciclo for recorre caracter por caracter
            char c = palabra.charAt(i);
/**
 * Este primer switch lee un lexema carácter por carácter para determinar
 * a que cateogria lexica pertenece
 * */
            switch (estado) {
                case 0:   // estado inicial
                    if (esMinuscula(c)) estado = 1; //si es minuscula va al estado 1
                    else if (c >= '1' && c <= '9') estado = 3; //si es del 1 al 9 va al estado 3
                    else if (c == '0') estado = 4; //si es un cero va al estado 4
                    else if (c == '=') estado = 8;
                    else if (c == '<') estado = 9;
                    else if (esCaracterSimple(c)) estado = 7; //si es caracter simple va al estado 7 de los caracteres
                    else if (esMayuscula(c)) estado = 10; // si es mayuscula va al estado 10 de palabras reservadas
                    else { estado = 11; descripcion = "Caracter no valido al inicio de la palabra"; }
                    break;

                case 1:   // identificador, acepta letras minusculas y digitos y se queda en el estado 1
                    if (esMinuscula(c) || esDigito(c)) estado = 1;
                    else if (c == '_')                 estado = 2;  //si lee un guion bajo salta al estado 2
                    else { estado = 11; descripcion = "Identificador con caracteres no validos"; }
                    break;

                case 2:   // leyó _ va al estado 2
                    if (c == '_')                           estado = 2;
                    else if (esMinuscula(c) || esDigito(c)) estado = 1;
                    else { estado = 11; descripcion = "Identificador con caracteres no validos"; }
                    break;

                case 3:   // número que empezó con 1-9
                    if (esDigito(c))  estado = 3; //si c es un digito va al estado 3
                    else if (c == '.') estado = 5; // si es un punto va al esatdo 5
                    else { estado = 11; descripcion = "Numero con caracteres no validos"; }
                    break;

                case 4:   // leyó 0
                    if (c == '.') estado = 5; //si es un punto va al estado 5
                    else { estado = 11; descripcion = "Numero que inicia con 0"; }
                    break;

                case 5:   // leyó el punto obligatoriamente debe segur un digito
                    if (esDigito(c)) estado = 6; //si leyo el pubto y un digito va ala estado 6
                    else { estado = 11; descripcion = "Despues del punto debe ir un digito"; }
                    break;

                case 6:   // número real
                    if (esDigito(c)) estado = 6;
                    else { estado = 11; descripcion = "Numero real con caracteres no validos"; }
                    break;

                case 8:   // leyó '='
                    if (c == '=') estado = 7;      // es ==
                    else { estado = 11; descripcion = "Simbolo no valido"; }
                    break;

                case 9:   // leyó '<'
                    if (c == '>') estado = 7;      // es <>
                    else { estado = 11; descripcion = "Simbolo no valido"; }
                    break;

                case 7:   // carácter simple: después ya no puede venir nada
                    estado = 11; descripcion = "Simbolo no valido";
                    break;

                case 10:  // palabra que empezó con mayúscula
                    if (esMinuscula(c)) estado = 10;
                    else { estado = 11; descripcion = "Palabra con mayuscula que no es reservada"; }
                    break;

                case 11:  // ERROR: de aquí ya no se sale
                    estado = 11;
                    break;
            }
        }

        /**
         * En este switch evalúa en qué estado quedó al terminar la palabra
         * para guardarla como un token válido o reportar un error.
        * */
        switch (estado) {
            case 1:
                guardarToken(palabra, "Identificador", 258);
                if (!simbolosLexema.contains(palabra)) {   // se agregga en la tabla de simbolos
                    simbolosLexema.add(palabra);
                    simbolosAtributo.add(258); //se le agrega el atributo 258
                }
                break;
            case 3:
            case 4:
                guardarToken(palabra, "Numero entero", 257);
                break;
            case 6:
                guardarToken(palabra, "Numero real", 259);
                break;
            case 7:
            case 8:
            case 9:
                if (palabra.equals("=="))      guardarToken(palabra, "Caracter simple", 260);
                else if (palabra.equals("<>")) guardarToken(palabra, "Caracter simple", 261);
                else                           guardarToken(palabra, "Caracter simple", (int) palabra.charAt(0));
                break;
            case 10:
                int atributo = buscarReservada(palabra);
                if (atributo != -1) guardarToken(palabra, "Palabra reservada", atributo);
                else guardarError(palabra, "Palabra con mayuscula que no es reservada");
                break;
            case 2:
                guardarError(palabra, "Identificador que termina con guion bajo");
                break;
            case 5:
                guardarError(palabra, "Numero real sin parte decimal");
                break;
            case 11:
                guardarError(palabra, descripcion);
                break;
        }
    }

    /**Busca la palabra en la tabla de reservadas; regresa su atributo o -1 si no está*/
    public int buscarReservada(String palabra) {
        for (int i = 0; i < reservadas.length; i++) {
            if (reservadas[i].equals(palabra)) {
                return atributosReservadas[i];
            }
        }
        return -1;
    }
    /**Metodo para guardar tokens dentro de la tabla de toknes encontrados*/
    public void guardarToken(String lexema, String clasificacion, int atributo) {
        tokensLexema.add(lexema);
        tokensClasificacion.add(clasificacion);
        tokensAtributo.add(atributo);
        System.out.println("Linea " + numLinea + ":  " + lexema + "  ->  " + clasificacion + " (" + atributo + ")");
    }
    /**Metodo para guardar los errores que tiene el codigo fuente. Se guarda en la tabla de errores lexicos*/
    public void guardarError(String lexema, String descripcion) {
        erroresLexema.add(lexema);
        erroresLinea.add(numLinea);
        erroresDescripcion.add(descripcion);
        System.out.println("Linea " + numLinea + ":  " + lexema + "  ->  ERROR LEXICO: " + descripcion);
    }

    /**
    * 3. Imprimir las tablas, en este metodo se imprimen las tablas:
     * i. Tabla de símbolos
     * ii. Tabla de palabras reservadas
     * iii. Tabla que contiene todos los tokens válidos identificados con su
     *      correspondiente atributo y nombre de categoría léxica.
     * iv.  Tabla de errores léxicos encontrados
    */
    public void imprimirTablas() {
        System.out.println();
        System.out.println("TABLA DE PALABRAS RESERVADAS");
        System.out.println("Lexema  |  Atributo");
        for (int i = 0; i < reservadas.length; i++) {
            System.out.println(reservadas[i] + "    " + atributosReservadas[i]);
        }

        System.out.println();
        System.out.println("TABLA DE SIMBOLOS");
        System.out.println("Lexema  |  Atributo");
        for (int i = 0; i < simbolosLexema.size(); i++) {
            System.out.println(simbolosLexema.get(i) + "    " + simbolosAtributo.get(i));
        }

        System.out.println();
        System.out.println("TABLA DE TOKENS VALIDOS");
        System.out.println("Lexema  |  Clasificacion lexica  |  Atributo");
        for (int i = 0; i < tokensLexema.size(); i++) {
            System.out.println(tokensLexema.get(i) + "    " + tokensClasificacion.get(i) + "    " + tokensAtributo.get(i));
        }

        System.out.println();
        System.out.println("TABLA DE ERRORES LEXICOS");
        System.out.println("Lexema  |  Linea  |  Descripcion");
        for (int i = 0; i < erroresLexema.size(); i++) {
            System.out.println(erroresLexema.get(i) + "    " + erroresLinea.get(i) + "    " + erroresDescripcion.get(i));
        }
    }

    // Para saber qué tipo de carácter es
    public boolean esMinuscula(char c) { return (c >= 'a' && c <= 'z') || c == 'ñ'; }
    public boolean esMayuscula(char c) { return (c >= 'A' && c <= 'Z') || c == 'Ñ'; }
    public boolean esDigito(char c)    { return c >= '0' && c <= '9'; }

    public boolean esCaracterSimple(char c) {
        return c == ';' || c == '=' || c == '+' || c == '-' || c == '/' || c == '('
                || c == ')' || c == ',' || c == '<' || c == '>' || c == '*';
    }
}
