package layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

public class Menu {

	static ArrayList<JPanel> panels = new ArrayList<JPanel>();

	private final static Integer SIZE_X = 1280;
	private final static Integer SIZE_Y = 720;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Trabajo Práctico Integrador - DIED 2019");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(SIZE_X, SIZE_Y);
		frame.setMinimumSize(new Dimension(SIZE_X, SIZE_Y));
		frame.setResizable(false);

		menuPrincipal(frame);
		InsumoPanel(frame);
		StockPanel(frame);
		CamionPanel(frame);
		CaminoPanel(frame);
		PlantaPanel(frame);
		InfoPanel(frame);

		frame.setVisible(true);
	}

	public static void showPanel(Integer id) {
		System.out.println("Llamando ID: " + id);

		if (id > 0) {
			panels.get(id).setVisible(true);
			panels.get(0).setVisible(false);
		} else {
			panels.get(0).setVisible(true);

			int size = panels.size();

			for (int i = 1; i <= size; i++)
				panels.get(i).setVisible(false);
		}
	}

	public static JPanel menuPrincipal(JFrame frame) {
		JPanel panel = new JPanel(null);
		GridLayout grid = new GridLayout(3, 2);
		panel.setLayout(grid);
		
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

		ButtonEdit insumob = new ButtonEdit(insumoIcon);
		ButtonEdit stockb = new ButtonEdit(stockIcon);
		ButtonEdit camionb = new ButtonEdit(camionIcon);
		ButtonEdit caminob = new ButtonEdit(caminoIcon);
		ButtonEdit plantab = new ButtonEdit(plantaIcon);
		ButtonEdit infob = new ButtonEdit(infoIcon);

		ArrayList<ButtonEdit> botones = new ArrayList<ButtonEdit>();
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
		for (int i = 0; i < arraySize; i++) {
			ButtonEdit b = botones.get(i);
			b.setBackground(Color.WHITE);
			b.setVerticalTextPosition(SwingConstants.BOTTOM);
			b.setHorizontalTextPosition(SwingConstants.CENTER);
			b.setFocusPainted(false);
			panel.add(b);
			b.addActionListener(panel, i + 1);
		}

		panel.setBounds(0, 0, SIZE_X, SIZE_Y);
		frame.add(panel);

		panels.add(panel);
		return panel;
	}

	public static void InsumoPanel(JFrame frame) {
		JButton button;
		JPanel panel = new JPanel();
		JPanel contentPane = new JPanel();
		
		contentPane.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{141, 141, 141, 0};
		gbl_panel.rowHeights = new int[]{23, 23, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnNewButton = new JButton("Crear");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Editar");
		btnNewButton_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 0;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Borrar");
		btnNewButton_2.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2.gridx = 2;
		gbc_btnNewButton_2.gridy = 0;
		panel.add(btnNewButton_2, gbc_btnNewButton_2);
		
		JTextPane textPane = new JTextPane();
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.gridwidth = 2;
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.insets = new Insets(0, 0, 0, 5);
		gbc_textPane.gridx = 0;
		gbc_textPane.gridy = 1;
		panel.add(textPane, gbc_textPane);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(Color.WHITE);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.fill = GridBagConstraints.BOTH;
		gbc_btnBuscar.gridx = 2;
		gbc_btnBuscar.gridy = 1;
		panel.add(btnBuscar, gbc_btnBuscar);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton_3 = new JButton("Guardar");
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Volver");
		btnNewButton_4.setBackground(Color.WHITE);
		panel_1.add(btnNewButton_4);
		
		JList list = new JList();
		list.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(list, BorderLayout.CENTER);

		panel_1.setBounds(0, 0, SIZE_X, SIZE_Y);
		panel.setBounds(0, 0, SIZE_X, SIZE_Y);
		panel.setVisible(false);

		frame.add(panel);
		panels.add(panel);
	}

	public static void StockPanel(JFrame frame) {
		JPanel panel = new JPanel(null);
		GridLayout grid = new GridLayout(3, 3);
		panel.setLayout(grid);
		frame.add(panel);

		JButton newButton = new JButton("Nuevo");
		JButton editButton = new JButton("Editar");
		JButton deleteButton = new JButton("Borrar");

		panel.add(newButton);
		panel.add(editButton);
		panel.add(deleteButton);

		// b.setBackground(Color.WHITE);
		// b.setVerticalTextPosition(SwingConstants.BOTTOM);
		// b.setHorizontalTextPosition(SwingConstants.CENTER);

		panel.setBounds(0, 0, SIZE_X, SIZE_Y);
		panel.setVisible(false);

		panels.add(panel);
	}

	public static void CamionPanel(JFrame frame) {
		JPanel panel = new JPanel(null);
		GridLayout grid = new GridLayout(3, 3);
		panel.setLayout(grid);
		frame.add(panel);

		JButton newButton = new JButton("Nuevo");
		JButton editButton = new JButton("Editar");
		JButton deleteButton = new JButton("Borrar");

		panel.add(newButton);
		panel.add(editButton);
		panel.add(deleteButton);

		// b.setBackground(Color.WHITE);
		// b.setVerticalTextPosition(SwingConstants.BOTTOM);
		// b.setHorizontalTextPosition(SwingConstants.CENTER);

		panel.setBounds(0, 0, SIZE_X, SIZE_Y);
		panel.setVisible(false);

		panels.add(panel);
	}

	public static void CaminoPanel(JFrame frame) {
		JPanel panel = new JPanel(null);
		GridLayout grid = new GridLayout(3, 3);
		panel.setLayout(grid);
		frame.add(panel);

		JButton newButton = new JButton("Nuevo");
		JButton editButton = new JButton("Editar");
		JButton deleteButton = new JButton("Borrar");

		panel.add(newButton);
		panel.add(editButton);
		panel.add(deleteButton);

		// b.setBackground(Color.WHITE);
		// b.setVerticalTextPosition(SwingConstants.BOTTOM);
		// b.setHorizontalTextPosition(SwingConstants.CENTER);

		panel.setBounds(0, 0, SIZE_X, SIZE_Y);
		panel.setVisible(false);

		panels.add(panel);
	}

	public static void PlantaPanel(JFrame frame) {
		JPanel panel = new JPanel(null);
		GridLayout grid = new GridLayout(3, 3);
		panel.setLayout(grid);
		frame.add(panel);

		JButton newButton = new JButton("Nuevo");
		JButton editButton = new JButton("Editar");
		JButton deleteButton = new JButton("Borrar");

		panel.add(newButton);
		panel.add(editButton);
		panel.add(deleteButton);

		// b.setBackground(Color.WHITE);
		// b.setVerticalTextPosition(SwingConstants.BOTTOM);
		// b.setHorizontalTextPosition(SwingConstants.CENTER);

		panel.setBounds(0, 0, SIZE_X, SIZE_Y);
		panel.setVisible(false);

		panels.add(panel);
	}

	public static void InfoPanel(JFrame frame) {
		JPanel panel = new JPanel(null);
		GridLayout grid = new GridLayout(3, 3);
		panel.setLayout(grid);
		frame.add(panel);

		JButton newButton = new JButton("Nuevo");
		JButton editButton = new JButton("Editar");
		JButton deleteButton = new JButton("Borrar");

		panel.add(newButton);
		panel.add(editButton);
		panel.add(deleteButton);

		// b.setBackground(Color.WHITE);
		// b.setVerticalTextPosition(SwingConstants.BOTTOM);
		// b.setHorizontalTextPosition(SwingConstants.CENTER);

		panel.setBounds(0, 0, SIZE_X, SIZE_Y);
		panel.setVisible(false);

		panels.add(panel);
	}
}
