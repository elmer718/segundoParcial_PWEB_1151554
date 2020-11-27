package ufps.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ufps.Conexion.Conexion;
import ufps.model.Alumno;

public class AlumnoDAO {
	
	private Conexion conexion;
	
	private static final String INSERT_ALUMNO_SQL = "INSERT INTO alumno (codigo, nombre, email, clave) VALUES (?, ?, ?, ?);";
	private static final String DELETE_ALUMNO_SQL = "DELETE FROM alumno WHERE codigo = ?;";
	private static final String UPDATE_ALUMNO_SQL = "UPDATE alumno SET nombre = ?, email = ?, clave = ? WHERE codigo = ?;";
	private static final String SELECT_ALUMNO_BY_ID = "SELECT * FROM alumno WHERE codigo = ?;";
	private static final String SELECT_ALL_ALUMNOS = "SELECT * FROM alumno;";
	
	public AlumnoDAO() {
		this.conexion = Conexion.getConexion();
	}
	
	public void insert(Alumno alumno) throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(INSERT_ALUMNO_SQL);
			preparedStatement.setString(1, alumno.getCodigo());
			preparedStatement.setString(2, alumno.getNombre());
			preparedStatement.setString(3, alumno.getEmail());
			preparedStatement.setString(4, alumno.getClave());
			conexion.execute();
		} catch (SQLException e) {
			
		}
	}
	
	public void delete (String codigo)  throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(DELETE_ALUMNO_SQL);
			preparedStatement.setString(1, codigo);

			conexion.execute();
		} catch (SQLException e) {
			
		}
	}
	
	public void update(Alumno alumno)  throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(UPDATE_ALUMNO_SQL);
			preparedStatement.setString(1, alumno.getNombre());
			preparedStatement.setString(2, alumno.getEmail());
			preparedStatement.setString(3, alumno.getClave());
			preparedStatement.setString(4, alumno.getCodigo());
			conexion.execute();
		} catch (SQLException e) {
			
		}
	}
	
	public List<Alumno> selectAll() {
		List <Alumno> alumnos = new ArrayList<>();
		
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_ALL_ALUMNOS);
			
			ResultSet rs = conexion.query();
			
			while(rs.next()) {
				String codigo = rs.getString("codigo");
				String nombre = rs.getString("nombre");
				String email = rs.getString("email");
				String clave = rs.getString("clave");
				alumnos.add(new Alumno(codigo, nombre, email, clave));
			}
			
		} catch (SQLException e) {
			
		}
		
		return alumnos;
		
	}
	
	public Alumno select(String codigo) {
		Alumno alumno = null;
		
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_ALUMNO_BY_ID);
			preparedStatement.setString(1, codigo);
			
			ResultSet rs = conexion.query();
			
			while(rs.next()) {
				String nombre = rs.getString("nombre");
				String email = rs.getString("email");
				String clave = rs.getString("clave");
				alumno = new Alumno(codigo, nombre, email, clave);
			}
			
		} catch (SQLException e) {
			
		}
		
		return alumno;
		
	}
	
}
