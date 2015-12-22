package vista;

import controlador.Controlador;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import modelo.Alumno;
import static vista.Vista.CREATE;
import static vista.Vista.DELETE;
import static vista.Vista.EXIT;
import static vista.Vista.READ;
import static vista.Vista.UPDATE;

/**
 * Project 1415ceed106prgt8e1
 * @version 1.0
 * Package vista
 * File VistaTerminal.java
 * Code UTF-8
 * @since Mar 26, 2015, 4:41:46 PM
 * @author Joan Dídac Viana Fons <joanvianafons@gmail.com>;
 */
public class VistaTerminal extends JFrame implements Vista {

    private Controlador controlador;

    public void solicitaNuevaOperacion() throws IOException {

        System.out.println("MENU CRUD ");
        System.out.println("e. exit ");
        System.out.println("c. create ");
        System.out.println("r. read ");
        System.out.println("u. update ");
        System.out.println("d. delete ");
        System.out.print("Opción:  ");       
    }
    /*
    private char LeerCaracter() throws IOException {

        InputStreamReader isr = new InputStreamReader(System.in);
        char c = (char) isr.read();
        return c;
    }
    */
    private String LeerLinea() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String linea = br.readLine();
        return linea;
    }
    
    private void procesaNuevaOperacion() throws IOException {

        String command = null;
        solicitaNuevaOperacion();
        //command = Character.toString(LeerLinea().charAt(0)).toLowerCase();
        command = LeerLinea();
        command = command.toLowerCase();

        while (!command.equals("e")) {
        switch (command) {
            case "e":
                controlador.actionPerformed(new ActionEvent(this, 1, EXIT));                
                break;
            case "c":
                controlador.actionPerformed(new ActionEvent(this, 2, CREATE));
                procesaNuevaOperacion();
                break;
            case "r":
                controlador.actionPerformed(new ActionEvent(this, 3, READ));
                procesaNuevaOperacion();
                break;
            case "u":
                controlador.actionPerformed(new ActionEvent(this, 4, UPDATE));
                procesaNuevaOperacion();
                break;
            case "d":
                controlador.actionPerformed(new ActionEvent(this, 5, DELETE));
                procesaNuevaOperacion();
                break;
            default:
                error();
                break;
        }
        }
    }

    public void error() {
        System.out.println("Error: Opcion Incorrecta ");
    }

// Define los eventos CRUD
    @Override
    public void setControlador(Controlador c) {
        controlador = c;
    }

// comienza la visualización
    @Override
    public void arranca() {
        try {
            procesaNuevaOperacion();
        } catch (IOException ex) {
            Logger.getLogger(VistaTerminal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// Funcion que crea el alumno del formulario en el modelo
    @Override
    public String pedirId() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        char c = ' ';
        String linea = "";

        System.out.print("Id: ");
        try {
            linea = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(VistaTerminal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return linea;
    }

// Actualiza el alumno
    @Override
    public void update(Alumno alumno) {
      System.out.println(" Se ha modificado exitosamente el Alumno con id " + alumno.getId());  
    }
    
    @Override
    public void delete (Alumno alumno) {
      System.out.println(" Se ha eliminado exitosamente el Alumno con id " + alumno.getId());  
    }

    @Override
    public void exit() {
        System.out.println("Fin ");
        System.exit(0);
    }

// Muestra mensaje de error
    @Override
    public void error(String id) {
        System.out.println(" Error : El id " + id + " no se ha encontrado");
    }

// Muestra los datos de un alumno
    @Override
    public void mostrarAlumno(Alumno alumno) {
        if (alumno == null) {
            error();
        } else {
            System.out.println("Id: " + alumno.getId());
            System.out.println("Nombre: " + alumno.getNombre());
            System.out.println("Edad: " + alumno.getEdad());
            System.out.println("Email: " + alumno.getEmail());
        }
        try {
            procesaNuevaOperacion();
        } catch (IOException ex) {
            Logger.getLogger(VistaTerminal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// Pide los datos del alumno
    @Override
    public Alumno pedirAlumno() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        Alumno a;
        String nombre = "";
        String email = "";
        String linea = "";
        int edad = 0;
        boolean error = true;
        boolean esvalido = true;
        String id;

        System.out.println("ENTRADA DE DATOS");

        System.out.print("Nombre: ");
        try {
            nombre = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(VistaTerminal.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (error == true) {

            System.out.print("Edad: ");
            try {
                linea = br.readLine();
                edad = Integer.parseInt(linea);
                error = false;
            } catch (IOException ex) {
                Logger.getLogger(VistaTerminal.class.getName()).log(Level.SEVERE, null, ex);

            } catch (NumberFormatException nfe) {
                System.out.println("Error: Se debe introducir un número");
                error = true;
            }

        } // while

        error = true;
        while (error == true) {

            System.out.print("Email: ");
            try {
                email = br.readLine();
                esvalido = validarEmail(email);
                if (esvalido) {
                    error = false;
                } else {
                    System.out.println("Error: Email no valido");
                    error = true;
                }

            } catch (IOException ex) {
                Logger.getLogger(VistaTerminal.class.getName()).log(Level.SEVERE, null, ex);

            }

        } // while
        a = new Alumno("", nombre, edad, email);
        return a;
    }

    public static boolean validarEmail(String s) {
        Pattern p = Pattern.compile("[\\w\\.]+@\\w+\\.\\w+");
        Matcher m = p.matcher(s);
        return m.matches();
    }
    /*
     public void showAlumnos(HashSet<Alumno> hs) {

     Iterator it = hs.iterator();
     Alumno alumno;

     if (!hs.isEmpty()) {
     System.out.println("DATOS ALUMNOS");
     System.out.println("-------------");
     while (it.hasNext()) {
     alumno = (Alumno) it.next();
     readAlumno(alumno);
     System.out.println("...........");
     }
     } else {
     System.out.println("La lista está vacía");
     }
     }
     */
}
