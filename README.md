# Practica 6: Testing de una aplicacion de Spring Boot

En esta práctica se va a realizar por un lado una comprobación de las clases adjuntadas en el enunciado de la práctica (Telefono.java, DNI.java y ProcessController.java)

## Puebas JUnit

En esta parte se han comprobado los archivos Telefono.java y DNI.java mediante el uso de tests:

- DNITest.java: se comprueban un DNI falso y otro verdadero
- TelefonoTest.java: se comprueban 3 teléfonos: uno español, uno internacional y uno falso


## Pruebas End to End

En esta parte se va a comprobar el correcto funcionamiento de la clase ProcessController.java.

Para ello, se ha creado una clase test en la que se realizan varias comprobaciones:

- Usuario correcto
- Usuario con nombre incorrecto
- Usuario con DNI incorrecto
- Usuario con telefono incorrecto

Tras comprobar que se realizan correctamente cada una de las funcionalidades se ha concluido que están correctamente diseñadas.
