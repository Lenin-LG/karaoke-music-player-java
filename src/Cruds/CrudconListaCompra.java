package Cruds;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import GetaSet.listacompra;
import GetaSet.usuario;
import Metodos.CrudListaCompra;
import Metodos.CrudUsuario;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CrudconListaCompra extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtNombreLista;
	listacompra lisc = new listacompra();
	CrudListaCompra dao = new CrudListaCompra();
	DefaultTableModel tablemodel;
	int fila = -1;
	List<listacompra> list;
	private JComboBox<String> cboUsuario;
	private JLabel lblID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrudconListaCompra frame = new CrudconListaCompra();
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
	public CrudconListaCompra() {
		tablemodel = new DefaultTableModel();
		tablemodel.addColumn("id_ListaCompra");
		tablemodel.addColumn("id_usuario");
		tablemodel.addColumn("NombreLista");

		CrudUsuario usuario = new CrudUsuario();
		List<usuario> user = usuario.consultaUsuarios();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 629, 588);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(64, 236, 496, 302);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = table.getSelectedRow();
				lisc = list.get(fila);
				lblID.setText(String.valueOf(lisc.getId_ListaCompra()));
				cboUsuario.setSelectedItem(String.valueOf(lisc.getId_Usuario()));
				txtNombreLista.setText(lisc.getNombrelista());
				txtNombreLista.setEditable(true);
			}
		});
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(21, 35, 46, 14);
		contentPane.add(lblNewLabel);

		lblID = new JLabel("");
		lblID.setBounds(104, 35, 46, 14);
		contentPane.add(lblID);

		JLabel lblNewLabel_1 = new JLabel("Usuario:");
		lblNewLabel_1.setBounds(21, 69, 46, 14);
		contentPane.add(lblNewLabel_1);

		cboUsuario = new JComboBox<>();
		cboUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarNombreLista();
			}
		});
		cboUsuario.setBounds(104, 65, 97, 22);
		contentPane.add(cboUsuario);
		for (usuario usuario2 : user) {
			cboUsuario.addItem(usuario2.getUser());
		}
		actualizarNombreLista();
		JLabel lblNewLabel_2 = new JLabel("Nombrelista");
		lblNewLabel_2.setBounds(21, 111, 89, 14);
		contentPane.add(lblNewLabel_2);

		txtNombreLista = new JTextField();
		txtNombreLista.setEditable(false);
		txtNombreLista.setBounds(104, 108, 96, 20);
		contentPane.add(txtNombreLista);
		txtNombreLista.setColumns(10);
		table.setModel(tablemodel);

		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (cboUsuario.getSelectedItem() == null) {
						return;
					}

					String nombreUsuario = cboUsuario.getSelectedItem().toString();

					listacompra nuevaLista = new listacompra();
					nuevaLista.setId_Usuario(dao.obtenerIdUsuarioPorNombre(nombreUsuario));

					if (dao.insertarListaCompra(nuevaLista)) {
						ActualizarTabla();
						limpiarCampos();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnInsertar.setBounds(442, 31, 89, 23);
		contentPane.add(btnInsertar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fila == -1) {
						JOptionPane.showMessageDialog(null, "Selecciona una lista para actualizar");
						return;
					}

					if (txtNombreLista.getText().equals("") || cboUsuario.getSelectedItem() == null) {
						JOptionPane.showMessageDialog(null,
								"Los campos Nombre de la lista y Usuario no pueden estar vacíos");
						return;
					}

					int idListaCompra = Integer.parseInt(lblID.getText());
					String nombreLista = txtNombreLista.getText();
					String nombreUsuario = cboUsuario.getSelectedItem().toString();
					int idUsuario = dao.obtenerIdUsuarioPorNombre(nombreUsuario);

					listacompra listaCompraActualizada = new listacompra();
					listaCompraActualizada.setId_ListaCompra(idListaCompra);
					listaCompraActualizada.setId_Usuario(idUsuario);
					listaCompraActualizada.setNombrelista(nombreLista);

					if (dao.actualizarListaCompra(listaCompraActualizada)) {
						ActualizarTabla();
						limpiarCampos();
						JOptionPane.showMessageDialog(null, "Se actualizó la lista correctamente!!");
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un error al actualizar la lista");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Error al procesar los datos. Asegúrate de ingresar valores válidos.");
				}
			}
		});
		btnModificar.setBounds(442, 89, 89, 23);
		contentPane.add(btnModificar);

		JButton btnEliminar = new JButton("eliminar ");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fila = table.getSelectedRow();

					if (fila == -1) {
						return;
					}

					int idListaCompra = (int) tablemodel.getValueAt(fila, 0);

					int confirmacion = JOptionPane.showConfirmDialog(null,
							"¿Estás seguro de eliminar esta lista de compra?", "Confirmar eliminación",
							JOptionPane.YES_NO_OPTION);

					if (confirmacion == JOptionPane.YES_OPTION) {
						if (dao.eliminarListaCompra(idListaCompra)) {
							ActualizarTabla();
							limpiarCampos();
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(442, 142, 89, 23);
		contentPane.add(btnEliminar);
		ActualizarTabla();

	}

	public void ActualizarTabla() {
		while (tablemodel.getRowCount() > 0) {
			tablemodel.removeRow(0);
		}
		list = dao.obtenerTodasListasCompra();
		for (listacompra listacompra : list) {
			Object row[] = new Object[3];
			row[0] = listacompra.getId_ListaCompra();
			row[1] = listacompra.getId_Usuario();
			row[2] = listacompra.getNombrelista();
			tablemodel.addRow(row);
		}
		table.setModel(tablemodel);
	}

	private void actualizarNombreLista() {
		try {
			if (cboUsuario.getSelectedItem() == null) {
				return;
			}
			String nombreUsuario = cboUsuario.getSelectedItem().toString();

			String proximoNombreLista = dao.generarNombreLista(nombreUsuario);

			txtNombreLista.setText(proximoNombreLista);

		} catch (Exception ex) {

		}
	}

	private void limpiarCampos() {
		txtNombreLista.setText("");
		cboUsuario.setSelectedIndex(-1);
		lblID.setText("");
	}

}
