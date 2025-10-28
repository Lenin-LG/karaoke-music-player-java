package Metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BDconexion.conexion;
import GetaSet.Productos;

public class CrudProductos {
	conexion cx;

	public CrudProductos() {
		cx = new conexion();
	}

	public boolean insertarProducto(Productos producto) {
		try (PreparedStatement ps = cx.conectar()
				.prepareStatement("INSERT INTO Productos (nombre, prostock, precio) VALUES (?, ?, ?)")) {
			ps.setString(1, producto.getNombre());
			ps.setInt(2, producto.getStock());
			ps.setDouble(3, producto.getPrecio());

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

	public boolean eliminarProducto(int idProducto) {
		try (PreparedStatement ps = cx.conectar().prepareStatement("DELETE FROM Productos WHERE id_Producto=?")) {
			ps.setInt(1, idProducto);
			ps.executeUpdate();
			cx.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean editarProducto(Productos producto) {
		try (PreparedStatement ps = cx.conectar()
				.prepareStatement("UPDATE Productos SET nombre=?, prostock=?, precio=? WHERE id_Producto=?")) {
			ps.setString(1, producto.getNombre());
			ps.setInt(2, producto.getStock());
			ps.setDouble(3, producto.getPrecio());
			ps.setInt(4, producto.getId_Producto());

			ps.executeUpdate();
			cx.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Productos> consultaProductos() {
		List<Productos> productos = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = cx.conectar().prepareStatement("SELECT * FROM Productos");
			rs = ps.executeQuery();

			while (rs.next()) {
				Productos producto = new Productos();
				producto.setId_Producto(rs.getInt("id_Producto"));
				producto.setNombre(rs.getString("nombre"));
				producto.setStock(rs.getInt("prostock"));
				producto.setPrecio(rs.getDouble("precio"));
				productos.add(producto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cx.desconectar();
		}
		return productos;
	}

	public double obtenerPrecioProducto(String nombreProducto) {
		try (PreparedStatement ps = cx.conectar().prepareStatement("SELECT precio FROM Productos WHERE nombre = ?")) {
			ps.setString(1, nombreProducto);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getDouble("precio");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cx.desconectar();
		}

		return 0;
	}
}
