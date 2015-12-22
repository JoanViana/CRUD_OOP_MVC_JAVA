package modelo;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.io.File;
import java.io.IOException;

/**
 * Project 1415ceed106prgt9e1
 *
 * @version 1.0 Package modelo File ModeloDb4o.java Code UTF-8
 * @since Apr 20, 2015, 6:57:32 PM
 * @author Joan Dídac Viana Fons <joanvianafons@gmail.com>;
 */
public class ModeloDb4o implements Modelo {

    private final File fBd4o;
    private static final String nombreFile = "./alumnos.db4o";
    private int id;

    public ModeloDb4o() throws IOException {
        this.id = 0;
        fBd4o = new File(nombreFile);
        id = calculaId();
    }

    @Override
    public void create(Alumno alumno) {
        ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), nombreFile);
        bd.store(alumno);
        bd.close();
        id++;
    }

    @Override
    public Alumno read(String id) {
        ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), nombreFile);
        Alumno al0 = new Alumno(id, null, 0, null);
        ObjectSet res = bd.queryByExample(al0);
        if (res.hasNext()) {
            Alumno al = (Alumno) res.next();
            bd.close();
            return al;
        } else {
            bd.close();
            return null;
        }

    }

    @Override
    public void update(Alumno alumno) {
        ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), nombreFile);
        Alumno al0 = new Alumno(alumno.getId(), null, 0, null);
        ObjectSet res = bd.queryByExample(al0);
        Alumno al = (Alumno) res.next();
        al.setNombre(alumno.getNombre());
        al.setEdad(alumno.getEdad());
        al.setEmail(alumno.getEmail());
        bd.store(al);
        bd.close();
    }

    @Override
    public void delete(Alumno alumno) {
        ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), nombreFile);
        Alumno al0 = new Alumno(alumno.getId(), null, 0, null);
        ObjectSet res = bd.queryByExample(al0);
        Alumno al = (Alumno) res.next();
        bd.delete(al);
        bd.close();
    }

    @Override
    public int getId() {
        return id;
    }

    private int calculaId() {
        ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), nombreFile);
        Alumno al = new Alumno(null, null, 0, null);
        ObjectSet res = bd.queryByExample(al);
        int aux = 0;
        while (res.hasNext()) {
            al = (Alumno) res.next();
            if (Integer.parseInt(al.getId()) > aux) {
                aux = Integer.parseInt(al.getId());
            }
        }
        bd.close();
        return aux;
    }

}
