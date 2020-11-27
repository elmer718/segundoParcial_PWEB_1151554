package ufps.model;

import lombok.Data;

@Data
public class Alumno {
	
	String codigo;
	String nombre;
	String email;
	String clave;
	
	public Alumno(String codigo, String nombre, String email, String clave) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.email = email;
		this.clave = clave;
	}

}
