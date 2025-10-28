package Cruds;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import GetaSet.usuario;
import Metodos.CrudAdministrador;
import Metodos.CrudUsuario;
import GetaSet.administrador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrudconAdministrador extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel tablemodel;
	private JComboBox<String> cboUsuario;
	private JComboBox<String> cboCargo;
	int fila = -1;
	List<administrador> administradores;
	CrudAdministrador dao = new CrudAdministrador();
	administrador administrador = new administrador();
	private JLabel lblID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrudconAdministrador frame = new CrudconAdministrador();
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
	public CrudconAdministrador() {
		tablemodel = new DefaultTableModel();
		tablemodel.addColumn("ID Admin");
		tablemodel.addColumn("ID Usuario");
		tablemodel.addColumn("Cargo");
		CrudUsuario crudUsuario = new CrudUsuario();
		List<usuario> usuario = crudUsuario.consultaUsuarios();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(24, 45, 46, 14);
		contentPane.add(lblNewLabel);

		lblID = new JLabel("");
		lblID.setBounds(51, 45, 46, 14);
		contentPane.add(lblID);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 192, 426, 217);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = table.getSelectedRow();
				administrador = administradores.get(fila);
				lblID.setText(String.valueOf(administrador.getId_Admin()));
				cboUsuario.setSelectedItem(dao.obtenerNombreUsuarioPorId(administrador.getId_Usuario()));
				cboCargo.setSelectedItem(administrador.getCargo());
			}
		});
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_1 = new JLabel("Usuario:");
		lblNewLabel_1.setBounds(24, 70, 63, 14);
		contentPane.add(lblNewLabel_1);

		cboUsuario = new JComboBox();
		cboUsuario.setBounds(97, 70, 96, 22);
		contentPane.add(cboUsuario);
		for (usuario u : usuario) {
			cboUsuario.addItem(u.getUser());
		}

		JLabel lblNewLabel_2 = new JLabel("Cargo");
		lblNewLabel_2.setBounds(24, 111, 46, 14);
		contentPane.add(lblNewLabel_2);

		cboCargo = new JComboBox();
		cboCargo.setBounds(95, 107, 98, 18);
		contentPane.add(cboCargo);
		cboCargo.addItem("Tecnico");
		cboCargo.addItem("Recepcionista");
		cboCargo.addItem("Analista");

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String nombreUsuario = (String) cboUsuario.getSelectedItem();
					String nuevoCargo = (String) cboCargo.getSelectedItem();

					if (nombreUsuario == null || nombreUsuario.isEmpty()) {
						JOptionPane.showMessageDialog(null, "El campo Usuario no puede estar vacío");
						return;
					}

					int idUsuario = dao.obtenerIdUsuarioPorNombre(nombreUsuario);
					if (dao.existeAdministradorParaUsuario(idUsuario)) {
						JOptionPane.showMessageDialog(null, "Ya existe un administrador para este usuario");
						return;
					}
					if (dao.usuarioTieneCargo(idUsuario)) {
						JOptionPane.showMessageDialog(null, "Este usuario ya tiene un cargo asignado");
						return;
					}
					administrador nuevoAdmin = new administrador();
					nuevoAdmin.setId_Usuario(idUsuario);
					nuevoAdmin.setCargo(nuevoCargo);

					if (dao.insertarAdministrador(nuevoAdmin)) {
						actualizarTabla();
						limpiarCampos();
						JOptionPane.showMessageDialog(null, "Se agregó el administrador correctamente!!");
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un error al agregar el administrador");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Error al procesar los datos. Asegúrate de ingresar valores válidos.");
				}
			}
		});
		btnAgregar.setBounds(340, 41, 89, 23);
		contentPane.add(btnAgregar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fila == -1) {
						JOptionPane.showMessageDialog(null, "Selecciona un administrador de la tabla para modificar.");
						return;
					}

					String nombreUsuario = (String) cboUsuario.getSelectedItem();
					String nuevoCargo = (String) cboCargo.getSelectedItem();

					if (nombreUsuario == null || nombreUsuario.isEmpty()) {
						JOptionPane.showMessageDialog(null, "El campo Usuario no puede estar vacío");
						return;
					}

					int idUsuario = dao.obtenerIdUsuarioPorNombre(nombreUsuario);
					if (dao.existeAdministradorParaUsuario(idUsuario)) {
						JOptionPane.showMessageDialog(null, "Ya existe un administrador para este usuario");
						return;
					}
					if (dao.usuarioTieneCargo(idUsuario)) {
						JOptionPane.showMessageDialog(null, "Este usuario ya tiene un cargo asignado");
						return;
					}
					administrador.setId_Usuario(idUsuario);
					administrador.setCargo(nuevoCargo);

					if (dao.actualizarAdministrador(administrador)) {
						actualizarTabla();
						limpiarCampos();
						JOptionPane.showMessageDialog(null, "Se modificó el administrador correctamente!!");
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un error al modificar el administrador");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Error al procesar los datos. Asegúrate de ingresar valores válidos.");
				}
			}
		});
		btnModificar.setBounds(343, 85, 89, 23);
		contentPane.add(btnModificar);

		JButton btnNewButton = new JButton("Eliminar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fila == -1) {
						JOptionPane.showMessageDialog(null, "Selecciona un administrador para eliminar");
						return;
					}

					int confirm = JOptionPane.showConfirmDialog(null,
							"¿Estás seguro de que quieres eliminar este administrador?", "Confirmar eliminación",
							JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						int idAdministrador = Integer.parseInt(lblID.getText());

						if (dao.eliminarAdministrador(idAdministrador)) {
							actualizarTabla();
							limpiarCampos();
							JOptionPane.showMessageDialog(null, "Administrador eliminado correctamente");
						} else {
							JOptionPane.showMessageDialog(null, "Hubo un error al eliminar el administrador");
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Error al procesar los datos. Asegúrate de seleccionar un administrador válido.");
				}
			}
		});
		btnNewButton.setBounds(340, 134, 89, 23);
		contentPane.add(btnNewButton);
		table.setModel(tablemodel);
		actualizarTabla();
	}

	public void limpiarCampos() {
		lblID.setText("");
		cboUsuario.setSelectedIndex(-1);
		cboCargo.setSelectedIndex(0);

	}

	public void actualizarTabla() {
		while (tablemodel.getRowCount() > 0) {
			tablemodel.removeRow(0);

		}
		administradores = dao.obtenerTodosAdministradores();
		for (administrador adm : administradores) {
			Object row[] = new Object[3];
			row[0] = adm.getId_Admin();
			row[1] = adm.getId_Usuario();
			row[2] = adm.getCargo();
			tablemodel.addRow(row);
		}
		table.setModel(tablemodel);
	}

}
