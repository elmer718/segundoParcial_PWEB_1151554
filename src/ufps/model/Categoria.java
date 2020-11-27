package ufps.model;

import lombok.Data;

@Data
public class Categoria {
	
	int id;
	String descripcion;
	
	public Categoria(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}
	
	

}
