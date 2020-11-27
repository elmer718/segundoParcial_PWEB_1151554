package ufps.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ufps.Conexion.Conexion;
import ufps.model.Tipo;

public class TipoDAO {
	
	private Conexion conexion;
	
	private static final String INSERT_SQL = "INSERT INTO tipo (id, descripcion) VALUES (?, ?);";
	private static final String DELETE_SQL = "DELETE FROM tipo WHERE codigo = ?;";
	private static final String UPDATE_SQL = "UPDATE tipo SET descripcion = ? WHERE id = ?;";
	private static final String SELECT_BY_ID = "SELECT * FROM tipo WHERE id = ?;";
	private static final String SELECT_ALL = "SELECT * FROM tipo;";
	
	public TipoDAO() {
		this.conexion = Conexion.getConexion();
	}
	
	public void insert(Tipo tipo) throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(INSERT_SQL);
			preparedStatement.setInt(1, tipo.getId());
			preparedStatement.setString(2, tipo.getDescripcion());
			conexion.execute();
		} catch (SQLException e) {
			
		}
	}
	
	public void delete (int id)  throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(DELETE_SQL);
			preparedStatement.setInt(1, id);

			conexion.execute();
		} catch (SQLException e) {
			
		}
	}
	
	public void update(Tipo tipo)  throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(UPDATE_SQL);
			preparedStatement.setString(1, tipo.getDescripcion());
			preparedStatement.setInt(2, tipo.getId());
			conexion.execute();
		} catch (SQLException e) {
			
		}
	}
	
	public List<Tipo> selectAll() {
		List <Tipo> tipos = new ArrayList<>();
		
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_ALL);
			
			ResultSet rs = conexion.query();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String descripcion = rs.getString("descripcion");
				tipos.add(new Tipo(id, descripcion));
			}
			
		} catch (SQLException e) {
			
		}
		
		return tipos;
		
	}
	
	public Tipo select(int id) {
		Tipo tipo = null;
		
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_BY_ID);
			preparedStatement.setInt(1, id);
			
			ResultSet rs = conexion.query();
			
			while(rs.next()) {
				String descripcion = rs.getString("descripcion");
				tipo = new Tipo(id, descripcion);
			}
			
		} catch (SQLException e) {
			
		}
		
		return tipo;
		
	}


}
