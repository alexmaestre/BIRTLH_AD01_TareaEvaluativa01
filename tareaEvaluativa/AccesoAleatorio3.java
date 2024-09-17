package tareaEvaluativa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class AccesoAleatorio3 {

	private static final String RUTA = "src" + File.separator + "tareaEvaluativa" + File.separator + "Marvel.dat"; //Definimos constante con ruta del fichero de datos de personajes
	/*Para la serialización usaremos una estructura de 32 caracteres para las cadenas, resultando un total de 268 caracteres de serialización por personaje si tenemos en cuenta que son 3
	enteros y 4 cadenas por entidad. Definimos dos constantes para manejar tanto la longitud total como la longitud de las cadenas y varias constantes para las posiciones de los datos. */
	private static final int LONGITUD_TOTAL = 268; 
	private static final int LONGITUD_CADENAS = 32;
	private static final int POSICION_DNI = 4;
	private static final int POSICION_NOMBRE = POSICION_DNI + (LONGITUD_CADENAS*2);
	private static final int POSICION_IDENTIDAD = POSICION_NOMBRE + (LONGITUD_CADENAS*2);
	private static final int POSICION_TIPO = POSICION_IDENTIDAD + (LONGITUD_CADENAS*2);
	private static final int POSICION_PESO = POSICION_TIPO + (LONGITUD_CADENAS*2);
	private static final int POSICION_ALTURA = POSICION_PESO + 4;
	
	
	public static void main(String[] args) throws IOException {   	
		
		//Pedimos el tipo de personaje
		Scanner scanner = new Scanner(System.in);
		System.out.print("Introduce un tipo de personaje: ");
		String tipoIntroducido = scanner.nextLine();
		scanner.close();	
		
		//Instanciamos un objeto RandomAccesFile basado en una instancia de un File a partir de la ruta, empleando un bloque try para manejar excepciones		
		try (RandomAccessFile raf = new RandomAccessFile(new File(RUTA), "r")){
			// Primeramente tenemos que cotejar si el tipo existe. Como el archivo es dinámico y pueden agregarse tipos en el futuro, nos valemos de una función que coteje si existe el tipo
			if(tipoIntroducido.isEmpty()) {
	            System.out.println("El tipo no puede estar vacío");
	        }else {
	            /* Para recorrer los datos usamos un bucle for con una variable iteradora que se repita tantas veces como personajes tenemos almacenados
	            Para saber cuantos personajes tenemos almacenados creamos una variable entero llamada totalPersonjas que es el resultado de castear a entero la división
	            entre el tamaño total del RandomAccessFile entre la longitud de los datos de cada personaje */
	        	int totalPersonajes = (int) raf.length()/LONGITUD_TOTAL;
	        	int contador = 0; //Creamos una variable entera para contabilizar cuántos personajes tienen el tipo introducido
	        	String datosPersonajes = ""; //Creamos una variable cadena para ir guardando los mensajes con la información de los personajes
	            for(var i=1; i<=totalPersonajes; i++) {
					String tipo = leerCadena(raf , (i-1)*LONGITUD_TOTAL + POSICION_TIPO); //Leemos el tipo del personaje iterado, usando la función leerCadena y usando la posición del dato tipo
					if(tipo.equals(tipoIntroducido)) {
						//Si el tipo coincide, leemos los valores de los distintos atributos valiéndonos de leerCadena y leerEntero
						String dni = leerCadena(raf, (i-1)*LONGITUD_TOTAL + POSICION_DNI);
						String nombre = leerCadena(raf, (i-1)*LONGITUD_TOTAL + POSICION_NOMBRE);
						String identidad = leerCadena(raf, (i-1)*LONGITUD_TOTAL + POSICION_IDENTIDAD);
						int peso = leerEntero(raf, (i-1)*LONGITUD_TOTAL+ POSICION_PESO);
						int altura = leerEntero(raf, (i-1)*LONGITUD_TOTAL + POSICION_ALTURA);
						//Añadimos a la cadena datosPersonaje, la subcadena que refleja los datos del personaje iterado;
						datosPersonajes += "Personaje [dni="+dni+", nombre="+nombre+", identidad="+identidad+", tipo="+tipo+", peso="+peso+", altura="+altura+"]\n";
						contador++;
					}
	            }
				if(contador > 0) {  //Si el contador de personajes encontrados es mayor que 0, mostramos el mensaje con los datos de los personajes
					System.out.println("Se han encontrado " + contador + " " + tipoIntroducido + "(s)");
					System.out.println(datosPersonajes);
				}else { //Si el contador de personajes encontrados es igual a 0, avisamos de que no existe el tipo de personaje
					System.out.println("No existen " + tipoIntroducido + " en el fichero");
				}
				raf.close();
	        }   						
		} catch (FileNotFoundException e) {
  	      System.out.println("El archivo de datos no existe");
  	    } catch (IOException e) {
  	      System.out.println("Error de E/S: " + e.getMessage());
  	    }  
		
	}	

	//La función privada leerEntero recibe un RandomAccessFile y una posición y extrae el entero que hay en esa posición
	private static int leerEntero(RandomAccessFile raf, int posicion)  throws IOException {
		raf.seek(posicion);
		return raf.readInt();
	}
	
	//La función privada leerCadena recibe un RandomAccessFile y una posición y extrae la cadena que hay en esa posición, teniendo en cuenta la longitud escogida para las cadenas en la estructura de datos
    private static String leerCadena(RandomAccessFile raf, int posicion)  throws IOException {
    	raf.seek(posicion);
    	char [] data = new char[LONGITUD_CADENAS]; //Instanciamos un arreglo de caracteres con la dimension de cadena establecida
		for (int i=0;i<LONGITUD_CADENAS;i++) { //Iteramos hasta el número máximo de caracteres guardando en el arreglo los datos del RandomAccessFile
			data[i]=raf.readChar();
		}
		return new String(data).trim(); //Instanciamos una cadena a partir del arreglo de caracteres y eliminamos los espacios en blanco al principio y al final
    }			
	
}
