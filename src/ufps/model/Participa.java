package ufps.model;

import lombok.Data;

@Data
public class Participa {
	
	Alumno alumno;
	Proyecto proyecto;
	
	public Participa(Alumno alumno, Proyecto proyecto) {
		super();
		this.alumno = alumno;
		this.proyecto = proyecto;
	}

}
