package Metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import BDconexion.conexion;
import GetaSet.administrador;

public class CrudAdministrador {
	conexion cx;

	public CrudAdministrador() {
		cx = new conexion();
	}

	public boolean insertarAdministrador(administrador administrador) {
		try (PreparedStatement ps = cx.conectar()
				.prepareStatement("INSERT INTO Administrador(id_Usuario,cargo)VALUES(?,?)")) {
			ps.setInt(1, administrador.getId_Usuario());
			ps.setString(2, administrador.getCargo());
			int filasAfectadas = ps.executeUpdate();
			if (filasAfectadas > 0) {
				cx.desconectar();
				return true;
			} else {
				cx.desconectar();
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}

	public List<administrador> obtenerTodosAdministradores() {
		List<administrador> administradores = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			ps = cx.conectar().prepareStatement("SELECT *FROM Administrador");
			rs = ps.executeQuery();
			while (rs.next()) {
				administrador administrador = new administrador();
				administrador.setId_Admin(rs.getInt("id_Admin"));
				administrador.setId_Usuario(rs.getInt("id_Usuario"));
				administrador.setCargo(rs.getString("cargo"));
				administradores.add(administrador);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return administradores;
	}

	public boolean actualizarAdministrador(administrador administrador) {
		try (PreparedStatement ps = cx.conectar()
				.prepareStatement("UPDATE Administrador SET id_Usuario=?,cargo=? WHERE id_Admin=?")) {
			ps.setInt(1, administrador.getId_Usuario());
			ps.setString(2, administrador.getCargo());
			ps.setInt(3, administrador.getId_Admin());
			ps.executeUpdate();
			cx.desconectar();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean eliminarAdministrador(int idAdministrador) {
		PreparedStatement ps = null;
		PreparedStatement psActualizarIds = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM Administrador WHERE id_Admin=?");
			ps.setInt(1, idAdministrador);
			ps.executeUpdate();
			psActualizarIds = cx.conectar()
					.prepareStatement("UPDATE Administrador SET id_Admin=id_Admin-1 WHERE id_Admin>?");
			psActualizarIds.setInt(1, idAdministrador);
			psActualizarIds.executeUpdate();
			cx.desconectar();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e2) {
					e2.printStackTrace();

				}
			}
			if (psActualizarIds != null) {
				try {
					psActualizarIds.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	public int obtenerIdUsuarioPorNombre(String nombreUsuario) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int idUsuario = -1;

		try {
			ps = cx.conectar().prepareStatement("SELECT id_Usuario FROM Usuarios WHERE nombre = ?");
			ps.setString(1, nombreUsuario);
			rs = ps.executeQuery();

			if (rs.next()) {
				idUsuario = rs.getInt("id_Usuario");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			cx.desconectar();
		}

		return idUsuario;
	}

	public String obtenerNombreUsuarioPorId(int idUsuario) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String nombreUsuario = null;

		try {
			ps = cx.conectar().prepareStatement("SELECT nombre FROM Usuarios WHERE id_Usuario = ?");
			ps.setInt(1, idUsuario);
			rs = ps.executeQuery();

			if (rs.next()) {
				nombreUsuario = rs.getString("nombre");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			cx.desconectar();
		}

		return nombreUsuario;
	}

	public boolean existeAdministradorParaUsuario(int idUsuario) {
		try (PreparedStatement ps = cx.conectar()
				.prepareStatement("SELECT COUNT(*) FROM Administrador WHERE id_Usuario = ?")) {
			ps.setInt(1, idUsuario);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				return count > 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean usuarioTieneCargo(int idUsuario) {
		try (PreparedStatement ps = cx.conectar()
				.prepareStatement("SELECT COUNT(*) FROM Administrador WHERE id_Usuario = ?")) {
			ps.setInt(1, idUsuario);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				return count > 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
