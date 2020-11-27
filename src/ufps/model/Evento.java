package ufps.model;

import java.sql.Date;

import lombok.Data;


@Data
public class Evento {
	
	int id;
	String nombre;
	Date fecha;
	
	public Evento(int id, String nombre, Date fecha) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha = fecha;
	}
	
	
}
