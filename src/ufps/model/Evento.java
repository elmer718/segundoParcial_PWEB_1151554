package ufps.model;

import java.sql.Date;

import lombok.Data;


@Data
public class Evento {
	
	int id;
	String nombre;
	Date fecha;

}
