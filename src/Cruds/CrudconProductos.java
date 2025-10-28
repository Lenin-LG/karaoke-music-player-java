package Cruds;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import GetaSet.Productos;
import Metodos.CrudProductos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CrudconProductos extends JFrame {

	private JPanel contentPane;
	private JLabel lblID;
	private JTextField txtProducto;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JTable table;
	int fila = -1;
	DefaultTableModel tablemodel;
	Productos productos = new Productos();
	CrudProductos dao = new CrudProductos();
	List<Productos> pro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrudconProductos frame = new CrudconProductos();
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
	public CrudconProductos() {
		tablemodel = new DefaultTableModel();
		tablemodel.addColumn("id_Producto");
		tablemodel.addColumn("Nombre");
		tablemodel.addColumn("Stock");
		tablemodel.addColumn("Precio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(48, 63, 46, 14);
		contentPane.add(lblNewLabel);

		lblID = new JLabel("");
		lblID.setBounds(149, 63, 46, 14);
		contentPane.add(lblID);

		JLabel lblNewLabel_1 = new JLabel("Nombre Producto");
		lblNewLabel_1.setBounds(48, 88, 83, 14);
		contentPane.add(lblNewLabel_1);

		txtProducto = new JTextField();
		txtProducto.setBounds(142, 88, 86, 20);
		contentPane.add(txtProducto);
		txtProducto.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Stock");
		lblNewLabel_2.setBounds(48, 123, 46, 14);
		contentPane.add(lblNewLabel_2);

		txtStock = new JTextField();
		txtStock.setBounds(142, 120, 86, 20);
		contentPane.add(txtStock);
		txtStock.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Precio");
		lblNewLabel_3.setBounds(48, 157, 46, 14);
		contentPane.add(lblNewLabel_3);

		txtPrecio = new JTextField();
		txtPrecio.setBounds(142, 151, 86, 20);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(69, 252, 539, 302);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = table.getSelectedRow();
				productos = pro.get(fila);

				lblID.setText(String.valueOf(productos.getId_Producto()));
				txtProducto.setText(productos.getNombre());
				txtStock.setText(String.valueOf(productos.getStock()));
				txtPrecio.setText(String.valueOf(productos.getPrecio()));
			}
		});
		scrollPane.setViewportView(table);

		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtProducto.getText().equals("") || txtStock.getText().equals("")
							|| txtPrecio.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Los campos Nombre, Stock y Precio no pueden estar vacíos");
						return;
					}

					String nombreProducto = txtProducto.getText();
					int stock = Integer.parseInt(txtStock.getText());
					double precio = Double.parseDouble(txtPrecio.getText());

					productos.setNombre(nombreProducto);
					productos.setStock(stock);
					productos.setPrecio(precio);

					if (dao.insertarProducto(productos)) {
						actualizarTabla();
						limpiarCampos();
						JOptionPane.showMessageDialog(null, "Se agregó el producto correctamente!!");
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un error al agregar el producto");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Error al procesar los datos. Asegúrate de ingresar valores válidos.");
				}
			}
		});
		btnInsertar.setBounds(478, 47, 89, 23);
		contentPane.add(btnInsertar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fila == -1) {
						JOptionPane.showMessageDialog(null, "Selecciona un producto para actualizar");
						return;
					}

					if (txtProducto.getText().equals("") || txtStock.getText().equals("")
							|| txtPrecio.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Los campos Nombre, Stock y Precio no pueden estar vacíos");
						return;
					}

					int id = Integer.parseInt(lblID.getText());
					String nombreProducto = txtProducto.getText();
					int stock = Integer.parseInt(txtStock.getText());
					double precio = Double.parseDouble(txtPrecio.getText());

					productos.setId_Producto(id);
					productos.setNombre(nombreProducto);
					productos.setStock(stock);
					productos.setPrecio(precio);

					if (dao.editarProducto(productos)) {
						actualizarTabla();
						limpiarCampos();
						JOptionPane.showMessageDialog(null, "Se actualizó el producto correctamente!!");
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un error al actualizar el producto");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Error al procesar los datos. Asegúrate de ingresar valores válidos.");
				}
			}
		});
		btnModificar.setBounds(478, 88, 89, 23);
		contentPane.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (fila == -1) {
						JOptionPane.showMessageDialog(null, "Selecciona un producto para eliminar");
						return;
					}

					int confirm = JOptionPane.showConfirmDialog(null,
							"¿Estás seguro de que quieres eliminar este producto?", "Confirmar eliminación",
							JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						int idProducto = Integer.parseInt(lblID.getText());

						if (dao.eliminarProducto(idProducto)) {
							actualizarTabla();
							limpiarCampos();
							JOptionPane.showMessageDialog(null, "Producto eliminado correctamente");
						} else {
							JOptionPane.showMessageDialog(null, "Hubo un error al eliminar el producto");
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Error al procesar los datos. Asegúrate de seleccionar un producto válido.");
				}
			}
		});
		btnEliminar.setBounds(478, 135, 89, 23);
		contentPane.add(btnEliminar);
		table.setModel(tablemodel);
		actualizarTabla();
	}

	public void actualizarTabla() {
		while (tablemodel.getRowCount() > 0) {
			tablemodel.removeRow(0);
		}
		pro = dao.consultaProductos();
		for (Productos productos : pro) {
			Object row[] = new Object[4];
			row[0] = productos.getId_Producto();
			row[1] = productos.getNombre();
			row[2] = productos.getStock();
			row[3] = productos.getPrecio();
			tablemodel.addRow(row);
		}

		table.setModel(tablemodel);

	}

	public void limpiarCampos() {
		lblID.setText("");
		txtProducto.setText("");
		txtStock.setText("");
		txtPrecio.setText("");
	}

}
