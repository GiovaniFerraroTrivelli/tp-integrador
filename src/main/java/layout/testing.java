package layout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class testing {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testing window = new testing();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public testing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CardLayout cl = new CardLayout(0, 0);
		
		JButton btnCapo = new JButton("Capo el que apreta");
		btnCapo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(frame.getContentPane(), "name_289431001578800");
			}
		});
		frame.getContentPane().setLayout(cl);
		frame.getContentPane().add(btnCapo, "name_289426936674600");
		
		JButton btnPerro = new JButton("Puto el que lee");
		btnPerro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frame.getContentPane(), "name_289426936674600");
			}
		});
		frame.getContentPane().add(btnPerro, "name_289431001578800");
	}

}
