package Paneles;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class Menu extends JPanel {

	/**
	 * Create the panel.
	 */
	public Menu() {
		setOpaque(false);
	}

	protected void paintComponent(Graphics grphcs) {
		Graphics2D g2 = (Graphics2D) grphcs;
		g2.setColor(getBackground());
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		GradientPaint gp = new GradientPaint(0, 0, Color.decode("#43cea2"), 0, getHeight(), Color.decode("#185a9d"));
		g2.setPaint(gp);
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
		g2.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(grphcs);
	}
}