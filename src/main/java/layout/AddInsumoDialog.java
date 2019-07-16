package layout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dominio.Insumo;
import dominio.InsumoLiquido;
import dominio.UnidadDeMedida;
import gestores.GestorInsumo;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JList;

public class AddInsumoDialog extends JFrame {

	private JPanel contentPane;
	static GestorInsumo gestorInsumo = GestorInsumo.getInstance();

	/**
	 * Create the frame.
	 */
	public AddInsumoDialog() {
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
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblPeso = new JLabel("Insumo:");
		GridBagConstraints gbc_lblPeso = new GridBagConstraints();
		gbc_lblPeso.anchor = GridBagConstraints.EAST;
		gbc_lblPeso.insets = new Insets(0, 0, 0, 5);
		gbc_lblPeso.gridx = 1;
		gbc_lblPeso.gridy = 0;
		panel.add(lblPeso, gbc_lblPeso);
		
		JComboBox<Insumo> comboBox = new JComboBox<Insumo>();
		comboBox.setModel(new DefaultComboBoxModel<Insumo>(gestorInsumo.getListaInsumos().toArray(new Insumo[0])));
		
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 0;
		panel.add(comboBox, gbc_comboBox);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// ((Insumo)comboBox.getSelectedItem()) -> Instancia de la clase Insumo
				// se puede hacer esto por ejemplo -> ((Insumo)comboBox.getSelectedItem()).getId();
				
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
