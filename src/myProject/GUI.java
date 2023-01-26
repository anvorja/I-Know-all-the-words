package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * This class is designed in order to view Model class
 *
 * @author Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 *         Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version v
 */
public class GUI extends JFrame {

    private Header headerProject;
    private Model model;
    private Escucha escucha;

    // Information on the help button, in the home panel: when the user is asked
    private static final String INFO1 = "Con el nombre de usuario podremos guardar tus avances\n Ingresa sólo " +
            "caracteres en minúsculas, evita el uso de la Ñ y/o espacios en blanco";
    // Information on the help button, in the game panel.
    private static final String INFO2 = " Puedes salir en cualquier momento.\n"
            + "Sin embargo, si la partida no ha terminado la próxima vez que ingreses se iniciará la misma,\n " +
            "excepto si tu ultimo nivel aprobado es mayor o igual al  8, en este caso siempre iniciaras en \n el " +
            "mismo nivel ";

    private JPanel panelInicio, panelGame, panelBotones, panelPalabras, panelOpciones;
    private JTextField entradaUsuario;
    private JTextArea intro;
    private JButton botonOK, botonHelp, botonExit, botonIniciar, botonInstrucciones, botonSI, botonNO, botonContinuar, botonRepetirSI, botonRepetirNO;
    private JLabel labelUsername, labelInstrucciones, labelNivel, labelTiempo, labelPalabra;
    private ImageIcon image;
    private boolean opcionHelp;
    private String nombreJugador;
    private int fase, counter;
    private Timer timer;
    private GridBagConstraints constraints, layoutPanelGame; // JFrame and panelGame layout component

    /**
     * Constructor of GUI class
     */
    public GUI()
    {
        this.setContentPane(new Canvas(1)); // to Paint the background image of the Frame
        initGUI();
        // Default JFrame configuration
        this.setUndecorated(true);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {


        // Set up JFrame Container's Layout
        this.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();

        // Create Listener Object or Control Object
        escucha = new Escucha();
        model = new Model();
        // Set up JComponents
        opcionHelp = false; // It is used to know what message in the help button should be shown

        // Frame header
        headerProject = new Header("I KNOW THAT WORD", new Color(135, 7, 122));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(headerProject, constraints);

        // Button Creation
        botonHelp = new JButton();
        botonHelp.addActionListener(escucha);
        botonHelp.setPreferredSize(new Dimension(80, 50));
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/recursos/help1.png")));
        botonHelp.setIcon(new ImageIcon(image.getImage().getScaledInstance(80, 50, Image.SCALE_SMOOTH)));
        botonHelp.setBorderPainted(false);
        botonHelp.setContentAreaFilled(false);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;
        this.add(botonHelp, constraints);

        botonExit = new JButton();
        botonExit.addActionListener(escucha);
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/recursos/close1.png")));
        botonExit.setPreferredSize(new Dimension(80, 50));
        botonExit.setIcon(new ImageIcon(image.getImage().getScaledInstance(80, 50, Image.SCALE_SMOOTH)));
        botonExit.setBorderPainted(false);
        botonExit.setContentAreaFilled(false);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_END;
        this.add(botonExit, constraints);

        // This is the panel that contains the user label, the text input and the
        // confirmation button
        panelInicio = new JPanel(new GridBagLayout()); // Set up JPanel Container's Layout
        panelInicio.setPreferredSize(new Dimension(900, 500));
        panelInicio.setOpaque(false);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(panelInicio, constraints);
        componentesDelPanelInicio();
        revalidate();
        repaint();
    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha {

    }
}