package Cruds;

import java.awt.EventQueue;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import GetaSet.artista;
import Metodos.CrudArtista;

public class CrudconArtista extends JFrame {
	private String imagenSeleccionada;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField txtNombre;
	private JTextField txtGenero;
	private JTextField txtPais;
	private DefaultTableModel tableModel;
	private JFileChooser fileChooser;
	List<artista> artistas;
	artista artista = new artista();
	CrudArtista dao = new CrudArtista();
	int fila = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrudconArtista frame = new CrudconArtista();
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
	public CrudconArtista() {

		tableModel = new DefaultTableModel();
		tableModel.addColumn("ID");
		tableModel.addColumn("Nombre");
		tableModel.addColumn("Género");
		tableModel.addColumn("País");
		tableModel.addColumn("Imagen de Referencia");

		fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Imagen", "jpg", "jpeg", "png", "gif");
		fileChooser.setFileFilter(filter);

		setTitle("Crud Artits");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 828, 675);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 326, 726, 299);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = table.getSelectedRow();
				artista = artistas.get(fila);

				textField.setText(String.valueOf(artista.getIdArtista()));
				txtNombre.setText(artista.getNombre());
				txtGenero.setText(artista.getGenero());
				txtPais.setText(artista.getPais());

				String imgURL = artista.getImagenUrl();
				String projectPath = System.getProperty("user.dir");
				imagenSeleccionada = imgURL.replace(projectPath, "");

			}
		});
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(47, 50, 46, 14);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(68, 47, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(203, 50, 46, 14);
		contentPane.add(lblNewLabel_1);

		txtNombre = new JTextField();
		txtNombre.setBounds(259, 47, 86, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Genero");
		lblNewLabel_2.setBounds(425, 50, 46, 14);
		contentPane.add(lblNewLabel_2);

		txtGenero = new JTextField();
		txtGenero.setBounds(481, 47, 86, 20);
		contentPane.add(txtGenero);
		txtGenero.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Pais");
		lblNewLabel_3.setBounds(614, 50, 46, 14);
		contentPane.add(lblNewLabel_3);

		txtPais = new JTextField();
		txtPais.setText("");
		txtPais.setBounds(672, 47, 86, 20);
		contentPane.add(txtPais);
		txtPais.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Imagen de referencia");
		lblNewLabel_4.setBounds(43, 120, 121, 14);
		contentPane.add(lblNewLabel_4);

		JButton btnChooser = new JButton("Cick me");
		btnChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFileChooser();
			}
		});
		btnChooser.setBounds(186, 116, 89, 23);
		contentPane.add(btnChooser);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					if (txtNombre.getText().equals("") || txtGenero.getText().equals("")
							|| txtPais.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Los campos Nombre, Género y País no pueden estar vacíos");
						return;
					}
					String nombre = txtNombre.getText();
					String genero = txtGenero.getText();
					String pais = txtPais.getText();

					artista nuevoArtista = new artista();
					nuevoArtista.setNombre(nombre);
					nuevoArtista.setGenero(genero);
					nuevoArtista.setPais(pais);
					nuevoArtista.setImagenUrl(imagenSeleccionada);

					if (dao.insertarArtista(nuevoArtista)) {

						actualizarTabla();

						limpiarCampos();

						JOptionPane.showMessageDialog(null, "Se agregó correctamente!!");
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un error al agregar el artista");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Error al procesar los datos. Asegúrate de ingresar valores válidos.");
				}

			}
		});
		btnAgregar.setBounds(75, 271, 89, 23);
		contentPane.add(btnAgregar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selectedRow = table.getSelectedRow();

				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Por favor, selecciona una fila para eliminar");
					return;
				}

				int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres eliminar este artista?",
						"Confirmar eliminación", JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					int idArtista = (int) tableModel.getValueAt(selectedRow, 0);

					try {
						if (dao.eliminarArtista(idArtista) && idArtista > 0) {
							actualizarTabla();
							limpiarCampos();
							JOptionPane.showMessageDialog(null, "Artista eliminado correctamente");
						} else {
							JOptionPane.showMessageDialog(null, "Hubo un error al eliminar el artista");
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "ERROR");
					}
				}

			}
		});
		btnEliminar.setBounds(186, 271, 89, 23);
		contentPane.add(btnEliminar);

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtNombre.getText().equals("") || txtGenero.getText().equals("")
							|| txtPais.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Los campos Nombre, Género y País no pueden estar vacíos");
						return;
					}

					int id = Integer.parseInt(textField.getText());
					String nombre = txtNombre.getText();
					String genero = txtGenero.getText();
					String pais = txtPais.getText();

					artista artistaActualizado = new artista();
					artistaActualizado.setIdArtista(id);
					artistaActualizado.setNombre(nombre);
					artistaActualizado.setGenero(genero);
					artistaActualizado.setPais(pais);
					artistaActualizado.setImagenUrl(imagenSeleccionada);

					if (dao.actualizarArtista(artistaActualizado)) {
						actualizarTabla();
						limpiarCampos();
						JOptionPane.showMessageDialog(null, "Se actualizó correctamente!!");
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un error al actualizar el artista");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Error al procesar los datos. Asegúrate de ingresar valores válidos.");
				}
			}
		});
		btnActualizar.setBounds(306, 271, 89, 23);
		contentPane.add(btnActualizar);

		JButton btnNewButton_1 = new JButton("Limpiar campos");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
			}
		});
		btnNewButton_1.setBounds(422, 271, 105, 23);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Refresh");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTabla();
			}
		});
		btnNewButton_2.setBounds(559, 271, 89, 23);
		contentPane.add(btnNewButton_2);

		table.setModel(tableModel);
		actualizarTabla();
	}

	public void limpiarCampos() {
		txtGenero.setText("");
		txtNombre.setText("");
		txtPais.setText("");
		imagenSeleccionada = "";
	}

	private void abrirFileChooser() {
		int returnVal = fileChooser.showOpenDialog(CrudconArtista.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			String filePath = selectedFile.getAbsolutePath();

			String projectPath = System.getProperty("user.dir");
			imagenSeleccionada = filePath.replace(projectPath, "");
		}
	}

	public void actualizarTabla() {

		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		artistas = dao.obtenerTodosArtistas();

		for (artista artista : artistas) {
			Object rowData[] = new Object[5];
			rowData[0] = artista.getIdArtista();
			rowData[1] = artista.getNombre();
			rowData[2] = artista.getGenero();
			rowData[3] = artista.getPais();
			rowData[4] = artista.getImagenUrl();
			tableModel.addRow(rowData);
		}
		table.setModel(tableModel);
	}

}
