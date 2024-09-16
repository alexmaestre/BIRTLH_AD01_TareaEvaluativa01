package tareaEvaluativa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class AccesoAleatorio2 {

	private static final String RUTA = "src" + File.separator + "tareaEvaluativa" + File.separator + "Marvel.dat"; //Definimos constante con ruta del fichero de datos de personajes
	/*Para la serialización usaremos una estructura de 32 caracteres para las cadenas, resultando un total de 268 caracteres de serialización por personaje si tenemos en cuenta que son 3
	enteros y 4 cadenas por entidad. Definimos dos constantes para manejar tanto la longitud total como la longitud de las cadenas y varias constantes para las posiciones de los datos. */
	private static final int LONGITUD_TOTAL = 268; 
	private static final int LONGITUD_CADENAS = 32;
	private static final int POSICION_DNI = 4;
	private static final int POSICION_PESO = 4 + 4*2*LONGITUD_CADENAS;
	private static final int POSICION_NOMBRE = 4 + 2*LONGITUD_CADENAS;
	
	public static void main(String[] args) throws IOException {   
		
		//Instanciamos un objeto RandomAccesFile basado en una instancia de un File a partir de la ruta, empleando un bloque try para manejar excepciones		
		try (RandomAccessFile raf = new RandomAccessFile(new File(RUTA), "r")){
		
			//Pedimos el dni y el peso actual del personaje
			Scanner scanner = new Scanner(System.in);
			System.out.print("Introduzca el DNI (con letra) del personaje para el control de peso: ");
			String dniIntroducido = scanner.nextLine();
			System.out.print("Introduzca su peso actual: ");
			String pesoIntroducido = scanner.nextLine();	
			scanner.close();		
			
			// Si el dni o el peso están vacíos mostramos mensaje
			if(dniIntroducido.isEmpty() || pesoIntroducido.isEmpty()) {
	            System.out.println("El DNI y el peso no puede estar  vacío");
	        } else {
	        	// Usamos try & catch para manejar error en caso de que el peso no sea un entero
	            try {
	            	
	                int peso = Integer.parseInt(pesoIntroducido); //Parseamos a entero el peso introducido
	                boolean existe = false; //Creamos una variable booleana auxiliar para comprobar si se ha encontrado el personaje con el DNI dado
	                /* Para recorrer los datos usamos un bucle for con una variable iteradora que se repita tantas veces como personajes tenemos almacenados
	                Para saber cuantos personajes tenemos almacenados creamos una variable entero llamada totalPersonjas que es el resultado de castear a entero la división
	                entre el tamaño total del RandomAccessFile entre la longitud de los datos de cada personaje */
	                int totalPersonajes = (int) raf.length()/LONGITUD_TOTAL;
	                for(var i=1; i<=totalPersonajes; i++) {
	                	int posicionDni = (i-1)*LONGITUD_TOTAL + POSICION_DNI; //Instanciamos un entero auxiliar que almacena la posición del DNI del personaje iterado
						String dni = leerString(raf,posicionDni); //Usamos la función privada leerString para leer el dni del personaje en función de su posición
						if(dni.equals(dniIntroducido)) {
		                	int posicionPeso = (i-1)*LONGITUD_TOTAL + POSICION_PESO; //Instanciamos un entero auxiliar que almacena la posición del peso del personaje iterado
							raf.seek(posicionPeso); //Nos movemos a la posición del nombre					
							int pesoAntiguo = raf.readInt(); //Almacenamos en una instancia de numero entero llamada pesoAntiguo el entero del RandomAccesFile en la posición
							int posicionNombre = (i-1)*LONGITUD_TOTAL + POSICION_NOMBRE; //Instanciamos un entero auxiliar que almacena la posición del peso del personaje iterado
							String nombre = leerString(raf,posicionNombre); //Usamos la función privada leerString para leer el nombre del personaje en función de su posición
							String estado = "se mantiene en su peso anterior"; //Instanciamos una cadena estado que almacenará que parte final del mensaje corresponde al caso
							if( pesoAntiguo > peso) {
								estado = "ha adelgazado " + (pesoAntiguo-peso) + " kilos";
							}else if(pesoAntiguo < peso) {
								estado = "ha engordado " + (peso-pesoAntiguo) + " kilos";
							}
							System.out.println(nombre + " " + estado);
							existe = true; 
						}
	                }
					if(!existe) { //Si no se ha encontrado ningún personaje con ese DNI nos muetra un mensaje de aviso
						System.out.println("No existe ningún personaje con ese DNI");
					}              
	                
	            } catch (NumberFormatException e) {
	                System.out.println("El peso del personaje debe ser un número entero");
	            }
	        }
		} catch (FileNotFoundException e) {
  	      System.out.println("El archivo a comprobar no existe");
  	    } catch (IOException e) {
  	      System.out.println("Error de E/S: " + e.getMessage());
  	    }   			
		
	}
	
	//La función privada leerString recibe un RandomAccessFile y extrae la cadena que hay en esa posición, teniendo en cuenta la longitud escogida para las cadenas en la estructura de datos
    private static String leerString(RandomAccessFile raf, int posicion) throws IOException {
    	raf.seek(posicion);
    	char [] data = new char[LONGITUD_CADENAS]; //Instanciamos un arreglo de caracteres con dimensión de 32
		for (int i=0;i<LONGITUD_CADENAS;i++) { //Iteramos hasta el número máximo de caracteres guardando en el arreglo los datos del RandomAccessFile
			data[i]=raf.readChar();
		}
		return new String(data).trim(); //Instanciamos una cadena a partir del arreglo de caracteres y eliminamos los espacios en blanco al principio y al final
    }		
	
}
