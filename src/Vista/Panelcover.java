package Vista;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Cursor;

public class Panelcover extends JPanel {
	private final DecimalFormat df = new DecimalFormat("##0.###");
	private ActionListener event;
	private MigLayout layout;
	private JLabel title;
	private JLabel description;
	private JLabel description1;
	private JButton buton;
	private boolean isLogin;

	public Panelcover() {

		setOpaque(false);
		layout = new MigLayout("wrap,fill", "[center]", "push[]25[]10[]25[]push");
		setLayout(layout);
		init();

	}

	private void init() {
		title = new JLabel("Hola amigo!");
		title.setFont(new Font("sansserif", 1, 30));
		title.setForeground(new Color(245, 245, 245));
		add(title);
		description = new JLabel("introduce tus datos personales");
		description.setForeground(new Color(245, 245, 245));
		add(description);
		description1 = new JLabel("y comienza el viaje con nosotros");
		description1.setForeground(new Color(245, 245, 245));
		add(description1);
		buton = new JButton("Inicia sesión");
		buton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buton.setFocusPainted(false);
		buton.setBorderPainted(false);
		buton.setOpaque(false);

		buton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				event.actionPerformed(e);
			}
		});
		buton.setBackground(new Color(255, 255, 255));
		buton.setForeground(new Color(245, 245, 245));
		add(buton, "w 60%,h 40");

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2 = (Graphics2D) g;
		GradientPaint gra = new GradientPaint(0, 0, Color.decode("#43cea2"), 0, getHeight(), Color.decode("#185a9d"));
		g2.setPaint(gra);
		g2.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}

	public void addEvent(ActionListener event) {
		this.event = event;

	}

	public void registroLeft(double v) {
		v = Double.valueOf(df.format(v));
		login(false);
		layout.setComponentConstraints(title, "pad 0 -" + v + "% 0 0");
		layout.setComponentConstraints(description, "pad 0 -" + v + "% 0 0");
		layout.setComponentConstraints(description1, "pad 0 -" + v + "% 0 0");
	}

	public void registroRight(double v) {
		v = Double.valueOf(df.format(v));
		login(false);
		layout.setComponentConstraints(title, "pad 0 -" + v + "% 0 0");
		layout.setComponentConstraints(description, "pad 0 -" + v + "% 0 0");
		layout.setComponentConstraints(description1, "pad 0 -" + v + "% 0 0");
	}

	public void loginLeft(double v) {
		v = Double.valueOf(df.format(v));
		login(true);
		layout.setComponentConstraints(title, "pad 0 " + v + "% 0 " + v + "%");
		layout.setComponentConstraints(description, "pad 0 " + v + "% 0 " + v + "%");
		layout.setComponentConstraints(description1, "pad 0 " + v + "% 0 " + v + "%");

	}

	public void loginRight(double v) {
		v = Double.valueOf(df.format(v));
		login(true);
		layout.setComponentConstraints(title, "pad 0 " + v + "% 0 " + v + "%");
		layout.setComponentConstraints(description, "pad 0 " + v + "% 0 " + v + "%");
		layout.setComponentConstraints(description1, "pad 0 " + v + "% 0 " + v + "%");

	}

	private void login(boolean login) {
		if (this.isLogin != login) {
			if (login) {
				title.setText("¡Bienvenido denuevo!");
				description.setText("Para mantenerse conectado con nosotros por favor");
				description1.setText("inicia sesión con tu información personal");

				buton.setText("Registrate");
			} else {
				title.setText("Hola amigo!");
				description.setText("introduce tus datos personales");
				description1.setText("y comienza el viaje con nosotros");

				buton.setText("Inicia sesion");
			}
			this.isLogin = login;
		}
	}
}
