package layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.border.EtchedBorder;

public class Form extends JFrame {

	private JPanel contentPane;
	private JTextField txtDsfdfsd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form frame = new Form();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Form() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnNewButton_3 = new JButton("Guardar");
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Volver");
		panel_1.add(btnNewButton_4);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnNewButton = new JButton("Crear");
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Editar");
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Borrar");
		panel.add(btnNewButton_2);
		
		txtDsfdfsd = new JTextField();
		panel.add(txtDsfdfsd);
		txtDsfdfsd.setColumns(10);
		
		JButton btnNewButton_5 = new JButton("Buscar");
		panel.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Agregar stock");
		panel.add(btnNewButton_6);
		
		for(Component c : panel.getComponents()) {
			c.setBackground(Color.WHITE);
		}

		for(Component c : panel_1.getComponents()) {
			c.setBackground(Color.WHITE);
		}
		
		JList list = new JList();
		list.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(list, BorderLayout.CENTER);
	}

}
