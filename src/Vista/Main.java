package Vista;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import GetaSet.usuario;
import Paneles.Bottom;
import Paneles.Menu;
import Paneles.Perfil;
import Ventana.Form_Artists;
import Ventana.Form_Todo;
import Perzonalizado.Panel;
import Perzonalizado.ScrollBar;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class Main extends JFrame {
	Perfil perfil;
	private JPanel contentPane;
	Panel panel = new Panel();
	Menu menu = new Menu();
	private int x, y;
	Bottom bottom;
	Form_Todo todo = new Form_Todo();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		perfil = new Perfil();
		bottom = new Bottom();
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1091, 697);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenX = (screenSize.width - getWidth()) / 2;
		int screenY = (screenSize.height - getHeight()) / 2;
		setLocation(screenX, screenY);
		panel.setBounds(0, 0, 1091, 648);
		panel.setBackground(Color.LIGHT_GRAY);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		contentPane.add(panel);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addComponent(menu, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(todo, GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(todo, GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
				.addComponent(menu, GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE));
		todo.setBorder(null);

		menu.setBounds(0, 0, 272, 584);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Main.class.getResource("/img/playlist.png")));

		JLabel lblplay = new JLabel("PlayList");
		lblplay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblplay.setForeground(Color.WHITE);
		agregarCursorHand(lblplay);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Main.class.getResource("/img/artists.png")));

		JLabel lblarts = new JLabel("Artists");
		lblarts.setForeground(Color.WHITE);
		lblarts.setFont(new Font("Tahoma", Font.PLAIN, 14));
		agregarCursorHand(lblarts);
		lblarts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				todo.showPane(1);
			}
		});

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Main.class.getResource("/img/albums.png")));

		JLabel lblalb = new JLabel("Albums");
		lblalb.setForeground(Color.WHITE);
		lblalb.setFont(new Font("Tahoma", Font.PLAIN, 14));
		agregarCursorHand(lblalb);
		lblalb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				todo.showPane(2);
			}
		});

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Main.class.getResource("/img/song.png")));

		JLabel lblsong = new JLabel("Songs");
		lblsong.setForeground(Color.WHITE);
		lblsong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		agregarCursorHand(lblsong);
		lblsong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				todo.showPane(3);
			}
		});

		JLabel lblNewLabel_5 = new JLabel("Librerias");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblNewLabel_5.setForeground(Color.WHITE);

		JPanel panelMoving = new JPanel();

		panelMoving.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
			}
		});
		panelMoving.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			}
		});
		panelMoving.setOpaque(false);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Main.class.getResource("/IMG/lista.png")));

		JLabel lblredeem = new JLabel("Redeem");
		lblredeem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblredeem.setForeground(Color.WHITE);
		agregarCursorHand(lblredeem);
		lblredeem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				todo.showPane(4);
			}
		});

		GroupLayout gl_menu = new GroupLayout(menu);
		gl_menu.setHorizontalGroup(gl_menu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menu.createSequentialGroup().addGroup(gl_menu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_menu.createSequentialGroup().addGap(80).addComponent(lblNewLabel_5,
								GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_menu.createSequentialGroup().addContainerGap().addComponent(panelMoving,
								GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_menu.createSequentialGroup().addGap(23)
								.addGroup(gl_menu.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_menu.createSequentialGroup()
												.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 34,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblarts,
														GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_menu.createSequentialGroup()
												.addGroup(gl_menu.createParallelGroup(Alignment.LEADING)
														.addComponent(lblNewLabel_1).addComponent(lblNewLabel_4))
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addGroup(gl_menu.createParallelGroup(Alignment.LEADING)
														.addComponent(lblsong, GroupLayout.PREFERRED_SIZE, 65,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblredeem)))
										.addGroup(gl_menu.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 33,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblplay,
														GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_menu.createSequentialGroup().addComponent(lblNewLabel_3).addGap(18)
												.addComponent(lblalb, GroupLayout.PREFERRED_SIZE, 65,
														GroupLayout.PREFERRED_SIZE)))))
						.addContainerGap(31, Short.MAX_VALUE)));
		gl_menu.setVerticalGroup(gl_menu.createParallelGroup(Alignment.LEADING).addGroup(gl_menu.createSequentialGroup()
				.addComponent(panelMoving, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_menu.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING, gl_menu
						.createSequentialGroup().addGap(18).addComponent(lblNewLabel_5)
						.addPreferredGap(ComponentPlacement.RELATED, 67, Short.MAX_VALUE).addComponent(lblplay)
						.addGap(28).addComponent(lblarts, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addGap(17).addComponent(lblalb, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_menu.createSequentialGroup().addGap(100)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewLabel_3)))
				.addGap(21)
				.addGroup(gl_menu.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_4)
						.addComponent(lblsong, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
				.addGap(25).addGroup(gl_menu.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_1)
						.addComponent(lblredeem))
				.addContainerGap(318, Short.MAX_VALUE)));
		GroupLayout gl_panelMoving = new GroupLayout(panelMoving);
		gl_panelMoving.setHorizontalGroup(gl_panelMoving.createParallelGroup(Alignment.LEADING).addComponent(perfil,
				GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE));
		gl_panelMoving.setVerticalGroup(gl_panelMoving.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMoving.createSequentialGroup().addGap(5)
						.addComponent(perfil, GroupLayout.PREFERRED_SIZE, 34, Short.MAX_VALUE).addContainerGap()));
		panelMoving.setLayout(gl_panelMoving);

		bottom.setBounds(0, 642, 1091, 55);
		contentPane.add(bottom);

		menu.setLayout(gl_menu);
		panel.setLayout(gl_panel);

	}

	private void init() {

	}

	private void agregarCursorHand(JLabel label) {
		Border originalBorder = label.getBorder();
		Border highlightBorder = BorderFactory.createMatteBorder(0, 0, 0, 2, Color.white);
		label.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Lógica cuando se hace clic en la etiqueta
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// Lógica cuando se presiona el botón del mouse en la etiqueta
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// Lógica cuando se suelta el botón del mouse en la etiqueta
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// Cambiar el cursor cuando el mouse entra en la etiqueta
				label.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// label.setForeground(Color.RED);
				label.setBorder(highlightBorder);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// Restaurar el cursor cuando el mouse sale de la etiqueta
				label.setCursor(Cursor.getDefaultCursor());
				// label.setForeground(Color.white);
				label.setBorder(originalBorder);
			}
		});
	}
}