package Metodos;

import java.awt.Desktop;
import javazoom.jlgui.basicplayer.BasicPlayer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

/**
 *
 * @author erikj
 */
public class Reproductor implements BasicPlayerListener {
	private BasicPlayer player;
	private ArrayList<String> Direcciones;
	private ArrayList<String> Nombre;
	private int numeroDePista = 0;
	private double bytesLength;
	private JSlider avanzeCancion;
	private int Siguiente = 0;

	public Reproductor(List<String> arreglo, JSlider barra) {
		player = new BasicPlayer();
		player.addBasicPlayerListener(this);
		this.Direcciones = new ArrayList<>(arreglo);
		this.avanzeCancion = barra;
		this.Nombre = ObtenerNombresdeCanciones(Direcciones);
	}

	public void AbrirFichero() throws Exception {
		try {
			File file = new File(Direcciones.get(numeroDePista).replace("\\", "/"));
			System.out.println("Ruta del archivo: " + file.getAbsolutePath());
			if (file.exists()) {
				player.open(file);
			} else {
				System.out.println("El archivo no existe en la ruta especificada.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Play() throws Exception {
		player.play();
	}

	public void Pausa() throws Exception {
		player.pause();
	}

	public void Continuar() throws Exception {
		player.resume();
	}

	public void Stop() throws Exception {
		player.stop();
	}

	public void SiguienteCancion(int numero) {
		if (numero == Siguiente) {
			if (numeroDePista == Direcciones.size() - 1) {

			} else {
				try {
					numeroDePista += 1;
					AbrirFichero();
					Play();
				} catch (Exception ex) {
					Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
				}

			}
		}
	}

	public int CancionAnteriorOsiguiente(int numero) {
		int respuesta = 0;
		if (numero == 1) {
			// Final de la lista
			if (numeroDePista == Direcciones.size() - 1) {
				respuesta = 1;
			} else {
				try {
					Stop();
					numeroDePista += 1;
					respuesta = 0;
				} catch (Exception ex) {
					Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
				}

			}
		} else {
			// Inicio de la lista
			if (numero == 0) {
				if (numeroDePista == 0) {
					respuesta = 1;
				} else {
					try {
						System.out.println("N Pista_____ " + numeroDePista);
						respuesta = 0;
						Stop();
						numeroDePista -= 1;
						// Play();
					} catch (Exception ex) {
						Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
					}
				}

			}

		}
		return respuesta;
	}

	public void Repetir() {
		try {
			System.out.println("Repetir numero " + numeroDePista);
			Stop();
			AbrirFichero();
			Play();
		} catch (Exception ex) {
			Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public ArrayList<String> ObtenerNombresdeCanciones(ArrayList<String> arreglo) {
		ArrayList<String> NombreCompleto = new ArrayList<>();
		for (String ruta : arreglo) {
			File nombres = new File(ruta);
			NombreCompleto.add(nombres.getName());
		}
		return NombreCompleto;
	}

	@Override
	public void opened(Object o, Map map) {
		if (map.containsKey("audio.length.bytes")) {
			bytesLength = Double.parseDouble(map.get("audio.length.bytes").toString());
			System.out.println("Final " + bytesLength);
			int ProgresoMaximo = (int) (bytesLength);
			Siguiente = ProgresoMaximo;
			avanzeCancion.setMinimum(0);
			avanzeCancion.setMaximum(ProgresoMaximo);
		}

	}

	@Override
	public void progress(int i, long l, byte[] bytes, Map map) {
		float progressUpdate = (float) (i * 1.0f / bytesLength * 1.0f);
		int progressNow = (int) (bytesLength * progressUpdate);
		avanzeCancion.setValue(progressNow);
		SiguienteCancion(progressNow);
	}

	public void setVolumen(int volumen) {
		try {
			float volumenFloat = volumen / 100.0f;
			player.setGain(volumenFloat);
		} catch (BasicPlayerException ex) {
			Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void stateUpdated(BasicPlayerEvent bpe) {

	}

	@Override
	public void setController(BasicController bc) {

	}
}