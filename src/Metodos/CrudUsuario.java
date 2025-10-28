package Metodos;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import BDconexion.conexion;
import GetaSet.usuario;

public class CrudUsuario {

	conexion cx;

	public CrudUsuario() {
		cx = new conexion();
	}

	public boolean insertarUsuario(usuario user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO Usuarios VALUES(null,?,?,?,?)");
			ps.setString(1, user.getUser());
			ps.setString(2, convertirSHA256(user.getPacssword()));
			ps.setString(3, user.getNombre());
			ps.setInt(4, user.getPuntos());
			ps.executeUpdate();
			cx.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean eliminarUsuario(int id) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM Usuarios WHERE id_Usuario=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			cx.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean editarUsuario(usuario user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar()
					.prepareStatement("UPDATE Usuarios SET nombre=?,contrasena=?,correo=?,puntos=? WHERE id_Usuario=?");
			ps.setString(1, user.getUser());
			ps.setString(2, convertirSHA256(user.getPacssword()));
			ps.setString(3, user.getNombre());
			ps.setInt(4, user.getPuntos());
			ps.setInt(5, user.getId());
			ps.executeUpdate();
			cx.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public String convertirSHA256(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}

		byte[] hash = md.digest(password.getBytes());
		StringBuffer sb = new StringBuffer();

		for (byte b : hash) {
			sb.append(String.format("%02x", b));
		}

		return sb.toString();
	}

	public ArrayList<usuario> consultaUsuarios() {

		ArrayList<usuario> lista = new ArrayList<usuario>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT *FROM Usuarios");
			rs = ps.executeQuery();
			while (rs.next()) {
				usuario user = new usuario();
				user.setId(rs.getInt("id_Usuario"));
				user.setUser(rs.getString("nombre"));
				user.setPacssword(rs.getString("contrasena"));
				user.setNombre(rs.getString("correo"));
				user.setPuntos(rs.getInt("puntos"));
				lista.add(user);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;

	}

	public int obtenerIdUsuarioPorNombre(String nombreUsuario) {
		int idUsuario = -1;
		PreparedStatement ps = null;
		ResultSet rs = null;

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

}
