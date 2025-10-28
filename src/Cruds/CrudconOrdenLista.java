package Cruds;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import GetaSet.artista;
import GetaSet.cancion;
import GetaSet.lista;
import GetaSet.ordenLista;
import Metodos.CrudCancion;
import Metodos.CrudLista;
import Metodos.CrudOrdenLista;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JList;
import javax.swing.JCheckBox;

public class CrudconOrdenLista extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtID;
	private JComboBox<String> cboLista;
	private JComboBox<String> cboCancion;
	private JTextField txtPosicion;
	private CrudOrdenLista dao = new CrudOrdenLista();
	int fila = -1;
	ordenLista ordenLista = new ordenLista();
	List<ordenLista> ordenL;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane_1;
	private JCheckBox chckbxNewCheckBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrudconOrdenLista frame = new CrudconOrdenLista();
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
	public CrudconOrdenLista() {
		CrudCancion crudCancion = new CrudCancion();
		List<cancion> cancion = crudCancion.consultaCanciones();
		CrudLista crudLista = new CrudLista();
		List<lista> lista = crudLista.obtenerTodasListas();
		tableModel = new DefaultTableModel();
		tableModel.addColumn("ID Orden");
		tableModel.addColumn("ID Lista");
		tableModel.addColumn("ID Cancion");
		tableModel.addColumn("Posicion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 549, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 225, 423, 227);
		contentPane.add(scrollPane);

		table = new JTable();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = table.getSelectedRow();
				ordenLista = ordenL.get(fila);
				txtID.setText(String.valueOf(ordenLista.getIdOrden()));
				int idLista = ordenLista.getIdLista();
				String listaId = dao.obtenerNombreListaPorId(idLista);
				cboLista.setSelectedItem(listaId);
				int idCancion = ordenLista.getIdCancion();
				String nombreCancion = dao.obtenerNombreCancionPorID(idCancion);
				cboCancion.setSelectedItem(nombreCancion);
				txtPosicion.setText(String.valueOf(ordenLista.getPosicion()));
			}
		});
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("ID :");
		lblNewLabel.setBounds(47, 27, 46, 14);
		contentPane.add(lblNewLabel);

		txtID = new JTextField();
		txtID.setBounds(107, 24, 86, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Lista");
		lblNewLabel_1.setBounds(47, 62, 46, 14);
		contentPane.add(lblNewLabel_1);

		cboLista = new JComboBox();
		cboLista.setBounds(107, 55, 86, 22);
		contentPane.add(cboLista);
		for (lista l : lista) {
			cboLista.addItem(l.getNombreLista());
		}
		DefaultListModel<String> listModelCancionesSeleccionadas = new DefaultListModel<>();
		JList<String> listCancionesSeleccionadas = new JList<>(listModelCancionesSeleccionadas);
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(210, 27, 141, 187);
		scrollPane_1.setViewportView(listCancionesSeleccionadas);
		contentPane.add(scrollPane_1);

		JLabel lblNewLabel_2 = new JLabel("Cancion");
		lblNewLabel_2.setBounds(47, 101, 46, 14);
		contentPane.add(lblNewLabel_2);

		chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(178, 195, 26, 23);
		contentPane.add(chckbxNewCheckBox);

		cboCancion = new JComboBox();
		cboCancion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!chckbxNewCheckBox.isSelected()) {
					String cancionSeleccionada = cboCancion.getSelectedItem().toString();
					listModelCancionesSeleccionadas.addElement(cancionSeleccionada);
				}
			}
		});
		cboCancion.setBounds(107, 97, 86, 22);
		contentPane.add(cboCancion);
		for (cancion c : cancion) {
			cboCancion.addItem(c.getTituloCancion());
		}

		JLabel lblNewLabel_3 = new JLabel("Posicion");
		lblNewLabel_3.setBounds(47, 126, 46, 14);
		contentPane.add(lblNewLabel_3);

		txtPosicion = new JTextField();
		txtPosicion.setBounds(107, 123, 86, 20);
		contentPane.add(txtPosicion);
		txtPosicion.setColumns(10);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nombreLista = cboLista.getSelectedItem().toString();
					int idLista = dao.obtenerIdLista(nombreLista);

					DefaultListModel<String> model = (DefaultListModel<String>) listCancionesSeleccionadas.getModel();
					List<ordenLista> ordenListas = new ArrayList<>();

					for (int i = 0; i < model.getSize(); i++) {
						String nombreCancion = model.getElementAt(i);
						int idCancion = dao.obtenerIdCancion(nombreCancion);

						ordenLista orden = new ordenLista();
						orden.setIdLista(idLista);
						orden.setIdCancion(idCancion);
						orden.setPosicion(i + 1);
						ordenListas.add(orden);
					}
					if (dao.insertarOrdenLista(ordenListas)) {
						actualizarTabla();
						JOptionPane.showMessageDialog(null, "Se agregaron las canciones correctamente!!");
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un error al agregar las canciones");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Error al procesar los datos. Asegúrate de ingresar valores válidos.");
				}

			}
		});
		btnAgregar.setBounds(389, 27, 89, 23);
		contentPane.add(btnAgregar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (chckbxNewCheckBox.isSelected() && listModelCancionesSeleccionadas.isEmpty()) {
						if (fila == -1) {
							JOptionPane.showMessageDialog(null, "Selecciona una orden para actualizar");
							return;
						}

						if (txtID.getText().equals("") || cboLista.getSelectedItem() == null
								|| cboCancion.getSelectedItem() == null || txtPosicion.getText().equals("")) {
							JOptionPane.showMessageDialog(null,
									"Los campos ID, Lista, Canción y Posición no pueden estar vacíos");
							return;
						}

						int id = Integer.parseInt(txtID.getText());
						int idLista = dao.obtenerIdLista(cboLista.getSelectedItem().toString());
						String nombreCancion = cboCancion.getSelectedItem().toString();
						int idCancion = dao.obtenerIdCancion(nombreCancion);
						int posicion = Integer.parseInt(txtPosicion.getText());

						ordenLista ordenActualizada = new ordenLista();
						ordenActualizada.setIdOrden(id);
						ordenActualizada.setIdLista(idLista);
						ordenActualizada.setIdCancion(idCancion);
						ordenActualizada.setPosicion(posicion);

						if (dao.editarOrdenLista(ordenActualizada)) {
							actualizarTabla();
							JOptionPane.showMessageDialog(null, "Se actualizó la orden correctamente!!");
						} else {
							JOptionPane.showMessageDialog(null, "Hubo un error al actualizar la orden");
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"El checkbox debe estar seleccionado y el JList debe estar vacío para modificar.");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Error al procesar los datos. Asegúrate de ingresar valores válidos.");
				}
			}
		});
		btnModificar.setBounds(389, 76, 89, 23);
		contentPane.add(btnModificar);

		JButton btnEliminarJlist = new JButton("Eliminar Item Jlist");
		btnEliminarJlist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selectedIndex = listCancionesSeleccionadas.getSelectedIndex();

				if (selectedIndex != -1) {
					listModelCancionesSeleccionadas.removeElementAt(selectedIndex);
				} else {
					JOptionPane.showMessageDialog(null, "Selecciona un elemento para eliminar", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEliminarJlist.setBounds(389, 166, 115, 23);
		contentPane.add(btnEliminarJlist);

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
						int idOrden = Integer.parseInt(txtID.getText());
						if (dao.eliminarOrdenLista(idOrden)) {
							actualizarTabla();
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
		btnEliminar.setBounds(389, 122, 89, 23);
		contentPane.add(btnEliminar);
		table.setModel(tableModel);
		actualizarTabla();
	}

	public void actualizarTabla() {
		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		ordenL = dao.consultaOrdenLista();

		for (ordenLista orden : ordenL) {
			Object rowData[] = new Object[4];
			rowData[0] = orden.getIdOrden();
			rowData[1] = orden.getIdLista();
			rowData[2] = orden.getIdCancion();
			rowData[3] = orden.getPosicion();

			tableModel.addRow(rowData);
		}
		table.setModel(tableModel);

	}
}
