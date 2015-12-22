package vista;

import controlador.Controlador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
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
 * File VistaPantalla.java
 * Code UTF-8
 * @since Mar 26, 2015, 4:41:32 PM
 * @author Joan Dídac Viana Fons <joanvianafons@gmail.com>;
 */
public class VistaPantalla extends JFrame implements Vista {

    private final JButton buttonCreate, buttonRead, buttonUpdate, buttonDelete, buttonExit;
    private final JTextField textNombre, textEdad, textEmail, textShowId, textShowNombre, textShowEdad, textShowEmail;
    private final JPanel panelMain, panelCenter, panelNorth, panelNorthEast, panelNorthWest;
    private final JLabel labelNombre, labelEdad, labelEmail, labelShowId, labelShowNombre, labelShowEdad, labelShowEmail;

    public VistaPantalla() {

        super("Alumno");

        panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout());
        //panelMain.setBackground(Color.yellow);

        panelNorth = new JPanel();
        panelNorth.setLayout(new FlowLayout());

        panelNorthWest = new JPanel();
        panelNorthWest.setLayout(new GridLayout(0, 2));

        labelShowId = new JLabel("Id");
        labelShowNombre = new JLabel("Nombre");
        labelShowEdad = new JLabel("Edad");
        labelShowEmail = new JLabel("Email");

        textShowId = new JTextField(15);
        textShowId.setEditable(false);
        textShowNombre = new JTextField(15);
        textShowNombre.setEditable(false);
        textShowEdad = new JTextField(15);
        textShowEdad.setEditable(false);
        textShowEmail = new JTextField(15);
        textShowEmail.setEditable(false);

        panelNorthWest.add(labelShowId);
        panelNorthWest.add(textShowId);
        panelNorthWest.add(labelShowNombre);
        panelNorthWest.add(textShowNombre);
        panelNorthWest.add(labelShowEdad);
        panelNorthWest.add(textShowEdad);
        panelNorthWest.add(labelShowEmail);
        panelNorthWest.add(textShowEmail);

        panelNorthEast = new JPanel();
        panelNorthEast.setLayout(new GridLayout(0, 2));

        labelNombre = new JLabel("Nombre");
        labelEdad = new JLabel("Edad");
        labelEmail = new JLabel("Email");

        textNombre = new JTextField(15);
        textEdad = new JTextField(15);
        textEmail = new JTextField(15);

        panelNorthEast.add(labelNombre);
        panelNorthEast.add(textNombre);
        panelNorthEast.add(labelEdad);
        panelNorthEast.add(textEdad);
        panelNorthEast.add(labelEmail);
        panelNorthEast.add(textEmail);

        panelNorth.add(panelNorthWest);
        panelNorth.add(panelNorthEast);

        panelCenter = new JPanel();
        //panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.X_AXIS));
        panelCenter.setLayout(new FlowLayout(1, 0, 0));

        buttonCreate = new JButton("Create");
        buttonCreate.setActionCommand(CREATE);
        buttonRead = new JButton("Read");
        buttonRead.setActionCommand(READ);
        buttonRead.setPreferredSize(buttonCreate.getPreferredSize());
        buttonUpdate = new JButton("Update");
        buttonUpdate.setActionCommand(UPDATE);
        buttonDelete = new JButton("Delete");
        buttonDelete.setActionCommand(DELETE);
        buttonExit = new JButton("Exit");
        buttonExit.setActionCommand(EXIT);
        buttonExit.setPreferredSize(buttonCreate.getPreferredSize());

        panelCenter.add(buttonCreate);
        panelCenter.add(buttonRead);
        panelCenter.add(buttonUpdate);
        panelCenter.add(buttonDelete);
        panelCenter.add(buttonExit);

        panelMain.add(panelCenter, BorderLayout.CENTER);
        panelMain.add(panelNorth, BorderLayout.NORTH);

        getContentPane().add(panelMain);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500); // Ancho x Alto

    }
// Define los eventos CRUD

    @Override
    public void setControlador(Controlador c) {
        buttonCreate.addActionListener(c);
        buttonRead.addActionListener(c);
        buttonUpdate.addActionListener(c);
        buttonDelete.addActionListener(c);
        buttonExit.addActionListener(c);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });
    }
// comienza la visualización

    @Override
    public void arranca() {
        pack();// coloca los componentes
        setLocationRelativeTo(null);// centra la ventana en la pantalla
        setVisible(true);// visualiza la ventana
    }
// Funcion que crea el alumno del formulario en el modelo

    @Override
    public String pedirId() {
        return JOptionPane.showInputDialog(panelMain, " Introducir id: ", " Introducir id ", JOptionPane.QUESTION_MESSAGE);
    }
// Actualiza el alumno

    @Override
    public void update(Alumno alumno) {
        JOptionPane.showMessageDialog(panelMain, " Alumno con id " + alumno.getId() + " actualizado", " Actualización con éxito ", JOptionPane.INFORMATION_MESSAGE);
        mostrarAlumno(alumno);
    }

    @Override
    public void delete(Alumno alumno) {
        JOptionPane.showMessageDialog(panelMain, " Alumno con id " + alumno.getId() + " eliminado", " Borrado con éxito ", JOptionPane.INFORMATION_MESSAGE);
    }

    // Se sale de la aplicación
    @Override
    public void exit() {
        int confirmacion = JOptionPane.showConfirmDialog(panelMain, "¿Seguro que quieres salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
// Muestra mensaje de error

    @Override
    public void error(String id) {
        JOptionPane.showMessageDialog(panelMain, " Error : El id " + id + " no se ha encontrado", " Error ", JOptionPane.ERROR_MESSAGE);
    }
// Muestra los datos de un alumno

    @Override
    public void mostrarAlumno(Alumno alumno) {
        textShowId.setText(alumno.getId());
        textShowNombre.setText(alumno.getNombre());
        textShowEdad.setText(Integer.toString(alumno.getEdad()));
        textShowEmail.setText(alumno.getEmail());
    }
// Pide los datos del alumno

    @Override
    public Alumno pedirAlumno() {

        boolean esvalido;
        esvalido = validarEmail(textEmail.getText());
        if (!esvalido) {
            JOptionPane.showMessageDialog(panelMain, " Error : El email debe cumplir la máscara [\\w\\.]+@\\w+\\.\\w+", " Error en el e-mail ", JOptionPane.WARNING_MESSAGE);
            arranca();
            return null;
        }
        try {
            Integer.parseInt(textEdad.getText());
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(panelMain, " Error : La edad debe ser un número", " Error en la edad ", JOptionPane.WARNING_MESSAGE);
            arranca();
            return null;
        }
        return new Alumno("", textNombre.getText(), Integer.parseInt(textEdad.getText()), textEmail.getText());
    }

    public static boolean validarEmail(String s) {
        Pattern p = Pattern.compile("[\\w\\.]+@\\w+\\.\\w+");
        Matcher m = p.matcher(s);
        return m.matches();
    }

}
