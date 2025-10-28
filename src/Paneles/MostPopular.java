package Paneles;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import GetaSet.Model_Popular;
import Perzonalizado.ScrollBar;
import javax.swing.JScrollBar;

public class MostPopular extends JPanel {
	ScrollBar scroll = new ScrollBar();
	JPanel panel;

	/**
	 * Create the panel.
	 */
	public MostPopular() {
		setBackground(Color.WHITE);

		JScrollPane sp = new JScrollPane();
		sp.setViewportBorder(null);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		sp.setBorder(new EmptyBorder(0, 0, 0, 0));

		panel = new JPanel();
		sp.setViewportView(panel);
		panel.setBackground(Color.WHITE);
		scroll.setOrientation(JScrollBar.HORIZONTAL);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.CENTER)
				.addComponent(sp, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup().addGap(0)
						.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE).addGap(0)));

		groupLayout
				.setVerticalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(sp, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(scroll,
												GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
										.addGap(0, 198, Short.MAX_VALUE)));
		setLayout(groupLayout);
		sp.setHorizontalScrollBar(scroll);
	}

	public void addImage(Model_Popular data) {
		itemimage item = new itemimage();
		item.setData(data);
		panel.add(item);
		panel.repaint();
		panel.revalidate();
	}
}
