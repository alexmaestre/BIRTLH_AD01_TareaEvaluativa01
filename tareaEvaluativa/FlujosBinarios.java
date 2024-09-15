package tareaEvaluativa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FlujosBinarios {
	
	private static final String RUTA_PDF = "src" + File.separator+ "tareaEvaluativa" + File.separator+ "Ejemplo.pdf"; //Definimos constante con ruta del fichero que sí es PDF
	private static final String RUTA_NO_PDF = "src" + File.separator+ "tareaEvaluativa" + File.separator+ "Ejemplo.jpg"; //Definimos constante con ruta del fichero que no es PDF	
	
	public static void main(String[] args) throws IOException {   
		
		//Comprobamos si los archivos son PDF mediante la función esPDF
		esPDF(RUTA_PDF);
		esPDF(RUTA_NO_PDF);

    }	
	
	/* 
	 * Creamos una función privada que recibiendo una cadena con la ruta de un archivo, cree un FileInputStream y compruebe su cabecera 
	 * mostrando en pantalla un  aviso sobre si el archivo es o no PDF
	 */
	private static void esPDF(String ruta) {
        //InsTanciamos un FileInputStream mediante una instacia File que referencia la ruta dada empleando un bloque try para manejar excepciones
        try (FileInputStream fis = new FileInputStream(new File(ruta))) {
            byte[] encabezado = new byte[4]; //Creamos un arreglo de 4 bytes para almacenar los primeros 4 bytes de cabecera del archivo
            fis.read(encabezado); //Volcamos en el arreglo esos 4 primeros bytes
            //Mediante una sentencia if, cotejamos si esos 4 primeros bytes de encabezado coinciden con los valores del anunciado y mostramos el mensaje correspondiente
            if( 
            	encabezado[0] == 37 &&
                encabezado[1] == 80 &&
                encabezado[2] == 68 &&
                encabezado[3] == 70
            ){ 
            	System.out.println("El archivo " + ruta + " sí es PDF.");
            } else {
                System.out.println("El archivo " + ruta + " no es PDF.");
            }
        } catch (FileNotFoundException e) {
  	      System.out.println("El archivo a comprobar no existe");
  	    } catch (IOException e) {
  	      System.out.println("Error de E/S: " + e.getMessage());
  	    }
	}
	
}
