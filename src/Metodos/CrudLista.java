package Metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BDconexion.conexion;
import GetaSet.lista;

public class CrudLista {
	conexion cx;

	public CrudLista() {
		cx = new conexion();
	}

	public boolean insertarLista(lista lista) {
		try (PreparedStatement ps = cx.conectar()
				.prepareStatement("INSERT INTO Lista (nombreLista, id_Usuario) VALUES (?, ?)")) {
			ps.setString(1, lista.getNombreLista());
			ps.setInt(2, lista.getIdUsuario());

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

	public List<lista> obtenerTodasListas() {
		List<lista> listas = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT * FROM Lista");
			rs = ps.executeQuery();

			while (rs.next()) {
				lista lista = new lista();
				lista.setIdLista(rs.getInt("id_Lista"));
				lista.setNombreLista(rs.getString("nombreLista"));
				lista.setIdUsuario(rs.getInt("id_Usuario"));
				listas.add(lista);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cx.desconectar();
		}
		return listas;
	}

	public boolean actualizarLista(lista lista) {
		PreparedStatement ps = null;

		try {
			ps = cx.conectar().prepareStatement("UPDATE Lista SET nombreLista=?, id_Usuario=? WHERE id_Lista=?");
			ps.setString(1, lista.getNombreLista());
			ps.setInt(2, lista.getIdUsuario());
			ps.setInt(3, lista.getIdLista());
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

	public boolean eliminarLista(int idLista) {
		PreparedStatement ps = null;

		try {
			ps = cx.conectar().prepareStatement("DELETE FROM Lista WHERE id_Lista=?");
			ps.setInt(1, idLista);
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

	public int obtenerIdUsuarioPorNombre(String nombreUsuario) {
		int idUsuario = -1;

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = cx.conectar().prepareStatement("SELECT id_Usuario FROM Usuarios WHERE nombre = ?");
			ps.setString(1, nombreUsuario);
			rs = ps.executeQuery();

			if (rs.next()) {
				idUsuario = rs.getInt("id_Usuario");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			cx.desconectar();
		}

		return idUsuario;
	}

	public String obtenerNombreUsuarioPorId(int idUsuario) {
		String nombreUsuario = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			cx.desconectar();
		}

		return nombreUsuario;
	}
}
