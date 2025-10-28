package Paneles;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import GetaSet.Model_Music;

public class ItemMusic extends javax.swing.JPanel {
	private JLabel lblicon;
	private JLabel lblText;
	private JLabel lblTime;

	public boolean isPlay() {
		return play;
	}

	private final Model_Music data;
	private boolean play;

	public void setPlay(boolean play) {
		this.play = play;
		if (play) {
			lblicon.setText("");
			lblicon.setIcon(new ImageIcon(getClass().getResource("/IMG/playing.png")));
			lblText.setFont(new java.awt.Font("sansserif", 1, 14));
			lblText.setForeground(new Color(203, 30, 148));
			lblTime.setFont(new java.awt.Font("sansserif", 1, 14));
			lblTime.setForeground(new Color(203, 30, 148));
		} else {
			lblicon.setIcon(null);
			lblicon.setText(data.getNo());
			lblText.setFont(new java.awt.Font("sansserif", 0, 14));
			lblText.setForeground(new Color(51, 51, 51));
			lblTime.setFont(new java.awt.Font("sansserif", 0, 14));
			lblTime.setForeground(new Color(51, 51, 51));
		}
	}

	public ItemMusic(Model_Music data) {
		this.data = data;

		setOpaque(false);
		lblicon = new javax.swing.JLabel();
		lblText = new javax.swing.JLabel();
		lblTime = new javax.swing.JLabel();

		lblText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		lblText.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
		lblText.setForeground(new java.awt.Color(51, 51, 51));
		lblText.setText("Music Name");

		lblTime.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
		lblTime.setForeground(new java.awt.Color(51, 51, 51));
		lblTime.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblTime.setText("03:00");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout.createSequentialGroup().addContainerGap()
										.addComponent(lblicon, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(15, 15, 15).addComponent(lblText)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 53,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(lblicon, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
				.addComponent(lblTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(lblText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE));
		lblText.setText(data.getName());
		lblTime.setText(data.getTime());
	}

	@SuppressWarnings("unchecked")

	@Override
	protected void paintComponent(Graphics grphcs) {
		Graphics2D g2 = (Graphics2D) grphcs;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(new Color(246, 246, 246));
		g2.fillRect(0, getHeight() - 2, getWidth(), getHeight());
		super.paintComponent(grphcs);
	}

}