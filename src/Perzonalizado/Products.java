package Perzonalizado;

import javax.swing.JPanel;
import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JSpinner;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.SpinnerNumberModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Products extends JPanel {
	public JLabel lblNombre;
	public JLabel lblPrecio;
	public JSpinner spnCant;
	private JLabel lblimg;

	public JCheckBox chckbxNewCheckBox;

	/**
	 * Create the panel.
	 */
	public Products() {
		setBackground(Color.WHITE);

		JLabel lblNewLabel_2 = new JLabel("Precio :");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		JLabel lblNewLabel_3 = new JLabel("Cantidad");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		JLabel lblNewLabel_4 = new JLabel("Canjear");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		lblNombre = new JLabel("Pepsi");
		lblNombre.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		lblPrecio = new JLabel("S/.5.0");
		lblPrecio.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		spnCant = new JSpinner();
		spnCant.setModel(new SpinnerNumberModel(0, 0, 50, 1));

		chckbxNewCheckBox = new JCheckBox("");

		lblimg = new JLabel("");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel_4))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(chckbxNewCheckBox)
								.addComponent(spnCant, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup().addComponent(lblNewLabel_2).addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
										.addComponent(lblPrecio))))
				.addGap(62)).addComponent(lblimg, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addComponent(lblimg, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblNombre)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout
						.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_2).addComponent(lblPrecio))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_3).addComponent(
						spnCant, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED).addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_4).addComponent(chckbxNewCheckBox))
				.addContainerGap(17, Short.MAX_VALUE)));
		setLayout(groupLayout);

	}

	public void addCheckboxListener(ActionListener listener) {
		chckbxNewCheckBox.addActionListener(listener);
	}

	public void setProductName(String productName) {
		lblNombre.setText(productName);
	}

	public void setPrecio(String precio) {

		lblPrecio.setText(precio);

	}

	public void setCero() {
		spnCant.setValue(0);
	}

	public void setImage(String imagePath) {
		ImageIcon newImageIcon = new ImageIcon(getClass().getResource(imagePath));
		Image img = newImageIcon.getImage().getScaledInstance(lblimg.getWidth(), lblimg.getHeight(),
				Image.SCALE_SMOOTH);
		lblimg.setIcon(new ImageIcon(img));
	}

	public boolean qtyIsZero(int qty) {

		if (qty == 0) {
			JOptionPane.showMessageDialog(null, "Porfavor incremente algo");
			return false;
		}
		return true;
	}

}