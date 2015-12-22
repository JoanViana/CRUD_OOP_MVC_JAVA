package modelo;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Project 1415ceed106prgt8e1
 * @version 1.0
 * Package modelo
 * File ModeloHashSet.java
 * Code UTF-8
 * @since Mar 26, 2015, 4:40:15 PM
 * @author Joan Dídac Viana Fons <joanvianafons@gmail.com>;
 */
public class ModeloHashSet implements Modelo {

    HashSet<Alumno> alumnos = new HashSet<>();
    int id = 0;

    @Override
    public void create(Alumno alumno) {
        alumnos.add(alumno);
        id++;
    }

    @Override
    public Alumno read(String id) {
        
        Iterator it = alumnos.iterator();
        Alumno alumno;

        while (it.hasNext()) {
            alumno = (Alumno) it.next();
            if (alumno.getId().equals(id)) {
                return alumno;
            }
        }
        return null;
    }

    @Override
    public void update(Alumno alumno) {

        Iterator it = alumnos.iterator();
        Alumno a;

        while (it.hasNext()) {
            a = (Alumno) it.next();
            if (a.getId().equals(alumno.getId())) {
                alumnos.remove(a);
                alumnos.add(alumno);               
            }
        }
    }

    @Override
    public void delete(Alumno alumno) {

        Iterator it = alumnos.iterator();
        Alumno a;

        while (it.hasNext()) {
            a = (Alumno) it.next();
            if (a.getId().equals(alumno.getId())) {
                alumnos.remove(a);
            }
        }
    }

    @Override
    public int getId() {
        return id;
    }

}

