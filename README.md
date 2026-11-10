# Analizador Léxico

<p align="center">
  <samp>LEXICAL ANALYZER / COMPILER DESIGN</samp>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Language-Java-333333?style=flat-square&logo=openjdk&logoColor=white">
  <img src="https://img.shields.io/badge/Project-Academic-333333?style=flat-square">
  <img src="https://img.shields.io/badge/Status-In%20Development-333333?style=flat-square">
</p>

---

## Descripción general

Este proyecto implementa un analizador léxico desarrollado en Java como parte de la asignatura de Lenguajes y Autómatas I.

Un analizador léxico constituye la primera etapa de un compilador. Su función principal consiste en procesar el código fuente de entrada, identificar secuencias de caracteres y clasificarlas en «tokens» (unidades léxicas) conforme a un conjunto de reglas léxicas.

El objetivo de este proyecto es explorar los fundamentos del análisis léxico y comprender cómo el código fuente puede transformarse en una secuencia estructurada de «tokens».

## Objetivos

- Comprender el papel del análisis léxico en el diseño de compiladores.
- Identificar y clasificar los tokens léxicos.
- Procesar el código fuente carácter a carácter.
- Aplicar reglas léxicas para reconocer secuencias válidas.
- Desarrollar habilidades prácticas de programación utilizando Java.

## Tecnologías

| Tecnología | Propósito |
|---|---|
| Java | Main programming language |
| Git | Version control |
| GitHub | Source code hosting |

## Project Structure

```text
analizador-l-xico/
│
├── src/
│   └── ...
│
├── README.md
└── .gitignore
```

*The directory structure may vary according to the current implementation.*

## Como usarlo

### Requerimietos

- Java Development Kit (JDK).
- Git.
- An IDE or text editor capable of working with Java.

### Clonar el repositorio

```bash
git clone https://github.com/angelricardo127/analizador-l-xico.git
```

### Accede al directorio del proyecto

```bash
cd analizador-l-xico
```

### Corre el proyecto

Abre el proyecto en IntelliJ IDEA o en tu entorno de desarrollo Java preferido.

Compila y ejecuta la clase principal conforme a la implementación del proyecto.

> Las instrucciones exactas de ejecución se añadirán una vez que la clase principal y la configuración de compilación estén documentadas.

## Cómo funciona

El proceso general de análisis léxico consiste en:

1. Leer el código fuente de entrada.
2. Analizar sus secuencias de caracteres.
3. Reconocer patrones de acuerdo con reglas léxicas.
4. Clasificar las secuencias reconocidas en tokens.
5. Informar sobre la información léxica resultante o sobre los errores encontrados.

## Ejemplo

A continuación, se presenta un ejemplo ilustrativo de clasificación léxica:
**Input:**

```java
int number = 10;
```

**Posible clasificación de tokens:**

| Lexeme | Category |
|---|---|
| `int` | Palabra reservada |
| `number` | Identificador |
| `=` | Caracter simple |
| `10` | Números enteros (naturales) |
| `;` | Caracter simple |

*Este ejemplo ilustra el concepto esperado de análisis léxico.

## Documentación

Se añadirán documentación adicional, ejemplos y detalles de implementación a medida que el proyecto evolucione.

## Author

**Ricardo Hernández**

**Uriel Herrera**

Computer Systems Engineering Student

GitHub: [@angelricardo127](https://github.com/angelricardo127)

## License

This project is available for educational and learning purposes.

---

<p align="center">
  <samp>Languages and Automata · Compiler Design</samp>
</p>
