package Metodos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import BDconexion.conexion;
import GetaSet.ordenLista;

public class CrudOrdenLista {
	conexion cx;

	public CrudOrdenLista() {
		cx = new conexion();
	}

	public boolean insertarOrdenLista(List<ordenLista> ordenListas) {
		try (Connection connection = cx.conectar()) {
			connection.setAutoCommit(false);

			try (PreparedStatement ps = connection
					.prepareStatement("INSERT INTO Ordenlista (id_Lista, id_Cancion, posicion) VALUES (?, ?, ?)")) {

				for (ordenLista ordenLista : ordenListas) {
					ps.setInt(1, ordenLista.getIdLista());
					ps.setInt(2, ordenLista.getIdCancion());
					ps.setInt(3, ordenLista.getPosicion());
					ps.addBatch();
				}

				int[] filasAfectadas = ps.executeBatch();
				connection.commit();

				for (int filas : filasAfectadas) {
					if (filas <= 0) {
						return false;
					}
				}

				return true;
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
				return false;
			} finally {
				connection.setAutoCommit(true);
				cx.desconectar();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean eliminarOrdenLista(int idOrdenLista) {
		try (PreparedStatement ps = cx.conectar().prepareStatement("DELETE FROM Ordenlista WHERE id_Orden=?")) {
			ps.setInt(1, idOrdenLista);
			ps.executeUpdate();
			cx.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<ordenLista> consultaOrdenLista() {
		List<ordenLista> ordenListas = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = cx.conectar().prepareStatement("SELECT * FROM Ordenlista");
			rs = ps.executeQuery();

			while (rs.next()) {
				ordenLista ordenLista = new ordenLista();
				ordenLista.setIdOrden(rs.getInt("id_Orden"));
				ordenLista.setIdLista(rs.getInt("id_Lista"));
				ordenLista.setIdCancion(rs.getInt("id_Cancion"));
				ordenLista.setPosicion(rs.getInt("posicion"));
				ordenListas.add(ordenLista);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cx.desconectar();
		}
		return ordenListas;
	}

	public boolean editarOrdenLista(ordenLista ordenActualizada) {
		try (PreparedStatement ps = cx.conectar()
				.prepareStatement("UPDATE Ordenlista SET id_Lista=?, id_Cancion=?, posicion=? WHERE id_Orden=?")) {
			ps.setInt(1, ordenActualizada.getIdLista());
			ps.setInt(2, ordenActualizada.getIdCancion());
			ps.setInt(3, ordenActualizada.getPosicion());
			ps.setInt(4, ordenActualizada.getIdOrden());

			int filasAfectadas = ps.executeUpdate();

			cx.desconectar();

			return filasAfectadas > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public int obtenerIdLista(String nombreLista) {
		int idLista = -1;

		try {

			String query = "SELECT id_Lista FROM Lista WHERE nombrelista = ?";
			try (PreparedStatement ps = cx.conectar().prepareStatement(query)) {
				ps.setString(1, nombreLista);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {

						idLista = rs.getInt("id_Lista");
					} else {

						query = "INSERT INTO Lista (nombrelista) VALUES (?)";
						try (PreparedStatement insertStatement = cx.conectar().prepareStatement(query,
								PreparedStatement.RETURN_GENERATED_KEYS)) {
							insertStatement.setString(1, nombreLista);
							int filasAfectadas = insertStatement.executeUpdate();

							if (filasAfectadas > 0) {

								try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
									if (generatedKeys.next()) {
										idLista = generatedKeys.getInt(1);
									}
								}
							}
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cx.desconectar();
		}
		return idLista;

	}

	public String obtenerNombreCancionPorID(int idCancion) {
		String nombreCancion = "";

		try {
			String query = "SELECT tituloCancion FROM Canciones WHERE id_Cancion = ?";
			try (PreparedStatement ps = cx.conectar().prepareStatement(query)) {
				ps.setInt(1, idCancion);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						nombreCancion = rs.getString("tituloCancion");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cx.desconectar();
		}

		return nombreCancion;
	}

	public String obtenerNombreListaPorId(int idLista) {
		String nombreLista = "";

		try {
			String query = "SELECT nombrelista FROM Lista WHERE id_Lista = ?";
			try (PreparedStatement ps = cx.conectar().prepareStatement(query)) {
				ps.setInt(1, idLista);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						nombreLista = rs.getString("nombrelista");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cx.desconectar();
		}

		return nombreLista;
	}

	public int obtenerIdCancion(String nombreCancion) {
		int idCancion = -1;

		try {
			String query = "SELECT id_Cancion FROM Canciones WHERE tituloCancion = ?";
			try (PreparedStatement ps = cx.conectar().prepareStatement(query)) {
				ps.setString(1, nombreCancion);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						idCancion = rs.getInt("id_Cancion");
					} else {
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cx.desconectar();
		}

		return idCancion;
	}
}