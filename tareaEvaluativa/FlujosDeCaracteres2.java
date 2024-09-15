package tareaEvaluativa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FlujosDeCaracteres2 {
	
	private static final String RUTA_ORIGEN = "src" + File.separator + "tareaEvaluativa" + File.separator + "Nombres.txt"; //Definimos constante con ruta del fichero de origen
	private static final String RUTA_DESTINO = "src" + File.separator + "tareaEvaluativa" + File.separator + "NombresSolo5.txt"; //Definimos constante con ruta del fichero de destino
	
	public static void main(String[] args) throws IOException{   

		/* Instanciamos los objetos BufferedReader y BufferedWriter valiéndonos de dos instancias FileReader y FileWriter invocadas empleando las rutas de origen y destino
		 * Trabajamos con un bloque try para manejar las posibles excepciones surgidas
		 */
		try (
				BufferedReader br = new BufferedReader(new FileReader(RUTA_ORIGEN));
				BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_DESTINO));
			){
			File archivoDestino = new File(RUTA_DESTINO); //Instanciamos un elemento File para poder borrar el archivo destino si existe
			if(archivoDestino.exists()) {archivoDestino.delete(); } //Borramos el archivo destino si exsite
			String linea = br.readLine(); //Instanciamos un dato tipo String que irá almacenando el contenido de las líneas
		    while (linea != null) { //Iteramos reasignando en bucle cada línea del buffer a la variable linea hasta que esta sea nula porque haya terminado el archivo
		        int position = linea.indexOf(" "); //Hallamos la posición del espacio dentro de la línea
		        if(position==5) { 
		        	System.out.println(linea);  //Mostramos la línea que contenga el nombre que tiene exactamente 5 letras
		        	bw.write(linea + "\n");
		        }
		        linea = br.readLine();
		    }
		    
	    } catch (FileNotFoundException e) {
	      System.out.println("El archivo de nombres no existe");
	    } catch (IOException e) {
	      System.out.println("Error de E/S: " + e.getMessage());
	    }		
		
	}	

}
