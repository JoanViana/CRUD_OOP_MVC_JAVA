package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Project 1415ceed106prgt8e1
 *
 * @version 1.0 Package modelo File ModeloMysql.java Code UTF-8
 * @since Mar 26, 2015, 4:41:02 PM
 * @author Joan Dídac Viana Fons <joanvianafons@gmail.com>;
 */
public class ModeloMysql implements Modelo {

    final String driver = "com.mysql.jdbc.Driver";
    final String jdbcUrl = "jdbc:mysql://localhost:3306/ceedprgt8";

    @Override
    public void create(Alumno alumno) {

        try {
            Class.forName(driver).newInstance();
            Connection con = DriverManager.getConnection(jdbcUrl, "alumno", "alumno");
            Statement st = con.createStatement();
            if (getId() != 999) {
                String sql = "insert into alumnos (id,nombre,edad,email) values (" + alumno.getId() + ",'" + alumno.getNombre() + "'," + alumno.getEdad() + ",'" + alumno.getEmail() + "');";
                st.executeUpdate(sql);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public Alumno read(String id_) {

        try {
            Class.forName(driver).newInstance();
            Connection con = DriverManager.getConnection(jdbcUrl, "alumno", "alumno");
            Statement st = con.createStatement();
            String sql = "select id,nombre,edad,email from alumnos where id=" + id_ + ";";
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                Alumno al = new Alumno(rs.getString("id"), rs.getString("nombre"), rs.getInt("edad"), rs.getString("email"));
                con.close();
                return al;
            } else {
                con.close();
                return null;
            }
        } catch (Exception e) {        
            return null;
        }
        
    }

    @Override
    public void update(Alumno alumno) {
        try {
            Class.forName(driver).newInstance();
            Connection con = DriverManager.getConnection(jdbcUrl, "alumno", "alumno");
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "select id,nombre,edad,email from alumnos where id=" + alumno.getId() + ";";
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {

                rs.updateString("id", "" + alumno.getId());
                rs.updateString("nombre", "" + alumno.getNombre());
                rs.updateInt("edad", alumno.getEdad());
                rs.updateString("email", "" + alumno.getEmail());
                rs.updateRow();
                con.close();
            } 
            
        } catch (Exception e) {

        }
    }

    @Override
    public void delete(Alumno alumno) {
        try {
            Class.forName(driver).newInstance();
            Connection con = DriverManager.getConnection(jdbcUrl, "alumno", "alumno");
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "select id,nombre,edad,email from alumnos where id=" + alumno.getId() + ";";
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                rs.deleteRow();
                con.close();
            } 
        } catch (Exception e) {

        }
    }

    @Override
    public int getId() {
        try {
            Class.forName(driver).newInstance();
            Connection con = DriverManager.getConnection(jdbcUrl, "alumno", "alumno");
            Statement st = con.createStatement();
            String sql = "select * from alumnos";
            ResultSet rs = st.executeQuery(sql);
            rs.last();
            int id= Integer.parseInt(rs.getString("id"));
            con.close();
            return id;
        } catch (Exception e) {
            return 999;
        }
    }

}
