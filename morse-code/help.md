# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.3/maven-plugin/reference/html/#build-image)

Crea un objeto que contenga una función que sea capaz de transformar texto natural a código morse y viceversa.
Debe detectar automáticamente de qué tipo se trata y realizar  la conversión.
* En morse se soporta raya “—”, punto “.”, un espacio ” ” entre letras o símbolos y dos espacios entre palabras ”  “. Es decir “Hola Mundo” se traduce a “.... --- .-.. .- -- ..- -. -.. ---”
* NO se espera una conversión de audio sino una traducción de texto a texto . Es decir si la función recibe como parámetro “ABC DEF” devolver el string “.- -... -.-.  -.. . ..-.”
* El alfabeto morse soportado será el mostrado en  el thread siguiente