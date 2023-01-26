package myProject;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is designed in order to applies the game rules
 * @author Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 *         Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version v.
 */
public class Model
{

    private Diccionario diccionario;
    private boolean flagNivel;
    private User miUsuario;
    String nombreUsuario;
    int nivelesAprobados, errores, nivelActual, cantPalabrasDelNivel, aciertos, flagPalabrasCorrectas,flagPalabrasAleatorias;
    double porcentajeAciertos;
    private ArrayList<String> arraListPalabrasCorrectas;
    private ArrayList<String> arraListPalabrasIncorrectas;
    private ArrayList<String> arrayDePalabrasAleatorias;
    private ArrayList <String> palabrasAMostrar;


    /**
     * Constructor of class
     */
    public  Model()
    {
        arraListPalabrasCorrectas = new ArrayList<>();
        arraListPalabrasIncorrectas = new ArrayList<>();
        arrayDePalabrasAleatorias = new ArrayList<>();
        palabrasAMostrar = new ArrayList<>();
    }

    /**
     * This method restricts data entry in the JTexField to only characters without special signs.
     * @param entrada
     * @return valido
     */
    public boolean validarEntradaTexto(String entrada)
    {
        boolean valido = true;
        int ascii = 0;
        for (char aux : entrada.toCharArray())
        {
            ascii = (int) aux;
            if (ascii < 97 || ascii > 122) {
                valido = false;
                break;
            }
        }
        return valido;
    }

    /**
     * This method has the purpose of searching if a user exists and according to that it establishes the game level
     * @param nombreJugador
     */
    public void buscarElUsuario(String nombreJugador)
    {
        diccionario = new Diccionario();
        nombreUsuario = nombreJugador;
        miUsuario = new User(nombreUsuario);
        if (miUsuario.determinarExistenciaJugador())
        {
            nivelesAprobados = miUsuario.getNivelDelJugador();
        }
        else
        {
            miUsuario.registrarJugador();
            nivelesAprobados = 0;
        }

        flagPalabrasCorrectas = 0;
        flagNivel = false;
        if (nivelesAprobados<10)
        {
            nivelActual = nivelesAprobados + 1;
        }else
        {
            nivelActual = nivelesAprobados;
        }
        setNivelActual();

    }

    /**
     * This method sets all the words that will be in the game per level (corrects and incorrect)
     */
    private void setNivelActual()
    {
        aciertos = 0;
        errores = 0;
        if(flagNivel){
            nivelActual++;
            flagNivel=false;
        }
        asignarCantidadPalabrasPorNivel();
        asignarPorcentajesPorNivel();
        arraListPalabrasCorrectas = diccionario.generarPalabrasCorrectas(cantPalabrasDelNivel/2);
        arraListPalabrasIncorrectas = diccionario.generarPalabrasIncorrectas(cantPalabrasDelNivel/2);
        generarArrayDePalabrasAleatoriaDelNivel();
    }

    /**
     * This method has the purpose of assigning the number of words that
     * should be displayed on the screen according to the current level of the player
     */
    private void asignarCantidadPalabrasPorNivel()
    {
        switch (nivelActual){
            case 1-> cantPalabrasDelNivel =20;
            case 2-> cantPalabrasDelNivel =40;
            case 3-> cantPalabrasDelNivel =50;
            case 4-> cantPalabrasDelNivel =60;
            case 5-> cantPalabrasDelNivel =70;
            case 6-> cantPalabrasDelNivel =80;
            case 7-> cantPalabrasDelNivel =100;
            case 8-> cantPalabrasDelNivel =120;
            case 9-> cantPalabrasDelNivel =140;
            case 10-> cantPalabrasDelNivel =200;

        }

        //in case of doing fast tests by console
        //cantPalabrasDelNivel =2;

    }

    /**
     * Method to create an arrayList of random words containing
     * the correct and incorrect words to display on the screen
     */
    private void generarArrayDePalabrasAleatoriaDelNivel()
    {
        palabrasAMostrar.addAll(arraListPalabrasCorrectas);
        palabrasAMostrar.addAll(arraListPalabrasIncorrectas);
        ArrayList<String> auxPalabrasAMostrar = palabrasAMostrar;

        while (auxPalabrasAMostrar.size()>0)
        {
            Random random = new Random();
            String palabra = auxPalabrasAMostrar.get(random.nextInt(auxPalabrasAMostrar.size()));
            arrayDePalabrasAleatorias.add(palabra);
            auxPalabrasAMostrar.remove(palabra);
        }
    }

    /**
     * This method is responsible for assigning the necessary percentage to pass the current level
     */
    private void asignarPorcentajesPorNivel()
    {
        switch (nivelActual){
            case 1, 2 -> porcentajeAciertos = 0.7;
            case 3-> porcentajeAciertos = 0.75;
            case 4, 5 -> porcentajeAciertos = 0.8;
            case 6-> porcentajeAciertos = 0.85;
            case 7, 8 -> porcentajeAciertos = 0.9;
            case 9-> porcentajeAciertos = 0.95;
            case 10->porcentajeAciertos = 1;
        }
    }
}
