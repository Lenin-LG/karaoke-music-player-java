package Metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BDconexion.conexion;
import GetaSet.artista;

public class CrudArtista {
	conexion cx;

	public CrudArtista() {
		cx = new conexion();
	}

	public boolean insertarArtista(artista artista) {
		try (PreparedStatement ps = cx.conectar()
				.prepareStatement("INSERT INTO Artista (nombre, genero, pais, imagen_url) VALUES (?, ?, ?, ?)")) {
			ps.setString(1, artista.getNombre());
			ps.setString(2, artista.getGenero());
			ps.setString(3, artista.getPais());
			ps.setString(4, artista.getImagenUrl());

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

	public List<artista> obtenerTodosArtistas() {
		List<artista> artistas = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT * FROM Artista");
			rs = ps.executeQuery();

			while (rs.next()) {
				artista artista = new artista();
				artista.setIdArtista(rs.getInt("id_Artista"));
				artista.setNombre(rs.getString("nombre"));
				artista.setGenero(rs.getString("genero"));
				artista.setPais(rs.getString("pais"));
				artista.setImagenUrl(rs.getString("imagen_url"));
				artistas.add(artista);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return artistas;
	}

	public boolean actualizarArtista(artista artista) {
		PreparedStatement ps = null;

		try {
			ps = cx.conectar()
					.prepareStatement("UPDATE Artista SET nombre=?, genero=?, pais=?, imagen_url=? WHERE id_Artista=?");
			ps.setString(1, artista.getNombre());
			ps.setString(2, artista.getGenero());
			ps.setString(3, artista.getPais());
			ps.setString(4, artista.getImagenUrl());
			ps.setInt(5, artista.getIdArtista());
			ps.executeUpdate();
			cx.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean eliminarArtista(int idArtista) {
		PreparedStatement ps = null;
		PreparedStatement psActualizarIds = null;

		try {

			ps = cx.conectar().prepareStatement("DELETE FROM Artista WHERE id_Artista=?");
			ps.setInt(1, idArtista);
			ps.executeUpdate();
			psActualizarIds = cx.conectar()
					.prepareStatement("UPDATE Artista SET id_Artista=id_Artista-1 WHERE id_Artista>?");

			psActualizarIds.setInt(1, idArtista);
			psActualizarIds.executeUpdate();

			cx.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (psActualizarIds != null) {
				try {
					psActualizarIds.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			cx.desconectar();
		}
	}

}
