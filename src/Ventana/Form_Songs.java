package Ventana;

import javax.swing.JPanel;

import java.awt.AlphaComposite;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import GetaSet.Model_Music;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import Paneles.ListMusic;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

public class Form_Songs extends JPanel {
	private ListMusic list;

	/**
	 * Create the panel.
	 */
	public Form_Songs() {
		setBorder(null);
		setBackground(Color.WHITE);

		JLabel lblCancionesPreferidas = new JLabel("Canciones Preferidas");
		lblCancionesPreferidas.setForeground(Color.WHITE);
		lblCancionesPreferidas.setFont(new Font("Trebuchet MS", Font.BOLD, 24));

		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				Graphics2D g2d = (Graphics2D) g.create();
				g2d.setComposite(AlphaComposite.SrcOver.derive(0.8f));
				g2d.setColor(getBackground());
				g2d.fillRect(0, 0, getWidth(), getHeight());

				g2d.dispose();
			}
		};
		panel.setOpaque(false);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(31)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 695, GroupLayout.PREFERRED_SIZE).addComponent(
								lblCancionesPreferidas, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(36, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(27)
						.addComponent(lblCancionesPreferidas, GroupLayout.PREFERRED_SIZE, 17,
								GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		list = new ListMusic();
		list.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addComponent(list, GroupLayout.PREFERRED_SIZE, 688, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(19, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addComponent(list, GroupLayout.PREFERRED_SIZE, 645, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		init();
	}

	private void init() {
		list.addItem(new Model_Music("1", "Peace Of Mind (feat. Vargas & Lagola) Alan Walker", "03:00"));
		list.addItem(new Model_Music("2", "Heaven NCS", "04:37"));
		list.addItem(new Model_Music("3", "SOS (feat. Aloe Blacc) Camilo", "02:37"));
		list.addItem(new Model_Music("4", "Bad Reputation (feat. Joe Janiak) Romeo Santos", "03:25"));
		list.addItem(new Model_Music("5", "Ain't A Thing", "Coyote of Theory 03:03"));
		list.addItem(new Model_Music("6", "Hold The line (feat. A R I Z O N A) Edd Sheeran", "02:51"));
		list.addItem(new Model_Music("7", "Freak (feat. Bonn)", "Alan Walker 02:59"));
		list.addItem(new Model_Music("8", "Excuse me Mr Sir (feat. Vargas & Lagola) NCS", "03:07"));
		list.addItem(new Model_Music("9", "Heart Upon My Sleeve (feat. Imagine Dragons) Camilo", "04:14"));
		list.addItem(new Model_Music("10", "Never Leave Me (feat. Joe Janiak) Romeo Santos", "02:51"));
		list.addItem(new Model_Music("11", "Fades Away (feat. Noonie Bao) Coyote of Theory", "02:58"));
		list.addItem(new Model_Music("12", "Wake Me Up Edd Sheeran", "04:07"));
		list.addItem(new Model_Music("13", "You Make Me Alan Walker", "03:53"));
		list.addItem(new Model_Music("14", "Hey Brother NCS", "04:15"));
		list.addItem(new Model_Music("15", "Addicted To You Camilo", "02:28"));
		list.addItem(new Model_Music("16", "Bad Reputation (feat. Joe Janiak)", "03:25"));
		list.addItem(new Model_Music("17", "Ain't A Thing", "03:03"));
		list.addItem(new Model_Music("18", "Hold The line (feat. A R I Z O N A)", "02:51"));
		list.addItem(new Model_Music("19", "Freak (feat. Bonn)", "02:59"));
		list.addItem(new Model_Music("20", "Excuse me Mr Sir (feat. Vargas & Lagola)", "03:07"));
		list.addItem(new Model_Music("21", "Heart Upon My Sleeve (feat. Imagine Dragons)", "04:14"));
		list.addItem(new Model_Music("22", "Never Leave Me (feat. Joe Janiak)", "02:51"));
		list.addItem(new Model_Music("23", "Fades Away (feat. Noonie Bao)", "02:58"));
		list.addItem(new Model_Music("24", "Wake Me Up", "04:07"));
		list.addItem(new Model_Music("25", "You Make Me", "03:53"));
		list.addItem(new Model_Music("26", "Hey Brother", "04:15"));
		list.addItem(new Model_Music("27", "Addicted To You", "02:28"));

	}

	public void paint(Graphics g) {
		ImageIcon imagen = new ImageIcon(getClass().getResource("/icon/test/coyote.jpg"));
		g.drawImage(imagen.getImage(), 0, 0, getWidth(), getHeight(), this);
		setOpaque(false);
		super.paint(g);
	}

	public static void main(String[] args) {

		JFrame ventana = new JFrame("IMagen");
		Form_Songs fondo = new Form_Songs();
		ventana.setContentPane(fondo);
		ventana.setSize(600, 600);
		ventana.setVisible(true);
		ventana.setLocationRelativeTo(null);

	}

}
