package Metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BDconexion.conexion;
import GetaSet.ReporteCompra;

public class CrudReporteCompra {

	private conexion cx;

	public CrudReporteCompra() {
		cx = new conexion();
	}

	public boolean insertarReporteCompra(ReporteCompra reporteCompra) {
		try (PreparedStatement ps = cx.conectar().prepareStatement(
				"INSERT INTO ReporteCompra (id_Usuario, id_ListaCompra, fecha, hora, precioTotal, puntos_Utilizados) VALUES (?, ?, ?, ?, ?, ?)")) {
			ps.setInt(1, reporteCompra.getId_Usuario());
			ps.setInt(2, reporteCompra.getid_ListaCompra());
			ps.setString(3, reporteCompra.getFecha());
			ps.setString(4, reporteCompra.getHora());
			ps.setDouble(5, reporteCompra.getPrecioTotal());
			ps.setInt(6, reporteCompra.getPuntos_Utilizados());

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

	public List<ReporteCompra> obtenerTodosReportesCompra() {
		List<ReporteCompra> reportesCompra = new ArrayList<>();
		try (PreparedStatement ps = cx.conectar().prepareStatement("SELECT * FROM ReporteCompra");
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				ReporteCompra reporteCompra = new ReporteCompra();
				reporteCompra.setId_ReporteCompra(rs.getInt("id_ReporteCompra"));
				reporteCompra.setId_Usuario(rs.getInt("id_Usuario"));
				reporteCompra.setid_ListaCompra((rs.getInt("id_ListaCompra")));
				reporteCompra.setFecha(rs.getString("fecha"));
				reporteCompra.setHora(rs.getString("hora"));
				reporteCompra.setPrecioTotal(rs.getDouble("precioTotal"));
				reporteCompra.setPuntos_Utilizados(rs.getInt("puntos_Utilizados"));
				reportesCompra.add(reporteCompra);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reportesCompra;
	}

	public boolean actualizarReporteCompra(ReporteCompra reporteCompra) {
		try (PreparedStatement ps = cx.conectar().prepareStatement(
				"UPDATE ReporteCompra SET id_Usuario=?, id_ListaCompra=?, fecha=?, hora=?, precioTotal=?, puntos_Utilizados=? WHERE id_ReporteCompra=?")) {
			ps.setInt(1, reporteCompra.getId_Usuario());
			ps.setInt(2, reporteCompra.getid_ListaCompra());
			ps.setString(3, reporteCompra.getFecha());
			ps.setString(4, reporteCompra.getHora());
			ps.setDouble(5, reporteCompra.getPrecioTotal());
			ps.setInt(6, reporteCompra.getPuntos_Utilizados());
			ps.setInt(7, reporteCompra.getId_ReporteCompra());

			ps.executeUpdate();
			cx.desconectar();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean eliminarReporteCompra(int id_ReporteCompra) {
		try (PreparedStatement ps = cx.conectar()
				.prepareStatement("DELETE FROM ReporteCompra WHERE id_ReporteCompra=?")) {
			ps.setInt(1, id_ReporteCompra);
			ps.executeUpdate();
			cx.desconectar();
			return true;
		} catch (SQLException e) {
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

}