package Cruds;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import GetaSet.usuario;
import GetaSet.cancion;
import GetaSet.lista;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JButton;

import Metodos.CrudLista;
import Metodos.CrudUsuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CrudconLista extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTable table;
	private JLabel lblNewLabel_1;
	private JTextField txtNombre;
	private JLabel lblNewLabel_2;
	private DefaultTableModel tablemodel;
	private JComboBox<String> cboUsuario;
	CrudLista dao = new CrudLista();
	List<lista> li;
	int fila = -1;
	lista lista = new lista();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrudconLista frame = new CrudconLista();
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
	public CrudconLista() {
		tablemodel = new DefaultTableModel();
		tablemodel.addColumn("ID");
		tablemodel.addColumn("Nombre lista");
		tablemodel.addColumn("id usuario");
		CrudUsuario usuario = new CrudUsuario();
		List<usuario> user = usuario.consultaUsuarios();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 621, 582);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 168, 549, 364);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = table.getSelectedRow();
				lista = li.get(fila);
				txtID.setText(String.valueOf(lista.getIdLista()));
				txtNombre.setText(lista.getNombreLista());
				String nombreUsuario = dao.obtenerNombreUsuarioPorId(lista.getIdUsuario());
				cboUsuario.setSelectedItem(nombreUsuario);
			}
		});
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(44, 39, 46, 14);
		contentPane.add(lblNewLabel);

		txtID = new JTextField();
		txtID.setBounds(88, 36, 86, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);

		JLabel label = new JLabel("New label");
		label.setBounds(279, 39, -225, 55);
		contentPane.add(label);

		lblNewLabel_1 = new JLabel("Nombre de la lista:");
		lblNewLabel_1.setBounds(44, 77, 109, 14);
		contentPane.add(lblNewLabel_1);

		txtNombre = new JTextField();
		txtNombre.setBounds(196, 74, 86, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		lblNewLabel_2 = new JLabel("Nombre usuario:");
		lblNewLabel_2.setBounds(44, 102, 109, 14);
		contentPane.add(lblNewLabel_2);

		cboUsuario = new JComboBox();
		cboUsuario.setBounds(186, 102, 96, 20);
		contentPane.add(cboUsuario);
		for (usuario usuario2 : user) {
			cboUsuario.addItem(usuario2.getUser());
		}

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					if (txtNombre.getText().equals("") || cboUsuario.getSelectedItem() == null) {
						JOptionPane.showMessageDialog(null,
								"Los campos Nombre de la lista y Usuario no pueden estar vacíos");
						return;
					}

					String nombreLista = txtNombre.getText();
					String nombreUsuario = (String) cboUsuario.getSelectedItem();
					int idUsuario = dao.obtenerIdUsuarioPorNombre(nombreUsuario);

					lista nuevaLista = new lista();
					nuevaLista.setNombreLista(nombreLista);
					nuevaLista.setIdUsuario(idUsuario);

					if (dao.insertarLista(nuevaLista)) {
						actualizarTabla();
						limpiarCampos();
						JOptionPane.showMessageDialog(null, "Se agregó la lista correctamente!!");
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un error al agregar la lista");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Error al procesar los datos. Asegúrate de ingresar valores válidos.");
				}
			}
		});
		btnAgregar.setBounds(425, 35, 89, 23);
		contentPane.add(btnAgregar);

		JButton btnModificar = new JButton("modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fila == -1) {
						JOptionPane.showMessageDialog(null, "Selecciona una lista para actualizar");
						return;
					}

					if (txtNombre.getText().equals("") || cboUsuario.getSelectedItem() == null) {
						JOptionPane.showMessageDialog(null,
								"Los campos Nombre de la lista y Usuario no pueden estar vacíos");
						return;
					}

					int id = Integer.parseInt(txtID.getText());
					String nombreLista = txtNombre.getText();
					String nombreUsuario = (String) cboUsuario.getSelectedItem();
					int idUsuario = dao.obtenerIdUsuarioPorNombre(nombreUsuario);

					lista listaActualizada = new lista();
					listaActualizada.setIdLista(id);
					listaActualizada.setNombreLista(nombreLista);
					listaActualizada.setIdUsuario(idUsuario);

					if (dao.actualizarLista(listaActualizada)) {
						actualizarTabla();
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
		btnModificar.setBounds(425, 73, 89, 23);
		contentPane.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fila == -1) {
						JOptionPane.showMessageDialog(null, "Selecciona una lista para eliminar");
						return;
					}

					int confirm = JOptionPane.showConfirmDialog(null,
							"¿Estás seguro de que quieres eliminar esta lista?", "Confirmar eliminación",
							JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						int idLista = Integer.parseInt(txtID.getText());

						if (dao.eliminarLista(idLista)) {
							actualizarTabla();
							limpiarCampos();
							JOptionPane.showMessageDialog(null, "Lista eliminada correctamente");
						} else {
							JOptionPane.showMessageDialog(null, "Hubo un error al eliminar la lista");
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Error al procesar los datos. Asegúrate de seleccionar una lista válida.");
				}

			}
		});
		btnEliminar.setBounds(425, 107, 89, 23);
		contentPane.add(btnEliminar);
		table.setModel(tablemodel);
		actualizarTabla();
	}

	public void actualizarTabla() {

		while (tablemodel.getRowCount() > 0) {
			tablemodel.removeRow(0);
		}
		li = dao.obtenerTodasListas();

		for (lista l : li) {
			Object rowData[] = new Object[3];
			rowData[0] = l.getIdLista();
			rowData[1] = l.getNombreLista();
			rowData[2] = l.getIdUsuario();

			tablemodel.addRow(rowData);
		}
		table.setModel(tablemodel);
	}

	public void limpiarCampos() {
		txtID.setText("");
		txtNombre.setText("");
		cboUsuario.setSelectedIndex(0);
	}
}
