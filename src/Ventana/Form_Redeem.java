package Ventana;

import javax.swing.JPanel;
import Perzonalizado.Products;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

public class Form_Redeem extends JPanel {
	private boolean karaokeCalledAfterRestart = false;
	private JTextField txtTax;
	private JTextField txtSub;
	private JTextField txtTotal;
	private Products pro;
	List<Products> productosList = new ArrayList<>();
	private JLabel lbltime;
	private JLabel lbldate;
	private JTextArea txtArea;
	private double total = 0.0;
	private int x = 0;
	private double impuesto = 0.0;

	/**
	 * Create the panel.
	 */
	public Form_Redeem() {

		setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		setBackground(Color.LIGHT_GRAY);

		pro = new Products();

		Products pro1 = new Products();
		pro1.setProductName("Coca-Cola");
		pro1.setPrecio("S/.4.0");

		Products pro2 = new Products();
		pro2.setProductName("Guarana");
		pro2.setPrecio("S/.3.5");

		Products pro3 = new Products();
		pro3.setProductName("Rellenita");
		pro3.setPrecio("S/.1.0");

		Products pro4 = new Products();
		pro4.setProductName("Coffe");
		pro4.setPrecio("S/.3.0");

		Products pro5 = new Products();
		pro5.setProductName("Margirata");
		pro5.setPrecio("S/.1.5");

		Products pro6 = new Products();
		pro6.setProductName("Pizaa");
		pro6.setPrecio("S/.7.0");

		Products pro7 = new Products();
		pro7.setProductName("Doritos");
		pro7.setPrecio("S/.2.0");

		Products pro8 = new Products();
		pro8.setProductName("KFC");
		pro8.setPrecio("S/.18.0");

		JPanel panel = new JPanel();

		JPanel panel_1 = new JPanel();

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);

		productosList.add(pro);
		productosList.add(pro1);
		productosList.add(pro2);
		productosList.add(pro3);
		productosList.add(pro4);
		productosList.add(pro5);
		productosList.add(pro6);
		productosList.add(pro7);
		productosList.add(pro8);
		for (Products product : productosList) {
			product.addCheckboxListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					int qty = Integer.parseInt(product.spnCant.getValue().toString());
					if (qtyIsZero(qty) && product.chckbxNewCheckBox.isSelected()) {
						x++;
						if (!karaokeCalledAfterRestart) {

							Karaoke();
							karaokeCalledAfterRestart = true;
						}
						double price = qty * Double.parseDouble(product.lblPrecio.getText().replace("S/.", ""));
						txtArea.setText(
								txtArea.getText() + x + ". " + product.lblNombre.getText() + "\t\t" + price + "\n");

						total += price;
						getImpuesto(total);
						insert();
					} else {
						product.chckbxNewCheckBox.setSelected(false);
					}
				}
			});
		}

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(pro, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(pro1,
												GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(pro3, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(pro4,
												GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(pro6, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(pro7,
												GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(pro2, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
								.addComponent(pro5, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
								.addComponent(pro8, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)))
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 556, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(19, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(19)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false).addGroup(groupLayout
						.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(pro1, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
								.addComponent(pro, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
								.addComponent(pro2, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(pro3, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
								.addComponent(pro4, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
								.addComponent(pro5, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(pro6, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
								.addComponent(pro7, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
								.addComponent(pro8, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 705, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(51, Short.MAX_VALUE)));

		lbldate = new JLabel("");

		lbltime = new JLabel("");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(lbldate, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE).addGap(18)
						.addComponent(lbltime, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(lbltime, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
				.addComponent(lbldate, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE));
		panel.setLayout(gl_panel);
		SwingUtilities.invokeLater(() -> setImageForAllPanels());
		txtArea = new JTextArea();

		JLabel lblNewLabel = new JLabel("Impuesto");

		JLabel lblNewLabel_1 = new JLabel("Sub Total");

		JLabel lblNewLabel_2 = new JLabel("Total");

		txtTax = new JTextField();
		txtTax.setText("0.0");
		txtTax.setColumns(10);

		txtSub = new JTextField();
		txtSub.setText("0.0");
		txtSub.setColumns(10);

		txtTotal = new JTextField();
		txtTotal.setText("0.0");
		txtTotal.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addComponent(txtArea, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
				.addGroup(gl_panel_2.createSequentialGroup().addContainerGap().addComponent(lblNewLabel).addGap(18)
						.addComponent(txtTax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(78, Short.MAX_VALUE))
				.addGroup(gl_panel_2.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_2.createSequentialGroup().addComponent(lblNewLabel_2).addGap(39))
								.addGroup(gl_panel_2.createSequentialGroup().addComponent(lblNewLabel_1).addGap(18)))
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(txtSub, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(78, Short.MAX_VALUE)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
						.addComponent(txtArea, GroupLayout.PREFERRED_SIZE, 515, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtTax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
						.addGap(27)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtSub, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1))
						.addGap(37)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(txtTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2))
						.addContainerGap(38, Short.MAX_VALUE)));
		panel_2.setLayout(gl_panel_2);

		JButton btnNewButton = new JButton("Total");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (total == 0.0) {
					JOptionPane.showMessageDialog(null, "No has seleccionado ningun producto");
				} else {
					txtArea.setText(txtArea.getText() + "\n***********************************************\n"
							+ "Impuesto:\t\t" + impuesto + "\n" + "Sub total:\t\t" + total + "\n" + "Total:\t\t"
							+ (total - (total * impuesto)) + "\n\n"
							+ "****************Muchas Gracias*****************\n");
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));

		JButton btnNewButton_1 = new JButton("Boleta");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (total != 0) {
					try {
						txtArea.print();
					} catch (PrinterException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "No has seleccionado ningun producto");
				}
			}
		});
		btnNewButton_1.setFont(new Font("Trebuchet MS", Font.BOLD, 20));

		JButton btnReiniciar = new JButton("Reiniciar");
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Products product : productosList) {
					product.setCero();
					product.chckbxNewCheckBox.setSelected(false);
				}
				total = 0.0;
				x = 0;
				impuesto = 0.0;
				txtSub.setText("0.0");
				txtTax.setText("0.0");
				txtTotal.setText("0.0");
				txtArea.setText("");
				karaokeCalledAfterRestart = false;
			}
		});
		btnReiniciar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap().addComponent(btnNewButton).addGap(26)
						.addComponent(btnNewButton_1).addGap(18).addComponent(btnReiniciar)
						.addContainerGap(223, Short.MAX_VALUE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGap(24)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(btnReiniciar)
								.addComponent(btnNewButton_1).addComponent(btnNewButton))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		panel_1.setLayout(gl_panel_1);
		setLayout(groupLayout);
		setTime();
	}

	public void getImpuesto(double total) {
		if (00 < total && total <= 30) {
			impuesto = 0.01;
		} else if (total > 30.0 && total <= 60.0) {
			impuesto = 0.05;
		} else if (total > 60.0 && total < 90.0) {
			impuesto = 0.10;
		} else if (total > 90.0 && total < 120.0) {
			impuesto = .20;
		} else if (total > 130.0 && total < 160.0) {
			impuesto = 0.30;
		} else if (total > 160.0 && total < 200.0) {
			impuesto = 0.40;
		} else {
			impuesto = 0.50;
		}
	}

	public void insert() {

		txtSub.setText(String.valueOf(String.format("%.2f", total)));
		txtTax.setText(String.valueOf(String.format("%.2f", impuesto)));
		txtTotal.setText(String.valueOf(String.format("%.2f", total - (total * impuesto))));
	}

	public void Karaoke() {
		txtArea.setText("********************Karaoke*******************\n" + "Hora: " + lbltime.getText() + "Dia: "
				+ lbldate.getText() + "\n" + "***********************************************\n"
				+ "Nombre del Producto:\t" + "Precio(S/.)\n");
	}

	public boolean qtyIsZero(int qty) {

		if (qty == 0) {
			JOptionPane.showMessageDialog(null, "Porfavor incremente algo");
			return false;
		}
		return true;
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
				SimpleDateFormat df = new SimpleDateFormat("EEEE, dd-MM-yyyy");
				String time = tf.format(date);
				lbltime.setText(time);
				lbldate.setText(df.format(date));
			}
		});
	}

	private void setImageForAllPanels() {
		int i = 1;
		for (Products producto : productosList) {
			producto.setImage("/icon/test/sigala.jpg");
			i++;
		}
	}

}
