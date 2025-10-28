package GetaSet;

public class Maquina {

	private int id_Maquina;
	private int id_Producto;
	private int puntos;
	private String img_url;

	public int getId_Maquina() {
		return id_Maquina;
	}

	public void setId_Maquina(int id_Maquina) {
		this.id_Maquina = id_Maquina;
	}

	public int getId_Producto() {
		return id_Producto;
	}

	public void setId_Producto(int id_Producto) {
		this.id_Producto = id_Producto;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

}
