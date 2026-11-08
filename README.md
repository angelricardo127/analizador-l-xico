# analizador-lexico
Un analizador léxico, se refiere a la primera parte del compilador de un lenguaje de programación,se encarga de verificar que durante el proceso de
escritura del archivo fuente, se hayan utilizado solamente símbolos y estructuras léxicas reconocidas por el lenguaje.

Este proyecto fue realizado con lenguaje Java.

La lógica utilizada fue la aplicación de expresiones regulares para identificar palabras válidas y lograr su descripción, así como la construccion de un Autómata Finito no Determinsta para verificación de las posibles palabras a tomar en cuenta.

Los insumos necesarios para que el analizador léxico comience su funcionamiento son:
• El archivo fuente que contiene el programa a traducir.
• La orden del analizador sintáctico para producir una unidad léxica (token).
• Las clasificaciones léxicas aceptadas por el lenguaje y sus correspondientes patrones.

El analizador léxico proporciona como salida los tokens necesarios para que el analizador sintáctico realice su función.


