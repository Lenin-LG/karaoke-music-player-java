package Ventana;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import Paneles.MostPopular;
import GetaSet.Model_Popular;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Form_Albums extends JPanel {
	private MostPopular popu;
	private MostPopular popu_1;
	private MostPopular popu_2;

	/**
	 * Create the panel.
	 */
	public Form_Albums() {
		setBackground(Color.WHITE);

		JLabel lblNewLabel = new JLabel("Artista");
		lblNewLabel.setForeground(new Color(102, 153, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblNewLabel_1 = new JLabel("Mas popular");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lblNewLabel_1_1 = new JLabel("Romanticas");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lblNewLabel_1_1_1 = new JLabel("Ingles");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));

		popu = new MostPopular();

		popu_1 = new MostPopular();

		popu_2 = new MostPopular();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblNewLabel, Alignment.LEADING)
												.addComponent(lblNewLabel_1, Alignment.LEADING)
												.addComponent(lblNewLabel_1_1, Alignment.LEADING,
														GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel_1_1_1, Alignment.LEADING,
														GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
										.addContainerGap(405, Short.MAX_VALUE))
								.addComponent(popu, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE))
						.addComponent(popu_1, GroupLayout.PREFERRED_SIZE, 506, Short.MAX_VALUE))
						.addComponent(popu_2, GroupLayout.PREFERRED_SIZE, 506, Short.MAX_VALUE))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblNewLabel)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewLabel_1).addGap(18)
						.addComponent(popu, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE).addGap(34)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(popu_1, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
						.addGap(39)
						.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(popu_2, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(69, Short.MAX_VALUE)));
		setLayout(groupLayout);
		init();
		initr();
		initi();
	}

	private void init() {
		popu.addImage(new Model_Popular(new ImageIcon(getClass().getResource("/icon/test/sweat.jpg")), "Pink Sweats ",
				"15 albums | 17.5M Follow"));
		popu.addImage(new Model_Popular(new ImageIcon(getClass().getResource("/icon/test/kygo.png")),
				"Kygo and Alan walker   ", "15 albums | 17.5M Follow"));
		popu.addImage(new Model_Popular(new ImageIcon(getClass().getResource("/icon/test/ed-sheeran.jpg")),
				"Ed sheeran ", "15 albums | 17.5M Follow"));
		popu.addImage(new Model_Popular(new ImageIcon(getClass().getResource("/icon/test/james.jpg")), "James Arthur ",
				"15 albums | 17.5M Follow"));

	}

	private void initr() {
		popu_1.addImage(new Model_Popular(new ImageIcon(getClass().getResource("/IMG/rioroma.jpg")),
				"Rio Roma True Stories ", "15 albums | 17.5M Follow"));
		popu_1.addImage(new Model_Popular(new ImageIcon(getClass().getResource("/icon/test/camilo.jpg")), "Camilo  ",
				"15 albums | 17.5M Follow"));
		popu_1.addImage(new Model_Popular(new ImageIcon(getClass().getResource("/icon/test/romeosantos.jpg")),
				"Romeo Santo ", "15 albums | 17.5M Follow"));
		popu_1.addImage(new Model_Popular(new ImageIcon(getClass().getResource("/icon/test/sebastianyatra.jpg")),
				"Sebastian Yatra ", "15 albums | 17.5M Follow"));

	}

	private void initi() {
		popu_2.addImage(new Model_Popular(new ImageIcon(getClass().getResource("/icon/test/james.jpg")),
				"Avicii True Stories ", "15 albums | 17.5M Follow"));
		popu_2.addImage(new Model_Popular(new ImageIcon(getClass().getResource("/icon/test/coyote.jpg")),
				"Coyote Theory  ", "15 albums | 17.5M Follow"));
		popu_2.addImage(new Model_Popular(new ImageIcon(getClass().getResource("/icon/test/ed-sheeran.jpg")),
				"Ed sheeran ", "15 albums | 17.5M Follow"));
		popu_2.addImage(new Model_Popular(new ImageIcon(getClass().getResource("/icon/test/sweat.jpg")), "Pink Sweats ",
				"15 albums | 17.5M Follow"));

	}
}
