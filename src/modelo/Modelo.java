/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 * Project 1415ceed106prgt8e1
 * @version 1.0
 * Package modelo
 * File Modelo.java
 * Code UTF-8
 * @since Mar 26, 2015, 4:39:35 PM
 * @author Joan Dídac Viana Fons <joanvianafons@gmail.com>;
 */
public interface Modelo {

  public void create(Alumno alumno); // Crea un alumno nuevo

  public Alumno read(String id); // Muestra los alunnos.

  public void update(Alumno alumno); // Actuzaliza el alumno.

  public void delete(Alumno alumno);  // Borrar el alunno con el id dado

  public int getId(); // Obtiene el último id dado. Usado para create.

}