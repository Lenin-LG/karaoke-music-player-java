package Metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BDconexion.conexion;
import GetaSet.ordenProducto;

public class CrudOrdenProducto {

	private conexion cx;

	public CrudOrdenProducto() {
		cx = new conexion();
	}

	public boolean insertarOrdenProducto(ordenProducto ordenProducto) {
		try (PreparedStatement ps = cx.conectar().prepareStatement(
				"INSERT INTO OrdenProducto(id_Usuario, id_ListaCompra, id_Producto, cantidad, puntos_utilizados) VALUES (?, ?, ?, ?, ?)")) {
			ps.setInt(1, ordenProducto.getId_Usuario());
			ps.setInt(2, ordenProducto.getId_ListaCompra());
			ps.setInt(3, ordenProducto.getId_Producto());
			ps.setInt(4, ordenProducto.getCantidad());
			ps.setInt(5, ordenProducto.getPuntos_utilizados());

			int filasAfectadas = ps.executeUpdate();

			if (filasAfectadas > 0) {
				cx.desconectar();
				return true;
			} else {
				cx.desconectar();
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<ordenProducto> obtenerTodosOrdenProductos() {
		List<ordenProducto> ordenProductos = new ArrayList<>();
		try (PreparedStatement ps = cx.conectar().prepareStatement("SELECT * FROM OrdenProducto");
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				ordenProducto ordenProducto = new ordenProducto();
				ordenProducto.setId_OrdenProducto(rs.getInt("id_OrdenProducto"));
				ordenProducto.setId_Usuario(rs.getInt("id_Usuario"));
				ordenProducto.setId_ListaCompra(rs.getInt("id_ListaCompra"));
				ordenProducto.setId_Producto(rs.getInt("id_Producto"));
				ordenProducto.setCantidad(rs.getInt("cantidad"));
				ordenProducto.setPuntos_utilizados(rs.getInt("puntos_utilizados"));
				ordenProductos.add(ordenProducto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordenProductos;
	}

	public boolean actualizarOrdenProducto(ordenProducto ordenProducto) {
		try (PreparedStatement ps = cx.conectar().prepareStatement(
				"UPDATE OrdenProducto SET id_Usuario=?, id_ListaCompra=?, id_Producto=?, cantidad=?, puntos_utilizados=? WHERE id_OrdenProducto=?")) {
			ps.setInt(1, ordenProducto.getId_Usuario());
			ps.setInt(2, ordenProducto.getId_ListaCompra());
			ps.setInt(3, ordenProducto.getId_Producto());
			ps.setInt(4, ordenProducto.getCantidad());
			ps.setInt(5, ordenProducto.getPuntos_utilizados());
			ps.setInt(6, ordenProducto.getId_OrdenProducto());

			ps.executeUpdate();
			cx.desconectar();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean eliminarOrdenProducto(int idOrdenProducto) {
		try (PreparedStatement ps = cx.conectar()
				.prepareStatement("DELETE FROM OrdenProducto WHERE id_OrdenProducto=?")) {
			ps.setInt(1, idOrdenProducto);
			ps.executeUpdate();
			cx.desconectar();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String obtenerNombreUsuarioPorId(int idUsuario) {
		String nombreUsuario = null;
		try (PreparedStatement ps = cx.conectar().prepareStatement("SELECT nombre FROM Usuarios WHERE id_Usuario=?")) {
			ps.setInt(1, idUsuario);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				nombreUsuario = rs.getString("nombre");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nombreUsuario;
	}

	public List<ordenProducto> obtenerOrdenProductosPorUsuarioYLista(int idUsuario, int idListaCompra) {
		List<ordenProducto> ordenProductos = new ArrayList<>();
		try (PreparedStatement ps = cx.conectar()
				.prepareStatement("SELECT * FROM OrdenProducto WHERE id_Usuario = ? AND id_ListaCompra = ?")) {
			ps.setInt(1, idUsuario);
			ps.setInt(2, idListaCompra);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ordenProducto ordenProducto = new ordenProducto();
				ordenProducto.setId_OrdenProducto(rs.getInt("id_OrdenProducto"));
				ordenProducto.setId_Usuario(rs.getInt("id_Usuario"));
				ordenProducto.setId_ListaCompra(rs.getInt("id_ListaCompra"));
				ordenProducto.setId_Producto(rs.getInt("id_Producto"));
				ordenProducto.setCantidad(rs.getInt("cantidad"));
				ordenProducto.setPuntos_utilizados(rs.getInt("puntos_utilizados"));
				ordenProductos.add(ordenProducto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ordenProductos;
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
}
