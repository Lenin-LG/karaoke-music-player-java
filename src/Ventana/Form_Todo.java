package Ventana;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import Perzonalizado.ScrollBar;

public class Form_Todo extends JPanel {
	private JScrollPane scrollPane;
	private JScrollPane scrollPaneB;
	private CardLayout cardLayout;
	private JScrollPane scrollPaneC;
	private JScrollPane scrollPaneD;

	/**
	 * Create the panel.
	 */
	public Form_Todo() {
		setBackground(Color.WHITE);
		setLayout(new CardLayout(0, 0));
		cardLayout = new CardLayout();
		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, "name_5205584389600");

		Form_Artists artists = new Form_Artists();
		artists.setBorder(null);
		scrollPane.setViewportView(artists);
		scrollPane.setVerticalScrollBar(new ScrollBar());

		Form_Albums albums = new Form_Albums();
		scrollPaneB = new JScrollPane();
		scrollPaneB.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneB.setBorder(null);
		scrollPaneB.setViewportView(albums);
		scrollPaneB.setVerticalScrollBar(new ScrollBar());

		add(scrollPaneB, "name_5205584389600");
		Form_Songs song = new Form_Songs();
		scrollPaneC = new JScrollPane();
		scrollPaneC.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneC.setBorder(null);
		scrollPaneC.setViewportView(song);
		scrollPaneC.setVerticalScrollBar(new ScrollBar());
		add(scrollPaneC, "name_8530081279300");

		Form_Redeem redeem = new Form_Redeem();
		scrollPaneD = new JScrollPane();
		scrollPaneD.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneD.setBorder(null);
		scrollPaneD.setViewportView(redeem);
		scrollPaneD.setVerticalScrollBar(new ScrollBar());
		add(scrollPaneD, "name_46946834036800");

	}

	public void showPane(int panelName) {
		if (panelName == 1) {
			scrollPane.setVisible(true);
			scrollPaneB.setVisible(false);
			scrollPaneC.setVisible(false);
			scrollPaneD.setVisible(false);
		} else if (panelName == 2) {
			scrollPane.setVisible(false);
			scrollPaneB.setVisible(true);
			scrollPaneC.setVisible(false);
			scrollPaneD.setVisible(false);
		} else if (panelName == 3) {
			scrollPane.setVisible(false);
			scrollPaneB.setVisible(false);
			scrollPaneC.setVisible(true);
			scrollPaneD.setVisible(false);
		} else {
			scrollPane.setVisible(false);
			scrollPaneB.setVisible(false);
			scrollPaneC.setVisible(false);
			scrollPaneD.setVisible(true);
		}
	}

}
