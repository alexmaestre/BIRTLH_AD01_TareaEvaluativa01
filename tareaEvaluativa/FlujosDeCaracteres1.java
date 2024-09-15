package tareaEvaluativa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FlujosDeCaracteres1 {
	
	private static final String RUTA = "src" + File.separator + "tareaEvaluativa" + File.separator + "TextoAInvertir.txt"; //Definimos constante con ruta del fichero
	
	public static void main(String[] args) throws IOException{   

		//Instanciamos un objeto fileReader dentro de un bloque try para manejar posibles excepciones
		try (FileReader fr = new FileReader(RUTA))  {
	
			//Definimos una variable auxiliar en la que vamos volcando los valores de fichero en orden correcto
			String aux = "";
			
			//Leemos caracter por caracter y lo vamos volcando en la variable auxiliarw
	        int caracter = fr.read();
	        while (caracter != -1) { //Iteramos con un while que continuará hasta que read() sea -1
	        	char c = (char) caracter; //convertimos el entero a char
	        	aux = Character.toString(c) + aux; //Añadimos la cadena del chart al inicio de la cadena auxiliar
	        	caracter = fr.read(); //Pasamos a leer el siguiente caracter
	        }
	       
			System.out.println(aux); //Mostramos el contenido de la cadena auxiliar que contiene el texto invertido
			
	    } catch (FileNotFoundException e) {
	      System.out.println("El archivo de texto no existe");
	    } catch (IOException e) {
	      System.out.println("Error de E/S: " + e.getMessage());
	    }	
	}
	
}
