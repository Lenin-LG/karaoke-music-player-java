package Cruds;

import java.awt.EventQueue;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import GetaSet.ReporteCompra;
import GetaSet.listacompra;
import GetaSet.ordenProducto;
import GetaSet.usuario;
import Metodos.CrudListaCompra;
import Metodos.CrudMaquina;
import Metodos.CrudOrdenProducto;
import Metodos.CrudProductos;
import Metodos.CrudReporteCompra;
import Metodos.CrudUsuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CrudconReporteCompra extends JFrame {

	private JPanel contentPane;
	private JLabel lblID;
	private JTextField txtPrecioTotal;
	private JTextField txtTotalPuntos;
	private JTable table;
	private JComboBox cboUsuario;
	private JComboBox<String> cboListaCompra;
	private JLabel lblFecha;
	private JLabel lblHora;
	CrudUsuario crudUsuario = new CrudUsuario();
	CrudListaCompra crudListaCompra = new CrudListaCompra();
	CrudReporteCompra dao = new CrudReporteCompra();
	CrudOrdenProducto crudOrdenProducto = new CrudOrdenProducto();
	ReporteCompra repote = new ReporteCompra();
	CrudProductos crudProductos = new CrudProductos();
	CrudMaquina crudMaquina = new CrudMaquina();
	DefaultTableModel tablemodel;
	List<ReporteCompra> list;
	int fila = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrudconReporteCompra frame = new CrudconReporteCompra();
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
	public CrudconReporteCompra() {
		tablemodel = new DefaultTableModel();
		tablemodel.addColumn("ID ReporteCompra");
		tablemodel.addColumn("ID Usuario");
		tablemodel.addColumn("ID ListaCompra");
		tablemodel.addColumn("Fecha");
		tablemodel.addColumn("Hora");
		tablemodel.addColumn("Precio Total");
		tablemodel.addColumn("Puntos Utilizados");
		List<usuario> usuario = crudUsuario.consultaUsuarios();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 569);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(39, 48, 46, 14);
		contentPane.add(lblNewLabel);

		lblID = new JLabel("");
		lblID.setBounds(185, 48, 46, 14);
		contentPane.add(lblID);

		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setBounds(39, 81, 46, 14);
		contentPane.add(lblNewLabel_1);

		cboUsuario = new JComboBox();
		cboUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarListasPorUsuario((String) cboUsuario.getSelectedItem(), -1);
			}
		});
		cboUsuario.setBounds(184, 79, 84, 18);
		contentPane.add(cboUsuario);
		for (usuario usuario2 : usuario) {
			cboUsuario.addItem(usuario2.getUser());
		}

		JLabel lblNewLabel_2 = new JLabel("Lista de compra");
		lblNewLabel_2.setBounds(39, 117, 102, 14);
		contentPane.add(lblNewLabel_2);

		cboListaCompra = new JComboBox();
		cboListaCompra.setBounds(184, 113, 102, 22);
		contentPane.add(cboListaCompra);
		cboListaCompra.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				calcularPrecioPuntosPorLista();
			}
		});

		JLabel lblNewLabel_3 = new JLabel("Fecha");
		lblNewLabel_3.setBounds(39, 142, 46, 14);
		contentPane.add(lblNewLabel_3);

		lblFecha = new JLabel("");
		lblFecha.setBounds(185, 142, 169, 14);
		contentPane.add(lblFecha);

		JLabel lblNewLabel_4 = new JLabel("Hora");
		lblNewLabel_4.setBounds(39, 167, 46, 14);
		contentPane.add(lblNewLabel_4);

		lblHora = new JLabel("");
		lblHora.setBounds(185, 167, 169, 14);
		contentPane.add(lblHora);

		JLabel lblNewLabel_6 = new JLabel("precio total S/.");
		lblNewLabel_6.setBounds(39, 192, 102, 14);
		contentPane.add(lblNewLabel_6);

		txtPrecioTotal = new JTextField();
		txtPrecioTotal.setBounds(182, 192, 86, 20);
		contentPane.add(txtPrecioTotal);
		txtPrecioTotal.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Total de puntos utilizados");
		lblNewLabel_7.setBounds(39, 217, 122, 14);
		contentPane.add(lblNewLabel_7);

		txtTotalPuntos = new JTextField();
		txtTotalPuntos.setBounds(182, 214, 86, 20);
		contentPane.add(txtTotalPuntos);
		txtTotalPuntos.setColumns(10);

		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nombreUsuario = cboUsuario.getSelectedItem().toString();
					int idUsuario = crudUsuario.obtenerIdUsuarioPorNombre(nombreUsuario);

					String nombreListaCompra = cboListaCompra.getSelectedItem().toString();
					int idListaCompra = crudListaCompra.obtenerIdListaCompraPorNombreYUsuario(nombreListaCompra,
							nombreUsuario);

					double precioTotal = Double.parseDouble(txtPrecioTotal.getText());
					int puntosUtilizados = Integer.parseInt(txtTotalPuntos.getText());

					String fecha = lblFecha.getText();
					String hora = lblHora.getText();

					repote.setId_Usuario(idUsuario);
					repote.setid_ListaCompra(idListaCompra);
					repote.setPrecioTotal(precioTotal);
					repote.setPuntos_Utilizados(puntosUtilizados);
					repote.setFecha(fecha);
					repote.setHora(hora);

					if (dao.insertarReporteCompra(repote)) {
						actualizarTabla();
						limpiarCampos();
						JOptionPane.showMessageDialog(null, "Reporte de compra insertado correctamente!!");
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un error al insertar el reporte de compra");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Error al procesar los datos. Asegúrate de ingresar valores válidos.");
				}
			}
		});
		btnInsertar.setBounds(461, 44, 89, 23);
		contentPane.add(btnInsertar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fila == -1) {
						JOptionPane.showMessageDialog(null, "Selecciona un reporte para actualizar");
						return;
					}

					if (lblID.getText().equals("") || cboUsuario.getSelectedItem() == null
							|| cboListaCompra.getSelectedItem() == null || txtPrecioTotal.getText().equals("")
							|| txtTotalPuntos.getText().equals("")) {
						JOptionPane.showMessageDialog(null,
								"Todos los campos deben estar llenos para actualizar el reporte");
						return;
					}

					int idReporte = Integer.parseInt(lblID.getText());
					String nombreUsuario = cboUsuario.getSelectedItem().toString();
					int idUsuario = crudUsuario.obtenerIdUsuarioPorNombre(nombreUsuario);

					String nombreListaCompra = cboListaCompra.getSelectedItem().toString();
					int idListaCompra = crudListaCompra.obtenerIdListaCompraPorNombreYUsuario(nombreListaCompra,
							nombreUsuario);

					double precioTotal = Double.parseDouble(txtPrecioTotal.getText());
					int puntosUtilizados = Integer.parseInt(txtTotalPuntos.getText());

					repote.setId_ReporteCompra(idReporte);
					repote.setId_Usuario(idUsuario);
					repote.setid_ListaCompra(idListaCompra);
					repote.setPrecioTotal(precioTotal);
					repote.setPuntos_Utilizados(puntosUtilizados);

					if (dao.actualizarReporteCompra(repote)) {
						actualizarTabla();
						limpiarCampos();
						JOptionPane.showMessageDialog(null, "Reporte de compra actualizado correctamente!!");
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un error al actualizar el reporte de compra");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Error al procesar los datos. Asegúrate de ingresar valores válidos.");
				}
			}
		});
		btnModificar.setBounds(461, 108, 89, 23);
		contentPane.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fila == -1) {
						JOptionPane.showMessageDialog(null, "Selecciona un reporte para eliminar");
						return;
					}

					int confirmacion = JOptionPane.showConfirmDialog(null,
							"¿Estás seguro de que deseas eliminar este reporte de compra?", "Confirmar eliminación",
							JOptionPane.YES_NO_OPTION);

					if (confirmacion == JOptionPane.YES_OPTION) {
						int idReporte = Integer.parseInt(lblID.getText());
						if (dao.eliminarReporteCompra(idReporte)) {
							actualizarTabla();
							limpiarCampos();
							JOptionPane.showMessageDialog(null, "Se eliminó el reporte de compra correctamente!!");
						} else {
							JOptionPane.showMessageDialog(null, "Hubo un error al eliminar el reporte de compra");
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Error al procesar los datos. Asegúrate de ingresar valores válidos.");
				}
			}
		});
		btnEliminar.setBounds(461, 183, 89, 23);
		contentPane.add(btnEliminar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 260, 551, 259);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = table.getSelectedRow();
				repote = list.get(fila);
				lblID.setText(String.valueOf(repote.getId_ReporteCompra()));
				int idUsuario = repote.getId_Usuario();
				String Usuario = dao.obtenerNombreUsuarioPorId(idUsuario);
				cboUsuario.setSelectedItem(Usuario);
				cargarListasPorUsuario(Usuario, repote.getid_ListaCompra());
			}
		});
		scrollPane.setViewportView(table);
		setTime();
		setVisible(true);
		table.setModel(tablemodel);
		actualizarTabla();
	}

	private void limpiarCampos() {
		cboUsuario.setSelectedIndex(-1);
		cboListaCompra.setSelectedIndex(-1);
		txtPrecioTotal.setText("");
		txtTotalPuntos.setText("");

	}

	private void actualizarTabla() {
		while (tablemodel.getRowCount() > 0) {
			tablemodel.removeRow(0);
		}
		list = dao.obtenerTodosReportesCompra();
		for (ReporteCompra reporteCompra : list) {
			Object row[] = new Object[7];
			row[0] = reporteCompra.getId_ReporteCompra();
			row[1] = reporteCompra.getId_Usuario();
			row[2] = reporteCompra.getid_ListaCompra();
			row[3] = reporteCompra.getFecha();
			row[4] = reporteCompra.getHora();
			row[5] = reporteCompra.getPrecioTotal();
			row[6] = reporteCompra.getPuntos_Utilizados();
			tablemodel.addRow(row);
		}
		table.setModel(tablemodel);
		cboUsuario.setSelectedIndex(-1);
	}

	private List<listacompra> cargarListasPorUsuario(String nombreUsuario, int idListaSeleccionada) {
		if (cboListaCompra != null) {
			cboListaCompra.removeAllItems();
			int idUsuario = crudUsuario.obtenerIdUsuarioPorNombre(nombreUsuario);

			List<listacompra> listasCompra = crudListaCompra.obtenerListasCompraPorUsuario(idUsuario);

			for (listacompra lista : listasCompra) {
				cboListaCompra.addItem(lista.getNombrelista());
			}

			for (listacompra lista : listasCompra) {
				if (lista.getId_ListaCompra() == idListaSeleccionada) {
					cboListaCompra.setSelectedItem(lista.getNombrelista());
					break;
				}
			}

			return listasCompra;
		}

		return Collections.emptyList();
	}

	public void setTime() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					updateUIWithCurrentTime();
				}
			}
		}).start();
	}

	private void updateUIWithCurrentTime() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Date date = new Date();
				SimpleDateFormat tf = new SimpleDateFormat("h:mm:ss aa");
				SimpleDateFormat df = new SimpleDateFormat(" dd-MM-yyyy");
				String time = tf.format(date);
				lblHora.setText(time);
				lblFecha.setText(df.format(date));
			}
		});
	}

	private void calcularPrecioPuntosPorLista() {
		String nombreUsuario = (String) cboUsuario.getSelectedItem();
		String nombreListaCompra = (String) cboListaCompra.getSelectedItem();

		if (nombreUsuario != null && nombreListaCompra != null) {
			int idUsuario = crudUsuario.obtenerIdUsuarioPorNombre(nombreUsuario);
			int idListaCompra = crudListaCompra.obtenerIdListaCompraPorNombreYUsuario(nombreListaCompra, nombreUsuario);

			double precioTotal = 0;
			int puntosUtilizados = 0;

			List<ordenProducto> ordenProductos = crudOrdenProducto.obtenerOrdenProductosPorUsuarioYLista(idUsuario,
					idListaCompra);

			for (ordenProducto ordenProducto : ordenProductos) {
				String nombreProducto = crudOrdenProducto.obtenerNombreProductoPorId(ordenProducto.getId_Producto());
				int cantidad = ordenProducto.getCantidad();

				double precioProducto = crudProductos.obtenerPrecioProducto(nombreProducto);
				int puntosProducto = crudMaquina.obtenerPuntosPorProducto(ordenProducto.getId_Producto());

				precioTotal += (precioProducto * cantidad);
				puntosUtilizados += (puntosProducto * cantidad);
			}
			DecimalFormat df = new DecimalFormat("#.##");
			String precioTotalFormateado = df.format(precioTotal);
			txtPrecioTotal.setText(String.valueOf(precioTotalFormateado));
			txtTotalPuntos.setText(String.valueOf(puntosUtilizados));
		}
	}
}
