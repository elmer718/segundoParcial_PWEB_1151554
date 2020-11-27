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
	
	public Proyecto(int id, String nombre, String resumen, String video, Tipo tipo, Categoria categoria,
			Asignatura asignatura, Evento evento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.resumen = resumen;
		this.video = video;
		this.tipo = tipo;
		this.categoria = categoria;
		this.asignatura = asignatura;
		this.evento = evento;
	}
	
}
