package layout;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dominio.Insumo;
import dominio.InsumoLiquido;
import dominio.UnidadDeMedida;
import gestores.GestorInsumo;
import gestores.GestorPlanta;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class CreateInsumoDialog extends JFrame {

	private JPanel contentPane;
	private JTextField txtDescripcion;
	private JTextField txtCosto;
	private JTextField txtPeso;
	static GestorInsumo gestorInsumo = GestorInsumo.getInstance();

	/**
	 * Create the frame.
	 */
	public CreateInsumoDialog() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setTitle("Añadir insumo");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
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
				
				dispose();
			}
		});
		panel_1.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});	
		panel_1.add(btnCancelar);
	}

}
