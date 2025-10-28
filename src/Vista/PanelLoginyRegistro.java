package Vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import GetaSet.usuario;
import Metodos.RyL;
import Paneles.Perfil;
import Perzonalizado.PasswordField;
import Perzonalizado.TextField;
import Perzonalizado.button;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import java.awt.SystemColor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelLoginyRegistro extends JLayeredPane {

	private ActionListener event;
	private JPanel Login;
	private JPanel Registro;
	private JLabel label;
	private JLabel label_2;
	private JLabel label_1;
	private TextField txtUser;
	private TextField txtEmail_1;
	private PasswordField txtPassword_1;
	RyL dao = new RyL();
	Perfil perfil = new Perfil();
	private TextField txtEmail;
	private PasswordField txtPassword;
	private JButton cmdForget;
	private button cmd_1;
	private button cmd;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	public void limpiar() {

		txtUser.setText("");
		txtPassword_1.setText("");
		txtEmail_1.setText("");

	}

	public PanelLoginyRegistro() {
		initLogin();
		initRegistro();

		setLayout(new CardLayout(0, 0));

		add(Login, "name_80768964086100");

		lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(PanelLoginyRegistro.this);
				frame.dispose();
				System.exit(0);
			}
		});
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel.setIcon(new ImageIcon(PanelLoginyRegistro.class.getResource("/IMG/close.png")));
		GroupLayout gl_Login = new GroupLayout(Login);
		gl_Login.setHorizontalGroup(gl_Login.createParallelGroup(Alignment.LEADING).addGroup(gl_Login
				.createSequentialGroup()
				.addGroup(gl_Login.createParallelGroup(Alignment.LEADING).addGroup(gl_Login.createSequentialGroup()
						.addGap(110)
						.addGroup(gl_Login.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_Login.createSequentialGroup().addGap(59).addComponent(label_1))
								.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_Login.createSequentialGroup().addGap(53).addComponent(cmdForget))))
						.addGroup(gl_Login.createSequentialGroup().addGap(212).addComponent(cmd_1,
								GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel))
				.addContainerGap(217, Short.MAX_VALUE)));
		gl_Login.setVerticalGroup(gl_Login.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Login.createSequentialGroup().addComponent(lblNewLabel).addGap(74).addComponent(label_1)
						.addGap(73)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(23)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(20).addComponent(cmdForget).addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(cmd_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(63, Short.MAX_VALUE)));
		Login.setLayout(gl_Login);

		add(Registro, "name_80768964086100");

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(PanelLoginyRegistro.this);
				frame.dispose();
				System.exit(0);
			}
		});
		lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_1.setIcon(new ImageIcon(PanelLoginyRegistro.class.getResource("/IMG/close.png")));
		GroupLayout gl_Registro = new GroupLayout(Registro);
		gl_Registro.setHorizontalGroup(gl_Registro.createParallelGroup(Alignment.LEADING).addGroup(gl_Registro
				.createSequentialGroup()
				.addGroup(gl_Registro.createParallelGroup(Alignment.LEADING).addGroup(gl_Registro
						.createSequentialGroup().addGap(121)
						.addGroup(gl_Registro.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_Registro.createSequentialGroup().addGap(57).addComponent(label_2))
								.addComponent(txtUser, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtEmail_1, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPassword_1, GroupLayout.PREFERRED_SIZE, 270,
										GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_Registro.createSequentialGroup().addGap(188).addComponent(cmd,
								GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(206, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_Registro.createSequentialGroup().addContainerGap(573, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)));
		gl_Registro.setVerticalGroup(gl_Registro.createParallelGroup(Alignment.LEADING).addGroup(gl_Registro
				.createSequentialGroup()
				.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE).addGap(79)
				.addComponent(label_2).addGap(73)
				.addComponent(txtUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(23)
				.addComponent(txtEmail_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addGap(23)
				.addComponent(txtPassword_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addGap(18).addComponent(cmd, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(32, Short.MAX_VALUE)));
		Registro.setLayout(gl_Registro);
		Login.setVisible(false);
		Registro.setVisible(true);

	}

	private void initRegistro() {
		Registro = new JPanel();
		Registro.setBackground(Color.WHITE);

		label_2 = new JLabel("Crear cuenta");
		label_2.setFont(new Font("sansserif", 1, 30));
		label_2.setForeground(Color.BLACK);

		txtUser = new TextField();
		txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/img/user.png")));
		txtUser.setHint("Nombre");
		txtEmail_1 = new TextField();
		txtEmail_1.setPrefixIcon(new ImageIcon(getClass().getResource("/img/mail.png")));
		txtEmail_1.setHint("Correo");
		txtPassword_1 = new PasswordField();
		txtPassword_1.setPrefixIcon(new ImageIcon(getClass().getResource("/img/pass.png")));
		txtPassword_1.setHint("Contraseña");

		cmd = new button();

		cmd.setText("Registrate");
		cmd.setBorderPainted(false);
		cmd.setFocusPainted(false);
		cmd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if (txtUser.getText().equals("") || txtPassword_1.getPassword().length == 0
							|| txtEmail_1.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Campos vacios ");
						return;
					}
					String emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
					if (!txtEmail_1.getText().matches(emailPattern)) {
						JOptionPane.showMessageDialog(null, "Por favor, ingrese un correo electrónico válido.");
						return;
					}
					if (dao.existeUsuario(txtUser.getText())) {
						JOptionPane.showMessageDialog(null,
								"El nombre de usuario ya está en uso. Por favor, elija otro.");
						return;
					}
					if (dao.existeCorreoElectronico(txtEmail_1.getText())) {
						JOptionPane.showMessageDialog(null,
								"El correo electrónico ya está en uso. Por favor, use otro.");
						return;
					}
					usuario user = new usuario();
					user.setUser(txtUser.getText());
					user.setPassword(txtPassword_1.getPassword());
					user.setEmail(txtEmail_1.getText());
					if (dao.insertarUsuario(user)) {
						limpiar();
						JOptionPane.showMessageDialog(null, "Ya puede iniciar sesion!!");
					} else {
						JOptionPane.showMessageDialog(null, "ERROR");
					}
				} catch (Exception e2) {

					JOptionPane.showMessageDialog(null, "ERROR");
				}

			}
		});
		cmd.setBackground(SystemColor.textHighlight);
		cmd.setForeground(Color.BLACK);

	}

	private void initLogin() {
		Login = new JPanel();
		Login.setBackground(Color.WHITE);
		label_1 = new JLabel("Inicia Secion");
		label_1.setFont(new Font("sansserif", 1, 30));
		label_1.setForeground(Color.BLACK);
		txtEmail = new TextField();
		txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/img/mail.png")));
		txtEmail.setHint("Correo");
		txtPassword = new PasswordField();
		txtPassword.setPrefixIcon(new ImageIcon(getClass().getResource("/img/pass.png")));
		txtPassword.setHint("Contraseña");
		cmdForget = new JButton("Has olvidado tu contraseña?");
		cmdForget.setBorderPainted(false);
		cmdForget.setForeground(new Color(100, 100, 100));
		cmdForget.setFont(new Font("sansserif", 1, 12));
		cmdForget.setContentAreaFilled(false);
		cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cmd_1 = new button();
		cmd_1.setText("Inicia seccion");
		cmd_1.setBorderPainted(false);
		cmd_1.setFocusPainted(false);
		cmd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String correo = txtEmail.getText();
				char[] passwordChars = txtPassword.getPassword();
				String hashedPassword = dao.convertirSHA256(passwordChars);
				usuario user = dao.obtenerUsuarioPorCorreo(correo);

				if (user != null && Arrays.equals(user.getPassword(), hashedPassword.toCharArray())) {

					Main m = new Main();
					SwingUtilities.getWindowAncestor(PanelLoginyRegistro.this).dispose();
					m.setVisible(true);
					m.perfil.setNombreUsuario(user);
				} else {
					JOptionPane.showMessageDialog(null, "Credenciales incorrectas");
				}
			}
		});
		cmd_1.setBackground(SystemColor.textHighlight);
		cmd_1.setForeground(Color.BLACK);
	}

	public void showRegistrar(boolean show) {
		if (show) {
			Registro.setVisible(true);
			Login.setVisible(false);
		} else {
			Registro.setVisible(false);
			Login.setVisible(true);
		}

	}
}
