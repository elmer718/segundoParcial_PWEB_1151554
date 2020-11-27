package ufps.Prueba;

import java.sql.Date;
import java.sql.SQLException;

import ufps.DAO.AlumnoDAO;
import ufps.DAO.AsignaturaDAO;
import ufps.DAO.CategoriaDAO;
import ufps.DAO.EventoDAO;
import ufps.DAO.ParticipaDAO;
import ufps.DAO.ProyectoDAO;
import ufps.DAO.TipoDAO;
import ufps.model.Alumno;
import ufps.model.Asignatura;
import ufps.model.Categoria;
import ufps.model.Evento;
import ufps.model.Participa;
import ufps.model.Proyecto;
import ufps.model.Tipo;

public class Prueba {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Alumno alumno = new Alumno("1151554", "Elmer", "elmerfabianrc@ufps.edu.co", "faro");
		AlumnoDAO alumD = new AlumnoDAO();
		alumD.insert(alumno);
		
		Asignatura asig = new Asignatura("115", "PWEB");
		AsignaturaDAO asigD = new AsignaturaDAO();
		asigD.insert(asig);
		
		Categoria cate = new Categoria(9, "prueba");
		CategoriaDAO cateD = new CategoriaDAO();
		cateD.insert(cate);
		
		@SuppressWarnings("deprecation")
		Date fecha = new Date(27, 11, 2020);
		Evento event = new Evento(06, "previo", fecha);
		EventoDAO eventD = new EventoDAO();
		eventD.insert(event);
		
		Tipo tipo = new Tipo(05, "prueba");
		TipoDAO tipoD = new TipoDAO();
		tipoD.insert(tipo);
		
		Proyecto pro = new Proyecto(07, "Previo", "prueba", "https://www.youtube.com/watch?v=a3nE8neOwHM", 
				tipo, cate, asig, event);
		ProyectoDAO proD = new ProyectoDAO();
		proD.insert(pro);
		
		Participa p1 = new Participa(alumno, pro);
		ParticipaDAO pD = new ParticipaDAO();
		pD.insert(p1);
	}

}
