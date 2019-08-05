package layout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dominio.Camion;
import dominio.Insumo;
import dominio.InsumoLiquido;
import dominio.Planta.TipoPlanta;
import dominio.Ruta;
import dominio.Stock;
import dominio.UnidadDeMedida;
import dominio.Planta;
import gestores.*;

import java.awt.Insets;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.BorderFactory;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainMenu {
	private JFrame frmTrabajoPrctico;
	private CardLayout cl;
	private static JTable tablePlantas;
	private static JTable tableStock;
	private static JTable tableInsumos;
	private static JTable tableCamiones;
	private static JTable tableRutas;
	private static JComboBox<Insumo> comboInsumos;
	private static JComboBox<Planta> comboPlantasInicial;
	private static JComboBox<Planta> comboPlantasFinal;
	private static JComboBox<List<List<Planta>>> listaListaCaminos = new JComboBox<List<List<Planta>>>();
	private static JLabel lblResultados = new JLabel("");
	private static JLabel lblDatos = new JLabel("");

	static GestorPlanta gestorPlanta = GestorPlanta.getInstance();
	static GestorInsumo gestorInsumo = GestorInsumo.getInstance();
	static GestorRuta gestorRuta = GestorRuta.getInstance();
	static GestorCamion gestorCamion = GestorCamion.getInstance();
	static GrafoPanel grafoPanel = GrafoPanel.getInstance();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frmTrabajoPrctico.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTrabajoPrctico = new JFrame();
		cl = new CardLayout(0, 0);

		// Frame properties
		frmTrabajoPrctico.setResizable(false);
		frmTrabajoPrctico.setBackground(Color.WHITE);
		frmTrabajoPrctico.setTitle("Trabajo Práctico 2019 - DIED");
		frmTrabajoPrctico.setMinimumSize(new Dimension(1280, 720));
		frmTrabajoPrctico.setBounds(100, 100, 450, 300);
		frmTrabajoPrctico.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTrabajoPrctico.getContentPane().setLayout(cl);

		JMenuBar menuBar = new JMenuBar();
		frmTrabajoPrctico.setJMenuBar(menuBar);

		JMenuItem mntmTrabajoPrctico = new JMenuItem("<html><span style='font-size:8px'>"
				+ "<strong>Integrantes</strong>:" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + "Busso, Francisco Ignacio"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + "Bode, Matías" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+ "Ferraro Trivelli, Giovani" + "</span></html>");
		menuBar.add(mntmTrabajoPrctico);

		// Loading content of first panel (Main Menu)
		loadMainMenu();

		// Loading content of second panel (Insumos)
		loadInsumosMenu();

		// Loading content of third panel (Stock)
		loadPlantasMenu();

		// Loading content of fourth panel (Ruta)
		loadRutasMenu();

		// Loading content of quinto panel, ya me canse de hablar en ingles (Camiones)
		loadCamionesMenu();

		// Loading content of info panel
		loadInfoMenu();
	}

	private void loadMainMenu() {
		// Creamos el panel del menú principal
		JPanel pnlMainMenu = new JPanel();
		frmTrabajoPrctico.getContentPane().add(pnlMainMenu, "card__MainMenu");

		GridLayout grid = new GridLayout(1, 5);
		pnlMainMenu.setLayout(grid);

		String resourcesPath = "src/main/resources/";

		ImageIcon insumoIcon = new ImageIcon(
				new ImageIcon(resourcesPath + "Insumo.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		ImageIcon camionIcon = new ImageIcon(
				new ImageIcon(resourcesPath + "Camion.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		ImageIcon rutaIcon = new ImageIcon(
				new ImageIcon(resourcesPath + "Camino.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		ImageIcon plantaIcon = new ImageIcon(
				new ImageIcon(resourcesPath + "Planta.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		ImageIcon infoIcon = new ImageIcon(
				new ImageIcon(resourcesPath + "Info.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));

		JButton insumob = new JButton(insumoIcon);
		insumob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshInsumoTable(null);
				cl.show(frmTrabajoPrctico.getContentPane(), "card__Insumos");
			}
		});

		JButton plantab = new JButton(plantaIcon);
		plantab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshPlantaTable(null);
				refreshStockTable(0);
				cl.show(frmTrabajoPrctico.getContentPane(), "card__Planta");
			}
		});

		JButton rutab = new JButton(rutaIcon);
		rutab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshRutaTable();
				cl.show(frmTrabajoPrctico.getContentPane(), "card__Rutas");
			}
		});

		JButton infob = new JButton(infoIcon);
		infob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				grafoPanel.inicializarVertices(gestorPlanta.getListaPlantas());
				grafoPanel.inicializarAristas(gestorRuta.getListaRutas());
				comboInsumos.setModel(
						new DefaultComboBoxModel<Insumo>(gestorInsumo.getListaInsumos().toArray(new Insumo[0])));
				comboInsumos.setSelectedIndex(-1);

				comboPlantasInicial.setModel(new DefaultComboBoxModel<Planta>(
						gestorPlanta.getListaPlantas().values().toArray(new Planta[0])));
				comboPlantasFinal.setModel(new DefaultComboBoxModel<Planta>(
						gestorPlanta.getListaPlantas().values().toArray(new Planta[0])));
				comboPlantasInicial.setSelectedIndex(-1);
				comboPlantasFinal.setSelectedIndex(-1);
				listaListaCaminos.removeAllItems();
				listaListaCaminos.setVisible(false);
				lblResultados.setText("");
				lblDatos.setText("");

				cl.show(frmTrabajoPrctico.getContentPane(), "card__Info");
			}
		});

		JButton camionb = new JButton(camionIcon);
		camionb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshCamionTable();
				System.out.println(gestorRuta.flujoMaximo());
				cl.show(frmTrabajoPrctico.getContentPane(), "card__Camiones");
			}
		});

		ArrayList<JButton> botones = new ArrayList<JButton>();
		botones.add(insumob);
		botones.add(camionb);
		botones.add(rutab);
		botones.add(plantab);
		botones.add(infob);

		insumob.setText("Insumos");
		camionb.setText("Camiones");
		rutab.setText("Rutas");
		plantab.setText("Plantas");
		infob.setText("Información");

		for (JButton b : botones) {
			b.setOpaque(true);
			b.setBackground(Color.WHITE);
			b.setVerticalTextPosition(SwingConstants.BOTTOM);
			b.setHorizontalTextPosition(SwingConstants.CENTER);
			b.setCursor(new Cursor(Cursor.HAND_CURSOR));
			b.setFocusPainted(false);
			b.setFont(new Font("Segoe UI", Font.PLAIN, 24));
			pnlMainMenu.add(b);
		}
	}

	/*
	 * / JPanel pnlInsumos = new JPanel();
	 * frmTrabajoPrctico.getContentPane().add(pnlInsumos, "card__Insumos");
	 */
	private void loadInsumosMenu() {

		// ------------------------------------------------------------------------------------------------
		// Creamos el panel de insumos!
		// ------------------------------------------------------------------------------------------------

		JPanel pnlInsumos = new JPanel();
		frmTrabajoPrctico.getContentPane().add(pnlInsumos, "card__Insumos");
		pnlInsumos.setLayout(new BorderLayout(0, 0));

		// Panel del sur
		pnlInsumos.add(new JPanel(), BorderLayout.SOUTH);

		// Panel del centro
		JPanel panel = new JPanel();
		pnlInsumos.add(panel, BorderLayout.CENTER);

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 165, 0, 99, 155, 193, 0, 179, 84 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0 };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		// Panel arriba de todo para que no quede tan pegado al titulo
		GridBagConstraints gbc_panel_1a = new GridBagConstraints();
		gbc_panel_1a.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1a.fill = GridBagConstraints.BOTH;
		gbc_panel_1a.gridx = 4;
		gbc_panel_1a.gridy = 0;
		panel.add(new JPanel(), gbc_panel_1a);

		// Lo mismo pero abajo del titulo
		GridBagConstraints gbc_panel_1b = new GridBagConstraints();
		gbc_panel_1b.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1b.fill = GridBagConstraints.BOTH;
		gbc_panel_1b.gridx = 4;
		gbc_panel_1b.gridy = 2;
		panel.add(new JPanel(), gbc_panel_1b);

		// Ahora a la izquierda...
		JPanel panel_1c = new JPanel();
		GridBagConstraints gbc_panel_1c = new GridBagConstraints();
		gbc_panel_1c.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1c.fill = GridBagConstraints.BOTH;
		gbc_panel_1c.gridx = 0;
		gbc_panel_1c.gridy = 4;
		panel.add(panel_1c, gbc_panel_1c);

		// ...y ahora a la derecha :v
		JPanel panel_1d = new JPanel();
		GridBagConstraints gbc_panel_1d = new GridBagConstraints();
		gbc_panel_1d.fill = GridBagConstraints.BOTH;
		gbc_panel_1d.gridx = 9;
		gbc_panel_1d.gridy = 4;
		panel.add(panel_1d, gbc_panel_1d);

		// ------------------------------------------------------------------------------------------------
		// Boton de volver
		// ------------------------------------------------------------------------------------------------

		JButton btnVolver_1 = new JButton("");
		btnVolver_1.setForeground(Color.WHITE);
		btnVolver_1.setIcon(new ImageIcon(new ImageIcon("src/main/resources/back.png").getImage().getScaledInstance(32,
				32, Image.SCALE_DEFAULT)));

		btnVolver_1.setOpaque(false);
		btnVolver_1.setContentAreaFilled(false);
		btnVolver_1.setBorderPainted(false);
		btnVolver_1.setBorder(null);
		btnVolver_1.setCursor(new Cursor(Cursor.HAND_CURSOR));

		btnVolver_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(frmTrabajoPrctico.getContentPane(), "card__MainMenu");
			}
		});

		GridBagConstraints gbc_btnVolver_1 = new GridBagConstraints();
		gbc_btnVolver_1.anchor = GridBagConstraints.WEST;
		gbc_btnVolver_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnVolver_1.gridx = 1;
		gbc_btnVolver_1.gridy = 1;
		panel.add(btnVolver_1, gbc_btnVolver_1);

		// ------------------------------------------------------------------------------------------------
		// Título de arriba de todo
		// ------------------------------------------------------------------------------------------------
		JLabel lblPanelDeAdministracin = new JLabel("Panel de administración de insumos");
		lblPanelDeAdministracin.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		GridBagConstraints gbc_lblPanelDeAdministracin = new GridBagConstraints();
		gbc_lblPanelDeAdministracin.gridwidth = 6;
		gbc_lblPanelDeAdministracin.insets = new Insets(0, 0, 5, 5);
		gbc_lblPanelDeAdministracin.gridx = 2;
		gbc_lblPanelDeAdministracin.gridy = 1;
		panel.add(lblPanelDeAdministracin, gbc_lblPanelDeAdministracin);

		// ------------------------------------------------------------------------------------------------
		// Caja de búsqueda
		// ------------------------------------------------------------------------------------------------

		// ComboBox
		String[] searchComboItems = { "Nombre", "Costo mínimo", "Costo máximo", "Stock mínimo", "Stock máximo" };

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(searchComboItems));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 5;
		gbc_comboBox.gridy = 3;

		panel.add(comboBox, gbc_comboBox);

		// Label de buscar
		JLabel lblOrdenarPor = new JLabel("Buscar por:");
		GridBagConstraints gbc_lblOrdenarPor = new GridBagConstraints();
		gbc_lblOrdenarPor.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrdenarPor.anchor = GridBagConstraints.EAST;
		gbc_lblOrdenarPor.gridx = 4;
		gbc_lblOrdenarPor.gridy = 3;

		panel.add(lblOrdenarPor, gbc_lblOrdenarPor);

		JTextField txtSearchInsumo = new JTextField();

		// Event
		ActionListener searchAction = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String val = txtSearchInsumo.getText();

				if (val.length() == 0) {
					refreshInsumoTable(null);
					return;
				}
				
				if(gestorInsumo.getListaInsumos().size() == 0)
					return;

				switch (comboBox.getSelectedIndex()) {
				case 0: {
					ArrayList<Insumo> resultados = gestorInsumo.buscar(val);
					System.out.println(resultados);
					refreshInsumoTable(resultados);

					break;
				}
				case 1: {
					
					Float costo;
					
					try {
						costo = Float.parseFloat(val);
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(frmTrabajoPrctico, "El dato ingresado debe ser numérico",
								"Información", JOptionPane.INFORMATION_MESSAGE);

						return;
					}

					refreshInsumoTable(gestorInsumo.buscarPorCosto(costo, false));
					break;
				}
				case 2: {
					Float costo;
					
					try {
						costo = Float.parseFloat(val);
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(frmTrabajoPrctico, "El dato ingresado debe ser numérico",
								"Información", JOptionPane.INFORMATION_MESSAGE);

						return;
					}
					
					refreshInsumoTable(gestorInsumo.buscarPorCosto(costo, true));
					break;
				}
				case 3: {
					Integer stock;
					
					try {
						stock = Integer.parseInt(val);
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(frmTrabajoPrctico, "El dato ingresado debe ser numérico",
								"Información", JOptionPane.INFORMATION_MESSAGE);

						return;
					}
					
					refreshInsumoTable(gestorInsumo.buscarPorStock(stock, false));
					break;
				}
				case 4: {
					Integer stock;
					
					try {
						stock = Integer.parseInt(val);
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(frmTrabajoPrctico, "El dato ingresado debe ser numérico",
								"Información", JOptionPane.INFORMATION_MESSAGE);

						return;
					}
					
					refreshInsumoTable(gestorInsumo.buscarPorStock(stock, true));
					break;
				}
				}
			}
		};

		// Campo de texto
		txtSearchInsumo.setToolTipText("Escriba aquí lo que desea buscar...");
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.gridx = 6;
		gbc_textField_1.gridy = 3;
		txtSearchInsumo.setColumns(10);
		txtSearchInsumo.addActionListener(searchAction);

		panel.add(txtSearchInsumo, gbc_textField_1);

		// Botón de buscar
		JButton btnBuscar = new JButton("Buscar");
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.anchor = GridBagConstraints.WEST;
		gbc_btnBuscar.gridwidth = 2;
		gbc_btnBuscar.fill = GridBagConstraints.VERTICAL;
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 0);
		gbc_btnBuscar.gridx = 8;
		gbc_btnBuscar.gridy = 3;
		panel.add(btnBuscar, gbc_btnBuscar);
		btnBuscar.addActionListener(searchAction);

		// ------------------------------------------------------------------------------------------------
		// Botones de administración de insumos
		// ------------------------------------------------------------------------------------------------

		// Nuevo insumo
		JButton btnNuevoInsumo = new JButton("Nuevo insumo");
		GridBagConstraints gbc_btnNuevoInsumo = new GridBagConstraints();
		gbc_btnNuevoInsumo.anchor = GridBagConstraints.EAST;
		gbc_btnNuevoInsumo.insets = new Insets(0, 0, 5, 5);
		gbc_btnNuevoInsumo.gridx = 1;
		gbc_btnNuevoInsumo.gridy = 3;

		btnNuevoInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateInsumoDialog();
			}
		});

		panel.add(btnNuevoInsumo, gbc_btnNuevoInsumo);

		// Editar insumo
		JButton btnEditar_1 = new JButton("Editar");
		GridBagConstraints gbc_btnEditar_1 = new GridBagConstraints();
		gbc_btnEditar_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnEditar_1.gridx = 2;
		gbc_btnEditar_1.gridy = 3;
		panel.add(btnEditar_1, gbc_btnEditar_1);

		btnEditar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer rowId = tableInsumos.getSelectedRow();

				if (rowId < 0) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico, "No hay ningún insumo seleccionado", "Información",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				Insumo insumo;
				insumo = gestorInsumo.getInsumoById((Integer) tableInsumos.getValueAt(rowId, 0));

				if (insumo == null) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico, "No se pudo localizar el insumo seleccionado",
							"Información", JOptionPane.INFORMATION_MESSAGE);

					return;
				}

				EditInsumoDialog(insumo);
				refreshPlantaTable(null);
			}
		});

		// Eliminar insumo
		JButton btnEliminar = new JButton("Eliminar");
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.anchor = GridBagConstraints.WEST;
		gbc_btnEliminar.insets = new Insets(0, 0, 5, 5);
		gbc_btnEliminar.gridx = 3;
		gbc_btnEliminar.gridy = 3;
		panel.add(btnEliminar, gbc_btnEliminar);

		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer rowId = tableInsumos.getSelectedRow();

				if (rowId < 0) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico, "No hay ningún insumo seleccionado", "Información",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				int dialogResult = JOptionPane.showConfirmDialog(frmTrabajoPrctico,
						"¿Está seguro que desea eliminar el insumo seleccionado?", "Confirmar",
						JOptionPane.YES_NO_OPTION);

				if (dialogResult == JOptionPane.YES_OPTION) {
					gestorInsumo.borrar((Integer) tableInsumos.getValueAt(rowId, 0));
					refreshInsumoTable(null);

					JOptionPane.showMessageDialog(frmTrabajoPrctico, "El insumo seleccionado ha sido borrado",
							"Información", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		// ------------------------------------------------------------------------------------------------
		// Tabla de insumos
		// ------------------------------------------------------------------------------------------------

		tableInsumos = new JTable();
		tableInsumos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableInsumos.setAutoCreateRowSorter(true);
		tableInsumos.setDefaultEditor(Object.class, null);
		JScrollPane panel_scrlpn = new JScrollPane(tableInsumos);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 8;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 4;
		panel.add(panel_scrlpn, gbc_panel_1);
	}

	private void loadPlantasMenu() {
		// ------------------------------------------------------------------------------------------------
		// Creando el panel de plantas (por 328947349739 vez) :'v
		// ------------------------------------------------------------------------------------------------
		JPanel pnlPlantas = new JPanel();
		frmTrabajoPrctico.getContentPane().add(pnlPlantas, "card__Planta");
		pnlPlantas.setLayout(new BorderLayout(0, 0));

		pnlPlantas.add(new JPanel(), BorderLayout.SOUTH);

		JPanel panel = new JPanel();
		pnlPlantas.add(panel, BorderLayout.CENTER);

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 103, 0, 0, 99, 200, 101, 250, 10, 10, 84 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0 };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		// Título principal del panel
		JLabel lblPanelDeAdministracin = new JLabel("Panel de administración de plantas");
		lblPanelDeAdministracin.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		GridBagConstraints gbc_lblPanelDeAdministracin = new GridBagConstraints();
		gbc_lblPanelDeAdministracin.gridwidth = 8;
		gbc_lblPanelDeAdministracin.insets = new Insets(0, 0, 5, 5);
		gbc_lblPanelDeAdministracin.gridx = 2;
		gbc_lblPanelDeAdministracin.gridy = 2;
		panel.add(lblPanelDeAdministracin, gbc_lblPanelDeAdministracin);

		// Botón de volver
		JButton btnVolver_1 = new JButton("");
		btnVolver_1.setForeground(Color.WHITE);
		btnVolver_1.setIcon(new ImageIcon(new ImageIcon("src/main/resources/back.png").getImage().getScaledInstance(32,
				32, Image.SCALE_DEFAULT)));

		btnVolver_1.setOpaque(false);
		btnVolver_1.setContentAreaFilled(false);
		btnVolver_1.setBorderPainted(false);
		btnVolver_1.setBorder(null);
		btnVolver_1.setCursor(new Cursor(Cursor.HAND_CURSOR));

		GridBagConstraints gbc_btnVolver_1 = new GridBagConstraints();
		gbc_btnVolver_1.anchor = GridBagConstraints.WEST;
		gbc_btnVolver_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnVolver_1.gridx = 1;
		gbc_btnVolver_1.gridy = 2;
		panel.add(btnVolver_1, gbc_btnVolver_1);

		btnVolver_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(frmTrabajoPrctico.getContentPane(), "card__MainMenu");
			}
		});

		// Panelcito arriba para separar de la barra de título
		JPanel panel_555522 = new JPanel();
		GridBagConstraints gbc_panel_555522 = new GridBagConstraints();
		gbc_panel_555522.insets = new Insets(0, 0, 5, 5);
		gbc_panel_555522.fill = GridBagConstraints.BOTH;
		gbc_panel_555522.gridx = 6;
		gbc_panel_555522.gridy = 1;
		panel.add(panel_555522, gbc_panel_555522);

		// Panelcito abajo del título para separar y que no quede tan pegado
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 6;
		gbc_panel_2.gridy = 3;
		panel.add(panel_2, gbc_panel_2);

		// Panel a la izquierda para separar
		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 0, 5);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 5;
		panel.add(panel_5, gbc_panel_5);

		// Y aca a la derecha
		JPanel panel_133 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 11;
		gbc_panel_1.gridy = 5;
		panel.add(panel_133, gbc_panel_1);

		// ------------------------------------------------------------------------------------------------
		// Caja de búsqueda
		// ------------------------------------------------------------------------------------------------

		// Input de búsqueda
		JTextField txtSearch = new JTextField();
		txtSearch.setToolTipText("Escriba aquí lo que desea buscar...");
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 1;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.gridx = 5;
		gbc_textField_1.gridy = 4;
		panel.add(txtSearch, gbc_textField_1);
		txtSearch.setColumns(10);

		// Botón de buscar
		JButton btnBuscar = new JButton("Buscar planta");
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.anchor = GridBagConstraints.WEST;
		gbc_btnBuscar.gridwidth = 2;
		gbc_btnBuscar.fill = GridBagConstraints.VERTICAL;
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 0);
		gbc_btnBuscar.gridx = 6;
		gbc_btnBuscar.gridy = 4;
		panel.add(btnBuscar, gbc_btnBuscar);

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtSearch.getText().length() == 0)
					refreshPlantaTable(null);
				else
					refreshPlantaTable(gestorPlanta.buscar(txtSearch.getText()));
			}
		});

		// ------------------------------------------------------------------------------------------------
		// Botones de administración de Planta
		// ------------------------------------------------------------------------------------------------

		// Añadir planta
		JButton btnNuevoInsumo = new JButton("Añadir");
		GridBagConstraints gbc_btnNuevoInsumo = new GridBagConstraints();
		gbc_btnNuevoInsumo.anchor = GridBagConstraints.EAST;
		gbc_btnNuevoInsumo.insets = new Insets(0, 0, 5, 5);
		gbc_btnNuevoInsumo.gridx = 1;
		gbc_btnNuevoInsumo.gridy = 4;

		btnNuevoInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Boolean validInput = false;
				String plantaName;

				do {
					plantaName = JOptionPane.showInputDialog(frmTrabajoPrctico, "Nombre de la planta");

					if (plantaName != null) {
						if (plantaName.length() > 0) {
							if (gestorPlanta.buscar(plantaName).size() == 0) {
								gestorPlanta.crear(plantaName);
								refreshPlantaTable(null);
								validInput = true;
							} else {
								JOptionPane.showMessageDialog(frmTrabajoPrctico, "Ya existe una planta con este nombre",
										"Información", JOptionPane.INFORMATION_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(frmTrabajoPrctico,
									"Es necesario colocar un nombre para la planta", "Información",
									JOptionPane.INFORMATION_MESSAGE);
						}
					} else
						validInput = true;
				} while (!validInput);
			}
		});

		panel.add(btnNuevoInsumo, gbc_btnNuevoInsumo);

		// Editar planta
		JButton btnEditar_1 = new JButton("Editar");
		GridBagConstraints gbc_btnEditar_1 = new GridBagConstraints();
		gbc_btnEditar_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnEditar_1.gridx = 2;
		gbc_btnEditar_1.gridy = 4;

		btnEditar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer rowId = tablePlantas.getSelectedRow();

				if (rowId < 0) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico, "No hay ninguna planta seleccionada",
							"Información", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				String plantaName = JOptionPane.showInputDialog(frmTrabajoPrctico, "Nuevo nombre de la planta",
						(String) tablePlantas.getValueAt(rowId, 1));

				if (plantaName != null) {
					gestorPlanta.obtenerPlanta((Integer) tablePlantas.getValueAt(rowId, 0)).setNombre(plantaName);
					refreshPlantaTable(null);
				}
			}
		});

		panel.add(btnEditar_1, gbc_btnEditar_1);

		// Botón de eliminar
		JButton btnEliminar = new JButton("Eliminar");
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.anchor = GridBagConstraints.WEST;
		gbc_btnEliminar.insets = new Insets(0, 0, 5, 5);
		gbc_btnEliminar.gridx = 3;
		gbc_btnEliminar.gridy = 4;

		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer rowId = tablePlantas.getSelectedRow();

				if (rowId < 0) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico, "No hay ninguna planta seleccionada",
							"Información", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				int dialogResult = JOptionPane.showConfirmDialog(frmTrabajoPrctico,
						"¿Está seguro que desea eliminar la planta seleccionada?", "Confirmar",
						JOptionPane.YES_NO_OPTION);

				if (dialogResult == JOptionPane.YES_OPTION) {
					gestorPlanta.borrar((Integer) tablePlantas.getValueAt(rowId, 0));
					refreshPlantaTable(null);
					refreshStockTable(0);

					JOptionPane.showMessageDialog(frmTrabajoPrctico, "La planta seleccionada ha sido borrada",
							"Información", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		panel.add(btnEliminar, gbc_btnEliminar);

		// Botón de establecer tipo
		JButton btnType = new JButton("Establecer tipo");
		GridBagConstraints gbc_btnType = new GridBagConstraints();
		gbc_btnType.anchor = GridBagConstraints.WEST;
		gbc_btnType.insets = new Insets(0, 0, 5, 5);
		gbc_btnType.gridx = 4;
		gbc_btnType.gridy = 4;

		btnType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer rowId = tablePlantas.getSelectedRow();

				if (rowId < 0) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico, "No hay ninguna planta seleccionada",
							"Información", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				Boolean validInput = false;
				Planta curPlanta = gestorPlanta.obtenerPlanta((Integer) tablePlantas.getValueAt(rowId, 0));
				Planta tempPlanta;

				TipoPlanta[] items = Planta.TipoPlanta.values();

				do {
					TipoPlanta tipoPlanta = (TipoPlanta) JOptionPane.showInputDialog(frmTrabajoPrctico,
							"Establecer tipo de planta", "Tipo de planta", JOptionPane.QUESTION_MESSAGE, null, items,
							curPlanta.getTipo());

					System.out.println(tipoPlanta);

					if (tipoPlanta != null) {
						switch (tipoPlanta) {
						case PLANTA_PRODUCCION: // produccion
						{
							curPlanta.setTipo(Planta.TipoPlanta.PLANTA_PRODUCCION);
							validInput = true;
						}
						case PLANTA_ACOPIO_INICIAL:
						case PLANTA_ACOPIO_FINAL: {
							tempPlanta = gestorPlanta.getFirstPlantaWithTipo(tipoPlanta);

							if (tempPlanta != null && tempPlanta != curPlanta) {
								JOptionPane.showMessageDialog(frmTrabajoPrctico, "Ya hay una planta de este tipo.",
										"Información", JOptionPane.INFORMATION_MESSAGE);
							} else {
								curPlanta.setTipo(tipoPlanta);
								validInput = true;
							}
						}
						}
					} else
						validInput = true;
				} while (!validInput);

				refreshPlantaTable(null);
			}
		});

		panel.add(btnType, gbc_btnType);

		// ------------------------------------------------------------------------------------------------
		// Tabla de Plantas
		// ------------------------------------------------------------------------------------------------
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.gridwidth = 6;
		gbc_panel_9.insets = new Insets(0, 0, 0, 5);
		gbc_panel_9.fill = GridBagConstraints.BOTH;
		gbc_panel_9.gridx = 1;
		gbc_panel_9.gridy = 5;

		tablePlantas = new JTable();
		tablePlantas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePlantas.setAutoCreateRowSorter(true);
		tablePlantas.setDefaultEditor(Object.class, null);

		tablePlantas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && tablePlantas.getSelectedRow() != -1)
					refreshStockTable((Integer) tablePlantas.getValueAt(tablePlantas.getSelectedRow(), 0));
			}
		});

		panel.add(new JScrollPane(tablePlantas), gbc_panel_9);

		// ------------------------------------------------------------------------------------------------
		// Tabla de Stocks
		// ------------------------------------------------------------------------------------------------
		GridBagConstraints gbc_panel_222 = new GridBagConstraints();
		gbc_panel_222.gridwidth = 4;
		gbc_panel_222.insets = new Insets(0, 0, 0, 5);
		gbc_panel_222.fill = GridBagConstraints.BOTH;
		gbc_panel_222.gridx = 7;
		gbc_panel_222.gridy = 5;

		tableStock = new JTable();
		tableStock.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableStock.setAutoCreateRowSorter(true);
		tableStock.setDefaultEditor(Object.class, null);

		panel.add(new JScrollPane(tableStock), gbc_panel_222);

		// ------------------------------------------------------------------------------------------------
		// Botones de Insumo -> Stock a Planta
		// ------------------------------------------------------------------------------------------------

		// Añadir insumo
		JButton btnAadirInsumo = new JButton("Añadir insumo");
		GridBagConstraints gbc_btnAadirInsumo = new GridBagConstraints();
		gbc_btnAadirInsumo.gridwidth = 1;
		gbc_btnAadirInsumo.insets = new Insets(0, 0, 5, 0);
		gbc_btnAadirInsumo.gridx = 8;
		gbc_btnAadirInsumo.gridy = 4;
		panel.add(btnAadirInsumo, gbc_btnAadirInsumo);

		btnAadirInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer selectedRow = tablePlantas.getSelectedRow();

				if (selectedRow < 0) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico, "No hay ninguna planta seleccionada",
							"Información", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				if (gestorInsumo.getListaInsumos().size() == 0) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico, "No hay insumos cargados", "Información",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				Integer selectedPlanta = (Integer) tablePlantas.getValueAt(selectedRow, 0);
				AddInsumoDialog(selectedPlanta);
			}
		});

		// Eliminar insumo
		JButton btnEliminarInsumo = new JButton("Eliminar insumo");
		GridBagConstraints gbc_btnEliminarInsumo = new GridBagConstraints();
		gbc_btnEliminarInsumo.insets = new Insets(0, 0, 5, 0);
		gbc_btnEliminarInsumo.gridwidth = 1;
		gbc_btnEliminarInsumo.gridx = 9;
		gbc_btnEliminarInsumo.gridy = 4;
		panel.add(btnEliminarInsumo, gbc_btnEliminarInsumo);

		btnEliminarInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer rowId = tableStock.getSelectedRow();

				if (rowId < 0) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico, "No hay ningún insumo seleccionado", "Información",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				int dialogResult = JOptionPane.showConfirmDialog(frmTrabajoPrctico,
						"¿Está seguro que desea eliminar el insumo de la planta seleccionada?", "Confirmar",
						JOptionPane.YES_NO_OPTION);

				if (dialogResult == JOptionPane.YES_OPTION) {
					Integer plantaId = (Integer) tablePlantas.getValueAt(rowId, 0);
					Planta plantaSelect = gestorPlanta.obtenerPlanta(plantaId);

					if (plantaSelect == null) {
						JOptionPane.showMessageDialog(frmTrabajoPrctico, "No hay ninguna planta seleccionada",
								"Información", JOptionPane.INFORMATION_MESSAGE);

						return;
					}

					Integer insumoId = (Integer) tableInsumos.getValueAt(rowId, 0);
					Insumo insumoSelect = gestorInsumo.getInsumoById(insumoId);

					if (insumoSelect == null) {
						JOptionPane.showMessageDialog(frmTrabajoPrctico, "No hay ningún insumo seleccionado",
								"Información", JOptionPane.INFORMATION_MESSAGE);

						return;
					}

					plantaSelect.getListaStock().remove(insumoId);
					MainMenu.refreshStockTable(plantaId);

					JOptionPane.showMessageDialog(frmTrabajoPrctico,
							"El insumo ha sido eliminado de la planta seleccionada", "Información",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}

	private void loadCamionesMenu() {

		// ------------------------------------------------------------------------------------------------
		// Creamos el panel de insumos!
		// ------------------------------------------------------------------------------------------------

		JPanel pnlInsumos = new JPanel();
		frmTrabajoPrctico.getContentPane().add(pnlInsumos, "card__Camiones");
		pnlInsumos.setLayout(new BorderLayout(0, 0));

		// Panel del sur
		pnlInsumos.add(new JPanel(), BorderLayout.SOUTH);

		// Panel del centro
		JPanel panel = new JPanel();
		pnlInsumos.add(panel, BorderLayout.CENTER);

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 165, 0, 99, 155, 193, 0, 179, 84 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0 };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		// Panel arriba de todo para que no quede tan pegado al titulo
		GridBagConstraints gbc_panel_1a = new GridBagConstraints();
		gbc_panel_1a.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1a.fill = GridBagConstraints.BOTH;
		gbc_panel_1a.gridx = 4;
		gbc_panel_1a.gridy = 0;
		panel.add(new JPanel(), gbc_panel_1a);

		// Lo mismo pero abajo del titulo
		GridBagConstraints gbc_panel_1b = new GridBagConstraints();
		gbc_panel_1b.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1b.fill = GridBagConstraints.BOTH;
		gbc_panel_1b.gridx = 4;
		gbc_panel_1b.gridy = 2;
		panel.add(new JPanel(), gbc_panel_1b);

		// Ahora a la izquierda...
		JPanel panel_1c = new JPanel();
		GridBagConstraints gbc_panel_1c = new GridBagConstraints();
		gbc_panel_1c.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1c.fill = GridBagConstraints.BOTH;
		gbc_panel_1c.gridx = 0;
		gbc_panel_1c.gridy = 4;
		panel.add(panel_1c, gbc_panel_1c);

		// ...y ahora a la derecha :v
		JPanel panel_1d = new JPanel();
		GridBagConstraints gbc_panel_1d = new GridBagConstraints();
		gbc_panel_1d.fill = GridBagConstraints.BOTH;
		gbc_panel_1d.gridx = 9;
		gbc_panel_1d.gridy = 4;
		panel.add(panel_1d, gbc_panel_1d);

		// ------------------------------------------------------------------------------------------------
		// Boton de volver
		// ------------------------------------------------------------------------------------------------

		JButton btnVolver_1 = new JButton("");
		btnVolver_1.setForeground(Color.WHITE);
		btnVolver_1.setIcon(new ImageIcon(new ImageIcon("src/main/resources/back.png").getImage().getScaledInstance(32,
				32, Image.SCALE_DEFAULT)));

		btnVolver_1.setOpaque(false);
		btnVolver_1.setContentAreaFilled(false);
		btnVolver_1.setBorderPainted(false);
		btnVolver_1.setBorder(null);
		btnVolver_1.setCursor(new Cursor(Cursor.HAND_CURSOR));

		btnVolver_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(frmTrabajoPrctico.getContentPane(), "card__MainMenu");
			}
		});

		GridBagConstraints gbc_btnVolver_1 = new GridBagConstraints();
		gbc_btnVolver_1.anchor = GridBagConstraints.WEST;
		gbc_btnVolver_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnVolver_1.gridx = 1;
		gbc_btnVolver_1.gridy = 1;
		panel.add(btnVolver_1, gbc_btnVolver_1);

		// ------------------------------------------------------------------------------------------------
		// Título de arriba de todo
		// ------------------------------------------------------------------------------------------------
		JLabel lblPanelDeAdministracin = new JLabel("Panel de administración de camiones");
		lblPanelDeAdministracin.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		GridBagConstraints gbc_lblPanelDeAdministracin = new GridBagConstraints();
		gbc_lblPanelDeAdministracin.gridwidth = 6;
		gbc_lblPanelDeAdministracin.insets = new Insets(0, 0, 5, 5);
		gbc_lblPanelDeAdministracin.gridx = 2;
		gbc_lblPanelDeAdministracin.gridy = 1;
		panel.add(lblPanelDeAdministracin, gbc_lblPanelDeAdministracin);

		// ------------------------------------------------------------------------------------------------
		// Botones de administración de insumos
		// ------------------------------------------------------------------------------------------------

		// Nuevo insumo
		JButton btnNuevoCamion = new JButton("Nuevo camión");
		GridBagConstraints gbc_btnNuevoCamion = new GridBagConstraints();
		gbc_btnNuevoCamion.anchor = GridBagConstraints.EAST;
		gbc_btnNuevoCamion.insets = new Insets(0, 0, 5, 5);
		gbc_btnNuevoCamion.gridx = 1;
		gbc_btnNuevoCamion.gridy = 3;

		btnNuevoCamion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateCamionDialog();
			}
		});

		panel.add(btnNuevoCamion, gbc_btnNuevoCamion);

		// Editar insumo
		JButton btnEditar_1 = new JButton("Editar");
		GridBagConstraints gbc_btnEditar_1 = new GridBagConstraints();
		gbc_btnEditar_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnEditar_1.gridx = 2;
		gbc_btnEditar_1.gridy = 3;
		panel.add(btnEditar_1, gbc_btnEditar_1);

		btnEditar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer rowId = tableCamiones.getSelectedRow();

				if (rowId < 0) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico, "No hay ningún camión seleccionado", "Información",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				Camion camion;
				camion = gestorCamion.getCamionById((Integer) tableCamiones.getValueAt(rowId, 0));

				if (camion == null) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico, "No se pudo localizar el camión seleccionado",
							"Información", JOptionPane.INFORMATION_MESSAGE);

					return;
				}

				EditCamionDialog(camion);
				refreshPlantaTable(null);
			}
		});

		// Eliminar insumo
		JButton btnEliminar = new JButton("Eliminar");
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.anchor = GridBagConstraints.WEST;
		gbc_btnEliminar.insets = new Insets(0, 0, 5, 5);
		gbc_btnEliminar.gridx = 3;
		gbc_btnEliminar.gridy = 3;
		panel.add(btnEliminar, gbc_btnEliminar);

		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer rowId = tableCamiones.getSelectedRow();

				if (rowId < 0) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico, "No hay ningún camión seleccionado", "Información",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				int dialogResult = JOptionPane.showConfirmDialog(frmTrabajoPrctico,
						"¿Está seguro que desea eliminar el camión seleccionado?", "Confirmar",
						JOptionPane.YES_NO_OPTION);

				if (dialogResult == JOptionPane.YES_OPTION) {
					gestorCamion.borrar((Integer) tableCamiones.getValueAt(rowId, 0));
					refreshCamionTable();

					JOptionPane.showMessageDialog(frmTrabajoPrctico, "El camión seleccionado ha sido borrado",
							"Información", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		// Generar solución
		JButton btnCalcular = new JButton("Generar solución");
		GridBagConstraints gbc_btnCalcular = new GridBagConstraints();
		gbc_btnCalcular.anchor = GridBagConstraints.WEST;
		gbc_btnCalcular.insets = new Insets(0, 0, 5, 5);
		gbc_btnCalcular.gridx = 4;
		gbc_btnCalcular.gridy = 3;
		panel.add(btnCalcular, gbc_btnCalcular);

		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer rowId = tableCamiones.getSelectedRow();
	
				if (rowId < 0) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico, "No hay ningún camión seleccionado", "Información",
					JOptionPane.INFORMATION_MESSAGE);
					return;
				}
	
				Camion camionSelect = gestorCamion.getListaCamiones().get(tableCamiones.convertRowIndexToModel(rowId));
				
				ArrayList<Float> peso = new ArrayList<Float>(); 
				ArrayList<Float> valor = new ArrayList<Float>();				
				ArrayList<Insumo> listaInsumos = gestorInsumo.getListaInsumos();
				
				if(listaInsumos.size() == 0)
				{
					JOptionPane.showMessageDialog(frmTrabajoPrctico, "No hay insumos cargados", "Información",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				if(!camionSelect.getTransportaLiq())
				{
					listaInsumos = new ArrayList<Insumo>();
					
			        for (Insumo i : gestorInsumo.getListaInsumos()) {
			            if (!(i instanceof InsumoLiquido)) {
			            	listaInsumos.add(i);
			            }
			        }
				}
				
				for(Insumo i : listaInsumos)
				{
					peso.add(i.getPeso());
					valor.add(i.getCosto());
				}
	
				Boolean[] resultado = camionSelect.resolver(peso.toArray(new Float[0]), valor.toArray(new Float[0]));
				showBestCaseCamion(listaInsumos, resultado);
			}
		});

		// ------------------------------------------------------------------------------------------------
		// Tabla de insumos
		// ------------------------------------------------------------------------------------------------

		tableCamiones = new JTable();
		tableCamiones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCamiones.setAutoCreateRowSorter(true);
		tableCamiones.setDefaultEditor(Object.class, null);
		JScrollPane panel_scrlpn = new JScrollPane(tableCamiones);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 8;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 4;
		panel.add(panel_scrlpn, gbc_panel_1);
	}

	private void loadRutasMenu() {
		// ------------------------------------------------------------------------------------------------
		// Creando el panel de rutas
		// ------------------------------------------------------------------------------------------------
		JPanel pnlCaminos = new JPanel();
		frmTrabajoPrctico.getContentPane().add(pnlCaminos, "card__Rutas");
		pnlCaminos.setLayout(new BorderLayout(0, 0));

		pnlCaminos.add(new JPanel(), BorderLayout.SOUTH);

		JPanel panel = new JPanel();
		pnlCaminos.add(panel, BorderLayout.CENTER);

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 20, 150, 150, 150, 150, 150, 150, 150, 150, 150, 40 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0 };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		// Título principal del panel
		JLabel lblPanelDeAdministracin = new JLabel("Panel de administración de rutas");
		lblPanelDeAdministracin.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		GridBagConstraints gbc_lblPanelDeAdministracin = new GridBagConstraints();
		gbc_lblPanelDeAdministracin.gridwidth = 8;
		gbc_lblPanelDeAdministracin.insets = new Insets(0, 0, 5, 5);
		gbc_lblPanelDeAdministracin.gridx = 2;
		gbc_lblPanelDeAdministracin.gridy = 2;
		panel.add(lblPanelDeAdministracin, gbc_lblPanelDeAdministracin);

		// Botón de volver
		JButton btnVolver_1 = new JButton("");
		btnVolver_1.setForeground(Color.WHITE);
		btnVolver_1.setIcon(new ImageIcon(new ImageIcon("src/main/resources/back.png").getImage().getScaledInstance(32,
				32, Image.SCALE_DEFAULT)));

		btnVolver_1.setOpaque(false);
		btnVolver_1.setContentAreaFilled(false);
		btnVolver_1.setBorderPainted(false);
		btnVolver_1.setBorder(null);
		btnVolver_1.setCursor(new Cursor(Cursor.HAND_CURSOR));

		GridBagConstraints gbc_btnVolver_1 = new GridBagConstraints();
		gbc_btnVolver_1.anchor = GridBagConstraints.WEST;
		gbc_btnVolver_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnVolver_1.gridx = 1;
		gbc_btnVolver_1.gridy = 2;
		panel.add(btnVolver_1, gbc_btnVolver_1);

		btnVolver_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(frmTrabajoPrctico.getContentPane(), "card__MainMenu");
			}
		});

		// Panelcito arriba para separar de la barra de título
		JPanel panel_555522 = new JPanel();
		GridBagConstraints gbc_panel_555522 = new GridBagConstraints();
		gbc_panel_555522.insets = new Insets(0, 0, 5, 5);
		gbc_panel_555522.fill = GridBagConstraints.BOTH;
		gbc_panel_555522.gridx = 6;
		gbc_panel_555522.gridy = 1;
		panel.add(panel_555522, gbc_panel_555522);

		// Panelcito abajo del título para separar y que no quede tan pegado
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 6;
		gbc_panel_2.gridy = 3;
		panel.add(panel_2, gbc_panel_2);

		// Panel a la izquierda para separar
		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 0, 5);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 5;
		panel.add(panel_5, gbc_panel_5);

		// Y aca a la derecha
		JPanel panel_133 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 11;
		gbc_panel_1.gridy = 5;
		panel.add(panel_133, gbc_panel_1);

		// Añadir camino
		JButton btnNuevoCamino = new JButton("Añadir");
		GridBagConstraints gbc_btnNuevoCamino = new GridBagConstraints();
		gbc_btnNuevoCamino.anchor = GridBagConstraints.EAST;
		gbc_btnNuevoCamino.insets = new Insets(0, 0, 5, 5);
		gbc_btnNuevoCamino.gridx = 2;
		gbc_btnNuevoCamino.gridy = 4;

		btnNuevoCamino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (gestorPlanta.getListaPlantas().size() < 2) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico, "No hay suficientes plantas cargadas",
							"Información", JOptionPane.INFORMATION_MESSAGE);

					return;
				}

				CreateRutaDialog();
			}
		});

		panel.add(btnNuevoCamino, gbc_btnNuevoCamino);

		// Eliminar camino
		JButton btnEliminarCamino = new JButton("Eliminar");
		GridBagConstraints gbc_btnEliminarCamino = new GridBagConstraints();
		gbc_btnEliminarCamino.anchor = GridBagConstraints.EAST;
		gbc_btnEliminarCamino.insets = new Insets(0, 0, 5, 5);
		gbc_btnEliminarCamino.gridx = 3;
		gbc_btnEliminarCamino.gridy = 4;

		btnEliminarCamino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer rowId = tableRutas.getSelectedRow();

				if (rowId == -1) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico, "No hay ninguna ruta seleccionada", "Información",
							JOptionPane.INFORMATION_MESSAGE);

					return;
				}

				Planta origen = gestorRuta.getListaRutas().get(tableRutas.convertRowIndexToModel(rowId)).getOrigen();
				Planta destino = gestorRuta.getListaRutas().get(tableRutas.convertRowIndexToModel(rowId)).getDestino();

				gestorRuta.deleteRuta(origen, destino);

				refreshRutaTable();
			}
		});

		panel.add(btnEliminarCamino, gbc_btnEliminarCamino);

		// Tabla de rutas
		tableRutas = new JTable();
		tableRutas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableRutas.setAutoCreateRowSorter(true);
		tableRutas.setDefaultEditor(Object.class, null);

		JScrollPane panel_scrlpn = new JScrollPane(tableRutas);
		GridBagConstraints gbc_panel_12 = new GridBagConstraints();
		gbc_panel_12.gridwidth = 9;
		gbc_panel_12.insets = new Insets(0, 0, 0, 5);
		gbc_panel_12.fill = GridBagConstraints.BOTH;
		gbc_panel_12.gridx = 1;
		gbc_panel_12.gridy = 5;
		panel.add(panel_scrlpn, gbc_panel_12);
	}

	private void loadInfoMenu() {
		// ------------------------------------------------------------------------------------------------
		// Creando el panel de informacion, donde estaran los grafos visualizados
		// ------------------------------------------------------------------------------------------------
		JPanel pnlInfo = new JPanel();
		frmTrabajoPrctico.getContentPane().add(pnlInfo, "card__Info");
		pnlInfo.setLayout(new BorderLayout(0, 0));

		pnlInfo.add(new JPanel(), BorderLayout.SOUTH);

		JPanel panel = new JPanel();
		pnlInfo.add(panel, BorderLayout.CENTER);

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 20, 150, 150, 150, 150, 150, 150, 150, 150, 150, 40 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0 };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		// Título principal del panel
		JLabel lblPanelDeAdministracin = new JLabel("Visualización de la información guardada"); // TODO: pensar un
																									// nombre mejor
		lblPanelDeAdministracin.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		GridBagConstraints gbc_lblPanelDeAdministracin = new GridBagConstraints();
		gbc_lblPanelDeAdministracin.gridwidth = 7;
		gbc_lblPanelDeAdministracin.insets = new Insets(0, 0, 5, 5);
		gbc_lblPanelDeAdministracin.gridx = 2;
		gbc_lblPanelDeAdministracin.gridy = 2;
		panel.add(lblPanelDeAdministracin, gbc_lblPanelDeAdministracin);

		// Botón de volver
		JButton btnVolver_1 = new JButton("");
		btnVolver_1.setForeground(Color.WHITE);
		btnVolver_1.setIcon(new ImageIcon(new ImageIcon("src/main/resources/back.png").getImage().getScaledInstance(32,
				32, Image.SCALE_DEFAULT)));

		btnVolver_1.setOpaque(false);
		btnVolver_1.setContentAreaFilled(false);
		btnVolver_1.setBorderPainted(false);
		btnVolver_1.setBorder(null);
		btnVolver_1.setCursor(new Cursor(Cursor.HAND_CURSOR));

		GridBagConstraints gbc_btnVolver_1 = new GridBagConstraints();
		gbc_btnVolver_1.anchor = GridBagConstraints.WEST;
		gbc_btnVolver_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnVolver_1.gridx = 1;
		gbc_btnVolver_1.gridy = 2;
		panel.add(btnVolver_1, gbc_btnVolver_1);

		btnVolver_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(frmTrabajoPrctico.getContentPane(), "card__MainMenu");
			}
		});

		// Panelcito arriba para separar de la barra de título
		JPanel panel_555522 = new JPanel();
		GridBagConstraints gbc_panel_555522 = new GridBagConstraints();
		gbc_panel_555522.insets = new Insets(0, 0, 5, 5);
		gbc_panel_555522.fill = GridBagConstraints.BOTH;
		gbc_panel_555522.gridx = 6;
		gbc_panel_555522.gridy = 1;
		panel.add(panel_555522, gbc_panel_555522);

		// Panelcito abajo del título para separar y que no quede tan pegado
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 6;
		gbc_panel_2.gridy = 3;
		panel.add(panel_2, gbc_panel_2);

		// Panel a la izquierda para separar
		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 5);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 5;
		panel.add(panel_5, gbc_panel_5);

		// Y aca a la derecha
		JPanel panel_133 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 11;
		gbc_panel_1.gridy = 5;
		panel.add(panel_133, gbc_panel_1);

		// ------------------------------------------------------------------------------------------------
		// Panel para visualización
		// ------------------------------------------------------------------------------------------------
		GridBagConstraints gbc_panel_91 = new GridBagConstraints();
		gbc_panel_91.gridwidth = 7;
		gbc_panel_91.insets = new Insets(0, 0, 0, 5);
		gbc_panel_91.fill = GridBagConstraints.BOTH;
		gbc_panel_91.gridx = 1;
		gbc_panel_91.gridy = 6;

		grafoPanel.setBackground(Color.WHITE);
		grafoPanel.setBorder(BorderFactory.createLineBorder(new Color(0x7A8A99)));
		panel.add(grafoPanel, gbc_panel_91);

		// ------------------------------------------------------------------------------------------------
		// Panel de la derecha
		// ------------------------------------------------------------------------------------------------
		JPanel panel_1331 = new JPanel();
		GridBagConstraints gbc_panel_1331 = new GridBagConstraints();
		gbc_panel_1331.insets = new Insets(0, 15, 0, 5);
		gbc_panel_1331.fill = GridBagConstraints.BOTH;
		gbc_panel_1331.gridx = 8;
		gbc_panel_1331.gridy = 6;
		gbc_panel_1331.gridwidth = 3;
		panel.add(panel_1331, gbc_panel_1331);

		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 30, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1331.setLayout(gbl_panel_1);

		// Insumos faltantes
		JLabel lblInsumo = new JLabel("Insumo:");
		GridBagConstraints gbc_lblInsumo = new GridBagConstraints();
		gbc_lblInsumo.insets = new Insets(0, 0, 5, 5);
		gbc_lblInsumo.anchor = GridBagConstraints.EAST;
		gbc_lblInsumo.gridx = 0;
		gbc_lblInsumo.gridy = 1;

		panel_1331.add(lblInsumo, gbc_lblInsumo);

		comboInsumos = new JComboBox<Insumo>();
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		panel_1331.add(comboInsumos, gbc_btnNewButton);

		comboInsumos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Insumo insumoSelect = (Insumo) comboInsumos.getSelectedItem();

				if (insumoSelect == null)
					return;

				grafoPanel.repintarVertices();

				for (Planta planta : gestorPlanta.necesitanInsumo(insumoSelect)) {
					grafoPanel.nodoNecesitaInsumo(planta);
				}

				grafoPanel.repaint();
			}
		});

		// Mostrar camino optimo por tiempo
		JButton btnCaminoOptimoTiempo = new JButton("Mostrar camino óptimo por tiempo");
		GridBagConstraints gbc_btnCaminoOptimoTiempo = new GridBagConstraints();
		gbc_btnCaminoOptimoTiempo.anchor = GridBagConstraints.CENTER;
		gbc_btnCaminoOptimoTiempo.gridwidth = 5;
		gbc_btnCaminoOptimoTiempo.insets = new Insets(10, 0, 10, 0);
		gbc_btnCaminoOptimoTiempo.gridx = 0;
		gbc_btnCaminoOptimoTiempo.gridy = 2;
		panel_1331.add(btnCaminoOptimoTiempo, gbc_btnCaminoOptimoTiempo);

		// Mostrar camino optimo por distancia
		JButton btnCaminoOptimoDistancia = new JButton("Mostrar camino óptimo por distancia");
		GridBagConstraints gbc_btnCaminoOptimoDistancia = new GridBagConstraints();
		gbc_btnCaminoOptimoDistancia.anchor = GridBagConstraints.CENTER;
		gbc_btnCaminoOptimoDistancia.gridwidth = 5;
		gbc_btnCaminoOptimoDistancia.insets = new Insets(0, 0, 5, 0);
		gbc_btnCaminoOptimoDistancia.gridx = 0;
		gbc_btnCaminoOptimoDistancia.gridy = 3;
		panel_1331.add(btnCaminoOptimoDistancia, gbc_btnCaminoOptimoDistancia);

		// Separador
		GridBagConstraints gbc_lblSep = new GridBagConstraints();
		gbc_lblSep.insets = new Insets(15, 0, 15, 0);
		gbc_lblSep.anchor = GridBagConstraints.NORTH;
		gbc_lblSep.gridx = 0;
		gbc_lblSep.gridy = 4;
		gbc_lblSep.gridwidth = 2;
		gbc_lblSep.weighty = 0;
		JSeparator jsep = new JSeparator(SwingConstants.HORIZONTAL);
		jsep.setPreferredSize(new Dimension(150, 5));
		panel_1331.add(jsep, gbc_lblSep);

		// Planta inicial
		JLabel lblInicial = new JLabel("Planta inicial:");
		GridBagConstraints gbc_lblInicial = new GridBagConstraints();
		gbc_lblInicial.insets = new Insets(0, 0, 5, 5);
		gbc_lblInicial.anchor = GridBagConstraints.EAST;
		gbc_lblInicial.gridx = 0;
		gbc_lblInicial.gridy = 5;

		panel_1331.add(lblInicial, gbc_lblInicial);

		comboPlantasInicial = new JComboBox<Planta>();
		GridBagConstraints gbc_btnComboPlantasInicial = new GridBagConstraints();
		gbc_btnComboPlantasInicial.gridwidth = 1;
		gbc_btnComboPlantasInicial.insets = new Insets(0, 0, 5, 0);
		gbc_btnComboPlantasInicial.gridx = 1;
		gbc_btnComboPlantasInicial.gridy = 5;
		panel_1331.add(comboPlantasInicial, gbc_btnComboPlantasInicial);

		// Planta final
		JLabel lblFinal = new JLabel("Planta final:");
		GridBagConstraints gbc_lblFinal = new GridBagConstraints();
		gbc_lblFinal.insets = new Insets(0, 0, 5, 5);
		gbc_lblFinal.anchor = GridBagConstraints.EAST;
		gbc_lblFinal.gridx = 0;
		gbc_lblFinal.gridy = 6;

		panel_1331.add(lblFinal, gbc_lblFinal);

		comboPlantasFinal = new JComboBox<Planta>();
		GridBagConstraints gbc_btnComboPlantasFinal = new GridBagConstraints();
		gbc_btnComboPlantasFinal.gridwidth = 2;
		gbc_btnComboPlantasFinal.insets = new Insets(0, 0, 5, 0);
		gbc_btnComboPlantasFinal.gridx = 1;
		gbc_btnComboPlantasFinal.gridy = 6;
		panel_1331.add(comboPlantasFinal, gbc_btnComboPlantasFinal);

		// Mostrar caminos entre plantas
		JButton btnBuscar = new JButton("Mostrar caminos entre plantas");
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.anchor = GridBagConstraints.CENTER;
		gbc_btnBuscar.gridwidth = 5;
		gbc_btnBuscar.insets = new Insets(10, 0, 10, 0);
		gbc_btnBuscar.gridx = 0;
		gbc_btnBuscar.gridy = 7;
		panel_1331.add(btnBuscar, gbc_btnBuscar);

		// Planta inicial
		GridBagConstraints gbc_lblResultados = new GridBagConstraints();
		gbc_lblResultados.insets = new Insets(0, 0, 0, 0);
		gbc_lblResultados.anchor = GridBagConstraints.NORTH;
		gbc_lblResultados.gridx = 0;
		gbc_lblResultados.gridy = 8;
		gbc_lblResultados.gridwidth = 3;

		panel_1331.add(lblResultados, gbc_lblResultados);

		GridBagConstraints gbc_listScroller = new GridBagConstraints();
		gbc_listScroller.anchor = GridBagConstraints.NORTH;
		gbc_listScroller.gridwidth = 3;
		gbc_listScroller.insets = new Insets(0, 0, 0, 0);
		gbc_listScroller.gridx = 0;
		gbc_listScroller.gridy = 9;
		panel_1331.add(listaListaCaminos, gbc_listScroller);

		GridBagConstraints gbc_lblDatos = new GridBagConstraints();
		gbc_lblDatos.insets = new Insets(0, 0, 0, 0);
		gbc_lblDatos.anchor = GridBagConstraints.NORTH;
		gbc_lblDatos.gridx = 0;
		gbc_lblDatos.gridy = 10;
		gbc_lblDatos.gridwidth = 5;

		panel_1331.add(lblDatos, gbc_lblDatos);

		btnCaminoOptimoTiempo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Insumo insumoSelect = (Insumo) comboInsumos.getSelectedItem();

				if (insumoSelect == null) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico, "No hay ningún insumo seleccionado", "Información",
							JOptionPane.INFORMATION_MESSAGE);

					return;
				}

				Planta acopioInicial = gestorPlanta.getAcopioInicial();
				Planta acopioFinal = gestorPlanta.getAcopioFinal();

				if (acopioInicial == null || acopioFinal == null) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico,
							"No hay definida ninguna planta de acopio inicial o final", "Información",
							JOptionPane.INFORMATION_MESSAGE);

					return;
				}

				List<Planta> mejorCaminoConInsumoTiempo = gestorRuta.mejorCaminoConInsumoTiempo(acopioInicial,
						acopioFinal, gestorPlanta.necesitanInsumo(insumoSelect));

				if (mejorCaminoConInsumoTiempo == null) {
					lblDatos.setText(
							"<html><center style='padding-top:15px'>No hay camino óptimo por tiempo</center></html>");
					return;
				}

				System.out.println(mejorCaminoConInsumoTiempo);
				grafoPanel.pintarRuta(mejorCaminoConInsumoTiempo);

				grafoPanel.repaint();

				ArrayList<Integer> infoRuta = gestorRuta.getInfoRuta(mejorCaminoConInsumoTiempo);
				lblDatos.setText(
						"<html><center style='padding-top:15px'><strong>Camino óptimo por tiempo:</strong><br />"
								+ "Distancia total del viaje: " + infoRuta.get(0) + " km" + "<br>Duración del viaje: "
								+ infoRuta.get(1) + " min.<br>" + "Peso máximo admitido: " + infoRuta.get(2)
								+ " Tn.</center></html>");
			}
		});

		btnCaminoOptimoDistancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Insumo insumoSelect = (Insumo) comboInsumos.getSelectedItem();

				if (insumoSelect == null) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico, "No hay ningún insumo seleccionado", "Información",
							JOptionPane.INFORMATION_MESSAGE);

					return;
				}

				Planta acopioInicial = gestorPlanta.getAcopioInicial();
				Planta acopioFinal = gestorPlanta.getAcopioFinal();

				if (acopioInicial == null || acopioFinal == null) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico,
							"No hay definida ninguna planta de acopio inicial o final", "Información",
							JOptionPane.INFORMATION_MESSAGE);

					return;
				}

				List<Planta> mejorCaminoConInsumoDistancia = gestorRuta.mejorCaminoConInsumoDistancia(acopioInicial,
						acopioFinal, gestorPlanta.necesitanInsumo(insumoSelect));

				System.out.println(mejorCaminoConInsumoDistancia);
				grafoPanel.pintarRuta(mejorCaminoConInsumoDistancia);

				grafoPanel.repaint();

				ArrayList<Integer> infoRuta = gestorRuta.getInfoRuta(mejorCaminoConInsumoDistancia);
				lblDatos.setText(
						"<html><center style='padding-top:15px'><strong>Camino óptimo por distancia:</strong><br />"
								+ "Distancia total del viaje: " + infoRuta.get(0) + " km" + "<br>Duración del viaje: "
								+ infoRuta.get(1) + " min.<br>" + "Peso máximo admitido: " + infoRuta.get(2)
								+ " Tn.</center></html>");
			}
		});

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Planta pInicial = (Planta) comboPlantasInicial.getSelectedItem();
				Planta pFinal = (Planta) comboPlantasFinal.getSelectedItem();

				if (pInicial == null || pFinal == null) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico,
							"Debe seleccionar una planta inicial y una planta final", "Información",
							JOptionPane.INFORMATION_MESSAGE);

					return;
				}

				// Grafo g = new Grafo();
				// List<List<Vertice>> lista = g.caminos(pInicial, pFinal);
				List<List<Planta>> lista = gestorRuta.caminos(pInicial, pFinal);

				System.out.println(lista);

				if (lista.size() > 0) {
					listaListaCaminos.setVisible(true);
					lblResultados.setText(lista.size() + " resultado(s):");
					listaListaCaminos.setModel(new DefaultComboBoxModel(lista.toArray()));
					listaListaCaminos.setSelectedIndex(-1);
				} else {
					listaListaCaminos.setVisible(false);
					lblResultados.setText("Sin caminos");
				}

				lblDatos.setText("");
			}
		});

		listaListaCaminos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// List<Vertice> listaSelect = (List<Vertice>)
				// listaListaCaminos.getSelectedItem();
				List<Planta> listaSelect = (List<Planta>) listaListaCaminos.getSelectedItem();

				if (listaSelect == null)
					return;

				grafoPanel.pintarRuta(listaSelect);
				grafoPanel.repaint();

				ArrayList<Integer> infoRuta = gestorRuta.getInfoRuta(listaSelect);
				lblDatos.setText(
						"<html><center style='padding-top:15px'><strong>- Camino entre plantas -</strong><br />"
								+ "Distancia total del viaje: " + infoRuta.get(0) + " km" + "<br>Duración del viaje: "
								+ infoRuta.get(1) + " min.<br>" + "Peso máximo admitido: " + infoRuta.get(2)
								+ " Tn.</center></html>");
			}
		});

	}

	public static void refreshCamionTable() {
		String col[] = { "ID", "Marca", "Modelo", "Año", "Dominio", "Costo/km", "Transp. líquidos", "Capacidad" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);

		ArrayList<Camion> listaCamiones = gestorCamion.getListaCamiones();

		if (listaCamiones.isEmpty()) {
			tableCamiones.setModel(tableModel);
			return;
		}

		for (Camion camion : listaCamiones) {
			int id = camion.getId();
			String marca = camion.getMarca();
			String modelo = camion.getModelo();
			Integer anio = camion.getAnio();
			String dominio = camion.getDominio();
			Float costoKm = camion.getCostoKm();
			String transpLiq = camion.getTransportaLiq() ? "Sí" : "No";
			Integer capacidad = camion.getCapacidad();

			Object[] data = { id, marca, modelo, anio, dominio, costoKm, transpLiq, capacidad };
			tableModel.addRow(data);
		}

		tableCamiones.setModel(tableModel);
	}

	public static void refreshRutaTable() {
		String col[] = { "Origen", "Destino", "Distancia", "Duracion", "Peso máximo" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);

		ArrayList<Ruta> listaRutas = gestorRuta.getListaRutas();

		if (listaRutas.isEmpty()) {
			tableRutas.setModel(tableModel);
			return;
		}

		for (Ruta ruta : listaRutas) {
			String origen = ruta.getOrigen().toString();
			String destino = ruta.getDestino().toString();
			Integer distancia = ruta.getDistancia();
			Integer duracion = ruta.getDuracion();
			Integer pesoMaximo = ruta.getPesoMaximo();

			Object[] data = { origen, destino, distancia, duracion, pesoMaximo };
			tableModel.addRow(data);
		}

		tableRutas.setModel(tableModel);
	}

	public static void refreshPlantaTable(ArrayList<Planta> search) {
		String col[] = { "ID", "Nombre", "Tipo", "PageRank" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);

		if (gestorPlanta.getListaPlantas().isEmpty()) {
			tablePlantas.setModel(tableModel);
			return;
		}

		ArrayList<Planta> tempListaPlantas;

		if (search == null) {
			tempListaPlantas = new ArrayList<>(gestorPlanta.getListaPlantas().values());
		} else {
			tempListaPlantas = search;
		}

		for (Planta planta : tempListaPlantas) {
			Integer id = planta.getId();
			String nombre = planta.getNombre();
			String tipo = planta.getTipo().toString();
			Integer pagerank = gestorRuta.getPageRank(planta);

			Object[] data = { id, nombre, tipo, pagerank };
			tableModel.addRow(data);
		}

		tablePlantas.setModel(tableModel);
	}

	public static void refreshStockTable(Integer plantaId) {
		String col[] = { "Insumo", "Cantidad", "Punto pedido" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		HashMap<Integer, Stock> tempListaStock;

		if (plantaId == 0 || (tempListaStock = gestorPlanta.getStockByPlantaId(plantaId)) == null) {
			tableStock.setModel(tableModel);
			return;
		}

		for (Stock stock : tempListaStock.values()) {
			String descripcion = stock.getInsumo().getDescripcion();
			Integer cantidad = stock.getCantidad();
			Integer puntopedido = stock.getPuntoPedido();

			Object[] data = { descripcion, cantidad, puntopedido };
			tableModel.addRow(data);
		}

		tableStock.setModel(tableModel);
	}

	public static void refreshInsumoTable(ArrayList<Insumo> search) {
		String col[] = { "ID", "Descripción", "Costo", "Peso", "Unidad de medida", "Refrigerado", "Stock" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);

		ArrayList<Insumo> tempListaInsumos;

		if (search == null)
			tempListaInsumos = gestorInsumo.getListaInsumos();
		else
			tempListaInsumos = search;

		for (Insumo insumo : tempListaInsumos) {
			Integer id = insumo.getId();
			String descripcion = insumo.getDescripcion();
			Float costo = insumo.getCosto();
			String peso = insumo.getPeso() + " kg";
			String unidadDeMedida = insumo.getUnidadDeMedida().toString();
			String refrigerado = insumo instanceof InsumoLiquido ? "Si" : "No";
			Integer stock = insumo.getStock();

			Object[] data = { id, descripcion, costo, peso, unidadDeMedida, refrigerado, stock };
			tableModel.addRow(data);
		}

		tableInsumos.setModel(tableModel);
	}

	public void CreateRutaDialog() {
		JDialog jdialog = new JDialog(frmTrabajoPrctico, "Crear ruta", Dialog.ModalityType.DOCUMENT_MODAL);
		JPanel contentPane;

		JComboBox<Planta> comboStart;
		JComboBox<Planta> comboEnd;

		jdialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		jdialog.setResizable(false);
		jdialog.setBounds(100, 100, 330, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		jdialog.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 40, 58, 86, 32, 0 };
		gbl_panel.rowHeights = new int[] { 10, 0, 20, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblDescripcin = new JLabel("Planta de origen:");
		GridBagConstraints gbc_lblDescripcin = new GridBagConstraints();
		gbc_lblDescripcin.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcin.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcin.gridx = 1;
		gbc_lblDescripcin.gridy = 2;
		panel.add(lblDescripcin, gbc_lblDescripcin);

		comboStart = new JComboBox<Planta>();
		comboStart.setModel(
				new DefaultComboBoxModel<Planta>(gestorPlanta.getListaPlantas().values().toArray(new Planta[0])));
		comboStart.setSelectedIndex(-1);

		GridBagConstraints gbc_comboStart = new GridBagConstraints();
		gbc_comboStart.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboStart.anchor = GridBagConstraints.NORTH;
		gbc_comboStart.insets = new Insets(0, 0, 5, 5);
		gbc_comboStart.gridx = 2;
		gbc_comboStart.gridy = 2;
		panel.add(comboStart, gbc_comboStart);

		JLabel lblCosto = new JLabel("Planta de destino:");
		GridBagConstraints gbc_lblCosto = new GridBagConstraints();
		gbc_lblCosto.insets = new Insets(0, 0, 5, 5);
		gbc_lblCosto.anchor = GridBagConstraints.EAST;
		gbc_lblCosto.gridx = 1;
		gbc_lblCosto.gridy = 3;
		panel.add(lblCosto, gbc_lblCosto);

		comboEnd = new JComboBox<Planta>();
		comboEnd.setModel(
				new DefaultComboBoxModel<Planta>(gestorPlanta.getListaPlantas().values().toArray(new Planta[0])));
		comboEnd.setSelectedIndex(-1);

		GridBagConstraints gbc_comboEnd = new GridBagConstraints();
		gbc_comboEnd.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboEnd.anchor = GridBagConstraints.NORTH;
		gbc_comboEnd.insets = new Insets(0, 0, 5, 5);
		gbc_comboEnd.gridx = 2;
		gbc_comboEnd.gridy = 3;
		panel.add(comboEnd, gbc_comboEnd);

		JLabel lblDistancia = new JLabel("Distancia:");
		GridBagConstraints gbc_lblDistancia = new GridBagConstraints();
		gbc_lblDistancia.insets = new Insets(0, 0, 5, 5);
		gbc_lblDistancia.anchor = GridBagConstraints.EAST;
		gbc_lblDistancia.gridx = 1;
		gbc_lblDistancia.gridy = 4;
		panel.add(lblDistancia, gbc_lblDistancia);

		JTextField textDistancia = new JTextField();
		GridBagConstraints gbc_textDistancia = new GridBagConstraints();
		gbc_textDistancia.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDistancia.anchor = GridBagConstraints.NORTH;
		gbc_textDistancia.insets = new Insets(0, 0, 5, 5);
		gbc_textDistancia.gridx = 2;
		gbc_textDistancia.gridy = 4;
		panel.add(textDistancia, gbc_textDistancia);

		JLabel lblDuracion = new JLabel("Duración:");
		GridBagConstraints gbc_lblDuracion = new GridBagConstraints();
		gbc_lblDuracion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDuracion.anchor = GridBagConstraints.EAST;
		gbc_lblDuracion.gridx = 1;
		gbc_lblDuracion.gridy = 5;
		panel.add(lblDuracion, gbc_lblDuracion);

		JTextField textDuracion = new JTextField();
		GridBagConstraints gbc_textDuracion = new GridBagConstraints();
		gbc_textDuracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDuracion.anchor = GridBagConstraints.NORTH;
		gbc_textDuracion.insets = new Insets(0, 0, 5, 5);
		gbc_textDuracion.gridx = 2;
		gbc_textDuracion.gridy = 5;
		panel.add(textDuracion, gbc_textDuracion);

		JLabel lblPesoMaximo = new JLabel("Peso máximo:");
		GridBagConstraints gbc_lblPesoMaximo = new GridBagConstraints();
		gbc_lblPesoMaximo.insets = new Insets(0, 0, 5, 5);
		gbc_lblPesoMaximo.anchor = GridBagConstraints.EAST;
		gbc_lblPesoMaximo.gridx = 1;
		gbc_lblPesoMaximo.gridy = 6;
		panel.add(lblPesoMaximo, gbc_lblPesoMaximo);

		JTextField textPesoMaximo = new JTextField();
		GridBagConstraints gbc_textPesoMaximo = new GridBagConstraints();
		gbc_textPesoMaximo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPesoMaximo.anchor = GridBagConstraints.NORTH;
		gbc_textPesoMaximo.insets = new Insets(0, 0, 5, 5);
		gbc_textPesoMaximo.gridx = 2;
		gbc_textPesoMaximo.gridy = 6;
		panel.add(textPesoMaximo, gbc_textPesoMaximo);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Planta origen = (Planta) comboStart.getSelectedItem();
				Planta destino = (Planta) comboEnd.getSelectedItem();

				if (origen == destino) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico, "La planta de origen y destino son iguales",
							"Información", JOptionPane.INFORMATION_MESSAGE);

					return;
				}

				Ruta nuevaRuta = gestorRuta.crearRuta(origen, destino);

				nuevaRuta.setDistancia(Integer.parseInt(textDistancia.getText()));
				nuevaRuta.setDuracion(Integer.parseInt(textDuracion.getText()));
				nuevaRuta.setPesoMaximo(Integer.parseInt(textPesoMaximo.getText()));

				System.out.println("Nueva ruta: " + nuevaRuta);

				refreshRutaTable();

				jdialog.dispose();
			}
		});
		panel_1.add(btnGuardar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jdialog.dispose();
			}
		});
		panel_1.add(btnCancelar);
		jdialog.setVisible(true);
	}

	public void CreateInsumoDialog() {
		JDialog jdialog = new JDialog(frmTrabajoPrctico, "Crear insumo", Dialog.ModalityType.DOCUMENT_MODAL);
		JPanel contentPane;

		JTextField txtDescripcion = new JTextField();
		JTextField txtCosto = new JTextField();
		JTextField txtPeso = new JTextField();
		JComboBox<UnidadDeMedida> comboUnidades = new JComboBox<UnidadDeMedida>();
		JCheckBox chckbxS = new JCheckBox("Insumo refrigerado");
		JButton btnGuardar = new JButton("Guardar");

		jdialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		jdialog.setResizable(false);
		jdialog.setBounds(100, 100, 330, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		jdialog.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 65, 58, 86, 32, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 20, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 0;
		panel.add(panel_2, gbc_panel_2);

		KeyAdapter validForm = new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (txtDescripcion.getText().length() == 0 || txtCosto.getText().length() == 0
						|| txtPeso.getText().length() == 0)
					btnGuardar.setEnabled(false);
				else
					btnGuardar.setEnabled(true);
			}
		};

		ActionListener saveInsumo = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (txtCosto.getText().length() == 0 || txtPeso.getText().length() == 0
						|| txtDescripcion.getText().length() == 0)
					return;

				Float costo;
				Float peso;

				try {
					costo = Float.parseFloat(txtCosto.getText());
					peso = Float.parseFloat(txtPeso.getText());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico, "Uno o más datos ingresados no son válidos",
							"Información", JOptionPane.INFORMATION_MESSAGE);

					return;
				}

				Insumo insumo;

				if (chckbxS.isSelected()) {
					insumo = gestorInsumo.crearLiquido(txtDescripcion.getText());
				} else {
					insumo = gestorInsumo.crear(txtDescripcion.getText());
				}

				insumo.setCosto(costo);
				insumo.setPeso(peso);
				insumo.setUnidadDeMedida((UnidadDeMedida) comboUnidades.getSelectedItem());

				MainMenu.refreshInsumoTable(null);

				jdialog.dispose();
			}
		};

		JLabel lblDescripcin = new JLabel("Descripción:");
		GridBagConstraints gbc_lblDescripcin = new GridBagConstraints();
		gbc_lblDescripcin.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcin.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcin.gridx = 1;
		gbc_lblDescripcin.gridy = 2;
		panel.add(lblDescripcin, gbc_lblDescripcin);

		GridBagConstraints gbc_txtDescripcion = new GridBagConstraints();
		gbc_txtDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDescripcion.anchor = GridBagConstraints.NORTH;
		gbc_txtDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_txtDescripcion.gridx = 2;
		gbc_txtDescripcion.gridy = 2;
		panel.add(txtDescripcion, gbc_txtDescripcion);
		txtDescripcion.addKeyListener(validForm);
		txtDescripcion.addActionListener(saveInsumo);
		txtDescripcion.setColumns(10);

		JLabel lblCosto = new JLabel("Costo:");
		GridBagConstraints gbc_lblCosto = new GridBagConstraints();
		gbc_lblCosto.insets = new Insets(0, 0, 5, 5);
		gbc_lblCosto.anchor = GridBagConstraints.EAST;
		gbc_lblCosto.gridx = 1;
		gbc_lblCosto.gridy = 3;
		panel.add(lblCosto, gbc_lblCosto);

		GridBagConstraints gbc_txtCosto = new GridBagConstraints();
		gbc_txtCosto.insets = new Insets(0, 0, 5, 5);
		gbc_txtCosto.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCosto.gridx = 2;
		gbc_txtCosto.gridy = 3;
		panel.add(txtCosto, gbc_txtCosto);
		txtCosto.setColumns(10);
		txtCosto.addKeyListener(validForm);
		txtCosto.addActionListener(saveInsumo);

		JLabel lblPeso = new JLabel("Peso (kg):");
		GridBagConstraints gbc_lblPeso = new GridBagConstraints();
		gbc_lblPeso.anchor = GridBagConstraints.EAST;
		gbc_lblPeso.insets = new Insets(0, 0, 5, 5);
		gbc_lblPeso.gridx = 1;
		gbc_lblPeso.gridy = 4;
		panel.add(lblPeso, gbc_lblPeso);

		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 4;
		panel.add(txtPeso, gbc_textField);
		txtPeso.setColumns(10);
		txtPeso.addKeyListener(validForm);
		txtPeso.addActionListener(saveInsumo);

		JLabel lblUnidadMedida = new JLabel("Unidad:");
		GridBagConstraints gbc_lblUnidadMedida = new GridBagConstraints();
		gbc_lblUnidadMedida.anchor = GridBagConstraints.EAST;
		gbc_lblUnidadMedida.insets = new Insets(0, 0, 5, 5);
		gbc_lblUnidadMedida.gridx = 1;
		gbc_lblUnidadMedida.gridy = 5;
		panel.add(lblUnidadMedida, gbc_lblUnidadMedida);

		comboUnidades.setModel(new DefaultComboBoxModel<UnidadDeMedida>(UnidadDeMedida.values()));

		GridBagConstraints gbc_comboUnidades = new GridBagConstraints();
		gbc_comboUnidades.insets = new Insets(0, 0, 5, 5);
		gbc_comboUnidades.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboUnidades.gridx = 2;
		gbc_comboUnidades.gridy = 5;
		panel.add(comboUnidades, gbc_comboUnidades);

		GridBagConstraints gbc_chckbxS = new GridBagConstraints();
		gbc_chckbxS.anchor = GridBagConstraints.WEST;
		gbc_chckbxS.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxS.gridx = 2;
		gbc_chckbxS.gridy = 6;
		panel.add(chckbxS, gbc_chckbxS);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		btnGuardar.setEnabled(false);
		btnGuardar.addActionListener(saveInsumo);
		panel_1.add(btnGuardar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jdialog.dispose();
			}
		});
		panel_1.add(btnCancelar);
		jdialog.setVisible(true);
	}

	public void EditInsumoDialog(Insumo insumo) {
		JDialog jdialog = new JDialog(frmTrabajoPrctico, "Modificar insumo", Dialog.ModalityType.DOCUMENT_MODAL);
		JPanel contentPane;

		JTextField txtDescripcion = new JTextField(insumo.getDescripcion());
		JTextField txtCosto = new JTextField(insumo.getCosto().toString());
		;
		JTextField txtPeso = new JTextField(insumo.getPeso().toString());
		JComboBox<UnidadDeMedida> comboUnidades = new JComboBox<UnidadDeMedida>();
		JButton btnGuardar = new JButton("Guardar");

		jdialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		jdialog.setResizable(false);
		jdialog.setBounds(100, 100, 330, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		jdialog.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 65, 58, 86, 32, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 20, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 0;
		panel.add(panel_2, gbc_panel_2);

		KeyAdapter validForm = new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (txtDescripcion.getText().length() == 0 || txtCosto.getText().length() == 0
						|| txtPeso.getText().length() == 0)
					btnGuardar.setEnabled(false);
				else
					btnGuardar.setEnabled(true);
			}
		};

		ActionListener saveInsumo = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (txtCosto.getText().length() == 0 || txtPeso.getText().length() == 0
						|| txtDescripcion.getText().length() == 0)
					return;

				insumo.setDescripcion(txtDescripcion.getText());
				insumo.setCosto(Float.parseFloat(txtCosto.getText()));
				insumo.setPeso(Float.parseFloat(txtPeso.getText()));
				insumo.setUnidadDeMedida((UnidadDeMedida) comboUnidades.getSelectedItem());

				MainMenu.refreshInsumoTable(null);

				jdialog.dispose();
			}
		};

		JLabel lblDescripcin = new JLabel("Descripción:");
		GridBagConstraints gbc_lblDescripcin = new GridBagConstraints();
		gbc_lblDescripcin.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcin.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcin.gridx = 1;
		gbc_lblDescripcin.gridy = 2;
		panel.add(lblDescripcin, gbc_lblDescripcin);

		GridBagConstraints gbc_txtDescripcion = new GridBagConstraints();
		gbc_txtDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDescripcion.anchor = GridBagConstraints.NORTH;
		gbc_txtDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_txtDescripcion.gridx = 2;
		gbc_txtDescripcion.gridy = 2;
		panel.add(txtDescripcion, gbc_txtDescripcion);
		txtDescripcion.setColumns(10);
		txtDescripcion.addKeyListener(validForm);
		txtDescripcion.addActionListener(saveInsumo);

		JLabel lblCosto = new JLabel("Costo:");
		GridBagConstraints gbc_lblCosto = new GridBagConstraints();
		gbc_lblCosto.insets = new Insets(0, 0, 5, 5);
		gbc_lblCosto.anchor = GridBagConstraints.EAST;
		gbc_lblCosto.gridx = 1;
		gbc_lblCosto.gridy = 3;
		panel.add(lblCosto, gbc_lblCosto);

		GridBagConstraints gbc_txtCosto = new GridBagConstraints();
		gbc_txtCosto.insets = new Insets(0, 0, 5, 5);
		gbc_txtCosto.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCosto.gridx = 2;
		gbc_txtCosto.gridy = 3;
		panel.add(txtCosto, gbc_txtCosto);
		txtCosto.setColumns(10);
		txtCosto.addKeyListener(validForm);
		txtCosto.addActionListener(saveInsumo);

		JLabel lblPeso = new JLabel("Peso (kg):");
		GridBagConstraints gbc_lblPeso = new GridBagConstraints();
		gbc_lblPeso.anchor = GridBagConstraints.EAST;
		gbc_lblPeso.insets = new Insets(0, 0, 5, 5);
		gbc_lblPeso.gridx = 1;
		gbc_lblPeso.gridy = 4;
		panel.add(lblPeso, gbc_lblPeso);

		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 4;
		panel.add(txtPeso, gbc_textField);
		txtPeso.setColumns(10);
		txtPeso.addKeyListener(validForm);
		txtPeso.addActionListener(saveInsumo);

		JLabel lblUnidadMedida = new JLabel("Unidad:");
		GridBagConstraints gbc_lblUnidadMedida = new GridBagConstraints();
		gbc_lblUnidadMedida.anchor = GridBagConstraints.EAST;
		gbc_lblUnidadMedida.insets = new Insets(0, 0, 5, 5);
		gbc_lblUnidadMedida.gridx = 1;
		gbc_lblUnidadMedida.gridy = 5;
		panel.add(lblUnidadMedida, gbc_lblUnidadMedida);

		comboUnidades.setModel(new DefaultComboBoxModel<UnidadDeMedida>(UnidadDeMedida.values()));
		comboUnidades.setSelectedItem(insumo.getUnidadDeMedida());

		GridBagConstraints gbc_comboUnidades = new GridBagConstraints();
		gbc_comboUnidades.insets = new Insets(0, 0, 5, 5);
		gbc_comboUnidades.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboUnidades.gridx = 2;
		gbc_comboUnidades.gridy = 5;
		panel.add(comboUnidades, gbc_comboUnidades);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		btnGuardar.addActionListener(saveInsumo);

		panel_1.add(btnGuardar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jdialog.dispose();
			}
		});
		panel_1.add(btnCancelar);
		jdialog.setVisible(true);
	}

	public void showBestCaseCamion(ArrayList<Insumo> listaInsumos, Boolean[] resultado) {
		JDialog jdialog = new JDialog(frmTrabajoPrctico, "Mostrando la solución generada", Dialog.ModalityType.DOCUMENT_MODAL);
		JPanel contentPane;
		jdialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jdialog.setResizable(false);
		jdialog.setMinimumSize(new Dimension(100, 100));
		jdialog.setBounds(100, 100, 350, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		jdialog.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 200 };
		gbl_panel.rowHeights = new int[] { 10, 10, 10 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblInsumos = new JLabel("<html><center>Insumos que deben transportarse<br>para que la entrega sea óptima:</center></html>");
		GridBagConstraints gbc_lblInsumos = new GridBagConstraints();
		gbc_lblInsumos.anchor = GridBagConstraints.CENTER;
		gbc_lblInsumos.insets = new Insets(10, 10, 10, 10);
		gbc_lblInsumos.gridx = 0;
		gbc_lblInsumos.gridy = 0;
		panel.add(lblInsumos, gbc_lblInsumos);
		
        DefaultListModel<String> dataList = new DefaultListModel<String>(); 
        
        for(int i = 0; i < resultado.length; i++)
		{
        	if(resultado[i])
        		dataList.addElement(listaInsumos.get(i).getDescripcion());
		}
		
        JList<String> insumoList = new JList<String>(dataList);
		GridBagConstraints gbc_InsumoList = new GridBagConstraints();
		gbc_InsumoList.anchor = GridBagConstraints.CENTER;
		gbc_InsumoList.insets = new Insets(0, 0, 0, 0);
		gbc_InsumoList.gridx = 0;
		gbc_InsumoList.gridy = 1;
		insumoList.setFixedCellWidth(200);
		panel.add(new JScrollPane(insumoList), gbc_InsumoList);

		JButton btnCancelar = new JButton("Cerrar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jdialog.dispose();
			}
		});
		
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.CENTER;
		gbc_btnCancelar.insets = new Insets(10, 0, 10, 0);
		gbc_btnCancelar.gridx = 0;
		gbc_btnCancelar.gridy = 2;
		panel.add(btnCancelar, gbc_btnCancelar);
		
		jdialog.setVisible(true);
	}

	public void CreateCamionDialog() {
		JDialog jdialog = new JDialog(frmTrabajoPrctico, "Añadir camión", Dialog.ModalityType.DOCUMENT_MODAL);
		JPanel contentPane;
		JTextField txtMarca = new JTextField();
		JTextField txtModelo = new JTextField();
		JTextField txtAnio = new JTextField();
		JTextField txtDominio = new JTextField();
		JTextField txtCostoKm = new JTextField();
		JTextField txtCapacidad = new JTextField();
		JButton btnGuardar = new JButton("Guardar");
		JCheckBox chckbxS = new JCheckBox("Transporta líquidos");

		jdialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		jdialog.setResizable(false);
		jdialog.setMinimumSize(new Dimension(100, 100));
		jdialog.setBounds(100, 100, 350, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		jdialog.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 86, 58, 205, 32, 0 };
		gbl_panel.rowHeights = new int[] { 10, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		KeyAdapter validForm = new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (txtMarca.getText().length() == 0 || txtModelo.getText().length() == 0
						|| txtAnio.getText().length() == 0 || txtDominio.getText().length() == 0
						|| txtCostoKm.getText().length() == 0 || txtCapacidad.getText().length() == 0)
					btnGuardar.setEnabled(false);
				else
					btnGuardar.setEnabled(true);
			}
		};

		ActionListener saveCamion = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtMarca.getText().length() == 0 || txtModelo.getText().length() == 0
						|| txtAnio.getText().length() == 0 || txtDominio.getText().length() == 0
						|| txtCostoKm.getText().length() == 0 || txtCapacidad.getText().length() == 0)
					return;
				
				Integer anio;
				Float costokm;
				Integer capacidad;
				
				try
				{
					anio = Integer.parseInt(txtAnio.getText());
					costokm = Float.parseFloat(txtCostoKm.getText());
					capacidad = Integer.parseInt(txtCapacidad.getText());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico, "Uno o más datos ingresados no son válidos",
							"Información", JOptionPane.INFORMATION_MESSAGE);

					return;
				}
				
				Camion camion = gestorCamion.crearCamion();
				camion.setMarca(txtMarca.getText());
				camion.setModelo(txtModelo.getText());
				camion.setAnio(anio);
				camion.setDominio(txtDominio.getText());
				camion.setCostoKm(costokm);
				camion.setTransportaLiq(chckbxS.isSelected());
				camion.setCapacidad(capacidad);

				refreshCamionTable();
				jdialog.dispose();
			}
		};
		
		JLabel lblMarca = new JLabel("Marca:");
		GridBagConstraints gbc_lblMarca = new GridBagConstraints();
		gbc_lblMarca.anchor = GridBagConstraints.EAST;
		gbc_lblMarca.insets = new Insets(0, 0, 5, 5);
		gbc_lblMarca.gridx = 1;
		gbc_lblMarca.gridy = 1;
		panel.add(lblMarca, gbc_lblMarca);

		GridBagConstraints gbc_txtMarca = new GridBagConstraints();
		gbc_txtMarca.insets = new Insets(0, 0, 5, 5);
		gbc_txtMarca.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMarca.gridx = 2;
		gbc_txtMarca.gridy = 1;
		panel.add(txtMarca, gbc_txtMarca);
		txtMarca.setColumns(10);
		txtMarca.addKeyListener(validForm);
		txtMarca.addActionListener(saveCamion);
		
		JLabel lblModelo = new JLabel("Modelo:");
		GridBagConstraints gbc_lblModelo = new GridBagConstraints();
		gbc_lblModelo.anchor = GridBagConstraints.EAST;
		gbc_lblModelo.insets = new Insets(0, 0, 5, 5);
		gbc_lblModelo.gridx = 1;
		gbc_lblModelo.gridy = 2;
		panel.add(lblModelo, gbc_lblModelo);

		GridBagConstraints gbc_txtModelo = new GridBagConstraints();
		gbc_txtModelo.insets = new Insets(0, 0, 5, 5);
		gbc_txtModelo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtModelo.gridx = 2;
		gbc_txtModelo.gridy = 2;
		panel.add(txtModelo, gbc_txtModelo);
		txtModelo.setColumns(10);
		txtModelo.addKeyListener(validForm);
		txtModelo.addActionListener(saveCamion);
		
		JLabel lblAnio = new JLabel("Año:");
		GridBagConstraints gbc_lblAnio = new GridBagConstraints();
		gbc_lblAnio.anchor = GridBagConstraints.EAST;
		gbc_lblAnio.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnio.gridx = 1;
		gbc_lblAnio.gridy = 3;
		panel.add(lblAnio, gbc_lblAnio);

		GridBagConstraints gbc_txtAnio = new GridBagConstraints();
		gbc_txtAnio.insets = new Insets(0, 0, 5, 5);
		gbc_txtAnio.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAnio.gridx = 2;
		gbc_txtAnio.gridy = 3;
		panel.add(txtAnio, gbc_txtAnio);
		txtAnio.setColumns(10);
		txtAnio.addKeyListener(validForm);
		txtAnio.addActionListener(saveCamion);
		
		JLabel lblDominio = new JLabel("Dominio:");
		GridBagConstraints gbc_lblDominio = new GridBagConstraints();
		gbc_lblDominio.anchor = GridBagConstraints.EAST;
		gbc_lblDominio.insets = new Insets(0, 0, 5, 5);
		gbc_lblDominio.gridx = 1;
		gbc_lblDominio.gridy = 4;
		panel.add(lblDominio, gbc_lblDominio);

		GridBagConstraints gbc_txtDominio = new GridBagConstraints();
		gbc_txtDominio.insets = new Insets(0, 0, 5, 5);
		gbc_txtDominio.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDominio.gridx = 2;
		gbc_txtDominio.gridy = 4;
		panel.add(txtDominio, gbc_txtDominio);
		txtAnio.setColumns(10);
		txtAnio.addKeyListener(validForm);
		txtAnio.addActionListener(saveCamion);
		
		JLabel lblCostoKm = new JLabel("Costo/km:");
		GridBagConstraints gbc_lblCostoKm = new GridBagConstraints();
		gbc_lblCostoKm.anchor = GridBagConstraints.EAST;
		gbc_lblCostoKm.insets = new Insets(0, 0, 5, 5);
		gbc_lblCostoKm.gridx = 1;
		gbc_lblCostoKm.gridy = 5;
		panel.add(lblCostoKm, gbc_lblCostoKm);

		GridBagConstraints gbc_txtCostoKm = new GridBagConstraints();
		gbc_txtCostoKm.insets = new Insets(0, 0, 5, 5);
		gbc_txtCostoKm.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCostoKm.gridx = 2;
		gbc_txtCostoKm.gridy = 5;
		panel.add(txtCostoKm, gbc_txtCostoKm);
		txtCostoKm.setColumns(10);
		txtCostoKm.addKeyListener(validForm);
		txtCostoKm.addActionListener(saveCamion);
		
		JLabel lblCapacidad = new JLabel("Capacidad:");
		GridBagConstraints gbc_lblCapacidad = new GridBagConstraints();
		gbc_lblCapacidad.anchor = GridBagConstraints.EAST;
		gbc_lblCapacidad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCapacidad.gridx = 1;
		gbc_lblCapacidad.gridy = 6;
		panel.add(lblCapacidad, gbc_lblCapacidad);

		GridBagConstraints gbc_txtCapacidad = new GridBagConstraints();
		gbc_txtCapacidad.insets = new Insets(0, 0, 5, 5);
		gbc_txtCapacidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCapacidad.gridx = 2;
		gbc_txtCapacidad.gridy = 6;
		panel.add(txtCapacidad, gbc_txtCapacidad);
		txtCapacidad.setColumns(10);
		txtCapacidad.addKeyListener(validForm);
		txtCapacidad.addActionListener(saveCamion);
		
		GridBagConstraints gbc_chckbxS = new GridBagConstraints();
		gbc_chckbxS.anchor = GridBagConstraints.WEST;
		gbc_chckbxS.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxS.gridx = 2;
		gbc_chckbxS.gridy = 7;
		panel.add(chckbxS, gbc_chckbxS);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		btnGuardar.addActionListener(saveCamion);
		btnGuardar.setEnabled(false);
		panel_1.add(btnGuardar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jdialog.dispose();
			}
		});
		
		panel_1.add(btnCancelar);
		jdialog.setVisible(true);
	}

	public void EditCamionDialog(Camion camion) {
		JDialog jdialog = new JDialog(frmTrabajoPrctico, "Modificar camión", Dialog.ModalityType.DOCUMENT_MODAL);
		JPanel contentPane;
		JTextField txtMarca = new JTextField(camion.getMarca());
		JTextField txtModelo = new JTextField(camion.getModelo());
		JTextField txtAnio = new JTextField(camion.getAnio().toString());
		JTextField txtDominio = new JTextField(camion.getDominio());
		JTextField txtCostoKm = new JTextField(camion.getCostoKm().toString());
		JTextField txtCapacidad = new JTextField(camion.getCapacidad().toString());
		JButton btnGuardar = new JButton("Guardar");
		JCheckBox chckbxS = new JCheckBox("Transporta líquidos");

		System.out.println("Capacidad:" + camion.getCapacidad());
		
		jdialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		jdialog.setResizable(false);
		jdialog.setMinimumSize(new Dimension(100, 100));
		jdialog.setBounds(100, 100, 350, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		jdialog.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 86, 58, 205, 32, 0 };
		gbl_panel.rowHeights = new int[] { 10, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		KeyAdapter validForm = new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (txtMarca.getText().length() == 0 || txtModelo.getText().length() == 0
						|| txtAnio.getText().length() == 0 || txtDominio.getText().length() == 0
						|| txtCostoKm.getText().length() == 0 || txtCapacidad.getText().length() == 0)
					btnGuardar.setEnabled(false);
				else
					btnGuardar.setEnabled(true);
			}
		};

		ActionListener saveCamion = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtMarca.getText().length() == 0 || txtModelo.getText().length() == 0
						|| txtAnio.getText().length() == 0 || txtDominio.getText().length() == 0
						|| txtCostoKm.getText().length() == 0 || txtCapacidad.getText().length() == 0)
					return;
				
				Integer anio;
				Float costokm;
				Integer capacidad;
				
				try
				{
					anio = Integer.parseInt(txtAnio.getText());
					costokm = Float.parseFloat(txtCostoKm.getText());
					capacidad = Integer.parseInt(txtCapacidad.getText());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico, "Uno o más datos ingresados no son válidos",
							"Información", JOptionPane.INFORMATION_MESSAGE);

					return;
				}
				
				camion.setMarca(txtMarca.getText());
				camion.setModelo(txtModelo.getText());
				camion.setAnio(anio);
				camion.setDominio(txtDominio.getText());
				camion.setCostoKm(costokm);
				camion.setTransportaLiq(chckbxS.isSelected());
				camion.setCapacidad(capacidad);

				refreshCamionTable();
				jdialog.dispose();
			}
		};
		
		JLabel lblMarca = new JLabel("Marca:");
		GridBagConstraints gbc_lblMarca = new GridBagConstraints();
		gbc_lblMarca.anchor = GridBagConstraints.EAST;
		gbc_lblMarca.insets = new Insets(0, 0, 5, 5);
		gbc_lblMarca.gridx = 1;
		gbc_lblMarca.gridy = 1;
		panel.add(lblMarca, gbc_lblMarca);

		GridBagConstraints gbc_txtMarca = new GridBagConstraints();
		gbc_txtMarca.insets = new Insets(0, 0, 5, 5);
		gbc_txtMarca.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMarca.gridx = 2;
		gbc_txtMarca.gridy = 1;
		panel.add(txtMarca, gbc_txtMarca);
		txtMarca.setColumns(10);
		txtMarca.addActionListener(saveCamion);
		txtMarca.addKeyListener(validForm);

		JLabel lblModelo = new JLabel("Modelo:");
		GridBagConstraints gbc_lblModelo = new GridBagConstraints();
		gbc_lblModelo.anchor = GridBagConstraints.EAST;
		gbc_lblModelo.insets = new Insets(0, 0, 5, 5);
		gbc_lblModelo.gridx = 1;
		gbc_lblModelo.gridy = 2;
		panel.add(lblModelo, gbc_lblModelo);

		GridBagConstraints gbc_txtModelo = new GridBagConstraints();
		gbc_txtModelo.insets = new Insets(0, 0, 5, 5);
		gbc_txtModelo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtModelo.gridx = 2;
		gbc_txtModelo.gridy = 2;
		panel.add(txtModelo, gbc_txtModelo);
		txtModelo.setColumns(10);
		txtModelo.addActionListener(saveCamion);
		txtModelo.addKeyListener(validForm);

		JLabel lblAnio = new JLabel("Año:");
		GridBagConstraints gbc_lblAnio = new GridBagConstraints();
		gbc_lblAnio.anchor = GridBagConstraints.EAST;
		gbc_lblAnio.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnio.gridx = 1;
		gbc_lblAnio.gridy = 3;
		panel.add(lblAnio, gbc_lblAnio);

		GridBagConstraints gbc_txtAnio = new GridBagConstraints();
		gbc_txtAnio.insets = new Insets(0, 0, 5, 5);
		gbc_txtAnio.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAnio.gridx = 2;
		gbc_txtAnio.gridy = 3;
		panel.add(txtAnio, gbc_txtAnio);
		txtAnio.setColumns(10);
		txtAnio.addActionListener(saveCamion);
		txtAnio.addKeyListener(validForm);

		JLabel lblDominio = new JLabel("Dominio:");
		GridBagConstraints gbc_lblDominio = new GridBagConstraints();
		gbc_lblDominio.anchor = GridBagConstraints.EAST;
		gbc_lblDominio.insets = new Insets(0, 0, 5, 5);
		gbc_lblDominio.gridx = 1;
		gbc_lblDominio.gridy = 4;
		panel.add(lblDominio, gbc_lblDominio);

		GridBagConstraints gbc_txtDominio = new GridBagConstraints();
		gbc_txtDominio.insets = new Insets(0, 0, 5, 5);
		gbc_txtDominio.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDominio.gridx = 2;
		gbc_txtDominio.gridy = 4;
		panel.add(txtDominio, gbc_txtDominio);
		txtDominio.setColumns(10);
		txtDominio.addActionListener(saveCamion);
		txtDominio.addKeyListener(validForm);

		JLabel lblCostoKm = new JLabel("Costo/km:");
		GridBagConstraints gbc_lblCostoKm = new GridBagConstraints();
		gbc_lblCostoKm.anchor = GridBagConstraints.EAST;
		gbc_lblCostoKm.insets = new Insets(0, 0, 5, 5);
		gbc_lblCostoKm.gridx = 1;
		gbc_lblCostoKm.gridy = 5;
		panel.add(lblCostoKm, gbc_lblCostoKm);

		GridBagConstraints gbc_txtCostoKm = new GridBagConstraints();
		gbc_txtCostoKm.insets = new Insets(0, 0, 5, 5);
		gbc_txtCostoKm.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCostoKm.gridx = 2;
		gbc_txtCostoKm.gridy = 5;
		panel.add(txtCostoKm, gbc_txtCostoKm);
		txtCostoKm.setColumns(10);
		txtCostoKm.addActionListener(saveCamion);
		txtCostoKm.addKeyListener(validForm);

		JLabel lblCapacidad = new JLabel("Capacidad:");
		GridBagConstraints gbc_lblCapacidad = new GridBagConstraints();
		gbc_lblCapacidad.anchor = GridBagConstraints.EAST;
		gbc_lblCapacidad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCapacidad.gridx = 1;
		gbc_lblCapacidad.gridy = 6;
		panel.add(lblCapacidad, gbc_lblCapacidad);

		GridBagConstraints gbc_txtCapacidad = new GridBagConstraints();
		gbc_txtCapacidad.insets = new Insets(0, 0, 5, 5);
		gbc_txtCapacidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCapacidad.gridx = 2;
		gbc_txtCapacidad.gridy = 6;
		panel.add(txtCapacidad, gbc_txtCapacidad);
		txtCapacidad.setColumns(10);
		txtCapacidad.addActionListener(saveCamion);
		txtCapacidad.addKeyListener(validForm);

		GridBagConstraints gbc_chckbxS = new GridBagConstraints();
		gbc_chckbxS.anchor = GridBagConstraints.WEST;
		gbc_chckbxS.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxS.gridx = 2;
		gbc_chckbxS.gridy = 7;
		chckbxS.setSelected(camion.getTransportaLiq());
		panel.add(chckbxS, gbc_chckbxS);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		btnGuardar.addActionListener(saveCamion);
		panel_1.add(btnGuardar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jdialog.dispose();
			}
		});
		panel_1.add(btnCancelar);
		jdialog.setVisible(true);
	}

	public void AddInsumoDialog(Integer plantaId) {
		Planta planta = gestorPlanta.obtenerPlanta(plantaId);
		JDialog jdialog = new JDialog(frmTrabajoPrctico, "Añadir insumo a planta " + planta.getNombre(),
				Dialog.ModalityType.DOCUMENT_MODAL);
		JPanel contentPane;
		JTextField txtCantidad;
		JTextField txtPuntoPedido;

		jdialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		jdialog.setResizable(false);
		jdialog.setMinimumSize(new Dimension(100, 100));
		jdialog.setBounds(100, 100, 350, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		jdialog.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 86, 58, 205, 32, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 0;
		panel.add(panel_2, gbc_panel_2);

		JLabel lblPeso = new JLabel("Insumo:");
		GridBagConstraints gbc_lblPeso = new GridBagConstraints();
		gbc_lblPeso.anchor = GridBagConstraints.EAST;
		gbc_lblPeso.insets = new Insets(0, 0, 5, 5);
		gbc_lblPeso.gridx = 1;
		gbc_lblPeso.gridy = 1;
		panel.add(lblPeso, gbc_lblPeso);

		JComboBox<Insumo> comboBox = new JComboBox<Insumo>();
		comboBox.setModel(new DefaultComboBoxModel<Insumo>(gestorInsumo.getListaInsumos().toArray(new Insumo[0])));
		comboBox.setSelectedIndex(-1);

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 1;
		panel.add(comboBox, gbc_comboBox);

		JLabel lblCantidad = new JLabel("Cantidad:");
		GridBagConstraints gbc_lblCantidad = new GridBagConstraints();
		gbc_lblCantidad.anchor = GridBagConstraints.EAST;
		gbc_lblCantidad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCantidad.gridx = 1;
		gbc_lblCantidad.gridy = 2;
		panel.add(lblCantidad, gbc_lblCantidad);

		txtCantidad = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 2;
		panel.add(txtCantidad, gbc_textField);
		txtCantidad.setColumns(10);

		JLabel lblPuntoDePedido = new JLabel("Punto de pedido:");
		GridBagConstraints gbc_lblPuntoDePedido = new GridBagConstraints();
		gbc_lblPuntoDePedido.anchor = GridBagConstraints.EAST;
		gbc_lblPuntoDePedido.insets = new Insets(0, 0, 5, 5);
		gbc_lblPuntoDePedido.gridx = 1;
		gbc_lblPuntoDePedido.gridy = 3;
		panel.add(lblPuntoDePedido, gbc_lblPuntoDePedido);

		txtPuntoPedido = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 3;
		panel.add(txtPuntoPedido, gbc_textField_1);
		txtPuntoPedido.setColumns(10);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico, "Debe seleccionarse un insumo.", "Información",
							JOptionPane.INFORMATION_MESSAGE);

					return;
				}

				Integer puntoPedido;
				Integer cantidad;

				try {
					puntoPedido = Integer.parseInt(txtPuntoPedido.getText());
					cantidad = Integer.parseInt(txtCantidad.getText());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico, "Uno o más datos ingresados no son válidos",
							"Información", JOptionPane.INFORMATION_MESSAGE);

					return;
				}

				HashMap<Integer, Stock> listaStock = gestorPlanta.obtenerPlanta(plantaId).getListaStock();

				Insumo insumoSelect = (Insumo) comboBox.getSelectedItem();

				if (listaStock.keySet().contains(insumoSelect.getId())) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico,
							"El insumo seleccionado ya se encuentra en la lista de Stock", "Información",
							JOptionPane.INFORMATION_MESSAGE);

					return;
				}

				Stock stockInsumo = new Stock();
				stockInsumo.setCantidad(cantidad);
				stockInsumo.setPuntoPedido(puntoPedido);
				stockInsumo.setInsumo(insumoSelect);
				listaStock.put(insumoSelect.getId(), stockInsumo);

				MainMenu.refreshStockTable(plantaId);
				jdialog.dispose();
			}
		});
		panel_1.add(btnGuardar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jdialog.dispose();
			}
		});
		panel_1.add(btnCancelar);
		jdialog.setVisible(true);
	}
}
