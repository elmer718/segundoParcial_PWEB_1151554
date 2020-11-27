package ufps.model;

import lombok.Data;

@Data
public class Tipo {
	
	int id;
	String descripcion;
	
	public Tipo(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

}
