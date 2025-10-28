package Cruds;

import java.awt.EventQueue;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import GetaSet.Maquina;
import GetaSet.Productos;
import Metodos.CrudMaquina;
import Metodos.CrudProductos;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CrudconMaquina extends JFrame {
	private JFileChooser fileChooser;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtPuntos;
	Maquina maquina = new Maquina();
	CrudMaquina dao = new CrudMaquina();
	List<Maquina> m;
	int fila = -1;
	DefaultTableModel tablemodel;
	private String imagenSeleccionada;
	private JComboBox<String> cboProducto;
	private JLabel lblID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrudconMaquina frame = new CrudconMaquina();
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
	public CrudconMaquina() {
		tablemodel = new DefaultTableModel();
		tablemodel.addColumn("id_Maquina");
		tablemodel.addColumn("id_Producto");
		tablemodel.addColumn("Puntos");
		tablemodel.addColumn("IMG_URL");

		fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Imagen", "jpg", "jpeg", "png", "gif");
		fileChooser.setFileFilter(filter);

		CrudProductos productos = new CrudProductos();
		List<Productos> pro = productos.consultaProductos();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 641, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(81, 242, 491, 358);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = table.getSelectedRow();
				maquina = m.get(fila);
				lblID.setText(String.valueOf(maquina.getId_Maquina()));
				String nombreProducto = dao.obtenerNombreProductoPorId(maquina.getId_Producto());
				cboProducto.setSelectedItem(nombreProducto);
				txtPuntos.setText(String.valueOf(maquina.getPuntos()));

				String imgURL = maquina.getImg_url();
				String projectPath = System.getProperty("user.dir");
				imagenSeleccionada = imgURL.replace(projectPath, "");
			}
		});
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(52, 32, 46, 14);
		contentPane.add(lblNewLabel);

		lblID = new JLabel("");
		lblID.setBounds(126, 32, 46, 14);
		contentPane.add(lblID);

		JLabel lblNewLabel_1 = new JLabel("Producto:");
		lblNewLabel_1.setBounds(52, 69, 57, 14);
		contentPane.add(lblNewLabel_1);

		cboProducto = new JComboBox<>();
		cboProducto.setBounds(126, 65, 71, 18);
		contentPane.add(cboProducto);
		for (Productos productos2 : pro) {
			cboProducto.addItem(productos2.getNombre());
		}
		JLabel lblNewLabel_2 = new JLabel("Puntos :");
		lblNewLabel_2.setBounds(52, 107, 46, 14);
		contentPane.add(lblNewLabel_2);

		txtPuntos = new JTextField();
		txtPuntos.setBounds(126, 104, 86, 20);
		contentPane.add(txtPuntos);
		txtPuntos.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("img:");
		lblNewLabel_3.setBounds(52, 148, 46, 14);
		contentPane.add(lblNewLabel_3);

		JButton btnchooser = new JButton("Click me¡");
		btnchooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFileChooser();
			}
		});
		btnchooser.setBounds(126, 148, 89, 23);
		contentPane.add(btnchooser);

		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtPuntos.getText().equals("") || imagenSeleccionada == null) {
						JOptionPane.showMessageDialog(null, "Los campos Puntos e Imagen no pueden estar vacíos");
						return;
					}
					String nombreProducto = cboProducto.getSelectedItem().toString();

					int idProducto = dao.obtenerIdProductoPorNombre(nombreProducto);
					int puntos = Integer.parseInt(txtPuntos.getText());

					if (dao.existeMaquinaConProducto(idProducto)) {
						JOptionPane.showMessageDialog(null, "Producto asociado a esta maquina");
						return;
					}
					maquina.setId_Producto(idProducto);
					maquina.setPuntos(puntos);
					maquina.setImg_url(imagenSeleccionada);

					if (dao.insertarMaquina(maquina)) {
						actualizarTabla();
						limpiarCampos();
						JOptionPane.showMessageDialog(null, "Se agregó la máquina correctamente!!");
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un error al agregar la máquina");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Error al procesar los datos. Asegúrate de ingresar valores válidos.");
				}
			}
		});
		btnInsertar.setBounds(434, 45, 89, 23);
		contentPane.add(btnInsertar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fila == -1) {
						JOptionPane.showMessageDialog(null, "Selecciona una máquina de la tabla para modificar");
						return;
					}

					if (txtPuntos.getText().equals("") || imagenSeleccionada == null) {
						JOptionPane.showMessageDialog(null, "Los campos Puntos e Imagen no pueden estar vacíos");
						return;
					}

					String nombreProducto = cboProducto.getSelectedItem().toString();
					int idProducto = dao.obtenerIdProductoPorNombre(nombreProducto);
					int puntos = Integer.parseInt(txtPuntos.getText());

					maquina.setId_Maquina(Integer.parseInt(lblID.getText()));
					maquina.setId_Producto(idProducto);
					maquina.setPuntos(puntos);
					maquina.setImg_url(imagenSeleccionada);

					if (dao.editarMaquina(maquina)) {
						actualizarTabla();
						limpiarCampos();
						JOptionPane.showMessageDialog(null, "Se actualizó la máquina correctamente!!");
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un error al actualizar la máquina");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Error al procesar los datos. Asegúrate de ingresar valores válidos.");
				}
			}
		});
		btnModificar.setBounds(434, 103, 89, 23);
		contentPane.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fila == -1) {
						JOptionPane.showMessageDialog(null, "Selecciona una máquina de la tabla para eliminar");
						return;
					}

					int confirm = JOptionPane.showConfirmDialog(null,
							"¿Estás seguro de que quieres eliminar esta máquina?", "Confirmar eliminación",
							JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						int idMaquina = Integer.parseInt(lblID.getText());

						if (dao.eliminarMaquina(idMaquina)) {
							actualizarTabla();
							limpiarCampos();
							JOptionPane.showMessageDialog(null, "Máquina eliminada correctamente");
						} else {
							JOptionPane.showMessageDialog(null, "Hubo un error al eliminar la máquina");
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Error al procesar los datos. Asegúrate de seleccionar una máquina válida.");
				}
			}
		});
		btnEliminar.setBounds(434, 148, 89, 23);
		contentPane.add(btnEliminar);
		table.setModel(tablemodel);
		actualizarTabla();
	}

	public void limpiarCampos() {
		lblID.setText("");
		cboProducto.setSelectedIndex(0);
		txtPuntos.setText("");
		imagenSeleccionada = null;

	}

	public void actualizarTabla() {
		while (tablemodel.getRowCount() > 0) {
			tablemodel.removeRow(0);
		}
		m = dao.consultaMaquinas();
		for (Maquina maquina : m) {
			Object row[] = new Object[4];
			row[0] = maquina.getId_Maquina();
			row[1] = maquina.getId_Producto();
			row[2] = maquina.getPuntos();
			row[3] = maquina.getImg_url();
			tablemodel.addRow(row);

		}
		table.setModel(tablemodel);
	}

	private void abrirFileChooser() {
		int returnVal = fileChooser.showOpenDialog(CrudconMaquina.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			String filePath = selectedFile.getAbsolutePath();

			String projectPath = System.getProperty("user.dir");
			imagenSeleccionada = filePath.replace(projectPath, "");
		}
	}

}
