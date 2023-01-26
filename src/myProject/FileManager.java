package myProject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class allows to manipulate .txt files to be read or to write to them
 * @author Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 *         Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version v.
 */
public class FileManager
{
    private FileReader fileReader;
    private BufferedReader input;  // it allows to read the text of an input stream
    private FileWriter fileWriter;
    private BufferedWriter output; // it allows to writes text to a character-output stream
    public static final String bancoDePalabras = "src/myProject/files/bancoDePalabras.txt";   //constant location
    public static final String usuariosListados = "src/myProject/files/usuariosListados.txt"; //constant location
    public String lecturaFile() {
        String texto="";

        try {
            fileReader = new FileReader("src/myProyect/files/diccionario.txt");
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while(line!=null){
                texto+=line;
                texto+="\n";
                line=input.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return texto;
    }

    public void escribirTexto(String linea){
        try {
            fileWriter = new FileWriter("src/myProject/files/fileText.txt",true);
            output = new BufferedWriter(fileWriter);
            output.write(linea);
            output.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}