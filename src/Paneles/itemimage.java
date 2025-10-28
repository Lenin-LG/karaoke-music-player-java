package Paneles;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;

import GetaSet.Model_Popular;

public class itemimage extends JPanel {
	private Model_Popular data;
	private JLabel lblDescription;
	private JLabel lblTitle;

	public itemimage() {
		setOpaque(false);
		setPreferredSize(new Dimension(350, 200));
		setMaximumSize(new Dimension(350, 200));
		setMinimumSize(new Dimension(350, 200));
		init();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
			}
		});

	}

	private void init() {
		setLayout(null);

		lblTitle = new JLabel("Title");
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setBounds(0, 99, 350, 22);
		add(lblTitle);

		lblDescription = new JLabel("Description");
		lblDescription.setForeground(Color.WHITE);
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescription.setHorizontalAlignment(JLabel.CENTER);
		lblDescription.setBounds(10, 123, 350, 20);
		add(lblDescription);
	}

	public void setData(Model_Popular data) {
		this.data = data;
		lblDescription.setText(data.getDescription());
		lblTitle.setText(data.getTitle());
		repaint();
	}

	@Override
	protected void paintComponent(Graphics grphcs) {
		super.paintComponent(grphcs);
		if (data != null) {
			Graphics2D g2 = (Graphics2D) grphcs;
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			Rectangle size = getAutoSize(data.getImage());
			g2.drawImage(toImage(data.getImage()), size.x, size.y, size.width, size.height, null);
		}
	}

	private Image toImage(Icon icon) {
		return ((ImageIcon) icon).getImage();
	}

	private Rectangle getAutoSize(Icon image) {
		int w = getWidth();
		int h = getHeight();
		int iw = image.getIconWidth();
		int ih = image.getIconHeight();
		double xScale = (double) w / iw;
		double yScale = (double) h / ih;
		double scale = Math.max(xScale, yScale);
		int width = (int) (scale * iw);
		int height = (int) (scale * ih);
		int x = (w - width) / 2;
		int y = (h - height) / 2;
		return new Rectangle(new Point(x, y), new Dimension(width, height));
	}
}