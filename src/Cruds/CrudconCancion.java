package Cruds;

import java.awt.EventQueue;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import GetaSet.artista;
import GetaSet.cancion;
import Metodos.CrudArtista;
import Metodos.CrudCancion;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class CrudconCancion extends JFrame {
	private JFileChooser fileChooser;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtID;
	private JTextField txtNombre;
	private JComboBox<String> cboArtists;
	private DefaultTableModel tableModel;
	private String ubicacionCancion;
	CrudCancion dao = new CrudCancion();
	List<cancion> canciones;
	int fila = -1;
	cancion can = new cancion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrudconCancion frame = new CrudconCancion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CrudconCancion() {
		tableModel = new DefaultTableModel();
		tableModel.addColumn("ID");
		tableModel.addColumn("Titulo");
		tableModel.addColumn("Artista");
		tableModel.addColumn("Ubicacion");

		CrudArtista crudArtista = new CrudArtista();
		List<artista> artistas = crudArtista.obtenerTodosArtistas();

		fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Audio", "mp3", "wav");
		fileChooser.setFileFilter(filter);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 764, 677);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 225, 648, 402);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = table.getSelectedRow();
				can = canciones.get(fila);

				txtID.setText(String.valueOf(can.getIdCancion()));
				txtNombre.setText(can.getTituloCancion());
				String nombreArtista = dao.obtenerNombreArtistaPorID(can.getIdArtista());
				cboArtists.setSelectedItem(nombreArtista);
				ubicacionCancion = can.getUbicacion();
			}

		});
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(32, 67, 46, 14);
		contentPane.add(lblNewLabel);

		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(159, 64, 86, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nombre de la Cancion");
		lblNewLabel_1.setBounds(32, 98, 129, 14);
		contentPane.add(lblNewLabel_1);

		txtNombre = new JTextField();
		txtNombre.setBounds(159, 95, 86, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Artista");
		lblNewLabel_2.setBounds(32, 129, 46, 14);
		contentPane.add(lblNewLabel_2);

		cboArtists = new JComboBox<>();
		cboArtists.setBounds(116, 125, 129, 22);
		contentPane.add(cboArtists);
		for (artista a : artistas) {
			cboArtists.addItem(a.getNombre());
		}

		JLabel lblNewLabel_3 = new JLabel("Ruta de la cancion");
		lblNewLabel_3.setBounds(32, 158, 110, 14);
		contentPane.add(lblNewLabel_3);

		JButton btnchooser = new JButton("Click Me!");
		btnchooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFileChooser();
			}
		});
		btnchooser.setBounds(145, 158, 100, 28);
		contentPane.add(btnchooser);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtNombre.getText().equals("") || cboArtists.getSelectedItem() == null
							|| ubicacionCancion.equals("")) {
						JOptionPane.showMessageDialog(null,
								"Los campos Nombre, Artista y Ruta de la Canción no pueden estar vacíos");
						return;
					}

					String nombre = txtNombre.getText();
					String nombreArtista = (String) cboArtists.getSelectedItem();
					int idArtista = dao.obtenerIdArtistaPorNombre(nombreArtista);

					cancion nuevaCancion = new cancion();
					nuevaCancion.setTituloCancion(nombre);
					nuevaCancion.setIdArtista(idArtista);
					nuevaCancion.setUbicacion(ubicacionCancion);

					if (dao.insertarCancion(nuevaCancion)) {
						actualizarTabla();
						limpiarCampos();
						JOptionPane.showMessageDialog(null, "Se agregó la canción correctamente!!");
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un error al agregar la canción");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Error al procesar los datos. Asegúrate de ingresar valores válidos.");
				}
			}
		});
		btnAgregar.setBounds(406, 63, 100, 22);
		contentPane.add(btnAgregar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fila == -1) {
						JOptionPane.showMessageDialog(null, "Selecciona una canción para eliminar");
						return;
					}

					int confirm = JOptionPane.showConfirmDialog(null,
							"¿Estás seguro de que quieres eliminar esta canción?", "Confirmar eliminación",
							JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						int idCancion = Integer.parseInt(txtID.getText());

						if (dao.eliminarCancion(idCancion)) {
							actualizarTabla();
							limpiarCampos();
							JOptionPane.showMessageDialog(null, "Canción eliminada correctamente");
						} else {
							JOptionPane.showMessageDialog(null, "Hubo un error al eliminar la canción");
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Error al procesar los datos. Asegúrate de seleccionar una canción válida.");
				}
			}
		});
		btnEliminar.setBounds(559, 63, 100, 22);
		contentPane.add(btnEliminar);

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fila == -1) {
						JOptionPane.showMessageDialog(null, "Selecciona una canción para actualizar");
						return;
					}

					if (txtNombre.getText().equals("") || cboArtists.getSelectedItem() == null
							|| ubicacionCancion.equals("")) {
						JOptionPane.showMessageDialog(null,
								"Los campos Nombre, Artista y Ruta de la Canción no pueden estar vacíos");
						return;
					}

					int id = Integer.parseInt(txtID.getText());
					String nombre = txtNombre.getText();
					String nombreArtista = (String) cboArtists.getSelectedItem();
					int idArtista = dao.obtenerIdArtistaPorNombre(nombreArtista);

					cancion cancionActualizada = new cancion();
					cancionActualizada.setIdCancion(id);
					cancionActualizada.setTituloCancion(nombre);
					cancionActualizada.setIdArtista(idArtista);
					cancionActualizada.setUbicacion(ubicacionCancion);

					if (dao.editarCancion(cancionActualizada)) {
						actualizarTabla();
						limpiarCampos();
						JOptionPane.showMessageDialog(null, "Se actualizó la canción correctamente!!");
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un error al actualizar la canción");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Error al procesar los datos. Asegúrate de ingresar valores válidos.");
				}
			}
		});
		btnActualizar.setBounds(406, 121, 100, 22);
		contentPane.add(btnActualizar);

		JButton btnLimpiarCampos = new JButton("Limpiar Campos");
		btnLimpiarCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
			}
		});
		btnLimpiarCampos.setBounds(559, 125, 134, 22);
		contentPane.add(btnLimpiarCampos);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(479, 176, 100, 22);
		contentPane.add(btnRefresh);
		table.setModel(tableModel);
		actualizarTabla();
	}

	public void actualizarTabla() {

		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		canciones = dao.consultaCanciones();

		for (cancion can : canciones) {
			Object rowData[] = new Object[4];
			rowData[0] = can.getIdCancion();
			rowData[1] = can.getTituloCancion();
			rowData[2] = can.getIdArtista();
			rowData[3] = can.getUbicacion();
			tableModel.addRow(rowData);
		}
		table.setModel(tableModel);
	}

	public void limpiarCampos() {
		txtID.setText("");
		txtNombre.setText("");
		cboArtists.setSelectedIndex(-1);
		ubicacionCancion = "";
	}

	private void abrirFileChooser() {
		int returnVal = fileChooser.showOpenDialog(CrudconCancion.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			String filePath = selectedFile.getAbsolutePath();

			String projectPath = System.getProperty("user.dir");
			ubicacionCancion = filePath.replace(projectPath, "");
		}
	}

}
