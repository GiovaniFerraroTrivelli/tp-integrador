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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dominio.Camion;
import dominio.Insumo;
import dominio.InsumoLiquido;
import dominio.Planta.TipoPlanta;
import dominio.Stock;
import dominio.UnidadDeMedida;
import estructuras.Grafo;
import dominio.Planta;
import gestores.*;

import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.BoxLayout;
import javax.swing.JList;

public class MainMenu {
	private JFrame frmTrabajoPrctico;
	private CardLayout cl;
	private static JTable tablePlantas;
	private static JTable tableStock;
	private static JTable tableInsumos;
	private static JTable tableCamiones;
	private static JList<Planta> listRuta;
	static GestorPlanta gestorPlanta = GestorPlanta.getInstance();
	static GestorInsumo gestorInsumo = GestorInsumo.getInstance();
	static GestorRuta gestorRuta = GestorRuta.getInstance();
	static GestorCamion gestorCamion = GestorCamion.getInstance();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

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
				refreshRutaList();
				cl.show(frmTrabajoPrctico.getContentPane(), "card__Rutas");
			}
		});
		
		JButton infob = new JButton(infoIcon);
		infob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GrafoPanel.refreshVertices();
				cl.show(frmTrabajoPrctico.getContentPane(), "card__Info");
			}
		});
		
		JButton camionb = new JButton(camionIcon);
		camionb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshCamionTable();
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

		int arraySize = botones.size();
		for (int i = 0; i < arraySize; i++) {
			JButton b = botones.get(i);
			b.setBackground(Color.WHITE);
			b.setVerticalTextPosition(SwingConstants.BOTTOM);
			b.setHorizontalTextPosition(SwingConstants.CENTER);
			b.setCursor(new Cursor(Cursor.HAND_CURSOR));
			b.setFocusPainted(false);
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
				System.out.println(txtSearchInsumo.getText());

				if(val.length() == 0)
				{
					refreshInsumoTable(null);
					return;
				}
				
				switch (comboBox.getSelectedIndex()) {
				case 0: {
					System.out.println("Buscar por nombre: " + val);
					ArrayList<Insumo> resultados = gestorInsumo.buscar(val);
					System.out.println(resultados);
					refreshInsumoTable(resultados);

					break;
				}
				case 1: {
					System.out.println("Buscar por costo (false): " + val);
					refreshInsumoTable(gestorInsumo.buscarPorCosto(Float.parseFloat(val), false));

					break;
				}
				case 2: {
					System.out.println("Buscar por costo (true): " + val);
					refreshInsumoTable(gestorInsumo.buscarPorCosto(Float.parseFloat(val), true));

					break;
				}
				case 3: {
					System.out.println("Buscar por stock (false): " + val);
					System.out.println(gestorInsumo.buscarPorStock(Integer.parseInt(val), false));
					refreshInsumoTable(gestorInsumo.buscarPorStock(Integer.parseInt(val), false));

					break;
				}
				case 4: {
					System.out.println("Buscar por stock (true): " + val);
					refreshInsumoTable(gestorInsumo.buscarPorStock(Integer.parseInt(val), true));

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
		gbl_panel.columnWidths = new int[] { 0, 103, 0, 0, 99, 111, 101, 193, 0, 179, 84 };
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

		// Label de buscar planta
		JLabel lblBuscarPlantas = new JLabel("Buscar plantas:");
		GridBagConstraints gbc_lblBuscarPlantas = new GridBagConstraints();
		gbc_lblBuscarPlantas.insets = new Insets(0, 0, 5, 5);
		gbc_lblBuscarPlantas.anchor = GridBagConstraints.EAST;
		gbc_lblBuscarPlantas.gridx = 7;
		gbc_lblBuscarPlantas.gridy = 4;
		panel.add(lblBuscarPlantas, gbc_lblBuscarPlantas);

		// Input de búsqueda
		JTextField txtSearch = new JTextField();
		txtSearch.setToolTipText("Escriba aquí lo que desea buscar...");
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.gridx = 8;
		gbc_textField_1.gridy = 4;
		panel.add(txtSearch, gbc_textField_1);
		txtSearch.setColumns(10);

		// Botón de buscar
		JButton btnBuscar = new JButton("Buscar");
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.anchor = GridBagConstraints.WEST;
		gbc_btnBuscar.gridwidth = 2;
		gbc_btnBuscar.fill = GridBagConstraints.VERTICAL;
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 0);
		gbc_btnBuscar.gridx = 10;
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

		JLabel lblPlantas = new JLabel("Plantas:");
		GridBagConstraints gbc_lblPlantas = new GridBagConstraints();
		gbc_lblPlantas.anchor = GridBagConstraints.EAST;
		gbc_lblPlantas.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlantas.gridx = 1;
		gbc_lblPlantas.gridy = 4;
		panel.add(lblPlantas, gbc_lblPlantas);

		// Añadir planta
		JButton btnNuevoInsumo = new JButton("Añadir");
		GridBagConstraints gbc_btnNuevoInsumo = new GridBagConstraints();
		gbc_btnNuevoInsumo.anchor = GridBagConstraints.EAST;
		gbc_btnNuevoInsumo.insets = new Insets(0, 0, 5, 5);
		gbc_btnNuevoInsumo.gridx = 2;
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
		gbc_btnEditar_1.gridx = 3;
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
		gbc_btnEliminar.gridx = 4;
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
		gbc_btnType.gridx = 5;
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
					        "Establecer tipo de planta",
					        "Tipo de planta",
					        JOptionPane.QUESTION_MESSAGE,
					        null,
					        items,
					        curPlanta.getTipo()
					);
					
					System.out.println(tipoPlanta);
			
					if(tipoPlanta != null)
					{
						switch(tipoPlanta)
						{
							case plantaProduccion: // produccion
							{
								curPlanta.setTipo(Planta.TipoPlanta.plantaProduccion);
								validInput = true;
							}
							case plantaAcopioInicial:
							case plantaAcopioFinal:
							{
								tempPlanta = gestorPlanta.getFirstPlantaWithTipo(tipoPlanta);
								
								if(tempPlanta != null && tempPlanta != curPlanta)
								{
									JOptionPane.showMessageDialog(frmTrabajoPrctico, "Ya hay una planta de este tipo.",
											"Información", JOptionPane.INFORMATION_MESSAGE);
								}
								else
								{
									curPlanta.setTipo(tipoPlanta);
									validInput = true;
								}
							}
						}
					}
					else validInput = true;
				} while (!validInput);				
				
				refreshPlantaTable(null);
			}
		});

		panel.add(btnType, gbc_btnType);
		
		// ------------------------------------------------------------------------------------------------
		// Tabla de Plantas
		// ------------------------------------------------------------------------------------------------
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.gridwidth = 5;
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
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_11 = new GridBagConstraints();
		gbc_panel_11.insets = new Insets(0, 0, 0, 5);
		gbc_panel_11.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_11.gridx = 6;
		gbc_panel_11.gridy = 5;
		panel.add(panel_1, gbc_panel_11);

		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 30, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		// Añadir insumo
		JButton btnAadirInsumo = new JButton("Añadir insumo");
		GridBagConstraints gbc_btnAadirInsumo = new GridBagConstraints();
		gbc_btnAadirInsumo.gridwidth = 2;
		gbc_btnAadirInsumo.insets = new Insets(0, 0, 5, 0);
		gbc_btnAadirInsumo.gridx = 0;
		gbc_btnAadirInsumo.gridy = 0;
		panel_1.add(btnAadirInsumo, gbc_btnAadirInsumo);

		btnAadirInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer selectedRow = tablePlantas.getSelectedRow();

				if (selectedRow < 0) {
					JOptionPane.showMessageDialog(frmTrabajoPrctico, "No hay ninguna planta seleccionada",
							"Información", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				Integer selectedPlanta = (Integer) tablePlantas.getValueAt(selectedRow, 0);
				AddInsumoDialog(selectedPlanta);
			}
		});

		// Eliminar insumo
		JButton btnEliminarInsumo = new JButton("Eliminar insumo");
		GridBagConstraints gbc_btnEliminarInsumo = new GridBagConstraints();
		gbc_btnEliminarInsumo.gridwidth = 2;
		gbc_btnEliminarInsumo.gridx = 0;
		gbc_btnEliminarInsumo.gridy = 2;
		panel_1.add(btnEliminarInsumo, gbc_btnEliminarInsumo);

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

		// Editar insumo
		JButton btnEditarInsumo = new JButton("Editar insumo");
		GridBagConstraints gbc_btnEditarInsumo = new GridBagConstraints();
		gbc_btnEditarInsumo.gridwidth = 2;
		gbc_btnEditarInsumo.insets = new Insets(0, 0, 5, 0);
		gbc_btnEditarInsumo.gridx = 0;
		gbc_btnEditarInsumo.gridy = 1;
		panel_1.add(btnEditarInsumo, gbc_btnEditarInsumo);
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
				CreateRutaDialog();
			}
		});

		panel.add(btnNuevoCamino, gbc_btnNuevoCamino);

		// ------------------------------------------------------------------------------------------------
		// Lista de plantas (izquierda), origen
		// ------------------------------------------------------------------------------------------------
		GridBagConstraints gbc_panel_91 = new GridBagConstraints();
		gbc_panel_91.gridwidth = 3;
		gbc_panel_91.insets = new Insets(0, 0, 0, 5);
		gbc_panel_91.fill = GridBagConstraints.BOTH;
		gbc_panel_91.gridx = 1;
		gbc_panel_91.gridy = 5;

		JList<Planta> caminoOrigin = new JList<Planta>();
		caminoOrigin.setLayoutOrientation(JList.VERTICAL);
		caminoOrigin.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		panel.add(new JScrollPane(caminoOrigin), gbc_panel_91);		
		
		// ------------------------------------------------------------------------------------------------
		// Lista de plantas (derecha), destino
		// ------------------------------------------------------------------------------------------------
		GridBagConstraints gbc_panel_222 = new GridBagConstraints();
		gbc_panel_222.gridwidth = 3;
		gbc_panel_222.insets = new Insets(0, 0, 0, 5);
		gbc_panel_222.fill = GridBagConstraints.BOTH;
		gbc_panel_222.gridx = 7;
		gbc_panel_222.gridy = 5;

		JList<Planta> caminoEnd = new JList<Planta>();
		caminoEnd.setLayoutOrientation(JList.VERTICAL);
		caminoEnd.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		panel.add(new JScrollPane(caminoEnd), gbc_panel_222);
		
		// ------------------------------------------------------------------------------------------------
		// Lista de plantas (centro)
		// ------------------------------------------------------------------------------------------------
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.gridwidth = 3;
		gbc_panel_9.insets = new Insets(0, 0, 0, 5);
		gbc_panel_9.fill = GridBagConstraints.BOTH;
		gbc_panel_9.gridx = 4;
		gbc_panel_9.gridy = 5;

		listRuta = new JList<Planta>();
		listRuta.setListData(gestorPlanta.getListaPlantas().values().toArray(new Planta[0]));
		listRuta.setLayoutOrientation(JList.VERTICAL);
		listRuta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		listRuta.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && listRuta.getSelectedIndex() != -1)
				{
					caminoOrigin.setListData(gestorRuta.getStartingPlantas((Planta)listRuta.getSelectedValue()).toArray(new Planta[0]));
					System.out.println("origen: " + gestorRuta.getStartingPlantas((Planta)listRuta.getSelectedValue()).toArray(new Planta[0]).toString());
					caminoEnd.setListData(gestorRuta.getEndingPlantas((Planta)listRuta.getSelectedValue()).toArray(new Planta[0]));
					System.out.println("destino: " + gestorRuta.getEndingPlantas((Planta)listRuta.getSelectedValue()).toArray(new Planta[0]).toString());
				}
			}
		});

		panel.add(new JScrollPane(listRuta), gbc_panel_9);

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
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0 };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		// Título principal del panel
		JLabel lblPanelDeAdministracin = new JLabel("Visualización de la información guardada"); // TODO: pensar un nombre mejor
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
		// Panel para visualización
		// ------------------------------------------------------------------------------------------------
		GridBagConstraints gbc_panel_91 = new GridBagConstraints();
		gbc_panel_91.gridwidth = 9;
		gbc_panel_91.insets = new Insets(0, 0, 0, 5);
		gbc_panel_91.fill = GridBagConstraints.BOTH;
		gbc_panel_91.gridx = 1;
		gbc_panel_91.gridy = 5;

		JPanel panelGrafos = GrafoPanel.getInstance();
		// TODO: Codigo del panel de grafos
		panel.add(panelGrafos, gbc_panel_91);
		
		
		/*CODIGOS DE GRAFOS (FRAN)*/
		/*Grafo grafo = new Grafo(gestorPlanta.getListaPlantas(), gestorRuta.getListaRutas());*/
	}
	
	public static void refreshCamionTable()
	{
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
			Float capacidad = camion.getCapacidad();

			Object[] data = { id, marca, modelo, anio, dominio, costoKm, transpLiq, capacidad };
			tableModel.addRow(data);
		}

		tableCamiones.setModel(tableModel);
	}
	
	public static void refreshRutaList()
	{
		listRuta.setListData(gestorPlanta.getListaPlantas().values().toArray(new Planta[0]));
	}
	
	public static void refreshPlantaTable(ArrayList<Planta> search) {
		String col[] = { "ID", "Nombre", "Tipo" };
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

		for (int i = 0; i < tempListaPlantas.size(); i++) {
			int id = tempListaPlantas.get(i).getId();
			String nombre = tempListaPlantas.get(i).getNombre();
			String tipo = tempListaPlantas.get(i).getTipo().toString();

			Object[] data = { id, nombre, tipo };
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
		String col[] = { "ID", "Descripción", "Costo", "Peso", "Refrigerado", "Stock" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		
		ArrayList<Insumo> tempListaInsumos;
		
		if(search == null)
			tempListaInsumos = gestorInsumo.getListaInsumos();
		else
			tempListaInsumos = search;

		for (int i = 0; i < tempListaInsumos.size(); i++) {
			Integer id = tempListaInsumos.get(i).getId();
			String descripcion = tempListaInsumos.get(i).getDescripcion();
			Float costo = tempListaInsumos.get(i).getCosto();
			Float peso = tempListaInsumos.get(i).getPeso();
			String refrigerado = tempListaInsumos.get(i) instanceof InsumoLiquido ? "Si" : "No";
			Integer stock = tempListaInsumos.get(i).getStock();

			Object[] data = { id, descripcion, costo, peso, refrigerado, stock };
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
		jdialog.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		jdialog.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 119, 58, 86, 32, 0 };
		gbl_panel.rowHeights = new int[] { 55, 0, 20, 0, 0, 0, 0 };
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

		JLabel lblDescripcin = new JLabel("Planta de origen:");
		GridBagConstraints gbc_lblDescripcin = new GridBagConstraints();
		gbc_lblDescripcin.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcin.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcin.gridx = 1;
		gbc_lblDescripcin.gridy = 2;
		panel.add(lblDescripcin, gbc_lblDescripcin);

		comboStart = new JComboBox<Planta>();
		comboStart.setModel(new DefaultComboBoxModel<Planta>(gestorPlanta.getListaPlantas().values().toArray(new Planta[0])));
		
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
		comboEnd.setModel(new DefaultComboBoxModel<Planta>(gestorPlanta.getListaPlantas().values().toArray(new Planta[0])));

		GridBagConstraints gbc_comboEnd = new GridBagConstraints();
		gbc_comboEnd.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboEnd.anchor = GridBagConstraints.NORTH;
		gbc_comboEnd.insets = new Insets(0, 0, 5, 5);
		gbc_comboEnd.gridx = 2;
		gbc_comboEnd.gridy = 3;
		panel.add(comboEnd, gbc_comboEnd);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(gestorRuta.createRuta((Planta)comboStart.getSelectedItem(), (Planta)comboEnd.getSelectedItem()));
				
				/*Insumo insumo;

				if (chckbxS.isSelected()) {
					insumo = gestorInsumo.crearLiquido(txtDescripcion.getText());
				} else {
					insumo = gestorInsumo.crear(txtDescripcion.getText());
				}

				insumo.setCosto(Float.parseFloat(txtCosto.getText()));
				insumo.setPeso(Float.parseFloat(txtPeso.getText()));
				insumo.setUnidadDeMedida(UnidadDeMedida.KILO); */

				refreshRutaList();

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
		JCheckBox chckbxS = new JCheckBox("Insumo refrigerado");
		JButton btnGuardar = new JButton("Guardar");
		
		jdialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		jdialog.setResizable(false);
		jdialog.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		jdialog.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 119, 58, 86, 32, 0 };
		gbl_panel.rowHeights = new int[] { 55, 0, 20, 0, 0, 0, 0 };
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
	            if(txtDescripcion.getText().length() == 0 || txtCosto.getText().length() == 0 || txtPeso.getText().length() == 0)
	            	btnGuardar.setEnabled(false);
	            else
	            {
	            	btnGuardar.setEnabled(true);
	            }
	        }
		};

		ActionListener saveInsumo = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(txtCosto.getText().length() == 0 || txtPeso.getText().length() == 0 || txtDescripcion.getText().length() == 0)
					return;
				
				Float costo;
				Float peso;
				
				try
				{
			        costo = Float.parseFloat(txtCosto.getText());
			        peso = Float.parseFloat(txtPeso.getText());
			    }
				catch(NumberFormatException e)
				{
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
				insumo.setUnidadDeMedida(UnidadDeMedida.KILO);

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

		JLabel lblPeso = new JLabel("Peso:");
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

		GridBagConstraints gbc_chckbxS = new GridBagConstraints();
		gbc_chckbxS.anchor = GridBagConstraints.WEST;
		gbc_chckbxS.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxS.gridx = 2;
		gbc_chckbxS.gridy = 5;
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

		JTextField txtDescripcion;
		JTextField txtCosto;
		JTextField txtPeso;

		jdialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		jdialog.setResizable(false);
		jdialog.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		jdialog.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 119, 58, 86, 32, 0 };
		gbl_panel.rowHeights = new int[] { 55, 0, 20, 0, 0, 0, 0 };
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

		JLabel lblDescripcin = new JLabel("Descripción:");
		GridBagConstraints gbc_lblDescripcin = new GridBagConstraints();
		gbc_lblDescripcin.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcin.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcin.gridx = 1;
		gbc_lblDescripcin.gridy = 2;
		panel.add(lblDescripcin, gbc_lblDescripcin);

		txtDescripcion = new JTextField(insumo.getDescripcion());
		GridBagConstraints gbc_txtDescripcion = new GridBagConstraints();
		gbc_txtDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDescripcion.anchor = GridBagConstraints.NORTH;
		gbc_txtDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_txtDescripcion.gridx = 2;
		gbc_txtDescripcion.gridy = 2;
		panel.add(txtDescripcion, gbc_txtDescripcion);
		txtDescripcion.setColumns(10);

		JLabel lblCosto = new JLabel("Costo:");
		GridBagConstraints gbc_lblCosto = new GridBagConstraints();
		gbc_lblCosto.insets = new Insets(0, 0, 5, 5);
		gbc_lblCosto.anchor = GridBagConstraints.EAST;
		gbc_lblCosto.gridx = 1;
		gbc_lblCosto.gridy = 3;
		panel.add(lblCosto, gbc_lblCosto);

		txtCosto = new JTextField(insumo.getCosto().toString());
		GridBagConstraints gbc_txtCosto = new GridBagConstraints();
		gbc_txtCosto.insets = new Insets(0, 0, 5, 5);
		gbc_txtCosto.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCosto.gridx = 2;
		gbc_txtCosto.gridy = 3;
		panel.add(txtCosto, gbc_txtCosto);
		txtCosto.setColumns(10);

		JLabel lblPeso = new JLabel("Peso:");
		GridBagConstraints gbc_lblPeso = new GridBagConstraints();
		gbc_lblPeso.anchor = GridBagConstraints.EAST;
		gbc_lblPeso.insets = new Insets(0, 0, 5, 5);
		gbc_lblPeso.gridx = 1;
		gbc_lblPeso.gridy = 4;
		panel.add(lblPeso, gbc_lblPeso);

		txtPeso = new JTextField(insumo.getPeso().toString());
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 4;
		panel.add(txtPeso, gbc_textField);
		txtPeso.setColumns(10);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				insumo.setDescripcion(txtDescripcion.getText());
				insumo.setCosto(Float.parseFloat(txtCosto.getText()));
				insumo.setPeso(Float.parseFloat(txtPeso.getText()));
				insumo.setUnidadDeMedida(UnidadDeMedida.KILO);

				MainMenu.refreshInsumoTable(null);

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

	public void CreateCamionDialog()
	{
		JDialog jdialog = new JDialog(frmTrabajoPrctico, "Añadir camión", Dialog.ModalityType.DOCUMENT_MODAL);
		JPanel contentPane;
		JTextField txtMarca;
		JTextField txtModelo;
		JTextField txtAnio;
		JTextField txtDominio;
		JTextField txtCostoKm;
		JTextField txtCapacidad;
		JCheckBox chckbxS = new JCheckBox("Transporta líquidos");

		jdialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		jdialog.setResizable(false);
		jdialog.setMinimumSize(new Dimension(100, 100));
		jdialog.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		jdialog.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 86, 58, 205, 32, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblMarca = new JLabel("Marca:");
		GridBagConstraints gbc_lblMarca = new GridBagConstraints();
		gbc_lblMarca.anchor = GridBagConstraints.EAST;
		gbc_lblMarca.insets = new Insets(0, 0, 5, 5);
		gbc_lblMarca.gridx = 1;
		gbc_lblMarca.gridy = 1;
		panel.add(lblMarca, gbc_lblMarca);

		txtMarca = new JTextField();
		GridBagConstraints gbc_txtMarca = new GridBagConstraints();
		gbc_txtMarca.insets = new Insets(0, 0, 5, 5);
		gbc_txtMarca.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMarca.gridx = 2;
		gbc_txtMarca.gridy = 1;
		panel.add(txtMarca, gbc_txtMarca);
		txtMarca.setColumns(10);
		
		JLabel lblModelo = new JLabel("Modelo:");
		GridBagConstraints gbc_lblModelo = new GridBagConstraints();
		gbc_lblModelo.anchor = GridBagConstraints.EAST;
		gbc_lblModelo.insets = new Insets(0, 0, 5, 5);
		gbc_lblModelo.gridx = 1;
		gbc_lblModelo.gridy = 2;
		panel.add(lblModelo, gbc_lblModelo);

		txtModelo = new JTextField();
		GridBagConstraints gbc_txtModelo = new GridBagConstraints();
		gbc_txtModelo.insets = new Insets(0, 0, 5, 5);
		gbc_txtModelo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtModelo.gridx = 2;
		gbc_txtModelo.gridy = 2;
		panel.add(txtModelo, gbc_txtModelo);
		txtModelo.setColumns(10);

		JLabel lblAnio = new JLabel("Año:");
		GridBagConstraints gbc_lblAnio = new GridBagConstraints();
		gbc_lblAnio.anchor = GridBagConstraints.EAST;
		gbc_lblAnio.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnio.gridx = 1;
		gbc_lblAnio.gridy = 3;
		panel.add(lblAnio, gbc_lblAnio);

		txtAnio = new JTextField();
		GridBagConstraints gbc_txtAnio = new GridBagConstraints();
		gbc_txtAnio.insets = new Insets(0, 0, 5, 5);
		gbc_txtAnio.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAnio.gridx = 2;
		gbc_txtAnio.gridy = 3;
		panel.add(txtAnio, gbc_txtAnio);
		txtAnio.setColumns(10);

		JLabel lblDominio = new JLabel("Dominio:");
		GridBagConstraints gbc_lblDominio = new GridBagConstraints();
		gbc_lblDominio.anchor = GridBagConstraints.EAST;
		gbc_lblDominio.insets = new Insets(0, 0, 5, 5);
		gbc_lblDominio.gridx = 1;
		gbc_lblDominio.gridy = 4;
		panel.add(lblDominio, gbc_lblDominio);

		txtDominio = new JTextField();
		GridBagConstraints gbc_txtDominio = new GridBagConstraints();
		gbc_txtDominio.insets = new Insets(0, 0, 5, 5);
		gbc_txtDominio.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDominio.gridx = 2;
		gbc_txtDominio.gridy = 4;
		panel.add(txtDominio, gbc_txtDominio);
		txtAnio.setColumns(10);

		JLabel lblCostoKm = new JLabel("Costo/km:");
		GridBagConstraints gbc_lblCostoKm = new GridBagConstraints();
		gbc_lblCostoKm.anchor = GridBagConstraints.EAST;
		gbc_lblCostoKm.insets = new Insets(0, 0, 5, 5);
		gbc_lblCostoKm.gridx = 1;
		gbc_lblCostoKm.gridy = 5;
		panel.add(lblCostoKm, gbc_lblCostoKm);

		txtCostoKm = new JTextField();
		GridBagConstraints gbc_txtCostoKm = new GridBagConstraints();
		gbc_txtCostoKm.insets = new Insets(0, 0, 5, 5);
		gbc_txtCostoKm.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCostoKm.gridx = 2;
		gbc_txtCostoKm.gridy = 5;
		panel.add(txtCostoKm, gbc_txtCostoKm);
		txtAnio.setColumns(10);

		JLabel lblCapacidad = new JLabel("Capacidad:");
		GridBagConstraints gbc_lblCapacidad = new GridBagConstraints();
		gbc_lblCapacidad.anchor = GridBagConstraints.EAST;
		gbc_lblCapacidad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCapacidad.gridx = 1;
		gbc_lblCapacidad.gridy = 6;
		panel.add(lblCapacidad, gbc_lblCapacidad);

		txtCapacidad = new JTextField();
		GridBagConstraints gbc_txtCapacidad = new GridBagConstraints();
		gbc_txtCapacidad.insets = new Insets(0, 0, 5, 5);
		gbc_txtCapacidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCapacidad.gridx = 2;
		gbc_txtCapacidad.gridy = 6;
		panel.add(txtCapacidad, gbc_txtCapacidad);
		txtAnio.setColumns(10);
		
		GridBagConstraints gbc_chckbxS = new GridBagConstraints();
		gbc_chckbxS.anchor = GridBagConstraints.WEST;
		gbc_chckbxS.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxS.gridx = 2;
		gbc_chckbxS.gridy = 7;
		panel.add(chckbxS, gbc_chckbxS);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Camion camion = gestorCamion.crearCamion();
				camion.setMarca(txtMarca.getText());
				camion.setModelo(txtModelo.getText());
				camion.setAnio(Integer.parseInt(txtAnio.getText()));
				camion.setDominio(txtDominio.getText());
				camion.setCostoKm(Float.parseFloat(txtCostoKm.getText()));
				camion.setTransportaLiq(chckbxS.isSelected());
				camion.setCapacidad(Float.parseFloat(txtCapacidad.getText()));

				refreshCamionTable();
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
	
	public void EditCamionDialog(Camion camion)
	{
		JDialog jdialog = new JDialog(frmTrabajoPrctico, "Modificar camión", Dialog.ModalityType.DOCUMENT_MODAL);
		JPanel contentPane;
		JTextField txtMarca;
		JTextField txtModelo;
		JTextField txtAnio;
		JTextField txtDominio;
		JTextField txtCostoKm;
		JTextField txtCapacidad;
		JCheckBox chckbxS = new JCheckBox("Transporta líquidos");

		jdialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		jdialog.setResizable(false);
		jdialog.setMinimumSize(new Dimension(100, 100));
		jdialog.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		jdialog.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 86, 58, 205, 32, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblMarca = new JLabel("Marca:");
		GridBagConstraints gbc_lblMarca = new GridBagConstraints();
		gbc_lblMarca.anchor = GridBagConstraints.EAST;
		gbc_lblMarca.insets = new Insets(0, 0, 5, 5);
		gbc_lblMarca.gridx = 1;
		gbc_lblMarca.gridy = 1;
		panel.add(lblMarca, gbc_lblMarca);

		txtMarca = new JTextField(camion.getMarca());
		GridBagConstraints gbc_txtMarca = new GridBagConstraints();
		gbc_txtMarca.insets = new Insets(0, 0, 5, 5);
		gbc_txtMarca.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMarca.gridx = 2;
		gbc_txtMarca.gridy = 1;
		panel.add(txtMarca, gbc_txtMarca);
		txtMarca.setColumns(10);
		
		JLabel lblModelo = new JLabel("Modelo:");
		GridBagConstraints gbc_lblModelo = new GridBagConstraints();
		gbc_lblModelo.anchor = GridBagConstraints.EAST;
		gbc_lblModelo.insets = new Insets(0, 0, 5, 5);
		gbc_lblModelo.gridx = 1;
		gbc_lblModelo.gridy = 2;
		panel.add(lblModelo, gbc_lblModelo);

		txtModelo = new JTextField(camion.getModelo());
		GridBagConstraints gbc_txtModelo = new GridBagConstraints();
		gbc_txtModelo.insets = new Insets(0, 0, 5, 5);
		gbc_txtModelo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtModelo.gridx = 2;
		gbc_txtModelo.gridy = 2;
		panel.add(txtModelo, gbc_txtModelo);
		txtModelo.setColumns(10);

		JLabel lblAnio = new JLabel("Año:");
		GridBagConstraints gbc_lblAnio = new GridBagConstraints();
		gbc_lblAnio.anchor = GridBagConstraints.EAST;
		gbc_lblAnio.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnio.gridx = 1;
		gbc_lblAnio.gridy = 3;
		panel.add(lblAnio, gbc_lblAnio);

		txtAnio = new JTextField(camion.getAnio().toString());
		GridBagConstraints gbc_txtAnio = new GridBagConstraints();
		gbc_txtAnio.insets = new Insets(0, 0, 5, 5);
		gbc_txtAnio.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAnio.gridx = 2;
		gbc_txtAnio.gridy = 3;
		panel.add(txtAnio, gbc_txtAnio);
		txtAnio.setColumns(10);

		JLabel lblDominio = new JLabel("Dominio:");
		GridBagConstraints gbc_lblDominio = new GridBagConstraints();
		gbc_lblDominio.anchor = GridBagConstraints.EAST;
		gbc_lblDominio.insets = new Insets(0, 0, 5, 5);
		gbc_lblDominio.gridx = 1;
		gbc_lblDominio.gridy = 4;
		panel.add(lblDominio, gbc_lblDominio);

		txtDominio = new JTextField(camion.getDominio());
		GridBagConstraints gbc_txtDominio = new GridBagConstraints();
		gbc_txtDominio.insets = new Insets(0, 0, 5, 5);
		gbc_txtDominio.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDominio.gridx = 2;
		gbc_txtDominio.gridy = 4;
		panel.add(txtDominio, gbc_txtDominio);
		txtAnio.setColumns(10);

		JLabel lblCostoKm = new JLabel("Costo/km:");
		GridBagConstraints gbc_lblCostoKm = new GridBagConstraints();
		gbc_lblCostoKm.anchor = GridBagConstraints.EAST;
		gbc_lblCostoKm.insets = new Insets(0, 0, 5, 5);
		gbc_lblCostoKm.gridx = 1;
		gbc_lblCostoKm.gridy = 5;
		panel.add(lblCostoKm, gbc_lblCostoKm);

		txtCostoKm = new JTextField(camion.getCostoKm().toString());
		GridBagConstraints gbc_txtCostoKm = new GridBagConstraints();
		gbc_txtCostoKm.insets = new Insets(0, 0, 5, 5);
		gbc_txtCostoKm.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCostoKm.gridx = 2;
		gbc_txtCostoKm.gridy = 5;
		panel.add(txtCostoKm, gbc_txtCostoKm);
		txtAnio.setColumns(10);

		JLabel lblCapacidad = new JLabel("Capacidad:");
		GridBagConstraints gbc_lblCapacidad = new GridBagConstraints();
		gbc_lblCapacidad.anchor = GridBagConstraints.EAST;
		gbc_lblCapacidad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCapacidad.gridx = 1;
		gbc_lblCapacidad.gridy = 6;
		panel.add(lblCapacidad, gbc_lblCapacidad);

		txtCapacidad = new JTextField(camion.getCapacidad().toString());
		GridBagConstraints gbc_txtCapacidad = new GridBagConstraints();
		gbc_txtCapacidad.insets = new Insets(0, 0, 5, 5);
		gbc_txtCapacidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCapacidad.gridx = 2;
		gbc_txtCapacidad.gridy = 6;
		panel.add(txtCapacidad, gbc_txtCapacidad);
		txtAnio.setColumns(10);
		
		GridBagConstraints gbc_chckbxS = new GridBagConstraints();
		gbc_chckbxS.anchor = GridBagConstraints.WEST;
		gbc_chckbxS.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxS.gridx = 2;
		gbc_chckbxS.gridy = 7;
		chckbxS.setSelected(camion.getTransportaLiq());
		panel.add(chckbxS, gbc_chckbxS);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				camion.setMarca(txtMarca.getText());
				camion.setModelo(txtModelo.getText());
				camion.setAnio(Integer.parseInt(txtAnio.getText()));
				camion.setDominio(txtDominio.getText());
				camion.setCostoKm(Float.parseFloat(txtCostoKm.getText()));
				camion.setTransportaLiq(chckbxS.isSelected());
				camion.setCapacidad(Float.parseFloat(txtCapacidad.getText()));

				refreshCamionTable();
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
	
	public void AddInsumoDialog(Integer plantaId) {
		JDialog jdialog = new JDialog(frmTrabajoPrctico, "Añadir insumo a planta", Dialog.ModalityType.DOCUMENT_MODAL);
		JPanel contentPane;
		JTextField txtCantidad;
		JTextField txtPuntoPedido;

		jdialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		jdialog.setResizable(false);
		jdialog.setMinimumSize(new Dimension(100, 100));
		jdialog.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		jdialog.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 86, 58, 205, 32, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblPeso = new JLabel("Insumo:");
		GridBagConstraints gbc_lblPeso = new GridBagConstraints();
		gbc_lblPeso.anchor = GridBagConstraints.EAST;
		gbc_lblPeso.insets = new Insets(0, 0, 5, 5);
		gbc_lblPeso.gridx = 1;
		gbc_lblPeso.gridy = 1;
		panel.add(lblPeso, gbc_lblPeso);

		JComboBox<Insumo> comboBox = new JComboBox<Insumo>();
		comboBox.setModel(new DefaultComboBoxModel<Insumo>(gestorInsumo.getListaInsumos().toArray(new Insumo[0])));

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

		JLabel lblAgregandoAPlanta = new JLabel("Agregando a planta con ID " + plantaId);
		GridBagConstraints gbc_lblAgregandoAPlanta = new GridBagConstraints();
		gbc_lblAgregandoAPlanta.gridwidth = 4;
		gbc_lblAgregandoAPlanta.gridx = 0;
		gbc_lblAgregandoAPlanta.gridy = 7;
		panel.add(lblAgregandoAPlanta, gbc_lblAgregandoAPlanta);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Insumo insumoSelect = (Insumo) comboBox.getSelectedItem();
				Stock stockInsumo = new Stock();
				stockInsumo.setCantidad(Integer.parseInt(txtCantidad.getText()));
				stockInsumo.setPuntoPedido(Integer.parseInt(txtPuntoPedido.getText()));
				stockInsumo.setInsumo(insumoSelect);
				gestorPlanta.obtenerPlanta(plantaId).getListaStock().put(insumoSelect.getId(), stockInsumo);

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
