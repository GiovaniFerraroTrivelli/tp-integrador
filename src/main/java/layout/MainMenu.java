package layout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dominio.Insumo;
import dominio.InsumoLiquido;
import dominio.Planta;
import dominio.UnidadDeMedida;
import gestores.GestorPlanta;

import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class MainMenu {

	private JFrame frmTrabajoPrctico;
	private CardLayout cl;
	private JTextField textField_1;
	private static JTable tablePlantas;
	GestorPlanta gestorPlanta = GestorPlanta.getInstance();
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
	}
	
	private void loadMainMenu()
	{
		JPanel pnlMainMenu = new JPanel();
		frmTrabajoPrctico.getContentPane().add(pnlMainMenu, "card__MainMenu");
		
		GridLayout grid = new GridLayout(1, 5);
		pnlMainMenu.setLayout(grid);
		
		String resourcesPath = "src/main/resources/";

		ImageIcon insumoIcon = new ImageIcon(new ImageIcon(resourcesPath + "Insumo.png").getImage()
				.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		ImageIcon camionIcon = new ImageIcon(new ImageIcon(resourcesPath + "Camion.png").getImage()
				.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		ImageIcon caminoIcon = new ImageIcon(new ImageIcon(resourcesPath + "Camino.png").getImage()
				.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		ImageIcon plantaIcon = new ImageIcon(new ImageIcon(resourcesPath + "Planta.png").getImage()
				.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		ImageIcon infoIcon = new ImageIcon(
				new ImageIcon(resourcesPath + "Info.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
	
		JButton camionb = new JButton(camionIcon);
		JButton caminob = new JButton(caminoIcon);
		JButton infob = new JButton(infoIcon);

		JButton insumob = new JButton(insumoIcon);
		insumob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(frmTrabajoPrctico.getContentPane(), "card__Insumos");
			}
		});		

		JButton plantab = new JButton(plantaIcon);
		plantab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(frmTrabajoPrctico.getContentPane(), "card__Planta");
			}
		});
		
		ArrayList<JButton> botones = new ArrayList<JButton>();
		botones.add(insumob);
		botones.add(camionb);
		botones.add(caminob);
		botones.add(plantab);
		botones.add(infob);

		insumob.setText("Insumos");
		camionb.setText("Camiones");
		caminob.setText("Caminos");
		plantab.setText("Plantas");
		infob.setText("Informacion");

		int arraySize = botones.size();
		for (int i = 0; i < arraySize; i++)
		{
			JButton b = botones.get(i);
			b.setBackground(Color.WHITE);
			b.setVerticalTextPosition(SwingConstants.BOTTOM);
			b.setHorizontalTextPosition(SwingConstants.CENTER);
			b.setFocusPainted(false);
			pnlMainMenu.add(b);
		}
	}
	
	/*/
	 * JPanel pnlInsumos = new JPanel();
		frmTrabajoPrctico.getContentPane().add(pnlInsumos, "card__Insumos");
	 */
	private void loadInsumosMenu()
	{
		System.out.println("asdada");
		JPanel pnlInsumos = new JPanel();
		frmTrabajoPrctico.getContentPane().add(pnlInsumos, "card__Insumos");
		pnlInsumos.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			pnlInsumos.add(panel, BorderLayout.SOUTH);
			{
				JButton btnVolver = new JButton("Volver");
				btnVolver.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cl.show(frmTrabajoPrctico.getContentPane(), "card__MainMenu");
					}
				});
				panel.add(btnVolver);
			}
			{
				JButton btnGuardarCambios = new JButton("Guardar cambios");
				panel.add(btnGuardarCambios);
			}
		}
		{
			JPanel panel = new JPanel();
			pnlInsumos.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] {0, 165, 0, 99, 155, 193, 0, 179, 84};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				{
					JPanel panel_1 = new JPanel();
					GridBagConstraints gbc_panel_1 = new GridBagConstraints();
					gbc_panel_1.insets = new Insets(0, 0, 5, 5);
					gbc_panel_1.fill = GridBagConstraints.BOTH;
					gbc_panel_1.gridx = 4;
					gbc_panel_1.gridy = 0;
					panel.add(panel_1, gbc_panel_1);
				}
				{
					JLabel lblPanelDeAdministracin = new JLabel("Panel de administración de insumos");
					lblPanelDeAdministracin.setFont(new Font("Segoe UI", Font.PLAIN, 24));
					GridBagConstraints gbc_lblPanelDeAdministracin = new GridBagConstraints();
					gbc_lblPanelDeAdministracin.gridwidth = 8;
					gbc_lblPanelDeAdministracin.insets = new Insets(0, 0, 5, 5);
					gbc_lblPanelDeAdministracin.gridx = 1;
					gbc_lblPanelDeAdministracin.gridy = 1;
					panel.add(lblPanelDeAdministracin, gbc_lblPanelDeAdministracin);
				}
			}
			{
				{
					JPanel panel_1 = new JPanel();
					GridBagConstraints gbc_panel_1 = new GridBagConstraints();
					gbc_panel_1.insets = new Insets(0, 0, 5, 5);
					gbc_panel_1.fill = GridBagConstraints.BOTH;
					gbc_panel_1.gridx = 4;
					gbc_panel_1.gridy = 2;
					panel.add(panel_1, gbc_panel_1);
				}
			}
			{
				JComboBox<String> comboBox = new JComboBox<String>();
				GridBagConstraints gbc_comboBox = new GridBagConstraints();
				gbc_comboBox.insets = new Insets(0, 0, 5, 5);
				gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBox.gridx = 5;
				gbc_comboBox.gridy = 3;
				
				comboBox.addItem("Nombre");
				comboBox.addItem("Costo mínimo");
				comboBox.addItem("Costo máximo");
				comboBox.addItem("Stock mínimo");
				comboBox.addItem("Stock máximo");
				{
					JButton btnNuevoInsumo = new JButton("Nuevo insumo");
					GridBagConstraints gbc_btnNuevoInsumo = new GridBagConstraints();
					gbc_btnNuevoInsumo.anchor = GridBagConstraints.EAST;
					gbc_btnNuevoInsumo.insets = new Insets(0, 0, 5, 5);
					gbc_btnNuevoInsumo.gridx = 1;
					gbc_btnNuevoInsumo.gridy = 3;
					
					btnNuevoInsumo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							CreateInsumoDialog cid = new CreateInsumoDialog();
						}
					});

					panel.add(btnNuevoInsumo, gbc_btnNuevoInsumo);
				}
				{
					JButton btnEditar_1 = new JButton("Editar");
					GridBagConstraints gbc_btnEditar_1 = new GridBagConstraints();
					gbc_btnEditar_1.insets = new Insets(0, 0, 5, 5);
					gbc_btnEditar_1.gridx = 2;
					gbc_btnEditar_1.gridy = 3;
					panel.add(btnEditar_1, gbc_btnEditar_1);
				}
				{
					JButton btnEliminar = new JButton("Eliminar");
					GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
					gbc_btnEliminar.anchor = GridBagConstraints.WEST;
					gbc_btnEliminar.insets = new Insets(0, 0, 5, 5);
					gbc_btnEliminar.gridx = 3;
					gbc_btnEliminar.gridy = 3;
					panel.add(btnEliminar, gbc_btnEliminar);
				}
				{
					JLabel lblOrdenarPor = new JLabel("Buscar por:");
					GridBagConstraints gbc_lblOrdenarPor = new GridBagConstraints();
					gbc_lblOrdenarPor.insets = new Insets(0, 0, 5, 5);
					gbc_lblOrdenarPor.anchor = GridBagConstraints.EAST;
					gbc_lblOrdenarPor.gridx = 4;
					gbc_lblOrdenarPor.gridy = 3;
					panel.add(lblOrdenarPor, gbc_lblOrdenarPor);
				}
				
				panel.add(comboBox, gbc_comboBox);
			}
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			{
				textField_1 = new JTextField();
				textField_1.setToolTipText("Escriba aquí lo que desea buscar...");
				GridBagConstraints gbc_textField_1 = new GridBagConstraints();
				gbc_textField_1.gridwidth = 2;
				gbc_textField_1.insets = new Insets(0, 0, 5, 5);
				gbc_textField_1.fill = GridBagConstraints.BOTH;
				gbc_textField_1.gridx = 6;
				gbc_textField_1.gridy = 3;
				panel.add(textField_1, gbc_textField_1);
				textField_1.setColumns(10);
			}
			GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
			gbc_btnBuscar.anchor = GridBagConstraints.WEST;
			gbc_btnBuscar.gridwidth = 2;
			gbc_btnBuscar.fill = GridBagConstraints.VERTICAL;
			gbc_btnBuscar.insets = new Insets(0, 0, 5, 0);
			gbc_btnBuscar.gridx = 8;
			gbc_btnBuscar.gridy = 3;
			panel.add(btnBuscar, gbc_btnBuscar);
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.insets = new Insets(0, 0, 0, 5);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 4;
				panel.add(panel_1, gbc_panel_1);
			}
			{
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.gridwidth = 8;
				gbc_panel_1.insets = new Insets(0, 0, 0, 5);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 1;
				gbc_panel_1.gridy = 4;
				
				String data[][] = {
			              {"1","Man Utd","Luis Van gaal","86"},
		                  {"2","Chelsea","Jose Mourinho","84"},
		                  {"3","Man City","Manuele Pelegrini","82"},
		                  {"4","Arsenal","Arsene Wenger","80"},
		                  {"5","Liverpool","Brendan Rodgers","78"},
		          };
				
				String col[] = {"ID","Descripción","Costo","Peso", "Refrigerado"};
				
				DefaultTableModel model = new DefaultTableModel(data,col);
				JTable table = new JTable(model);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.setAutoCreateRowSorter(true);
				table.setDefaultEditor(Object.class, null);
				JTableHeader header = table.getTableHeader();
				JScrollPane panel_scrlpn = new JScrollPane(table);
				panel.add(panel_scrlpn, gbc_panel_1);
			}
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 9;
				gbc_panel_1.gridy = 4;
				panel.add(panel_1, gbc_panel_1);
			}
		}
	
	}

	private void loadPlantasMenu()
	{
		System.out.println("asdada");
		JPanel pnlPlantas = new JPanel();
		frmTrabajoPrctico.getContentPane().add(pnlPlantas, "card__Planta");
		pnlPlantas.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			pnlPlantas.add(panel, BorderLayout.SOUTH);
			{
				JButton btnVolver = new JButton("Volver");
				btnVolver.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cl.show(frmTrabajoPrctico.getContentPane(), "card__MainMenu");
					}
				});
				panel.add(btnVolver);
			}
			{
				JButton btnGuardarCambios = new JButton("Guardar cambios");
				btnGuardarCambios.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						JOptionPane.showMessageDialog(frmTrabajoPrctico,
							    "Todas las modificaciones fueron guardadas exitosamente",
							    "Información",
							    JOptionPane.INFORMATION_MESSAGE);
					}
				});
				panel.add(btnGuardarCambios);
			}
		}
		{
			JPanel panel = new JPanel();
			pnlPlantas.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] {0, 165, 0, 99, 111, 155, 193, 0, 179, 84};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				{
					JPanel panel_1 = new JPanel();
					GridBagConstraints gbc_panel_1 = new GridBagConstraints();
					gbc_panel_1.insets = new Insets(0, 0, 5, 5);
					gbc_panel_1.fill = GridBagConstraints.BOTH;
					gbc_panel_1.gridx = 5;
					gbc_panel_1.gridy = 0;
					panel.add(panel_1, gbc_panel_1);
				}
				{
					JLabel lblPanelDeAdministracin = new JLabel("Panel de administración de plantas");
					lblPanelDeAdministracin.setFont(new Font("Segoe UI", Font.PLAIN, 24));
					GridBagConstraints gbc_lblPanelDeAdministracin = new GridBagConstraints();
					gbc_lblPanelDeAdministracin.gridwidth = 9;
					gbc_lblPanelDeAdministracin.insets = new Insets(0, 0, 5, 5);
					gbc_lblPanelDeAdministracin.gridx = 1;
					gbc_lblPanelDeAdministracin.gridy = 1;
					panel.add(lblPanelDeAdministracin, gbc_lblPanelDeAdministracin);
				}
			}
			{
				{
					JPanel panel_1 = new JPanel();
					GridBagConstraints gbc_panel_1 = new GridBagConstraints();
					gbc_panel_1.insets = new Insets(0, 0, 5, 5);
					gbc_panel_1.fill = GridBagConstraints.BOTH;
					gbc_panel_1.gridx = 5;
					gbc_panel_1.gridy = 2;
					panel.add(panel_1, gbc_panel_1);
				}
			}
			{
				{
					JButton btnNuevoInsumo = new JButton("Nueva planta");
					GridBagConstraints gbc_btnNuevoInsumo = new GridBagConstraints();
					gbc_btnNuevoInsumo.anchor = GridBagConstraints.EAST;
					gbc_btnNuevoInsumo.insets = new Insets(0, 0, 5, 5);
					gbc_btnNuevoInsumo.gridx = 1;
					gbc_btnNuevoInsumo.gridy = 3;
					
					btnNuevoInsumo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							CreatePlantaDialog cid = new CreatePlantaDialog();
						}
					});

					panel.add(btnNuevoInsumo, gbc_btnNuevoInsumo);
				}
				{
					JButton btnEditar_1 = new JButton("Editar");
					GridBagConstraints gbc_btnEditar_1 = new GridBagConstraints();
					gbc_btnEditar_1.insets = new Insets(0, 0, 5, 5);
					gbc_btnEditar_1.gridx = 2;
					gbc_btnEditar_1.gridy = 3;
					panel.add(btnEditar_1, gbc_btnEditar_1);
				}
				{
					JButton btnEliminar = new JButton("Eliminar");
					GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
					gbc_btnEliminar.anchor = GridBagConstraints.WEST;
					gbc_btnEliminar.insets = new Insets(0, 0, 5, 5);
					gbc_btnEliminar.gridx = 3;
					gbc_btnEliminar.gridy = 3;
					
					btnEliminar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							try {
							gestorPlanta.borrar((Integer)tablePlantas.getValueAt(tablePlantas.getSelectedRow(), 0));
							refreshPlantaTable();}
							catch(Exception e) {
								System.out.println("No hay plantas seleccionadas");
							}
						}
					});
					
					panel.add(btnEliminar, gbc_btnEliminar);
				}
			}
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			{
				textField_1 = new JTextField();
				textField_1.setToolTipText("Escriba aquí lo que desea buscar...");
				GridBagConstraints gbc_textField_1 = new GridBagConstraints();
				gbc_textField_1.gridwidth = 2;
				gbc_textField_1.insets = new Insets(0, 0, 5, 5);
				gbc_textField_1.fill = GridBagConstraints.BOTH;
				gbc_textField_1.gridx = 7;
				gbc_textField_1.gridy = 3;
				panel.add(textField_1, gbc_textField_1);
				textField_1.setColumns(10);
			}
			GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
			gbc_btnBuscar.anchor = GridBagConstraints.WEST;
			gbc_btnBuscar.gridwidth = 2;
			gbc_btnBuscar.fill = GridBagConstraints.VERTICAL;
			gbc_btnBuscar.insets = new Insets(0, 0, 5, 0);
			gbc_btnBuscar.gridx = 9;
			gbc_btnBuscar.gridy = 3;
			panel.add(btnBuscar, gbc_btnBuscar);
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.insets = new Insets(0, 0, 0, 5);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 4;
				panel.add(panel_1, gbc_panel_1);
			}
			{
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.gridwidth = 4;
				gbc_panel_1.insets = new Insets(0, 0, 0, 5);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 1;
				gbc_panel_1.gridy = 4;

				tablePlantas = new JTable();
				tablePlantas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tablePlantas.setAutoCreateRowSorter(true);
				tablePlantas.setDefaultEditor(Object.class, null);
				JTableHeader header = tablePlantas.getTableHeader();
				JScrollPane panel_scrlpn = new JScrollPane(tablePlantas);
				panel.add(panel_scrlpn, gbc_panel_1);
			}
			{
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.gridwidth = 4;
				gbc_panel_1.insets = new Insets(0, 0, 0, 5);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 6;
				gbc_panel_1.gridy = 4;
				
				String data[][] = {
			              {"Manzana","5"},
		                  {"PaVos","84"},
		                  {"Premiums Nostalgia Gamers","82"},
		          };
				
				String col[] = {"Stock","Cantidad"};
				
				DefaultTableModel model = new DefaultTableModel(data,col);
				JTable table = new JTable(model);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.setAutoCreateRowSorter(true);
				table.setDefaultEditor(Object.class, null);
				JTableHeader header = table.getTableHeader();
				JScrollPane panel_scrlpn = new JScrollPane(table);
				panel.add(panel_scrlpn, gbc_panel_1);
			}
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 10;
				gbc_panel_1.gridy = 4;
				panel.add(panel_1, gbc_panel_1);
			}
		}
	
	}

	public static void refreshPlantaTable()
	{
		String col[] = {"ID","Nombre"};
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		ArrayList<Planta> tempListaPlantas = GestorPlanta.getInstance().getListaPlantas();
		
		for (int i = 0; i < tempListaPlantas.size(); i++)
		{
		   int id = tempListaPlantas.get(i).getId();
		   String nombre = tempListaPlantas.get(i).getNombre();
			   
		   Object[] data = {id, nombre};
		   tableModel.addRow(data);
		}
		
		tablePlantas.setModel(tableModel);
	}
}
