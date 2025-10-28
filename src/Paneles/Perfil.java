package Paneles;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import Cruds.CrudconAdministrador;
import Cruds.CrudconArtista;
import Cruds.CrudconCancion;
import Cruds.CrudconLista;
import Cruds.CrudconListaCompra;
import Cruds.CrudconMaquina;
import Cruds.CrudconOrdenLista;
import Cruds.CrudconOrdenProducto;
import Cruds.CrudconProductos;
import Cruds.CrudconReporteCompra;
import Cruds.CrudconfUsuario;
import GetaSet.lista;
import GetaSet.usuario;
import Vista.Main;
import Vista.PanelLoginyRegistro;
import Vista.RLogin;
import Metodos.RyL;

import java.awt.Font;
import java.awt.Color;

public class Perfil extends JPanel {
	public JLabel lblUsuario;
	private JLabel lblrol;
	private JLabel lblicon;

	/**
	 * Create the panel.
	 */
	public Perfil() {
		setOpaque(false);
		lblicon = new JLabel("");
		lblicon.setIcon(new ImageIcon(Perfil.class.getResource("/img/artists.png")));

		lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Tahoma", Font.ITALIC, 12));

		lblrol = new JLabel("User");

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblicon).addGap(18)
						.addComponent(lblUsuario).addPreferredGap(ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
						.addComponent(lblrol).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(lblicon, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE).addGroup(Alignment.LEADING,
						groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout
								.createParallelGroup(Alignment.BASELINE).addComponent(lblrol).addComponent(lblUsuario))
								.addContainerGap(20, Short.MAX_VALUE)));
		setLayout(groupLayout);

	}

	public void setNombreUsuario(usuario user) {
		lblUsuario.setText(user.getUser());

		String rol = new RyL().obtenerRolUsuario(user.getId());
		lblrol.setText(rol);
		configurarListeners();
	}

	private void configurarListeners() {
		JPopupMenu popupMenu = new JPopupMenu();
		String rol = lblrol.getText();

		JMenuItem configuracionAdministradorItem = new JMenuItem("ConfiguracionAdministrador");
		JMenuItem configuracionUsuarioItem = new JMenuItem("ConfiguraciónUsuario");
		JMenuItem configuracionArtistaItem = new JMenuItem("ConfiguraciónArtista");
		JMenuItem configuracionCancionItem = new JMenuItem("ConfiguraciónCancion");
		JMenuItem configuracionListaItem = new JMenuItem("ConfiguracionLista");
		JMenuItem configuracionOrdenListaItem = new JMenuItem("ConfiguracionOrdenLista");
		JMenuItem configuracionListaCompraItem = new JMenuItem("ConfiguracionListaCompra");
		JMenuItem configuracionMaquinaItem = new JMenuItem("ConfiguracionMaquina");
		JMenuItem configuracionProductosItem = new JMenuItem("ConfiguracionProductos");
		JMenuItem configuracionOrdenProductoItem = new JMenuItem("ConfiguracionOrdenProducto");
		JMenuItem configuracionReporteCompraItem = new JMenuItem("ConfiguracionReporteCompra");
		JMenuItem cerrarSesionItem = new JMenuItem("Cerrar Sesión");

		configuracionAdministradorItem.addActionListener(e -> abrirVentanaConfiguracionAdministrador());
		configuracionUsuarioItem.addActionListener(e -> abrirVentanaConfiguracionUsuario());
		configuracionArtistaItem.addActionListener(e -> abrirVentanaConfiguracionArtista());
		configuracionCancionItem.addActionListener(e -> abrirVentanaConfiguracionCancion());
		configuracionListaItem.addActionListener(e -> abrirVentanaConfiguracionLista());
		configuracionOrdenListaItem.addActionListener(e -> abrirVentanaConfiguracionOrdenLista());
		configuracionListaCompraItem.addActionListener(e -> abrirVentanaConfiguracionListaCompra());
		configuracionMaquinaItem.addActionListener(e -> abrirVentanaConfiguracionMaquina());
		configuracionProductosItem.addActionListener(e -> abrirVentanaConfiguracionProductos());
		configuracionOrdenProductoItem.addActionListener(e -> abrirVentanaConfiguracionOrdenProductos());
		configuracionReporteCompraItem.addActionListener(e -> abrirVentanaConfiguracionReporteCompra());
		cerrarSesionItem.addActionListener(e -> cerrarSesion());
		if ("admin".equals(rol)) {

			popupMenu.add(configuracionAdministradorItem);
			popupMenu.add(configuracionUsuarioItem);
			popupMenu.add(configuracionArtistaItem);
			popupMenu.add(configuracionCancionItem);
			popupMenu.add(configuracionListaItem);
			popupMenu.add(configuracionOrdenListaItem);
			popupMenu.add(configuracionListaCompraItem);
			popupMenu.add(configuracionMaquinaItem);
			popupMenu.add(configuracionProductosItem);
			popupMenu.add(configuracionOrdenProductoItem);
			popupMenu.add(configuracionReporteCompraItem);
			popupMenu.add(cerrarSesionItem);
		} else if ("Analista".equals(rol)) {
			popupMenu.add(configuracionUsuarioItem);
			popupMenu.add(configuracionArtistaItem);
			popupMenu.add(configuracionCancionItem);
			popupMenu.add(configuracionListaItem);
			popupMenu.add(configuracionOrdenListaItem);
			popupMenu.add(configuracionListaCompraItem);
			popupMenu.add(configuracionMaquinaItem);
			popupMenu.add(configuracionProductosItem);
			popupMenu.add(configuracionOrdenProductoItem);
			popupMenu.add(configuracionReporteCompraItem);
			popupMenu.add(cerrarSesionItem);
		}else if("Tecnico".equals(rol)) {
			popupMenu.add(configuracionUsuarioItem);
			popupMenu.add(configuracionArtistaItem);
			popupMenu.add(configuracionCancionItem);
			popupMenu.add(configuracionListaItem);
			popupMenu.add(configuracionOrdenListaItem);
			popupMenu.add(configuracionMaquinaItem);
			popupMenu.add(configuracionProductosItem);
			popupMenu.add(cerrarSesionItem);
		}else if("Recepcionista".equals(rol)) {
			popupMenu.add(configuracionListaItem);
			popupMenu.add(configuracionOrdenListaItem);
			popupMenu.add(configuracionReporteCompraItem);
			popupMenu.add(cerrarSesionItem);
		}else {
			popupMenu.add(configuracionListaItem);
			popupMenu.add(configuracionOrdenListaItem);
			popupMenu.add(cerrarSesionItem);
		}
		lblicon.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				popupMenu.show(lblicon, evt.getX(), evt.getY());
			}
		});
	}

	private void abrirVentanaConfiguracionUsuario() {
		CrudconfUsuario usuario = new CrudconfUsuario();
		usuario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		usuario.setVisible(true);
	}

	private void abrirVentanaConfiguracionArtista() {
		CrudconArtista artista = new CrudconArtista();
		artista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		artista.setVisible(true);
	}

	private void abrirVentanaConfiguracionCancion() {
		CrudconCancion cancion = new CrudconCancion();
		cancion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		cancion.setVisible(true);
	}

	private void abrirVentanaConfiguracionAdministrador() {
		CrudconAdministrador administrador = new CrudconAdministrador();
		administrador.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		administrador.setVisible(true);
	}

	private void abrirVentanaConfiguracionLista() {
		CrudconLista lista = new CrudconLista();
		lista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		lista.setVisible(true);
	}

	private void abrirVentanaConfiguracionOrdenLista() {
		CrudconOrdenLista ordenLista = new CrudconOrdenLista();
		ordenLista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ordenLista.setVisible(true);
	}

	private void abrirVentanaConfiguracionListaCompra() {
		CrudconListaCompra listaCompra = new CrudconListaCompra();
		listaCompra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		listaCompra.setVisible(true);
	}

	private void abrirVentanaConfiguracionMaquina() {
		CrudconMaquina maquina = new CrudconMaquina();
		maquina.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		maquina.setVisible(true);
	}

	private void abrirVentanaConfiguracionProductos() {
		CrudconProductos productos = new CrudconProductos();
		productos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		productos.setVisible(true);
	}

	private void abrirVentanaConfiguracionOrdenProductos() {
		CrudconOrdenProducto ordenProducto = new CrudconOrdenProducto();
		ordenProducto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ordenProducto.setVisible(true);
	}

	private void abrirVentanaConfiguracionReporteCompra() {
		CrudconReporteCompra reporteCompra = new CrudconReporteCompra();
		reporteCompra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		reporteCompra.setVisible(true);
	}

	private void cerrarSesion() {
		RLogin inicio = new RLogin();
		inicio.setVisible(true);
		JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(Perfil.this);
		mainFrame.dispose();
	}

}
