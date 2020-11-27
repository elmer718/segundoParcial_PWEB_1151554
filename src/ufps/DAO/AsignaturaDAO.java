package ufps.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ufps.Conexion.Conexion;
import ufps.model.Asignatura;

public class AsignaturaDAO {
	
	private Conexion conexion;
	
	private static final String INSERT_ASIGNATURA_SQL = "INSERT INTO asignatura (codigo, nombre) VALUES (?, ?);";
	private static final String DELETE_ASIGNATURA_SQL = "DELETE FROM asignatura WHERE codigo = ?;";
	private static final String UPDATE_ASIGNATURA_SQL = "UPDATE asignatura SET nombre = ? WHERE codigo = ?;";
	private static final String SELECT_ASIGNATURA_BY_ID = "SELECT * FROM asignatura WHERE codigo = ?;";
	private static final String SELECT_ALL_ASIGNATURAS = "SELECT * FROM asignatura;";
	
	public AsignaturaDAO() {
		this.conexion = Conexion.getConexion();
	}
	
	public void insert(Asignatura asignatura) throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(INSERT_ASIGNATURA_SQL);
			preparedStatement.setString(1, asignatura.getCodigo());
			preparedStatement.setString(2, asignatura.getNombre());
			conexion.execute();
		} catch (SQLException e) {
			
		}
	}
	
	public void delete (String codigo)  throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(DELETE_ASIGNATURA_SQL);
			preparedStatement.setString(1, codigo);

			conexion.execute();
		} catch (SQLException e) {
			
		}
	}
	
	public void update(Asignatura asignatura)  throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(UPDATE_ASIGNATURA_SQL);
			preparedStatement.setString(1, asignatura.getNombre());
			preparedStatement.setString(2, asignatura.getCodigo());
			conexion.execute();
		} catch (SQLException e) {
			
		}
	}
	
	public List<Asignatura> selectAll() {
		List <Asignatura> asignaturas = new ArrayList<>();
		
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_ALL_ASIGNATURAS);
			
			ResultSet rs = conexion.query();
			
			while(rs.next()) {
				String codigo = rs.getString("codigo");
				String nombre = rs.getString("nombre");
				asignaturas.add(new Asignatura(codigo, nombre));
			}
			
		} catch (SQLException e) {
			
		}
		
		return asignaturas;
		
	}
	
	public Asignatura select(String codigo) {
		Asignatura asignatura = null;
		
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_ASIGNATURA_BY_ID);
			preparedStatement.setString(1, codigo);
			
			ResultSet rs = conexion.query();
			
			while(rs.next()) {
				String nombre = rs.getString("nombre");
				asignatura = new Asignatura(codigo, nombre);
			}
			
		} catch (SQLException e) {
			
		}
		
		return asignatura;
		
	}

}
