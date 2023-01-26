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
     * This method creates and adds to the home panel the components:
     * 1) JLabel labelUsername: label to indicate what you want the user to enter in
     * the text box
     * 2) JTextField entradaUsuario: component for text input
     * 3) JButton botonOk: confirmation button after entering username
     */
    public void componentesDelPanelInicio()
    {
        // Layout component
        GridBagConstraints layoutPanelInicio = new GridBagConstraints();
        // Label for user
        labelUsername = new JLabel();
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/recursos/username.png")));
        labelUsername.setIcon(new ImageIcon(image.getImage().getScaledInstance(200, 50, Image.SCALE_SMOOTH)));
        layoutPanelInicio.gridx = 0;
        layoutPanelInicio.gridy = 0;
        layoutPanelInicio.gridwidth = 2;
        layoutPanelInicio.fill = GridBagConstraints.NONE;
        layoutPanelInicio.anchor = GridBagConstraints.CENTER;
        panelInicio.add(labelUsername, layoutPanelInicio);

        // TextField input box
        entradaUsuario = new JTextField();
        entradaUsuario.setPreferredSize(new Dimension(250, 40));
        entradaUsuario.setFont(new Font("Arial ", Font.PLAIN, 30));
        layoutPanelInicio.gridx = 0;
        layoutPanelInicio.gridy = 1;
        layoutPanelInicio.gridwidth = 1;
        layoutPanelInicio.fill = GridBagConstraints.NONE;
        layoutPanelInicio.anchor = GridBagConstraints.LINE_END;
        panelInicio.add(entradaUsuario, layoutPanelInicio);

        // Confirmation button
        botonOK = new JButton();
        botonOK.addActionListener(escucha);
        botonOK.setPreferredSize(new Dimension(57, 57));
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/recursos/okB.png")));
        botonOK.setIcon(new ImageIcon(image.getImage().getScaledInstance(57, 57, Image.SCALE_SMOOTH)));
        botonOK.setBorderPainted(false);
        botonOK.setContentAreaFilled(false);
        layoutPanelInicio.gridx = 1;
        layoutPanelInicio.gridy = 1;
        layoutPanelInicio.gridwidth = 1;
        layoutPanelInicio.fill = GridBagConstraints.NONE;
        layoutPanelInicio.anchor = GridBagConstraints.LINE_START;
        panelInicio.add(botonOK, layoutPanelInicio);
        revalidate();
        repaint();
    }

    /**
     * This method returns an object of type Icon. It is used for the help button
     * icon
     *
     * @param reference String, path
     * @param width     int
     * @param height    int
     * @return Icon
     */
    public Icon iconoMessage(String reference, int width, int height) {
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource(reference)));
        image = new ImageIcon(image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        return image;
    }

    /**
     * After entering the user, create the following components:
     * 1) JPanel panelGame: the game takes place in it
     * 2) JTextArea intro: start message
     * 3) Invokes the method to add the instructions and play buttons
     */
    public void crearPanelGame()
    {
        panelGame = new Canvas(2); // Create the panel with the image
        panelGame.setLayout(new GridBagLayout()); // Set up JPanel Container's Layout
        layoutPanelGame = new GridBagConstraints(); // panelGame layout component
        panelGame.setPreferredSize(new Dimension(700, 400));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(panelGame, constraints);

        intro = new JTextArea("   ¡HOLA " + nombreJugador.toUpperCase() + "!\n" +
                "   Estas en el nivel " + model.getNivelActual() + "\n   Presiona PLAY para iniciar");
        intro.setEditable(false);
        intro.setLineWrap(true);
        intro.setWrapStyleWord(true);
        intro.setBackground(new Color(186, 186, 252, 130));
        intro.setOpaque(true);
        intro.setPreferredSize(new Dimension(400, 150));
        intro.setFont(new Font("Impact", Font.PLAIN, 28));
        layoutPanelGame.gridx = 0;
        layoutPanelGame.gridy = 0;
        layoutPanelGame.gridwidth = 1;
        layoutPanelGame.fill = GridBagConstraints.NONE;
        layoutPanelGame.anchor = GridBagConstraints.CENTER;
        panelGame.add(intro, layoutPanelGame);
        crearPanelBotonesInicio();
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