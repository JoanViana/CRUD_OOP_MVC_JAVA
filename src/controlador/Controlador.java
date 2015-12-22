package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import modelo.Alumno;
import modelo.Modelo;
import vista.Vista;

/**
 * Project 1415ceed106prgt9e1
 * @version 1.0
 * Package controlador
 * File Controlador.java
 * Code UTF-8
 * @since Mar 26, 2015, 4:38:42 PM
 * @author Joan Dídac Viana Fons <joanvianafons@gmail.com>;
 */
public class Controlador implements ActionListener {

    private final Modelo modelo;
    private final Vista vista;
    
    public Controlador(Modelo modelo_, Vista vista_) throws IOException {

        modelo = modelo_;
        vista = vista_;

        vista.setControlador(this);
        vista.arranca();
        
    }

    @Override
    public void actionPerformed(ActionEvent evento) {

        Alumno alumno;
        String id;
        String command = evento.getActionCommand();

        switch (command) {
            
            case Vista.EXIT:
                vista.exit();
                break;
                
            case Vista.CREATE:
                alumno = vista.pedirAlumno();
                id = Integer.toString(modelo.getId() + 1);
                alumno.setId(id);
                modelo.create(alumno);
                vista.mostrarAlumno(alumno);
                break;
                
            case Vista.READ:
                id = vista.pedirId();
                exito(modelo.read(id), id);
                break;

            case Vista.UPDATE:
                id = vista.pedirId();
                alumno = vista.pedirAlumno();
                alumno.setId(id);
                modelo.update(alumno);
                vista.update(alumno);
                break;

            case Vista.DELETE:
                id = vista.pedirId();
                alumno = new Alumno(id, "", 0, "");
                modelo.delete(alumno); 
                vista.delete(alumno);
                break;
        }
    }

    private void exito(Alumno alumno, String id) {
        if (alumno == null) {
            vista.error(id);
        } else {
            vista.mostrarAlumno(alumno);
        }
    }

    public char aMinuscula(char opcion) {

        return (opcion + "").toLowerCase().charAt(0);
      }
}
