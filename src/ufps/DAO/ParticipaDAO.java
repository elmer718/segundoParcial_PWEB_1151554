package ufps.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ufps.Conexion.Conexion;
import ufps.model.Alumno;
import ufps.model.Participa;
import ufps.model.Proyecto;

public class ParticipaDAO {
	
	private Conexion conexion;
	
	private static final String INSERT_PARTICIPA_SQL = "INSERT INTO participa (alumno, proyecto) VALUES (?, ?);";
	private static final String DELETE_PARTICIPA_SQL = "DELETE FROM participa WHERE alumno = ?;";
	private static final String UPDATE_PARTICIPA_SQL = "UPDATE participa SET proyecto = ? WHERE alumno = ?;";
	private static final String SELECT_PARTICIPA_BY_ID = "SELECT * FROM participa WHERE alumno = ?;";
	private static final String SELECT_ALL_PARTICIPAS = "SELECT * FROM participa;";
	
	public ParticipaDAO() {
		this.conexion = Conexion.getConexion();
	}
	
	public void insert(Participa participa) throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(INSERT_PARTICIPA_SQL);
			preparedStatement.setString(1, participa.getAlumno().getCodigo());
			preparedStatement.setInt(2, participa.getProyecto().getId());
			conexion.execute();
		} catch (SQLException e) {
			
		}
	}
	
	public void delete (String alumno)  throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(DELETE_PARTICIPA_SQL);
			preparedStatement.setString(1, alumno);

			conexion.execute();
		} catch (SQLException e) {
			
		}
	}
	
	public void update(Participa participa)  throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(UPDATE_PARTICIPA_SQL);
			preparedStatement.setInt(1, participa.getProyecto().getId());
			preparedStatement.setString(2, participa.getAlumno().getCodigo());
			conexion.execute();
		} catch (SQLException e) {
			
		}
	}
	
	public List<Participa> selectAll() {
		List <Participa> participas = new ArrayList<>();
		Alumno alumno;
		Proyecto proyecto;
		
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_ALL_PARTICIPAS);
			
			ResultSet rs = conexion.query();
			
			while(rs.next()) {
				String codigo = rs.getString("alumno");
				alumno = new AlumnoDAO().select(codigo);
				int id = rs.getInt("proyecto");
				proyecto = new ProyectoDAO().select(id);
				participas.add(new Participa(alumno, proyecto));
			}
			
		} catch (SQLException e) {
			
		}
		
		return participas;
		
	}
	
	public Participa select(String alumno) {
		Participa participa = null;
		Proyecto proyecto;
		Alumno alumnoM;
		
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_PARTICIPA_BY_ID);
			preparedStatement.setString(1, alumno);
			
			ResultSet rs = conexion.query();
			
			while(rs.next()) {
				alumnoM = new AlumnoDAO().select(alumno);
				int id = rs.getInt("proyecto");
				proyecto = new ProyectoDAO().select(id);
				participa = new Participa(alumnoM, proyecto);
			}
			
		} catch (SQLException e) {
			
		}
		
		return participa;
		
	}

}
