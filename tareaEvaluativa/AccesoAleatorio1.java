package tareaEvaluativa;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;


public class AccesoAleatorio1 {
	
	private static final String RUTA = "src" + File.separator + "tareaEvaluativa" + File.separator + "Marvel.dat"; //Definimos constante con ruta del fichero de datos de personajes
	/*Para la serialización usaremos una estructura de 32 caracteres para las cadenas, resultando un total de 268 caracteres de serialización por personaje si tenemos en cuenta que son 3
	enteros y 4 cadenas por entidad. Definimos dos constantes para manejar tanto la longitud total como la longitud de las cadenas. */
	private static final int LONGITUD_TOTAL = 268; 
	private static final int LONGITUD_CADENAS = 32; 
	  
	public static void main(String[] args) throws IOException {   
	      
		//Instanciamos los arreglos con la información de los distintos personajes
		int [] ids= {1, 2, 3, 4, 5, 6, 7};
		String[] dnis= {"01010101A", "03030303C", "05050505E", "07070707G", "02020202B", "04040404D", "06060606F"};
		String[] noms= {"Spiderman", "Green Goblin", "Storm", "Wolverine", "Mystique", "IronMan", "Mandarin"};
		String[] identidades = {"Peter Parker", "Norman Osborn", "Ororo Munroe","James Howlett", "Raven Darkholme", "Tony Stark", "Zhang Tong"};
		String[] tipos = {"heroe","villano","heroe","heroe","villano","heroe","villano"};
		int[] pesos = {76,84,66,136,78,102,70};
		int[] alturas = {178,183,156,152,177,182,188};
		
		File fichero = new File(RUTA); //Instanciamos un objeto File basado en la ruta del archivo donde se almacenará la información de los personajes
		if(fichero.exists()){ //Borramos el archivo si ya existe
			fichero.delete();
		}
		
		//Instanciamos un objeto RandomAccessFile basado en el objeto fichero, empleando un bloque Try para manejar excepciones
		try (RandomAccessFile raf = new RandomAccessFile(fichero, "rw")){
		
			int posicion = 0; //Instanciamos un entero auxiliar llamado posición para situar el puntero del fichero no secuenciable	
			for (int i=0;i<ids.length; i++){ //recorremos ids de la información dada
				posicion = i*LONGITUD_TOTAL; //Calculamos la posición donde tendremos que ponernos en función del nº de iteración multiplicaa por la longitud de información de cada personaje
				raf.seek(posicion); //Nos movemos a la posición
				//Guardamos los atributos usando writeInt cuando son enteros y la función guardarString cuando son cadena
				raf.writeInt(ids[i]); 
				guardarString(dnis[i], raf); 
				guardarString(noms[i], raf);
				guardarString(identidades[i], raf);
				guardarString(tipos[i], raf);
				raf.writeInt(pesos[i]); 
				raf.writeInt(alturas[i]);
			}   
			System.out.println("Datos guardados"); //Avisamos de uqe los datos han sido guardados
			
		}catch (IOException e) {
	        System.out.println("Error de E/S: " + e.getMessage());
	    }


	}	

	 /* La función privada guardarString recibe una cadena y guardar en el archivo de acceso no secuencial el valor de dicha cadena, teniendo en cuenta la longitud 
	  * que hemos escogido para el tamaño de los datos de tipo cadena en el archivo no secuencial */
	 private static void guardarString(String cadena, RandomAccessFile raf) throws IOException {
		StringBuffer buffer = new StringBuffer( cadena );  //Instanciamos un buffer auxiliar a partir de la cadena introducida  
		buffer.setLength(LONGITUD_CADENAS);  //Definimos la longuitud del buffer de manera que se rellenará de elementos nulos hasta llegar al tamaño dado
		raf.writeChars(buffer.toString()); //Volcamos el contenido del buffer, pasado a cadena, en el archivo de acceso no secuencial
	 }	  	
	    
	
}
