package ufps.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ufps.Conexion.Conexion;
import ufps.model.Categoria;

public class CategoriaDAO {
	
	private Conexion conexion;
	
	private static final String INSERT_CATEGORIA_SQL = "INSERT INTO categoria (id, descripcion) VALUES (?, ?);";
	private static final String DELETE_CATEGORIA_SQL = "DELETE FROM categoria WHERE id = ?;";
	private static final String UPDATE_CATEGORIA_SQL = "UPDATE categoria SET descripcion = ? WHERE id = ?;";
	private static final String SELECT_CATEGORIA_BY_ID = "SELECT * FROM categoria WHERE id = ?;";
	private static final String SELECT_ALL_CATEGORIAS = "SELECT * FROM categoria;";
	
	public CategoriaDAO() {
		this.conexion = Conexion.getConexion();
	}
	
	public void insert(Categoria categoria) throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(INSERT_CATEGORIA_SQL);
			preparedStatement.setInt(1, categoria.getId());
			preparedStatement.setString(2, categoria.getDescripcion());
			conexion.execute();
		} catch (SQLException e) {
			
		}
	}
	
	public void delete (int id)  throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(DELETE_CATEGORIA_SQL);
			preparedStatement.setInt(1, id);

			conexion.execute();
		} catch (SQLException e) {
			
		}
	}
	
	public void update(Categoria categoria)  throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(UPDATE_CATEGORIA_SQL);
			preparedStatement.setString(1, categoria.getDescripcion());
			preparedStatement.setInt(2, categoria.getId());
			conexion.execute();
		} catch (SQLException e) {
			
		}
	}
	
	public List<Categoria> selectAll() {
		List <Categoria> categorias = new ArrayList<>();
		
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_ALL_CATEGORIAS);
			
			ResultSet rs = conexion.query();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String descripcion = rs.getString("descripcion");
				categorias.add(new Categoria(id, descripcion));
			}
			
		} catch (SQLException e) {
			
		}
		
		return categorias;
		
	}
	
	public Categoria select(int id) {
		Categoria categoria = null;
		
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_CATEGORIA_BY_ID);
			preparedStatement.setInt(1, id);
			
			ResultSet rs = conexion.query();
			
			while(rs.next()) {
				String descripcion = rs.getString("descripcion");
				categoria = new Categoria(id, descripcion);
			}
			
		} catch (SQLException e) {
			
		}
		
		return categoria;
		
	}

}
