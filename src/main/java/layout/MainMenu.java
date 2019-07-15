package layout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.JPasswordField;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.BoxLayout;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import java.awt.Font;

public class MainMenu {

	private JFrame frmTrabajoPrctico;
	private CardLayout cl;
	private JTextField textField;
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
		loadStockMenu();
	}
	
	private void loadMainMenu()
	{
		JPanel pnlMainMenu = new JPanel();
		frmTrabajoPrctico.getContentPane().add(pnlMainMenu, "card__MainMenu");
		
		GridLayout grid = new GridLayout(3, 2);
		pnlMainMenu.setLayout(grid);
		
		String resourcesPath = "src/main/resources/";

		ImageIcon insumoIcon = new ImageIcon(new ImageIcon(resourcesPath + "Insumo.png").getImage()
				.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		ImageIcon stockIcon = new ImageIcon(new ImageIcon(resourcesPath + "Stock.png").getImage()
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
		JButton plantab = new JButton(plantaIcon);
		JButton infob = new JButton(infoIcon);

		JButton insumob = new JButton(insumoIcon);
		insumob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(frmTrabajoPrctico.getContentPane(), "card__Insumos");
			}
		});		
		
		JButton stockb = new JButton(stockIcon);
		stockb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(frmTrabajoPrctico.getContentPane(), "card__Stock");
			}
		});
		
		ArrayList<JButton> botones = new ArrayList<JButton>();
		botones.add(insumob);
		botones.add(stockb);
		botones.add(camionb);
		botones.add(caminob);
		botones.add(plantab);
		botones.add(infob);

		insumob.setText("Insumos");
		stockb.setText("Stock");
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
			pnlInsumos.add(panel, BorderLayout.NORTH);
			{
				JLabel lblInsumos = new JLabel("Panel de administración de Insumos");
				lblInsumos.setFont(new Font("Arial", Font.PLAIN, 18));
				lblInsumos.setHorizontalAlignment(SwingConstants.LEFT);
				panel.add(lblInsumos);
			}
		}
		{
			JPanel panel = new JPanel();
			pnlInsumos.add(panel, BorderLayout.CENTER);
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
				gbc_textField_1.gridwidth = 5;
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
				gbc_btnBuscar.fill = GridBagConstraints.BOTH;
				gbc_btnBuscar.insets = new Insets(0, 0, 5, 5);
				gbc_btnBuscar.gridx = 7;
				gbc_btnBuscar.gridy = 1;
				panel.add(btnBuscar, gbc_btnBuscar);
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

	private void loadStockMenu()
	{
		System.out.println("asdada");
		JPanel pnlStock = new JPanel();
		frmTrabajoPrctico.getContentPane().add(pnlStock, "card__Stock");
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
				JLabel lblInsumos = new JLabel("Panel de administración de Stock");
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
