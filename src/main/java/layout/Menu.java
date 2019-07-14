package layout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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

		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.1;

		button = new JButton("Nuevo");

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(button, c);

		button = new JButton("Editar");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		panel.add(button, c);

		button = new JButton("Borrar");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		panel.add(button, c);

		button = new JButton("Long-Named Button 4");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 360; // make this component tall
		c.weightx = 0.0;
		c.gridwidth = 6;
		c.gridx = 0;
		c.gridy = 1;
		panel.add(button, c);

		button = new JButton("5");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0; // reset to default
		c.weighty = 2.0; // request any extra vertical space
		c.gridx = 0; // aligned with button 2
		c.gridwidth = 8; // 2 columns wide
		c.gridy = 3; // third row
		panel.add(button, c);

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
