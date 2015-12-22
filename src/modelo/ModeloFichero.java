package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * Project 1415ceed106prgt8e1
 * @version 1.0
 * Package modelo
 * File ModeloFichero.java
 * Code UTF-8
 * @since Mar 26, 2015, 4:40:26 PM
 * @author Joan Dídac Viana Fons <joanvianafons@gmail.com>;
 */
public class ModeloFichero implements Modelo {

    private final File fs;
    private int id = 0;
    private static final String nombreFile = "./1415ceed106prgt6e1.csv";
    private static final String tempFile = "./temp.csv";

    public ModeloFichero() throws IOException {
        fs = new File(nombreFile);
        id = calculaid();
    }

    @Override
    public void create(Alumno alumno) {

        FileWriter fw;
        try {
            fw = new FileWriter(fs, true);
            grabarAlumno(alumno, fw);
            fw.close();
        } catch (IOException ex) {
        }

        id++;
    }

    @Override
    public Alumno read(String id) {
        StringTokenizer st;
        Alumno alumno;
        FileReader fr;
        try {
            fr = new FileReader(fs);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                st = new StringTokenizer(linea, ";");

                String id_ = st.nextToken();
                String nombre_ = st.nextToken();
                int edad_ = Integer.parseInt(st.nextToken());
                String email_ = st.nextToken();

                if (id_.equals(id)) {
                    alumno = new Alumno(id_, nombre_, edad_, email_);
                    return alumno;
                }
            }
            fr.close();
        } catch (IOException e) {
        }
        return null;
    }

    @Override
    public void update(Alumno alumno) {
        
        try {
            File temp = new File(tempFile);
            FileWriter fw = new FileWriter(temp);
            FileReader fr = new FileReader(fs);

            BufferedReader br = new BufferedReader(fr);
            Alumno a;
            String linea;

            linea = br.readLine();
            while (linea != null) {
                a = extraeAlumno(linea);
                if (a.getId().equals(alumno.getId())) {
                    grabarAlumno(alumno, fw);
                } else {
                    grabarAlumno(a, fw);
                }
                linea = br.readLine();
            }
            
            fr.close();
            fw.close();
            
            fs.delete();
            temp.renameTo(fs);
 
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }

    @Override
    public void delete(Alumno alumno) {

        try {

            File temp = new File(tempFile);

            FileWriter fw = new FileWriter(temp);
            FileReader fr = new FileReader(fs);

            BufferedReader br = new BufferedReader(fr);
            Alumno a;
            String linea;

            linea = br.readLine();
            while (linea != null) {
                a = extraeAlumno(linea);
                if (!a.getId().equals(alumno.getId())) {
                    grabarAlumno(a, fw);
                }
                linea = br.readLine();
            }
            
            fr.close();
            fw.close();
            
            fs.delete();
            temp.renameTo(fs);
            
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }

    @Override
    public int getId() {
        return id;
    }

    private int calculaid() {
        int idmax = 0;

        if (fs.exists()) {

            try {

                FileReader fr = new FileReader(fs);
                BufferedReader br = new BufferedReader(fr);
                Alumno alumno;
                String linea;
                int id_;

                linea = br.readLine();
                while (linea != null) {
                    alumno = extraeAlumno(linea);
                    id_ = Integer.parseInt(alumno.getId());
                    if (id_ > idmax) {
                        idmax = id_;
                    }
                    linea = br.readLine();
                }

                fr.close();

            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
            }

        }
        return idmax;

    }

    private Alumno extraeAlumno(String linea) {

        Alumno alumno;
        StringTokenizer str = new StringTokenizer(linea, ";");

        String id_ = str.nextToken();
        String nombre = str.nextToken();
        String edad_ = str.nextToken();
        int edad = Integer.parseInt(edad_);
        String email = str.nextToken();

        alumno = new Alumno(id_, nombre, edad, email);
        return alumno;
    }

    private void grabarAlumno(Alumno alumno, FileWriter fw) throws IOException {
        fw.write(alumno.getId());
        fw.write(";");
        fw.write(alumno.getNombre());
        fw.write(";");
        fw.write(alumno.getEdad() + "");
        fw.write(";");
        fw.write(alumno.getEmail());
        fw.write("\r\n");
    }
    
    /*
    public HashSet readHashSet() {
        HashSet alumnos = new HashSet();
        try {
            FileReader fr = new FileReader(fs);
            BufferedReader br = new BufferedReader(fr);
            Alumno alumno;
            String linea;

            linea = br.readLine();
            while (linea != null) {
                alumno = extraeAlumno(linea);
                alumnos.add(alumno);
                linea = br.readLine();
            }
            fr.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        return alumnos;
    }
    */

}
