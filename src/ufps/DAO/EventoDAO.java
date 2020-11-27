package ufps.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ufps.Conexion.Conexion;
import ufps.model.Evento;

public class EventoDAO {
	
	private Conexion conexion;
	
	private static final String INSERT_EVENTO_SQL = "INSERT INTO evento (id, nombre, fecha) VALUES (?, ?, ?);";
	private static final String DELETE_EVENTO_SQL = "DELETE FROM evento WHERE id = ?;";
	private static final String UPDATE_EVENTO_SQL = "UPDATE evento SET nombre = ?, fecha = ? WHERE id = ?;";
	private static final String SELECT_EVENTO_BY_ID = "SELECT * FROM evento WHERE id = ?;";
	private static final String SELECT_ALL_EVENTOS = "SELECT * FROM evento;";
	
	public EventoDAO() {
		this.conexion = Conexion.getConexion();
	}
	
	public void insert(Evento evento) throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(INSERT_EVENTO_SQL);
			preparedStatement.setInt(1, evento.getId());
			preparedStatement.setString(2, evento.getNombre());
			preparedStatement.setDate(3, evento.getFecha());
			conexion.execute();
		} catch (SQLException e) {
			
		}
	}
	
	public void delete (int id)  throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(DELETE_EVENTO_SQL);
			preparedStatement.setInt(1, id);

			conexion.execute();
		} catch (SQLException e) {
			
		}
	}
	
	public void update(Evento evento)  throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(UPDATE_EVENTO_SQL);
			preparedStatement.setString(1, evento.getNombre());
			preparedStatement.setDate(2, evento.getFecha());
			preparedStatement.setInt(3, evento.getId());
			conexion.execute();
		} catch (SQLException e) {
			
		}
	}
	
	public List<Evento> selectAll() {
		List <Evento> eventos = new ArrayList<>();
		
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_ALL_EVENTOS);
			
			ResultSet rs = conexion.query();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				Date fecha = rs.getDate("fecha");
				eventos.add(new Evento(id, nombre, fecha));
			}
			
		} catch (SQLException e) {
			
		}
		
		return eventos;
		
	}
	
	public Evento select(int id) {
		Evento evento = null;
		
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_EVENTO_BY_ID);
			preparedStatement.setInt(1, id);
			
			ResultSet rs = conexion.query();
			
			while(rs.next()) {
				String nombre = rs.getString("nombre");
				Date fecha = rs.getDate("fecha");
				evento = new Evento(id, nombre, fecha);
			}
			
		} catch (SQLException e) {
			
		}
		
		return evento;
		
	}


}
