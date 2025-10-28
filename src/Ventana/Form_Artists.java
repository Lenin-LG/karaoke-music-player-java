package Ventana;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.net.URL;

import javax.swing.LayoutStyle.ComponentPlacement;
import Paneles.MostPopular;
import Paneles.Music;
import GetaSet.Model_Popular;

public class Form_Artists extends JPanel {
	MostPopular popu = new MostPopular();
	Music music = new Music();

	/**
	 * Create the panel.
	 */
	public Form_Artists() {
		setBackground(Color.WHITE);

		JLabel lblNewLabel = new JLabel("Artista");
		lblNewLabel.setForeground(new Color(102, 153, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblNewLabel_1 = new JLabel("Mas popular");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		add(popu);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup().addGap(29)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(lblNewLabel).addComponent(lblNewLabel_1)))
										.addGroup(groupLayout.createSequentialGroup().addGap(18).addComponent(music,
												GroupLayout.PREFERRED_SIZE, 488, GroupLayout.PREFERRED_SIZE))
										.addComponent(popu, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE))
								.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(21).addComponent(lblNewLabel).addGap(11)
						.addComponent(lblNewLabel_1).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(popu, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(music, GroupLayout.PREFERRED_SIZE, 628, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(48, Short.MAX_VALUE)));
		setLayout(groupLayout);
		init();
	}

	private void init() {
		popu.addImage(new Model_Popular(new ImageIcon(getClass().getResource("/icon/test/coyote.jpg")),
				"Coyote of theory ", "15 albums | 17.5M Follow"));
		popu.addImage(new Model_Popular(new ImageIcon(getClass().getResource("/icon/test/kygo.png")),
				"Kygo and Alan walker   ", "15 albums | 17.5M Follow"));
		popu.addImage(new Model_Popular(new ImageIcon(getClass().getResource("/icon/test/ed-sheeran.jpg")),
				"Ed sheeran ", "15 albums | 17.5M Follow"));
		popu.addImage(new Model_Popular(new ImageIcon(getClass().getResource("/icon/test/sweat.jpg")), "Pink Sweats ",
				"15 albums | 17.5M Follow"));

	}

}
