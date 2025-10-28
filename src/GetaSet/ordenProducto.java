package GetaSet;

public class ordenProducto {
	private int id_OrdenProducto;
	private int id_Usuario;
	private int id_ListaCompra;
	private int id_Producto;
	private int cantidad;
	private int puntos_utilizados;

	public ordenProducto() {
	}

	public int getId_OrdenProducto() {
		return id_OrdenProducto;
	}

	public void setId_OrdenProducto(int id_OrdenProducto) {
		this.id_OrdenProducto = id_OrdenProducto;
	}

	public int getId_Usuario() {
		return id_Usuario;
	}

	public void setId_Usuario(int id_Usuario) {
		this.id_Usuario = id_Usuario;
	}

	public int getId_ListaCompra() {
		return id_ListaCompra;
	}

	public void setId_ListaCompra(int id_ListaCompra) {
		this.id_ListaCompra = id_ListaCompra;
	}

	public int getId_Producto() {
		return id_Producto;
	}

	public void setId_Producto(int id_Producto) {
		this.id_Producto = id_Producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getPuntos_utilizados() {
		return puntos_utilizados;
	}

	public void setPuntos_utilizados(int puntos_utilizados) {
		this.puntos_utilizados = puntos_utilizados;
	}
}