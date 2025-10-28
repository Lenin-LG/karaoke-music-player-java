package Vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.DecimalFormat;

import net.miginfocom.swing.MigLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import javax.swing.JLayeredPane;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class RLogin extends JFrame {

	private JPanel contentPane;
	private MigLayout layout;
	private Panelcover cover;
	private PanelLoginyRegistro loginregistro;
	private boolean isLogin;
	private final DecimalFormat df = new DecimalFormat("##0.###");
	private final double addSize = 30;
	private final double coverSize = 40;
	private final double loginSize = 60;
	private int x, y;

	public RLogin() {
		setUndecorated(true);
		bg = new JLayeredPane();
		bg.setOpaque(true);
		bg.setBackground(Color.WHITE);
		bg.setBounds(0, 31, 882, 561);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		contentPane.add(bg);

		panel = new JPanel();
		panel.setBounds(0, 0, 883, 33);
		panel.setBackground(new Color(68, 68, 68));

		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
			}
		});
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			}
		});
		contentPane.add(panel);

		lblNewLabel = new JLabel("Karaoke -Red Moon");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 26));
		lblNewLabel.setForeground(Color.WHITE);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel.createSequentialGroup().addContainerGap(331, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
						.addGap(315)));
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addComponent(lblNewLabel).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		init();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 883, 592);
		setLocationRelativeTo(null);

		setVisible(true);
	}

	private void init() {
		layout = new MigLayout("fill,insets 0");
		loginregistro = new PanelLoginyRegistro();
		TimingTarget target = new TimingTargetAdapter() {
			@Override
			public void timingEvent(float fraction) {
				double fractionCover;
				double fractionLogin;
				double size = coverSize;
				if (fraction <= 0.5f) {
					size += fraction * addSize;
				} else {
					size += addSize - fraction * addSize;
				}
				if (isLogin) {
					fractionCover = 1f - fraction;
					fractionLogin = fraction;
					if (fraction >= 0.5f) {
						cover.registroRight(fractionCover * 100);
					} else {
						cover.loginRight(fractionLogin * 100);
					}
				} else {
					fractionCover = fraction;
					fractionLogin = 1f - fraction;
					if (fraction <= 0.5f) {
						cover.registroLeft(fraction * 100);
					} else {
						cover.loginLeft((1f - fraction) * 100);
					}
				}
				if (fraction >= 0.5f) {
					loginregistro.showRegistrar(isLogin);
				}
				fractionCover = Double.valueOf(df.format(fractionCover));
				fractionLogin = Double.valueOf(df.format(fractionLogin));
				layout.setComponentConstraints(cover, "width " + size + "%, pos " + fractionCover + "al 0 n 100%");
				layout.setComponentConstraints(loginregistro,
						"width " + loginSize + "%, pos " + fractionLogin + "al 0 n 100%");
				bg.revalidate();
			}

			@Override
			public void end() {
				isLogin = !isLogin;
			}
		};
		Animator animator = new Animator(800, target);
		animator.setAcceleration(0.5f);
		animator.setDeceleration(0.5f);
		animator.setResolution(0);

		bg.setLayout(layout);
		cover = new Panelcover();
		bg.add(cover, "pos 0al 0 null 100%,cell 0 0,width 40%");
		cover.addEvent(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!animator.isRunning()) {
					animator.start();
				}
			}
		});
		bg.add(loginregistro, "pos 1al 0 null 100%,cell 0 0,width 60%");
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RLogin frame = new RLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	/**
	 * Create the frame.
	 */

	private javax.swing.JLayeredPane bg;
	private JPanel panel;
	private JLabel lblNewLabel;
}
