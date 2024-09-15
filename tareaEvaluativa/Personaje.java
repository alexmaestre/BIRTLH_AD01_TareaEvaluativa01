package tareaEvaluativa;

import java.io.Serializable;

/* 
 * La clase Personaje representa una entidad Personaje que tiene los atributos de tipo entero id, peso y altura y los atributos de tipo String dni, nombre, identidad secreta y tipo
 * Es una clase que implementa la interfaz serializable con objeto de poder almacenar la información en un fichero, por ello cuenta con una variable llamada serialVersionUID necesaria para serializar
 * La clase cuenta con un constructor y los getters y setters de rigor
 */
public class Personaje implements Serializable {

   private static final long serialVersionUID = 1L;

   private int id, peso, altura;
   private String dni, nombre, identidad, tipo;

	public Personaje(int id, String dni, String nombre, String identidad, String tipo, int peso, int altura)	{
	  this.id=id;
	  this.dni=dni;
	  this.nombre=nombre;
	  this.identidad=identidad;
	  this.tipo=tipo;
	  this.id=peso;
	  this.id=altura;
	 }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdentidad() {
		return identidad;
	}

	public void setIdentidad(String identidad) {
		this.identidad = identidad;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}   

	
	
}
