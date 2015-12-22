/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 * Project 1415ceed106prgt9e1
 *
 * @version 1.0 Package modelo File Alumno.java Code UTF-8
 * @since Mar 26, 2015, 4:39:44 PM
 * @author Joan Dídac Viana Fons <joanvianafons@gmail.com>;
 */
public class Alumno {

    private String id;
    private String nombre;
    private int edad;
    private String email;

    public Alumno(String id_, String nombre_, int edad_, String email_) {
        id = id_;
        nombre = nombre_;
        edad = edad_;
        email = email_;
    }

    public Alumno() {
        nombre = "";
        edad = 0;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

}
