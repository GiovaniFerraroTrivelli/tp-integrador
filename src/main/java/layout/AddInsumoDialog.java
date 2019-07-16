package layout;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dominio.Insumo;
import dominio.Stock;
import gestores.GestorInsumo;
import gestores.GestorPlanta;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;

public class AddInsumoDialog extends JFrame {

	private JPanel contentPane;
	static GestorInsumo gestorInsumo = GestorInsumo.getInstance();
	static GestorPlanta gestorPlanta = GestorPlanta.getInstance();
	private JTextField txtCantidad;
	private JTextField txtPuntoPedido;
	
	/**
	 * Create the frame.
	 */
	public AddInsumoDialog(Integer plantaId) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setMinimumSize(new Dimension(100, 100));
		setTitle("Añadir insumo a planta");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
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
