package Metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BDconexion.conexion;
import GetaSet.Maquina;

public class CrudMaquina {
	conexion cx;

	public CrudMaquina() {
		cx = new conexion();
	}

	public boolean insertarMaquina(Maquina maquina) {
		try (PreparedStatement ps = cx.conectar()
				.prepareStatement("INSERT INTO Maquina (id_Producto, puntos, img_url) VALUES (?, ?, ?)")) {
			ps.setInt(1, maquina.getId_Producto());
			ps.setInt(2, maquina.getPuntos());
			ps.setString(3, maquina.getImg_url());

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

	public boolean eliminarMaquina(int idMaquina) {
		try (PreparedStatement ps = cx.conectar().prepareStatement("DELETE FROM Maquina WHERE id_Maquina=?")) {
			ps.setInt(1, idMaquina);
			ps.executeUpdate();
			cx.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean editarMaquina(Maquina maquina) {
		try (PreparedStatement ps = cx.conectar()
				.prepareStatement("UPDATE Maquina SET id_Producto=?, puntos=?, img_url=? WHERE id_Maquina=?")) {
			ps.setInt(1, maquina.getId_Producto());
			ps.setInt(2, maquina.getPuntos());
			ps.setString(3, maquina.getImg_url());
			ps.setInt(4, maquina.getId_Maquina());

			ps.executeUpdate();
			cx.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Maquina> consultaMaquinas() {
		List<Maquina> maquinas = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = cx.conectar().prepareStatement("SELECT * FROM Maquina");
			rs = ps.executeQuery();

			while (rs.next()) {
				Maquina maquina = new Maquina();
				maquina.setId_Maquina(rs.getInt("id_Maquina"));
				maquina.setId_Producto(rs.getInt("id_Producto"));
				maquina.setPuntos(rs.getInt("puntos"));
				maquina.setImg_url((rs.getString("img_url")));
				;
				maquinas.add(maquina);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cx.desconectar();
		}
		return maquinas;
	}

	public int obtenerIdProductoPorNombre(String nombreProducto) {
		try (PreparedStatement ps = cx.conectar()
				.prepareStatement("SELECT id_Producto FROM Productos WHERE nombre = ?")) {
			ps.setString(1, nombreProducto);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt("id_Producto");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cx.desconectar();
		}

		return -1;
	}

	public boolean existeMaquinaConProducto(int idProducto) {
		try (PreparedStatement ps = cx.conectar()
				.prepareStatement("SELECT COUNT(*) FROM Maquina WHERE id_Producto = ?")) {
			ps.setInt(1, idProducto);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int count = rs.getInt(1);
				return count > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cx.desconectar();
		}

		return false;
	}

	public String obtenerNombreProductoPorId(int idProducto) {
		try (PreparedStatement ps = cx.conectar()
				.prepareStatement("SELECT nombre FROM Productos WHERE id_Producto = ?")) {
			ps.setInt(1, idProducto);

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

	public int obtenerPuntosPorProducto(int idProducto) {
		try (PreparedStatement ps = cx.conectar()
				.prepareStatement("SELECT puntos FROM Maquina WHERE id_Producto = ?")) {
			ps.setInt(1, idProducto);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt("puntos");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cx.desconectar();
		}

		return -1;
	}

	public int obtenerPuntosPorProducto(String nombreProducto) {
		int puntos = -1;
		try (PreparedStatement ps = cx.conectar().prepareStatement(
				"SELECT puntos FROM Maquina WHERE id_Producto = (SELECT id_Producto FROM Productos WHERE nombre = ?)")) {
			ps.setString(1, nombreProducto);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				puntos = rs.getInt("puntos");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return puntos;
	}
}