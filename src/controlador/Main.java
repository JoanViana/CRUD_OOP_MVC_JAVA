package controlador;

import java.io.IOException;
import modelo.Modelo;
import modelo.ModeloFichero;
import modelo.ModeloHashSet;
import modelo.ModeloArrayList;
import modelo.ModeloDb4o;
import modelo.ModeloMysql;
import modelo.ModeloVector;
import vista.VistaTerminal;
import vista.Vista;
import vista.VistaPantalla;

/**
 * Project 1415ceed106prgt9e1
 * @version 1.0
 * Package controlador
 * File Main.java
 * Code UTF-8
 * @since Mar 26, 2015, 4:39:01 PM
 * @author Joan Dídac Viana Fons <joanvianafons@gmail.com>;
 */
public class Main {

    public static void main(String[] args) throws IOException {
//       Modelo modelo = new ModeloDb4o();
//        Modelo modelo = new ModeloMysql();
//        Modelo modelo = new ModeloFichero();
//        Modelo modelo = new ModeloArrayList();
        Modelo modelo = new ModeloHashSet();
//        Modelo modelo = new ModeloVector();
        Vista vista = new VistaPantalla();
//        Vista vista = new VistaTerminal();
        Controlador c = new Controlador(modelo, vista);
    }
}
