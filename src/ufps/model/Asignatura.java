package ufps.model;

import lombok.Data;

@Data
public class Asignatura {
	
	String codigo;
	String nombre;
	
	public Asignatura(String codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
	
}
