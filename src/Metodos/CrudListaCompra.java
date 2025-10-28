package Metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BDconexion.conexion;
import GetaSet.listacompra;

public class CrudListaCompra {
	conexion cx;

	public CrudListaCompra() {
		cx = new conexion();
	}

	public boolean insertarListaCompra(listacompra listaCompra) {
		try (PreparedStatement ps = cx.conectar()
				.prepareStatement("INSERT INTO ListaCompra (id_Usuario, nombreLista) VALUES (?, ?)")) {
			ps.setInt(1, listaCompra.getId_Usuario());

			String nombreUsuario = obtenerNombreUsuarioPorId(listaCompra.getId_Usuario());
			String nuevoNombreLista = generarNombreLista(nombreUsuario);

			ps.setString(2, nuevoNombreLista);

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

	public List<listacompra> obtenerTodasListasCompra() {
		List<listacompra> listasCompra = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT * FROM ListaCompra");
			rs = ps.executeQuery();

			while (rs.next()) {
				listacompra listaCompra = new listacompra();
				listaCompra.setId_ListaCompra(rs.getInt("id_ListaCompra"));
				listaCompra.setId_Usuario(rs.getInt("id_Usuario"));
				listaCompra.setNombrelista(rs.getString("nombreLista"));
				listasCompra.add(listaCompra);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cx.desconectar();
		}
		return listasCompra;
	}

	public boolean actualizarListaCompra(listacompra listaCompra) {
		PreparedStatement ps = null;

		try {
			ps = cx.conectar()
					.prepareStatement("UPDATE ListaCompra SET id_Usuario=?, nombreLista=? WHERE id_ListaCompra=?");
			ps.setInt(1, listaCompra.getId_Usuario());
			ps.setString(2, listaCompra.getNombrelista());
			ps.setInt(3, listaCompra.getId_ListaCompra());
			ps.executeUpdate();
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
			cx.desconectar();
		}
	}

	public boolean eliminarListaCompra(int idListaCompra) {
		try (PreparedStatement ps = cx.conectar().prepareStatement("DELETE FROM ListaCompra WHERE id_ListaCompra=?")) {
			ps.setInt(1, idListaCompra);
			ps.executeUpdate();
			cx.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String obtenerNombreUsuarioPorId(int idUsuario) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String nombreUsuario = "";

		try {
			ps = cx.conectar().prepareStatement("SELECT nombre FROM Usuarios WHERE id_Usuario = ?");
			ps.setInt(1, idUsuario);
			rs = ps.executeQuery();

			if (rs.next()) {
				nombreUsuario = rs.getString("nombre");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cx.desconectar();
		}

		return nombreUsuario;
	}

	public String generarNombreLista(String nombreUsuario) {
		int cantidadListas = obtenerCantidadListasPorUsuario(nombreUsuario) + 1;

		String nuevoNombreLista = String.format("Lista%s%03d", nombreUsuario.toUpperCase(), cantidadListas);

		return nuevoNombreLista;
	}

	public int obtenerCantidadListasPorUsuario(String nombreUsuario) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int cantidadListas = 0;

		try {
			ps = cx.conectar().prepareStatement(
					"SELECT COUNT(*) AS cantidad FROM ListaCompra WHERE id_Usuario = (SELECT id_Usuario FROM Usuarios WHERE nombre = ?)");
			ps.setString(1, nombreUsuario);
			rs = ps.executeQuery();

			if (rs.next()) {
				cantidadListas = rs.getInt("cantidad");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cx.desconectar();
		}

		return cantidadListas;
	}

	public listacompra obtenerListaCompraPorId(int idListaCompra) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		listacompra listaCompra = null;

		try {
			ps = cx.conectar().prepareStatement("SELECT * FROM ListaCompra WHERE id_ListaCompra = ?");
			ps.setInt(1, idListaCompra);
			rs = ps.executeQuery();

			if (rs.next()) {
				listaCompra = new listacompra();
				listaCompra.setId_ListaCompra(rs.getInt("id_ListaCompra"));
				listaCompra.setId_Usuario(rs.getInt("id_Usuario"));
				listaCompra.setNombrelista(rs.getString("nombreLista"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cx.desconectar();
		}

		return listaCompra;
	}

	public List<listacompra> obtenerListasCompraPorUsuario(int idUsuario) {
		List<listacompra> listasCompra = new ArrayList<>();
		try (PreparedStatement ps = cx.conectar().prepareStatement("SELECT * FROM ListaCompra WHERE id_Usuario = ?")) {
			ps.setInt(1, idUsuario);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					listacompra listaCompra = new listacompra();
					listaCompra.setId_ListaCompra(rs.getInt("id_ListaCompra"));
					listaCompra.setId_Usuario(rs.getInt("id_Usuario"));
					listaCompra.setNombrelista(rs.getString("nombreLista"));
					listasCompra.add(listaCompra);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listasCompra;
	}

	public int obtenerIdUsuarioPorNombre(String nombreUsuario) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int idUsuario = -1;
		try {
			String query = "SELECT id_Usuario FROM Usuarios WHERE nombre = ?";
			ps = cx.conectar().prepareStatement(query);
			ps.setString(1, nombreUsuario);
			rs = ps.executeQuery();

			if (rs.next()) {
				idUsuario = rs.getInt("id_Usuario");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				cx.desconectar();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return idUsuario;
	}

	public int obtenerIdListaPorNombreYUsuario(String nombreLista, int idUsuario) {
		try (PreparedStatement ps = cx.conectar()
				.prepareStatement("SELECT id_ListaCompra FROM ListaCompra WHERE nombrelista=? AND id_Usuario=?")) {
			ps.setString(1, nombreLista);
			ps.setInt(2, idUsuario);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("id_ListaCompra");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int obtenerIdListaCompraPorNombreYUsuario(String nombreLista, String idUsuario) {
		int idListaCompra = -1;
		try (PreparedStatement ps = cx.conectar()
				.prepareStatement("SELECT LC.id_ListaCompra " + "FROM ListaCompra LC "
						+ "INNER JOIN Usuarios U ON LC.id_Usuario = U.id_Usuario "
						+ "WHERE LC.nombrelista = ? AND U.nombre = ?")) {
			ps.setString(1, nombreLista);
			ps.setString(2, idUsuario);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				idListaCompra = rs.getInt("id_ListaCompra");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idListaCompra;
	}
}