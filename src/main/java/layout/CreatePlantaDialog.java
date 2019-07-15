package layout;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dominio.Insumo;
import dominio.InsumoLiquido;
import dominio.UnidadDeMedida;
import gestores.GestorPlanta;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;

public class CreatePlantaDialog extends JFrame {

	private JPanel contentPane;
	private JTextField txtDescripcion;

	/**
	 * Create the frame.
	 */
	public CreatePlantaDialog() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setTitle("Añadir planta");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{83, 38, 211, 32, 0};
		gbl_panel.rowHeights = new int[]{55, 20, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblDescripcin = new JLabel("Nombre:");
		GridBagConstraints gbc_lblDescripcin = new GridBagConstraints();
		gbc_lblDescripcin.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcin.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcin.gridx = 1;
		gbc_lblDescripcin.gridy = 1;
		panel.add(lblDescripcin, gbc_lblDescripcin);
		
		txtDescripcion = new JTextField();
		GridBagConstraints gbc_txtDescripcion = new GridBagConstraints();
		gbc_txtDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDescripcion.anchor = GridBagConstraints.NORTH;
		gbc_txtDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_txtDescripcion.gridx = 2;
		gbc_txtDescripcion.gridy = 1;
		panel.add(txtDescripcion, gbc_txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestorPlanta.crearPlanta(txtDescripcion.getText());
				System.out.println(GestorPlanta.getListaPlantas().toString());
				
				MainMenu.refreshPlantaTable();
				
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