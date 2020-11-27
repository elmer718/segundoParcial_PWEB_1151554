package ufps.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ufps.Conexion.Conexion;
import ufps.model.Asignatura;
import ufps.model.Categoria;
import ufps.model.Evento;
import ufps.model.Proyecto;
import ufps.model.Tipo;

public class ProyectoDAO {
	
	private Conexion conexion;
	
	private static final String INSERT_SQL = "INSERT INTO proyecto (id, nombre, resumen, video, tipo, categoria, asignatura, evento) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String DELETE_SQL = "DELETE FROM proyecto WHERE id = ?;";
	private static final String UPDATE_SQL = "UPDATE proyecto SET nombre = ?, resumen = ?, video = ?, tipo = ?, categoria = ?, asignatura = ?, evento = ?;";
	private static final String SELECT_BY_ID = "SELECT * FROM proyecto WHERE id = ?;";
	private static final String SELECT_ALL = "SELECT * FROM proyecto;";
	
	public ProyectoDAO() {
		this.conexion = Conexion.getConexion();
	}
	
	public void insert(Proyecto proyecto) throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(INSERT_SQL);
			preparedStatement.setInt(1, proyecto.getId());
			preparedStatement.setString(2, proyecto.getNombre());
			preparedStatement.setString(3, proyecto.getResumen());
			preparedStatement.setString(4, proyecto.getVideo());
			preparedStatement.setInt(5, proyecto.getTipo().getId());
			preparedStatement.setInt(6, proyecto.getCategoria().getId());
			preparedStatement.setString(7, proyecto.getAsignatura().getCodigo());
			preparedStatement.setInt(8, proyecto.getEvento().getId());
			conexion.execute();
		} catch (SQLException e) {
			
		}
	}
	
	public void delete (int id)  throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(DELETE_SQL);
			preparedStatement.setString(1, id);

			conexion.execute();
		} catch (SQLException e) {
			
		}
	}
	
	public void update(Proyecto proyecto)  throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(UPDATE_SQL);
			preparedStatement.setString(1, proyecto.getNombre());
			preparedStatement.setString(2, proyecto.getResumen());
			preparedStatement.setString(3, proyecto.getVideo());
			preparedStatement.setInt(4, proyecto.getTipo().getId());
			preparedStatement.setInt(5, proyecto.getCategoria().getId());
			preparedStatement.setString(6, proyecto.getAsignatura().getCodigo());
			preparedStatement.setInt(7, proyecto.getEvento().getId());
			preparedStatement.setInt(8, proyecto.getId());
			conexion.execute();
		} catch (SQLException e) {
			
		}
	}
	
	public List<Proyecto> selectAll() {
		List <Proyecto> proyectos = new ArrayList<>();
		
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_ALL);
			
			ResultSet rs = conexion.query();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String resumen = rs.getString("resumen");
				String video = rs.getString("video");
				Tipo tipo = new TipoDAO().select(rs.getInt("tipo"));
				Categoria categoria = new CategoriaDAO().select(rs.getInt("categoria"));
				Asignatura asig = new AsignaturaDAO().select(rs.getString("asignatura"));
				Evento evento = EventoDAO().select(rs.getInt("evento"));
				
				proyectos.add(new Proyecto(id, nombre, resumen, video, tipo, categoria, asig, evento);
			}
			
		} catch (SQLException e) {
			
		}
		
		return proyectos;
		
	}
	
	public Proyecto select(int id) {
		Proyecto proyecto = null;
		
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_BY_ID);
			preparedStatement.setInt(1, id);
			
			ResultSet rs = conexion.query();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String resumen = rs.getString("resumen");
				String video = rs.getString("video");
				Tipo tipo = new TipoDAO().select(rs.getInt("tipo"));
				Categoria categoria = new CategoriaDAO().select(rs.getInt("categoria"));
				Asignatura asig = new AsignaturaDAO().select(rs.getString("asignatura"));
				Evento evento = EventoDAO().select(rs.getInt("evento"));
				
				proyecto = new Proyecto(id, nombre, resumen, video, tipo, categoria, asig, evento);
			}
			
		} catch (SQLException e) {
			
		}
		
		return proyecto;
		
	}


}
