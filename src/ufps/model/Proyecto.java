package ufps.model;

import lombok.Data;

@Data
public class Proyecto {
	
	int id;
	String nombre;
	String resumen;
	String video;
	
	Tipo tipo;
	Categoria categoria;
	Asignatura asignatura;
	Evento evento;
	
}
