package Cruds;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Metodos.CrudUsuario;
import GetaSet.usuario;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CrudconfUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtuser;
	private JTextField txtpassword;
	private JTextField txtnombre;
	private JTable table;
	private JButton btnAgregar;
	private JButton btneliminar;
	private JButton btnactualizar;
	private JButton btnlimpiar;
	CrudUsuario dao = new CrudUsuario();
	private JLabel lblPuntos;
	private JTextField txtpuntos;
	DefaultTableModel modelo = new DefaultTableModel();
	ArrayList<usuario> lista;
	int fila = -1;
	usuario usuario = new usuario();
	private JLabel lblid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrudconfUsuario frame = new CrudconfUsuario();
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
	public void limpiar() {
		lblid.setText("");
		lblPuntos.setText("");
		txtuser.setText("");
		txtpassword.setText("");
		txtnombre.setText("");
		txtpuntos.setText("");

	}

	public void actualizarTabla() {

		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.consultaUsuarios();
		for (usuario u : lista) {
			Object user[] = new Object[5];
			user[0] = u.getId();
			user[1] = u.getUser();
			user[2] = u.getPacssword();
			user[3] = u.getNombre();
			user[4] = u.getPuntos();
			modelo.addRow(user);
		}
		table.setModel(modelo);
	}

	public CrudconfUsuario() {
		setTitle("CRUD USUARIOS");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CrudconfUsuario.class.getResource("/img/perfil1.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 568);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(31, 43, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblUser = new JLabel("USER:");
		lblUser.setBounds(31, 91, 46, 14);
		contentPane.add(lblUser);

		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setBounds(31, 141, 71, 14);
		contentPane.add(lblPassword);

		JLabel lblNombre = new JLabel("CORREO:");
		lblNombre.setBounds(31, 196, 87, 14);
		contentPane.add(lblNombre);

		lblid = new JLabel("0");
		lblid.setBounds(102, 43, 46, 14);
		contentPane.add(lblid);

		txtuser = new JTextField();
		txtuser.setBounds(100, 88, 154, 20);
		contentPane.add(txtuser);
		txtuser.setColumns(10);

		txtpassword = new JTextField();
		txtpassword.setColumns(10);
		txtpassword.setBounds(100, 138, 154, 20);
		contentPane.add(txtpassword);

		txtnombre = new JTextField();
		txtnombre.setColumns(10);
		txtnombre.setBounds(100, 193, 154, 20);
		contentPane.add(txtnombre);

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtuser.getText().equals("") || txtpassword.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Campos vacios ");
						return;
					}
					usuario user = new usuario();
					user.setUser(txtuser.getText());
					user.setPacssword(txtpassword.getText());
					user.setNombre(txtnombre.getText());
					user.setPuntos(Integer.parseInt(txtpuntos.getText()));
					if (dao.insertarUsuario(user)) {
						actualizarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "Se Agrego correctamente!!");
					} else {
						JOptionPane.showMessageDialog(null, "ERROR");
					}
				} catch (Exception e2) {

					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}
		});
		btnAgregar.setBounds(98, 261, 89, 23);
		contentPane.add(btnAgregar);

		btneliminar = new JButton("Eliminar");
		btneliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					int opcion = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar este usuario?",
							"ELiminar usuario", JOptionPane.YES_NO_OPTION);
					if (opcion == 0) {
						if (dao.eliminarUsuario(usuario.getId()) && usuario.getId() > 0) {
							actualizarTabla();
							limpiar();
							JOptionPane.showMessageDialog(null, "Se elimino correctamente");
						} else {
							JOptionPane.showMessageDialog(null, "ERROR");
						}
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "ERROR");
				}

			}
		});
		btneliminar.setBounds(102, 315, 89, 23);
		contentPane.add(btneliminar);

		btnactualizar = new JButton("Actualizar");
		btnactualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if (txtuser.getText().equals("") || txtpassword.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Campos vacios ");
						return;
					}

					usuario.setUser(txtuser.getText());
					usuario.setPacssword(txtpassword.getText());
					usuario.setNombre(txtnombre.getText());
					usuario.setPuntos(Integer.parseInt(txtpuntos.getText()));
					if (dao.editarUsuario(usuario)) {
						actualizarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "Se Actualizo correctamente!!");
					} else {
						JOptionPane.showMessageDialog(null, "ERROR");
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "ERROR");
				}

			}
		});
		btnactualizar.setBounds(98, 378, 89, 23);
		contentPane.add(btnactualizar);

		btnlimpiar = new JButton("Limpiar");
		btnlimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				limpiar();
			}
		});
		btnlimpiar.setBounds(98, 424, 89, 23);
		contentPane.add(btnlimpiar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(299, 24, 363, 494);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = table.getSelectedRow();
				usuario = lista.get(fila);
				lblid.setText("" + usuario.getId());
				txtuser.setText(usuario.getUser());
				txtpassword.setText(usuario.getPacssword());
				txtnombre.setText(usuario.getNombre());
				txtpuntos.setText("" + usuario.getPuntos());
			}
		});
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null }, { null, null, null, null, null },
						{ null, null, null, null, null }, },
				new String[] { "New column", "New column", "New column", "New column", "New column" }));
		scrollPane.setViewportView(table);

		lblPuntos = new JLabel("Puntos:");
		lblPuntos.setBounds(31, 221, 87, 14);
		contentPane.add(lblPuntos);

		txtpuntos = new JTextField();
		txtpuntos.setColumns(10);
		txtpuntos.setBounds(100, 218, 154, 20);
		contentPane.add(txtpuntos);

		JButton btnlogin = new JButton("Refresh");
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTabla();

			}
		});
		btnlogin.setBounds(98, 473, 89, 23);
		contentPane.add(btnlogin);
		modelo.addColumn("ID");
		modelo.addColumn("Usuario");
		modelo.addColumn("PASSWORD");
		modelo.addColumn("CORREO");
		modelo.addColumn("PUNTOS");
		actualizarTabla();
	}
}
