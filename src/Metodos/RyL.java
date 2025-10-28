package Metodos;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BDconexion.conexion;
import GetaSet.usuario;

public class RyL {

	conexion cx;

	public RyL() {
		cx = new conexion();
	}

	public boolean insertarUsuario(usuario user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO Usuarios VALUES(null,?,?,?,0)");
			ps.setString(1, user.getUser());
			ps.setString(2, convertirSHA256(user.getPassword()));
			ps.setString(3, user.getEmail());
			ps.executeUpdate();
			cx.desconectar();
			return true;
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}

	}

	public String convertirSHA256(char[] password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			byte[] passwordBytes = new byte[password.length];
			for (int i = 0; i < password.length; i++) {
				passwordBytes[i] = (byte) password[i];
			}

			byte[] hashBytes = md.digest(passwordBytes);
			StringBuilder sb = new StringBuilder();

			for (byte b : hashBytes) {
				sb.append(String.format("%02x", b));
			}

			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean existeUsuario(String username) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT COUNT(*) FROM Usuarios WHERE nombre = ?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				return count > 0;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			cx.desconectar();
		}
	}

	public boolean existeCorreoElectronico(String correo) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT COUNT(*) FROM Usuarios WHERE correo = ?");
			ps.setString(1, correo);
			rs = ps.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				return count > 0;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			cx.desconectar();
		}
	}

	public usuario obtenerUsuarioPorCorreo(String correo) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		usuario user = null;

		try {
			ps = cx.conectar().prepareStatement("SELECT * FROM Usuarios WHERE correo = ?");
			ps.setString(1, correo);
			rs = ps.executeQuery();

			if (rs.next()) {
				user = new usuario();
				user.setId(rs.getInt("id_Usuario"));
				user.setUser(rs.getString("nombre"));
				char[] passwordChars = rs.getString("contrasena").toCharArray();
				user.setPassword(passwordChars);
				user.setEmail(rs.getString("correo"));
				user.setPuntos(rs.getInt("puntos"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cx.desconectar();
		}

		return user;
	}

	public String obtenerRolUsuario(int idUsuario) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String rol = "User";

		try {
			ps = cx.conectar().prepareStatement("SELECT cargo FROM Administrador WHERE id_Usuario = ?");
			ps.setInt(1, idUsuario);
			rs = ps.executeQuery();

			if (rs.next()) {
				rol = rs.getString("cargo");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cx.desconectar();
		}

		return rol;
	}
}
