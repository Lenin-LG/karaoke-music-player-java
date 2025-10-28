package Cruds;

import java.awt.EventQueue;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import GetaSet.Productos;
import GetaSet.listacompra;
import GetaSet.ordenProducto;
import GetaSet.usuario;
import Metodos.CrudListaCompra;
import Metodos.CrudMaquina;
import Metodos.CrudOrdenProducto;
import Metodos.CrudProductos;
import Metodos.CrudUsuario;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CrudconOrdenProducto extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtCantidad;
	private JTextField txtPuntosTotales;
	ordenProducto ordenP = new ordenProducto();
	CrudOrdenProducto dao = new CrudOrdenProducto();
	int fila = -1;
	List<ordenProducto> list;
	DefaultTableModel tablemodel;
	private JComboBox<String> cboProducto;
	private JComboBox<String> cboLista;
	private JComboBox<String> cboUsuario;
	CrudListaCompra crudListaCompra = new CrudListaCompra();
	CrudUsuario crudUsuario = new CrudUsuario();
	CrudMaquina crudMaquina = new CrudMaquina();
	private JLabel lblID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrudconOrdenProducto frame = new CrudconOrdenProducto();
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
	public CrudconOrdenProducto() {
		tablemodel = new DefaultTableModel();
		tablemodel.addColumn("id_OrdenProducto");
		tablemodel.addColumn("id_Usuario");
		tablemodel.addColumn("id_ListaCompra");
		tablemodel.addColumn("id_Producto");
		tablemodel.addColumn("cantidad");
		tablemodel.addColumn("puntos_utilizados");

		CrudProductos crudProductos = new CrudProductos();
		List<Productos> pro = crudProductos.consultaProductos();

		List<usuario> usuario = crudUsuario.consultaUsuarios();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 619, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 225, 508, 275);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				fila = table.getSelectedRow();
				ordenP = list.get(fila);
				lblID.setText(String.valueOf(ordenP.getId_OrdenProducto()));
				int idUsuario = ordenP.getId_Usuario();
				String usuario = dao.obtenerNombreUsuarioPorId(idUsuario);
				cboUsuario.setSelectedItem(usuario);
				cargarListasPorUsuario(usuario, ordenP.getId_ListaCompra());
				cboProducto.setSelectedItem(crudMaquina.obtenerNombreProductoPorId(ordenP.getId_Producto()));
				txtCantidad.setText(String.valueOf(ordenP.getCantidad()));
				txtPuntosTotales.setText(String.valueOf(ordenP.getPuntos_utilizados()));
			}
		});
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(31, 28, 46, 14);
		contentPane.add(lblNewLabel);

		lblID = new JLabel("");
		lblID.setBounds(87, 28, 83, 14);
		contentPane.add(lblID);

		JLabel lblNewLabel_2 = new JLabel("Usuario");
		lblNewLabel_2.setBounds(31, 57, 46, 14);
		contentPane.add(lblNewLabel_2);

		cboUsuario = new JComboBox<>();
		cboUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarListasPorUsuario((String) cboUsuario.getSelectedItem(), -1);
			}
		});
		cboUsuario.setBounds(87, 53, 130, 22);
		contentPane.add(cboUsuario);
		for (usuario usuario2 : usuario) {
			cboUsuario.addItem(usuario2.getUser());
		}

		JLabel lblNewLabel_1 = new JLabel("Lista");
		lblNewLabel_1.setBounds(31, 97, 46, 14);
		contentPane.add(lblNewLabel_1);

		cboLista = new JComboBox<>();
		cboLista.setBounds(87, 93, 130, 18);
		contentPane.add(cboLista);
		JLabel lblNewLabel_3 = new JLabel("Producto");
		lblNewLabel_3.setBounds(31, 137, 46, 14);
		contentPane.add(lblNewLabel_3);

		cboProducto = new JComboBox<>();
		cboProducto.setBounds(87, 133, 130, 22);
		contentPane.add(cboProducto);
		for (Productos productos : pro) {
			cboProducto.addItem(productos.getNombre());
		}

		JLabel lblNewLabel_4 = new JLabel("Cantidad");
		lblNewLabel_4.setBounds(31, 162, 46, 14);
		contentPane.add(lblNewLabel_4);

		txtCantidad = new JTextField();
		txtCantidad.setEditable(false);
		txtCantidad.setBounds(87, 166, 130, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		txtCantidad.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				calcularPuntosTotales();
			}

			public void removeUpdate(DocumentEvent e) {
				calcularPuntosTotales();
			}

			public void insertUpdate(DocumentEvent e) {
				calcularPuntosTotales();
			}
		});

		JLabel lblNewLabel_5 = new JLabel("Puntos totales");
		lblNewLabel_5.setBounds(31, 200, 79, 14);
		contentPane.add(lblNewLabel_5);

		txtPuntosTotales = new JTextField();
		txtPuntosTotales.setEditable(false);
		txtPuntosTotales.setBounds(111, 194, 106, 20);
		contentPane.add(txtPuntosTotales);
		txtPuntosTotales.setColumns(10);

		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nombreUsuario = cboUsuario.getSelectedItem().toString();
					int idUsuario = crudUsuario.obtenerIdUsuarioPorNombre(nombreUsuario);

					String nombreLista = cboLista.getSelectedItem().toString();
					int idListaCompra = crudListaCompra.obtenerIdListaPorNombreYUsuario(nombreLista, idUsuario);

					String nombreProducto = cboProducto.getSelectedItem().toString();
					int idProducto = crudMaquina.obtenerIdProductoPorNombre(nombreProducto);

					int cantidad = Integer.parseInt(txtCantidad.getText());
					int puntosUtilizados = Integer.parseInt(txtPuntosTotales.getText());

					ordenP.setId_Usuario(idUsuario);
					ordenP.setId_ListaCompra(idListaCompra);
					ordenP.setId_Producto(idProducto);
					ordenP.setCantidad(cantidad);
					ordenP.setPuntos_utilizados(puntosUtilizados);

					if (dao.insertarOrdenProducto(ordenP)) {

						actualizarTabla();
						limpiarCampos();
						JOptionPane.showMessageDialog(null, "Orden de producto insertada correctamente!!");
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un error al insertar la orden de producto");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Error al procesar los datos. Asegúrate de ingresar valores válidos.");
				}
			}
		});
		btnInsertar.setBounds(452, 53, 89, 23);
		contentPane.add(btnInsertar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fila == -1) {
						JOptionPane.showMessageDialog(null, "Selecciona una orden para actualizar");
						return;
					}

					if (lblID.getText().equals("") || cboUsuario.getSelectedItem() == null
							|| cboLista.getSelectedItem() == null || cboProducto.getSelectedItem() == null
							|| txtCantidad.getText().equals("") || txtPuntosTotales.getText().equals("")) {
						JOptionPane.showMessageDialog(null,
								"Todos los campos deben estar llenos para actualizar la orden");
						return;
					}

					int idOrden = Integer.parseInt(lblID.getText());
					String nombreUsuario = cboUsuario.getSelectedItem().toString();
					int idUsuario = crudUsuario.obtenerIdUsuarioPorNombre(nombreUsuario);

					String nombreLista = cboLista.getSelectedItem().toString();
					int idListaCompra = crudListaCompra.obtenerIdListaPorNombreYUsuario(nombreLista, idUsuario);

					String nombreProducto = cboProducto.getSelectedItem().toString();
					int idProducto = crudMaquina.obtenerIdProductoPorNombre(nombreProducto);

					int cantidad = Integer.parseInt(txtCantidad.getText());
					int puntosUtilizados = Integer.parseInt(txtPuntosTotales.getText());

					ordenP.setId_OrdenProducto(idOrden);
					ordenP.setId_Usuario(idUsuario);
					ordenP.setId_ListaCompra(idListaCompra);
					ordenP.setId_Producto(idProducto);
					ordenP.setCantidad(cantidad);
					ordenP.setPuntos_utilizados(puntosUtilizados);

					if (dao.actualizarOrdenProducto(ordenP)) {
						actualizarTabla();
						limpiarCampos();
						JOptionPane.showMessageDialog(null, "Orden de producto actualizada correctamente!!");
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un error al actualizar la orden de producto");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Error al procesar los datos. Asegúrate de ingresar valores válidos.");
				}
			}
		});
		btnModificar.setBounds(452, 93, 89, 23);
		contentPane.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fila == -1) {
						JOptionPane.showMessageDialog(null, "Selecciona una orden para eliminar");
						return;
					}

					int confirmacion = JOptionPane.showConfirmDialog(null,
							"¿Estás seguro de que deseas eliminar esta orden?", "Confirmar eliminación",
							JOptionPane.YES_NO_OPTION);

					if (confirmacion == JOptionPane.YES_OPTION) {
						int idOrden = Integer.parseInt(lblID.getText());
						if (dao.eliminarOrdenProducto(idOrden)) {
							actualizarTabla();
							limpiarCampos();
							JOptionPane.showMessageDialog(null, "Se eliminó la orden correctamente!!");
						} else {
							JOptionPane.showMessageDialog(null, "Hubo un error al eliminar la orden");
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Error al procesar los datos. Asegúrate de ingresar valores válidos.");
				}
			}
		});
		btnEliminar.setBounds(452, 133, 89, 23);
		contentPane.add(btnEliminar);
		table.setModel(tablemodel);
		cboUsuario.setSelectedIndex(-1);
		cboProducto.setSelectedIndex(-1);
		actualizarTabla();
	}

	private void actualizarTabla() {
		while (tablemodel.getRowCount() > 0) {
			tablemodel.removeRow(0);
		}
		list = dao.obtenerTodosOrdenProductos();
		for (ordenProducto ordenProducto : list) {
			Object row[] = new Object[6];
			row[0] = ordenProducto.getId_OrdenProducto();
			row[1] = ordenProducto.getId_Usuario();
			row[2] = ordenProducto.getId_ListaCompra();
			row[3] = ordenProducto.getId_Producto();
			row[4] = ordenProducto.getCantidad();
			row[5] = ordenProducto.getPuntos_utilizados();
			tablemodel.addRow(row);
		}
		table.setModel(tablemodel);
	}

	private void limpiarCampos() {
		cboUsuario.setSelectedIndex(-1);
		cboLista.removeAllItems();
		cboProducto.setSelectedIndex(-1);
		txtCantidad.setText("");
		txtPuntosTotales.setText("");
	}

	private List<listacompra> cargarListasPorUsuario(String nombreUsuario, int idListaSeleccionada) {
		if (cboLista != null) {
			cboLista.removeAllItems();
			int idUsuario = crudUsuario.obtenerIdUsuarioPorNombre(nombreUsuario);

			List<listacompra> listasCompra = crudListaCompra.obtenerListasCompraPorUsuario(idUsuario);

			for (listacompra lista : listasCompra) {
				cboLista.addItem(lista.getNombrelista());
			}

			for (listacompra lista : listasCompra) {
				if (lista.getId_ListaCompra() == idListaSeleccionada) {
					cboLista.setSelectedItem(lista.getNombrelista());
					break;
				}
			}

			return listasCompra;
		}

		return Collections.emptyList();
	}

	private void calcularPuntosTotales() {
		try {
			String nombreProducto = (String) cboProducto.getSelectedItem();
			int cantidad = Integer.parseInt(txtCantidad.getText());

			if (nombreProducto != null && !nombreProducto.isEmpty()) {
				int idProducto = crudMaquina.obtenerIdProductoPorNombre(nombreProducto);

				if (idProducto != -1) {
					int puntosPorProducto = crudMaquina.obtenerPuntosPorProducto(idProducto);
					int puntosTotales = puntosPorProducto * cantidad;
					txtPuntosTotales.setText(String.valueOf(puntosTotales));
				}
			}
		} catch (NumberFormatException e) {
			txtPuntosTotales.setText("");
		}
	}
}
