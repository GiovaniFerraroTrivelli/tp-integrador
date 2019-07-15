package layout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
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
					JLabel lblOrdenarPor = new JLabel("Ordenar por:");
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
		JPanel pnlStock = new JPanel();
		frmTrabajoPrctico.getContentPane().add(pnlStock, "card__Planta");
		pnlStock.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			pnlStock.add(panel, BorderLayout.SOUTH);
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
			pnlStock.add(panel, BorderLayout.NORTH);
			{
				JLabel lblInsumos = new JLabel("Panel de administración de Plantas");
				lblInsumos.setFont(new Font("Arial", Font.PLAIN, 18));
				lblInsumos.setHorizontalAlignment(SwingConstants.LEFT);
				panel.add(lblInsumos);
			}
		}
		{
			JPanel panel = new JPanel();
			pnlStock.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] {0, 0, 30, 30, 0, 30, 30, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JButton btnNuevo = new JButton("Nuevo");
				btnNuevo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				GridBagConstraints gbc_btnNuevo = new GridBagConstraints();
				gbc_btnNuevo.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnNuevo.insets = new Insets(0, 0, 5, 5);
				gbc_btnNuevo.gridx = 1;
				gbc_btnNuevo.gridy = 0;
				panel.add(btnNuevo, gbc_btnNuevo);
			}
			{
				JButton btnEditar = new JButton("Editar");
				GridBagConstraints gbc_btnEditar = new GridBagConstraints();
				gbc_btnEditar.gridwidth = 3;
				gbc_btnEditar.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnEditar.insets = new Insets(0, 0, 5, 5);
				gbc_btnEditar.gridx = 3;
				gbc_btnEditar.gridy = 0;
				panel.add(btnEditar, gbc_btnEditar);
			}
			{
				JButton btnBorrar = new JButton("Borrar");
				GridBagConstraints gbc_btnBorrar = new GridBagConstraints();
				gbc_btnBorrar.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnBorrar.insets = new Insets(0, 0, 5, 5);
				gbc_btnBorrar.gridx = 7;
				gbc_btnBorrar.gridy = 0;
				panel.add(btnBorrar, gbc_btnBorrar);
			}
			{
				textField_1 = new JTextField();
				textField_1.setToolTipText("Escriba aquí lo que desea buscar...");
				GridBagConstraints gbc_textField_1 = new GridBagConstraints();
				gbc_textField_1.insets = new Insets(0, 0, 5, 5);
				gbc_textField_1.fill = GridBagConstraints.BOTH;
				gbc_textField_1.gridx = 1;
				gbc_textField_1.gridy = 1;
				panel.add(textField_1, gbc_textField_1);
				textField_1.setColumns(10);
			}
			{
				JButton btnBuscar = new JButton("Buscar");
				GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
				gbc_btnBuscar.gridwidth = 3;
				gbc_btnBuscar.fill = GridBagConstraints.BOTH;
				gbc_btnBuscar.insets = new Insets(0, 0, 5, 5);
				gbc_btnBuscar.gridx = 3;
				gbc_btnBuscar.gridy = 1;
				panel.add(btnBuscar, gbc_btnBuscar);
			}
			{
				JButton btnAgregarStock = new JButton("Agregar stock");
				GridBagConstraints gbc_btnAgregarStock = new GridBagConstraints();
				gbc_btnAgregarStock.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnAgregarStock.insets = new Insets(0, 0, 5, 5);
				gbc_btnAgregarStock.gridx = 7;
				gbc_btnAgregarStock.gridy = 1;
				panel.add(btnAgregarStock, gbc_btnAgregarStock);
			}
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.insets = new Insets(0, 0, 0, 5);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 2;
				panel.add(panel_1, gbc_panel_1);
			}
			{
				JList list = new JList();
				GridBagConstraints gbc_list = new GridBagConstraints();
				gbc_list.insets = new Insets(0, 0, 0, 5);
				gbc_list.gridwidth = 7;
				gbc_list.fill = GridBagConstraints.BOTH;
				gbc_list.gridx = 1;
				gbc_list.gridy = 2;
				panel.add(list, gbc_list);
			}
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 8;
				gbc_panel_1.gridy = 2;
				panel.add(panel_1, gbc_panel_1);
			}
		}
	}
}
