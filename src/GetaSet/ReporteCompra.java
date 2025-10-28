package GetaSet;

public class ReporteCompra {

	private int id_ReporteCompra;
	private int id_Usuario;
	private int id_ListaCompra;
	private String fecha;
	private String hora;
	private double precioTotal;
	private int puntos_Utilizados;

	public int getId_ReporteCompra() {
		return id_ReporteCompra;
	}

	public void setId_ReporteCompra(int id_ReporteCompra) {
		this.id_ReporteCompra = id_ReporteCompra;
	}

	public int getId_Usuario() {
		return id_Usuario;
	}

	public void setId_Usuario(int id_Usuario) {
		this.id_Usuario = id_Usuario;
	}

	public int getid_ListaCompra() {
		return id_ListaCompra;
	}

	public void setid_ListaCompra(int id_ListaCompra) {
		this.id_ListaCompra = id_ListaCompra;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public int getPuntos_Utilizados() {
		return puntos_Utilizados;
	}

	public void setPuntos_Utilizados(int puntos_Utilizados) {
		this.puntos_Utilizados = puntos_Utilizados;
	}

}