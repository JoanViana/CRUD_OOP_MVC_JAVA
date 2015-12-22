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
 * File ModeloVector.java
 * Code UTF-8
 * @since Mar 26, 2015, 4:40:50 PM
 * @author Joan Dídac Viana Fons <joanvianafons@gmail.com>;
 */
public class ModeloVector implements Modelo {

    Alumno alumnos[] = new Alumno[100];
    Alumno vacio = new Alumno("", "", 0, "");
    int id = 0;

    public ModeloVector() {
        for (int i = 0; i < alumnos.length; i++) {
            alumnos[i] = vacio;
        }
    }

    @Override
    public void create(Alumno alumno) {        
        alumnos[id] = alumno;
        id++;
    }

    @Override
    public Alumno read(String id_) {

        for (int i = 0; i < id; i++) {
            if (alumnos[i].getId().equals(id_)) {
                return alumnos[i];
            }
        }
        return null;
    }

    @Override
    public void update(Alumno alumno) {

        int i = 0;
        
        while (i < alumnos.length) {
            if (alumnos[i].getId().equals(alumno.getId())) {
                alumnos[i] = alumno;                
            }
            i++;
        }
    }

    @Override
    public void delete(Alumno alumno) {
        int i = 0;
        while (i < alumnos.length) {
            if (alumnos[i].getId().equals(alumno.getId())) {
                alumnos[i] = vacio;
            }
            i++;
        }
    }

    @Override
    public int getId() {
        return id;
    }

}


