package layout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

import dominio.Insumo;
import dominio.InsumoLiquido;
import dominio.Planta;
import dominio.Stock;
import dominio.UnidadDeMedida;
import gestores.GestorInsumo;
import gestores.GestorPlanta;

import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class MainMenu {

	private JFrame frmTrabajoPrctico;
	private CardLayout cl;
	private JTextField textField_1;
	private static JTable tablePlantas;
	private static JTable tableStock;
	private static JTable tableInsumos;
	static GestorPlanta gestorPlanta = GestorPlanta.getInstance();
	static GestorInsumo gestorInsumo = GestorInsumo.getInstance();
	
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
		infob.setText("Información");

		int arraySize = botones.size();
		for (int i = 0; i < arraySize; i++)
		{
			JButton b = botones.get(i);
			b.setBackground(Color.WHITE);
			b.setVerticalTextPosition(SwingConstants.BOTTOM);
			b.setHorizontalTextPosition(SwingConstants.CENTER);
			b.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
		JPanel pnlInsumos = new JPanel();
		frmTrabajoPrctico.getContentPane().add(pnlInsumos, "card__Insumos");
		pnlInsumos.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			pnlInsumos.add(panel, BorderLayout.SOUTH);
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
					JButton btnVolver_1 = new JButton("");
					btnVolver_1.setForeground(Color.WHITE);
					btnVolver_1.setIcon(new ImageIcon(new ImageIcon("src/main/resources/back.png").getImage()
							.getScaledInstance(32, 32, Image.SCALE_DEFAULT)));

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
				}
				{
					JLabel lblPanelDeAdministracin = new JLabel("Panel de administración de insumos");
					lblPanelDeAdministracin.setFont(new Font("Segoe UI", Font.PLAIN, 24));
					GridBagConstraints gbc_lblPanelDeAdministracin = new GridBagConstraints();
					gbc_lblPanelDeAdministracin.gridwidth = 6;
					gbc_lblPanelDeAdministracin.insets = new Insets(0, 0, 5, 5);
					gbc_lblPanelDeAdministracin.gridx = 2;
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
							CreateInsumoDialog();
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
					
					btnEditar_1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							Integer rowId = tableInsumos.getSelectedRow();
							
							if(rowId < 0)
		                    {
		                        JOptionPane.showMessageDialog(frmTrabajoPrctico,
		                            "No hay ningún insumo seleccionado",
		                            "Información",
		                            JOptionPane.INFORMATION_MESSAGE);
		                        return;
		                    }

							Insumo insumo;
							insumo = gestorInsumo.getInsumoById((Integer)tableInsumos.getValueAt(rowId, 0));
							
							if(insumo == null)
							{
								JOptionPane.showMessageDialog(frmTrabajoPrctico,
			                            "No se pudo localizar el insumo seleccionado",
			                            "Información",
			                            JOptionPane.INFORMATION_MESSAGE);
								
		                       return;
							}
							
							EditInsumoDialog(insumo);
							MainMenu.refreshPlantaTable();
						}
					});
				}
				{
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

		                            if(rowId < 0)
		                            {
		                                JOptionPane.showMessageDialog(frmTrabajoPrctico,
		                                        "No hay ningún insumo seleccionado",
		                                        "Información",
		                                        JOptionPane.INFORMATION_MESSAGE);
		                                return;
		                            }

		                            int dialogResult = JOptionPane.showConfirmDialog (frmTrabajoPrctico,
		                            		"¿Está seguro que desea eliminar el insumo seleccionado?",
		                            		"Confirmar",
		                            		JOptionPane.YES_NO_OPTION);
		                            
		                            if(dialogResult == JOptionPane.YES_OPTION)
		                            {
		                            	gestorInsumo.borrar((Integer)tableInsumos.getValueAt(rowId, 0));
			                            refreshInsumoTable();
			                            
		                            	JOptionPane.showMessageDialog(frmTrabajoPrctico,
		                                        "El insumo seleccionado ha sido borrado",
		                                        "Información",
		                                        JOptionPane.INFORMATION_MESSAGE);	
		                            }      
						}
					});
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
				
				tableInsumos = new JTable();
				tableInsumos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tableInsumos.setAutoCreateRowSorter(true);
				tableInsumos.setDefaultEditor(Object.class, null);
				JScrollPane panel_scrlpn = new JScrollPane(tableInsumos);
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
	
		refreshInsumoTable();
	}

	private void loadPlantasMenu()
	{
		JPanel pnlPlantas = new JPanel();
		frmTrabajoPrctico.getContentPane().add(pnlPlantas, "card__Planta");
		pnlPlantas.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			pnlPlantas.add(panel, BorderLayout.SOUTH);
		}
		{
			JPanel panel = new JPanel();
			pnlPlantas.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] {0, 165, 0, 0, 99, 111, 101, 193, 0, 179, 84};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				{
					JPanel panel_1 = new JPanel();
					GridBagConstraints gbc_panel_1 = new GridBagConstraints();
					gbc_panel_1.insets = new Insets(0, 0, 5, 5);
					gbc_panel_1.fill = GridBagConstraints.BOTH;
					gbc_panel_1.gridx = 6;
					gbc_panel_1.gridy = 1;
					panel.add(panel_1, gbc_panel_1);
				}
				{
					JButton btnVolver_1 = new JButton("");
					btnVolver_1.setForeground(Color.WHITE);
					btnVolver_1.setIcon(new ImageIcon(new ImageIcon("src/main/resources/back.png").getImage()
							.getScaledInstance(32, 32, Image.SCALE_DEFAULT)));

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
					gbc_btnVolver_1.gridy = 2;
					panel.add(btnVolver_1, gbc_btnVolver_1);
				}
				{
					JLabel lblPanelDeAdministracin = new JLabel("Panel de administración de plantas");
					lblPanelDeAdministracin.setFont(new Font("Segoe UI", Font.PLAIN, 24));
					GridBagConstraints gbc_lblPanelDeAdministracin = new GridBagConstraints();
					gbc_lblPanelDeAdministracin.gridwidth = 8;
					gbc_lblPanelDeAdministracin.insets = new Insets(0, 0, 5, 5);
					gbc_lblPanelDeAdministracin.gridx = 2;
					gbc_lblPanelDeAdministracin.gridy = 2;
					panel.add(lblPanelDeAdministracin, gbc_lblPanelDeAdministracin);
				}
			}
			{
				{
					JPanel panel_1 = new JPanel();
					GridBagConstraints gbc_panel_1 = new GridBagConstraints();
					gbc_panel_1.insets = new Insets(0, 0, 5, 5);
					gbc_panel_1.fill = GridBagConstraints.BOTH;
					gbc_panel_1.gridx = 6;
					gbc_panel_1.gridy = 3;
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
					gbc_btnNuevoInsumo.gridy = 4;
					
					btnNuevoInsumo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							String plantaName = JOptionPane.showInputDialog(frmTrabajoPrctico,
									"Nombre de la planta"
							);

							if(plantaName != null)
							{							
								gestorPlanta.crear(plantaName);
								MainMenu.refreshPlantaTable();
							}
						}
					});

					panel.add(btnNuevoInsumo, gbc_btnNuevoInsumo);
				}
				{
					{
						JButton btnEditar_1 = new JButton("Editar");
						GridBagConstraints gbc_btnEditar_1 = new GridBagConstraints();
						gbc_btnEditar_1.insets = new Insets(0, 0, 5, 5);
						gbc_btnEditar_1.gridx = 2;
						gbc_btnEditar_1.gridy = 4;
						
						btnEditar_1.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								Integer rowId = tablePlantas.getSelectedRow();
								
								if(rowId < 0)
			                    {
			                        JOptionPane.showMessageDialog(frmTrabajoPrctico,
			                            "No hay ninguna planta seleccionada",
			                            "Información",
			                            JOptionPane.INFORMATION_MESSAGE);
			                        return;
			                    }

								String plantaName = JOptionPane.showInputDialog(frmTrabajoPrctico,
										"Nuevo nombre de la planta",
										(String)tablePlantas.getValueAt(rowId, 1)
								);

								if(plantaName != null)
								{							
									gestorPlanta.obtenerPlanta((Integer)tablePlantas.getValueAt(rowId, 0)).setNombre(plantaName);
									MainMenu.refreshPlantaTable();
								}
							}
						});
						
						panel.add(btnEditar_1, gbc_btnEditar_1);
					}
				}
			}
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			JButton btnEliminar = new JButton("Eliminar");
			GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
			gbc_btnEliminar.anchor = GridBagConstraints.WEST;
			gbc_btnEliminar.insets = new Insets(0, 0, 5, 5);
			gbc_btnEliminar.gridx = 3;
			gbc_btnEliminar.gridy = 4;
			
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Integer rowId = tablePlantas.getSelectedRow();

                    if(rowId < 0)
                    {
                        JOptionPane.showMessageDialog(frmTrabajoPrctico,
                            "No hay ninguna planta seleccionada",
                            "Información",
                            JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    int dialogResult = JOptionPane.showConfirmDialog (frmTrabajoPrctico,
                    		"¿Está seguro que desea eliminar la planta seleccionada?",
                    		"Confirmar",
                    		JOptionPane.YES_NO_OPTION);
                    
                    if(dialogResult == JOptionPane.YES_OPTION)
                    {
                    	gestorPlanta.borrar((Integer)tablePlantas.getValueAt(rowId, 0));
                        refreshPlantaTable();
                        refreshStockTable(0);
                        
                    	JOptionPane.showMessageDialog(frmTrabajoPrctico,
                                "La planta seleccionada ha sido borrada",
                                "Información",
                                JOptionPane.INFORMATION_MESSAGE);	
                    }
				}
			});
			
			panel.add(btnEliminar, gbc_btnEliminar);
			{
				JButton btnAadirInsumo = new JButton("Añadir insumo");
				GridBagConstraints gbc_btnAadirInsumo = new GridBagConstraints();
				gbc_btnAadirInsumo.insets = new Insets(0, 0, 5, 5);
				gbc_btnAadirInsumo.gridx = 4;
				gbc_btnAadirInsumo.gridy = 4;
				
				btnAadirInsumo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0)
					{
						Integer selectedRow = tablePlantas.getSelectedRow();
						
						if(selectedRow < 0)
						{
							JOptionPane.showMessageDialog(frmTrabajoPrctico,
                                    "No hay ninguna planta seleccionada",
                                    "Información",
                                    JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						
						Integer selectedPlanta = (Integer)tablePlantas.getValueAt(selectedRow, 0);
						AddInsumoDialog(selectedPlanta);
					}
				});
				
				panel.add(btnAadirInsumo, gbc_btnAadirInsumo);
			}
			{
				JLabel lblBuscarPlantas = new JLabel("Buscar plantas:");
				GridBagConstraints gbc_lblBuscarPlantas = new GridBagConstraints();
				gbc_lblBuscarPlantas.insets = new Insets(0, 0, 5, 5);
				gbc_lblBuscarPlantas.anchor = GridBagConstraints.EAST;
				gbc_lblBuscarPlantas.gridx = 7;
				gbc_lblBuscarPlantas.gridy = 4;
				panel.add(lblBuscarPlantas, gbc_lblBuscarPlantas);
			}
			{
				textField_1 = new JTextField();
				textField_1.setToolTipText("Escriba aquí lo que desea buscar...");
				GridBagConstraints gbc_textField_1 = new GridBagConstraints();
				gbc_textField_1.gridwidth = 2;
				gbc_textField_1.insets = new Insets(0, 0, 5, 5);
				gbc_textField_1.fill = GridBagConstraints.BOTH;
				gbc_textField_1.gridx = 8;
				gbc_textField_1.gridy = 4;
				panel.add(textField_1, gbc_textField_1);
				textField_1.setColumns(10);
			}
			GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
			gbc_btnBuscar.anchor = GridBagConstraints.WEST;
			gbc_btnBuscar.gridwidth = 2;
			gbc_btnBuscar.fill = GridBagConstraints.VERTICAL;
			gbc_btnBuscar.insets = new Insets(0, 0, 5, 0);
			gbc_btnBuscar.gridx = 10;
			gbc_btnBuscar.gridy = 4;
			panel.add(btnBuscar, gbc_btnBuscar);
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.insets = new Insets(0, 0, 0, 5);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 5;
				panel.add(panel_1, gbc_panel_1);
			}
			{
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.gridwidth = 5;
				gbc_panel_1.insets = new Insets(0, 0, 0, 5);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 1;
				gbc_panel_1.gridy = 5;

				tablePlantas = new JTable();
				tablePlantas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tablePlantas.setAutoCreateRowSorter(true);
				tablePlantas.setDefaultEditor(Object.class, null);
				JScrollPane panel_scrlpn = new JScrollPane(tablePlantas);
				
				tablePlantas.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			        public void valueChanged(ListSelectionEvent event) {
			        	if (!event.getValueIsAdjusting() && tablePlantas.getSelectedRow() != -1)
			        		refreshStockTable((Integer)tablePlantas.getValueAt(tablePlantas.getSelectedRow(), 0));
			        }
			    });
				
				panel.add(panel_scrlpn, gbc_panel_1);
			}
			{
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.gridwidth = 4;
				gbc_panel_1.insets = new Insets(0, 0, 0, 5);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 7;
				gbc_panel_1.gridy = 5;
				
				tableStock = new JTable();
				tableStock.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tableStock.setAutoCreateRowSorter(true);
				tableStock.setDefaultEditor(Object.class, null);
				JScrollPane panel_scrlpn = new JScrollPane(tableStock);
				panel.add(panel_scrlpn, gbc_panel_1);
			}
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 11;
				gbc_panel_1.gridy = 5;
				panel.add(panel_1, gbc_panel_1);
			}
		}
	
		refreshPlantaTable();
		refreshStockTable(0);
	}

	public static void refreshPlantaTable()
	{
		String col[] = {"ID","Nombre"};
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		ArrayList<Planta> tempListaPlantas = gestorPlanta.getListaPlantas();
		
		for (int i = 0; i < tempListaPlantas.size(); i++)
		{
		   int id = tempListaPlantas.get(i).getId();
		   String nombre = tempListaPlantas.get(i).getNombre();
			   
		   Object[] data = {id, nombre};
		   tableModel.addRow(data);
		}
		
		tablePlantas.setModel(tableModel);
	}
	
	public static void refreshStockTable(Integer plantaId)
	{
		String col[] = {"Insumo", "Cantidad", "Punto pedido"};
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		ArrayList<Stock> tempListaStock = gestorPlanta.getStockByPlantaId(plantaId);

		if(plantaId > 0)
		{
			for (int i = 0; i < tempListaStock.size(); i++)
			{
			   
			   String descripcion = tempListaStock.get(i).getInsumo().getDescripcion();
			   Integer cantidad = tempListaStock.get(i).getCantidad();
			   Integer puntopedido = tempListaStock.get(i).getPuntoPedido();
			   
			   Object[] data = {descripcion, cantidad, puntopedido};
			   tableModel.addRow(data);
			}
			
			tableStock.setModel(tableModel);
		}
		else
		{
			tableStock.setModel(tableModel);			
		}
	}
	
	public static void refreshInsumoTable()
	{
		String col[] = {"ID", "Descripción", "Costo", "Peso", "Refrigerado"};
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		ArrayList<Insumo> tempListaInsumos = gestorInsumo.getListaInsumos();
		
		for (int i = 0; i < tempListaInsumos.size(); i++)
		{
		   Integer id = tempListaInsumos.get(i).getId();
		   String descripcion = tempListaInsumos.get(i).getDescripcion();
		   Float costo = tempListaInsumos.get(i).getCosto();
		   Float peso = tempListaInsumos.get(i).getPeso();
		   String refrigerado = tempListaInsumos.get(i) instanceof InsumoLiquido ? "Si" : "No";
		   
		   Object[] data = {id, descripcion, costo, peso, refrigerado};
		   tableModel.addRow(data);
		}
		
		tableInsumos.setModel(tableModel);
	}
	
	public void CreateInsumoDialog() {
		JDialog jdialog = new JDialog(frmTrabajoPrctico, "Crear insumo", Dialog.ModalityType.DOCUMENT_MODAL);
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
		gbl_panel.columnWidths = new int[]{119, 58, 86, 32, 0};
		gbl_panel.rowHeights = new int[]{55, 0, 20, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		
		txtDescripcion = new JTextField();
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
		
		txtCosto = new JTextField();
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
		
		txtPeso = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 4;
		panel.add(txtPeso, gbc_textField);
		txtPeso.setColumns(10);
		
		JCheckBox chckbxS = new JCheckBox("Insumo refrigerado");
		GridBagConstraints gbc_chckbxS = new GridBagConstraints();
		gbc_chckbxS.anchor = GridBagConstraints.WEST;
		gbc_chckbxS.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxS.gridx = 2;
		gbc_chckbxS.gridy = 5;
		panel.add(chckbxS, gbc_chckbxS);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Insumo insumo;
				
				if(chckbxS.isSelected())
				{
					insumo = gestorInsumo.crearLiquido(txtDescripcion.getText());
				}
				else
				{
					insumo = gestorInsumo.crear(txtDescripcion.getText());
				}
				
				insumo.setCosto(Float.parseFloat(txtCosto.getText()));
				insumo.setPeso(Float.parseFloat(txtPeso.getText()));
				insumo.setUnidadDeMedida(UnidadDeMedida.KILO);

				System.out.println(gestorInsumo.getListaInsumos().toString());
				MainMenu.refreshInsumoTable();
				
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
		gbl_panel.columnWidths = new int[]{119, 58, 86, 32, 0};
		gbl_panel.rowHeights = new int[]{55, 0, 20, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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

				System.out.println(gestorInsumo.getListaInsumos().toString());
				MainMenu.refreshInsumoTable();
				
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
	
	public void AddInsumoDialog(Integer plantaId)
	{
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
		gbl_panel.columnWidths = new int[]{86, 58, 205, 32, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		
		JLabel lblAgregandoAPlanta = new JLabel("Agregando a planta: " + plantaId);
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
				Stock stockInsumo = new Stock();
				stockInsumo.setCantidad(Integer.parseInt(txtCantidad.getText()));
				stockInsumo.setPuntoPedido(Integer.parseInt(txtPuntoPedido.getText()));
				stockInsumo.setInsumo(((Insumo)comboBox.getSelectedItem()));
				gestorPlanta.obtenerPlanta(plantaId).getListaStock().add(stockInsumo);
				
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
