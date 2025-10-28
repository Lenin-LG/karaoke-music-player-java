package Paneles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.LayoutStyle;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import BDconexion.conexion;
import GetaSet.lista;
import GetaSet.usuario;
import Metodos.CrudLista;
import Metodos.Reproductor;
import Metodos.RyL;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import Perzonalizado.Slider;

public class Bottom extends JPanel {
	Perzonalizado.Slider ProgresoCancion = new Perzonalizado.Slider();
	private int PermisoParaAñadir = 0;
	CrudLista dao = new CrudLista();
	private List<String> Rutas;
	private Reproductor reproductor;
	private JButton btnListadereproduccion;
	private int conta;
	private int cambio = 0;
	usuario user = new usuario();

	/**
	 * Create the panel.
	 */

	public Bottom() {
		Rutas = new ArrayList<>();
		Rutas = new ArrayList<>();
	    reproductor = new Reproductor(new ArrayList<>(), ProgresoCancion);
	    
		setOpaque(false);
		setBackground(new Color(68, 68, 68));

		JLabel lblNewLabel = new JLabel("0:00");
		lblNewLabel.setForeground(Color.WHITE);

		JLabel lblNewLabel_1 = new JLabel("3:50");
		lblNewLabel_1.setForeground(Color.WHITE);

		JButton btnSonido = new JButton("");
		btnSonido.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSonido.setIcon(new ImageIcon(Bottom.class.getResource("/IMG/speaker.png")));
		btnSonido.setFocusPainted(false);
		btnSonido.setContentAreaFilled(false);
		btnSonido.setBorderPainted(false);
		btnSonido.setBorder(null);

		JButton btnNewButton_1_1_1 = new JButton("");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reproductor.Repetir();
			}
		});
		btnNewButton_1_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1_1_1.setIcon(new ImageIcon(Bottom.class.getResource("/IMG/repeat.png")));
		btnNewButton_1_1_1.setFocusPainted(false);
		btnNewButton_1_1_1.setContentAreaFilled(false);
		btnNewButton_1_1_1.setBorderPainted(false);
		btnNewButton_1_1_1.setBorder(null);

		Slider Slider_1 = new Slider();
		Slider_1.addChangeListener((ChangeListener) new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (reproductor != null) {
				int volumen = Slider_1.getValue();
				reproductor.setVolumen(volumen);
				} else {
				    JOptionPane.showMessageDialog(null, "Primero selecciona una lista de reproducción.");
				}
			}
		});

		JButton btnAtras = new JButton("");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Validador = reproductor.CancionAnteriorOsiguiente(0);
				if (Validador == 1) {
				} else {
					try {
						// reproductor.Stop();
						reproductor.AbrirFichero();
						reproductor.Play();
					} catch (Exception ex) {
						Logger.getLogger(Bottom.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		});
		btnAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAtras.setIcon(new ImageIcon(Bottom.class.getResource("/IMG/back.png")));
		btnAtras.setFocusPainted(false);
		btnAtras.setContentAreaFilled(false);
		btnAtras.setBorderPainted(false);
		btnAtras.setBorder(null);

		JButton btnPlaypause = new JButton("");
		btnPlaypause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (conta == 0) {
					try {

						reproductor.AbrirFichero();
						reproductor.Play();
					} catch (Exception ex) {
						Logger.getLogger(Bottom.class.getName()).log(Level.SEVERE, null, ex);
					}
					conta = 1;
					return;
				} else if (conta == 1) {
					try {
						if (cambio == 0) {
							// Pausa
							reproductor.Pausa();
							cambio += 1;
						} else {
							reproductor.Continuar();
							cambio -= 1;
						}
					} catch (Exception ex) {
						Logger.getLogger(Bottom.class.getName()).log(Level.SEVERE, null, ex);
					}

				}

			}
		});
		btnPlaypause.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPlaypause.setIcon(new ImageIcon(Bottom.class.getResource("/IMG/playing.png")));
		btnPlaypause.setFocusPainted(false);
		btnPlaypause.setContentAreaFilled(false);
		btnPlaypause.setBorderPainted(false);
		btnPlaypause.setBorder(null);

		JButton btnSiguiente = new JButton("");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int validador = reproductor.CancionAnteriorOsiguiente(1);
					if (validador == 1) {
					} else {
						reproductor.AbrirFichero();
						reproductor.Play();
					}
				} catch (Exception ex) {
					Logger.getLogger(Bottom.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
		btnSiguiente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSiguiente.setIcon(new ImageIcon(Bottom.class.getResource("/IMG/next.png")));
		btnSiguiente.setFocusPainted(false);
		btnSiguiente.setContentAreaFilled(false);
		btnSiguiente.setBorderPainted(false);
		btnSiguiente.setBorder(null);

		btnListadereproduccion = new JButton("");
		btnListadereproduccion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnListadereproduccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ListadeReproduccionActionPerformed(e);
			}
		});
		btnListadereproduccion.setIcon(new ImageIcon(Bottom.class.getResource("/IMG/play.png")));
		btnListadereproduccion.setFocusPainted(false);
		btnListadereproduccion.setContentAreaFilled(false);
		btnListadereproduccion.setBorderPainted(false);
		btnListadereproduccion.setBorder(null);

		JButton btnSonido_1 = new JButton("");
		btnSonido_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					// Stop
					reproductor.Stop();
					PermisoParaAñadir = 0;
					System.out.println("Var: " + PermisoParaAñadir);
				} catch (Exception ex) {
					Logger.getLogger(Bottom.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
		btnSonido_1.setIcon(new ImageIcon(Bottom.class.getResource("/IMG/boton-detener.png")));
		btnSonido_1.setFocusPainted(false);
		btnSonido_1.setContentAreaFilled(false);
		btnSonido_1.setBorderPainted(false);
		btnSonido_1.setBorder(null);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addComponent(btnAtras, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(btnPlaypause, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(btnSiguiente, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(btnListadereproduccion, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewLabel)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(ProgresoCancion, GroupLayout.PREFERRED_SIZE, 606, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewLabel_1)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(btnSonido, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(Slider_1, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(btnNewButton_1_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE).addGap(4)
				.addComponent(btnSonido_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAtras, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPlaypause, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSiguiente, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnListadereproduccion, GroupLayout.PREFERRED_SIZE, 50,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup().addGap(19)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblNewLabel_1)
												.addComponent(ProgresoCancion, GroupLayout.PREFERRED_SIZE, 18,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel)))
								.addComponent(btnSonido, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(Slider_1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_1_1_1, GroupLayout.PREFERRED_SIZE, 50,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSonido_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(235, Short.MAX_VALUE)));
		setLayout(groupLayout);
	}

	private void ListadeReproduccionActionPerformed(java.awt.event.ActionEvent evt) {
		System.out.println("Var: " + PermisoParaAñadir);
		if (PermisoParaAñadir == 0) {
			int userId = 23;
			String query = "SELECT id_Lista, nombrelista FROM Lista WHERE id_Usuario = ?";

			try (PreparedStatement preparedStatement = new conexion().conectar().prepareStatement(query)) {
				preparedStatement.setInt(1, userId);

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					List<String> listas = new ArrayList<>();

					while (resultSet.next()) {
						listas.add(resultSet.getString("nombrelista"));
					}

					String selectedLista = MostrarDialogoDeSeleccion(listas);

					ObtenerCancionesDeListaSeleccionada(selectedLista);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			reproductor = new Reproductor(new ArrayList<>(Rutas), ProgresoCancion);
			PermisoParaAñadir = 1;
		} else {
			JOptionPane.showMessageDialog(null, "Detenga la pista actual para actualizar");
		}
	}

	private void ObtenerCancionesDeListaSeleccionada(String selectedLista) {
		if (selectedLista != null) {
			try (Connection connection = new conexion().conectar()) {
				String query = "SELECT Canciones.ubicacion FROM Ordenlista "
						+ "JOIN Canciones ON Ordenlista.id_Cancion = Canciones.id_Cancion "
						+ "JOIN Lista ON Ordenlista.id_Lista = Lista.id_Lista " + "WHERE Lista.nombrelista = ? "
						+ "ORDER BY Ordenlista.posicion";

				try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
					preparedStatement.setString(1, selectedLista);

					try (ResultSet resultSet = preparedStatement.executeQuery()) {
						int count = 0;
						while (resultSet.next()) {
							String basePath = System.getProperty("user.dir");
							Rutas.add(basePath + File.separator + resultSet.getString("ubicacion"));
							count++;
						}

						reproductor = new Reproductor(new ArrayList<>(Rutas), ProgresoCancion);
						PermisoParaAñadir = 1;

					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
		}
	}

	private String MostrarDialogoDeSeleccion(List<String> listas) {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Selecciona una lista:");
		JComboBox<String> comboBox = new JComboBox<>(listas.toArray(new String[0]));

		JButton btnCrearLista = new JButton("Crear Nueva Lista");
		btnCrearLista.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nuevaLista = JOptionPane.showInputDialog("Ingrese el nombre de la nueva lista:");

				if (nuevaLista != null && !nuevaLista.isEmpty()) {
					int idUsuario = 23;
					lista nuevaLista1 = new lista();
					nuevaLista1.setNombreLista(nuevaLista);
					nuevaLista1.setIdUsuario(idUsuario);
					if (dao.insertarLista(nuevaLista1)) {
						comboBox.addItem(nuevaLista);
						comboBox.setSelectedItem(nuevaLista);
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un error al agregar la lista");
					}
				}
			}
		});

		JButton btnAgregarCanciones = new JButton("Agregar Canciones");
		btnAgregarCanciones.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombreListaSeleccionada = (String) comboBox.getSelectedItem();
				if (nombreListaSeleccionada != null) {

				}
			}
		});

		panel.add(label);
		panel.add(comboBox);
		panel.add(btnCrearLista);

		JOptionPane optionPane = new JOptionPane(panel, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);

		JDialog dialog = optionPane.createDialog("Seleccionar Lista");
		dialog.setSize(500, 300);

		dialog.setVisible(true);

		Object selectedValue = optionPane.getValue();

		if (selectedValue.equals(JOptionPane.OK_OPTION)) {
			return (String) comboBox.getSelectedItem();
		} else {
			return null;
		}
	}

	protected void paintComponent(Graphics grphcs) {

		Graphics2D g2 = (Graphics2D) grphcs;
		g2.setColor(getBackground());
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
		g2.fillRect(0, 0, getWidth(), 25);
		super.paintComponent(grphcs);

	}
}
