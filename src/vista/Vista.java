package vista;

import controlador.Controlador;
import modelo.Alumno;

/**
 * Project 1415ceed106prgt8e1
 * @version 1.0
 * Package vista
 * File Vista.java
 * Code UTF-8
 * @since Mar 26, 2015, 4:41:14 PM
 * @author Joan Dídac Viana Fons <joanvianafons@gmail.com>;
 */
public interface Vista {

static final String CREATE = "Create";
static final String READ = "Read";
static final String UPDATE = "Update";
static final String DELETE = "Delete";
static final String EXIT = "Exit";
static final String ERROR = "Error";

// Define los eventos CRUD
public void setControlador(Controlador c);
// comienza la visualización
public void arranca();
// Funcion que crea el alumno del formulario en el modelo
public String pedirId();
// Actualiza el alumno
public void update(Alumno alumno);
// Elimina el alumno
public void delete(Alumno alumno);
// Se sale de la aplicación
public void exit();
// Muestra mensaje de error
public void error(String error);
// Muestra los datos de un alumno
public void mostrarAlumno(Alumno alumno);
// Pide los datos del alumno
public Alumno pedirAlumno();
}