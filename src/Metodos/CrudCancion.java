package Metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BDconexion.conexion;
import GetaSet.cancion;

public class CrudCancion {
	conexion cx;

	public CrudCancion() {
		cx = new conexion();
	}

	public boolean insertarCancion(cancion cancion) {
		try (PreparedStatement ps = cx.conectar()
				.prepareStatement("INSERT INTO Canciones (titulocancion, id_Artista, ubicacion) VALUES (?, ?, ?)")) {
			ps.setString(1, cancion.getTituloCancion());
			ps.setInt(2, cancion.getIdArtista());
			ps.setString(3, cancion.getUbicacion());

			int filasAfectadas = ps.executeUpdate();

			if (filasAfectadas > 0) {
				cx.desconectar();
				return true;
			} else {
				cx.desconectar();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean eliminarCancion(int idCancion) {
		try (PreparedStatement ps = cx.conectar().prepareStatement("DELETE FROM Canciones WHERE id_Cancion=?")) {
			ps.setInt(1, idCancion);
			ps.executeUpdate();
			cx.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean editarCancion(cancion cancion) {
		try (PreparedStatement ps = cx.conectar().prepareStatement(
				"UPDATE Canciones SET titulocancion=?, id_Artista=?, ubicacion=? WHERE id_Cancion=?")) {
			ps.setString(1, cancion.getTituloCancion());
			ps.setInt(2, cancion.getIdArtista());
			ps.setString(3, cancion.getUbicacion());
			ps.setInt(4, cancion.getIdCancion());

			ps.executeUpdate();
			cx.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<cancion> consultaCanciones() {
		List<cancion> canciones = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = cx.conectar().prepareStatement("SELECT * FROM Canciones");
			rs = ps.executeQuery();

			while (rs.next()) {
				cancion cancion = new cancion();
				cancion.setIdCancion(rs.getInt("id_Cancion"));
				cancion.setTituloCancion(rs.getString("titulocancion"));
				cancion.setIdArtista(rs.getInt("id_Artista"));
				cancion.setUbicacion(rs.getString("ubicacion"));
				canciones.add(cancion);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cx.desconectar();
		}
		return canciones;
	}

	public String obtenerNombreArtistaPorID(int idArtista) {
		try (PreparedStatement ps = cx.conectar().prepareStatement("SELECT nombre FROM Artista WHERE id_Artista=?")) {
			ps.setInt(1, idArtista);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getString("nombre");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cx.desconectar();
		}
		return null;
	}

	public int obtenerIdArtistaPorNombre(String nombreArtista) {
		int idArtista = -1;

		try (PreparedStatement ps = cx.conectar().prepareStatement("SELECT id_Artista FROM Artista WHERE nombre = ?")) {
			ps.setString(1, nombreArtista);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				idArtista = rs.getInt("id_Artista");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cx.desconectar();
		}

		return idArtista;
	}
}
