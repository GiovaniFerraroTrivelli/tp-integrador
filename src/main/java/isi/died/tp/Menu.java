package isi.died.tp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Menu {

	static ArrayList<JPanel> panels = new ArrayList<JPanel>();
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Trabajo Práctico Integrador - DIED 2019");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 720);
		frame.setMinimumSize(new Dimension(1280, 720));
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
	
	public static void showPanel(Integer id)
	{
		if(id > 0)
		{
			panels.get(id).setVisible(true);
			panels.get(0).setVisible(false);
		}
		else
		{
			panels.get(0).setVisible(true);
			
			int size = panels.size();

			for(int i = 1; i <= size; i++)
				panels.get(i).setVisible(false);
		}
	}
	
	public static JPanel menuPrincipal(JFrame frame)
	{
		JPanel panel = new JPanel(null);
		GridLayout grid = new GridLayout(3,2);
		panel.setLayout(grid);
		
		ImageIcon insumoIcon = new ImageIcon(new ImageIcon("images/Insumo.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		ImageIcon stockIcon = new ImageIcon(new ImageIcon("images/Stock.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		ImageIcon camionIcon = new ImageIcon(new ImageIcon("images/Camion.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		ImageIcon caminoIcon = new ImageIcon(new ImageIcon("images/Camino.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		ImageIcon plantaIcon = new ImageIcon(new ImageIcon("images/Planta.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		ImageIcon infoIcon = new ImageIcon(new ImageIcon("images/Info.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		
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
		for(int i = 0; i < arraySize; i++)
		{
			ButtonEdit b = botones.get(i);
			b.setBackground(Color.WHITE);
			b.setVerticalTextPosition(SwingConstants.BOTTOM);
			b.setHorizontalTextPosition(SwingConstants.CENTER);
			b.setFocusPainted(false);
			panel.add(b);
			b.addActionListener(panel, i + 1);
		}
		
		panel.setBounds(0, 0, 1280, 720);
		frame.add(panel);
		panel.setVisible(true);
		
		panels.add(panel);		
		return panel;
	}
	
	public static JPanel InsumoPanel(JFrame frame)
	{
		JPanel panel = new JPanel(null);
		GridLayout grid = new GridLayout(3,3);
		panel.setLayout(grid);
		frame.add(panel);
		
		JButton newButton = new JButton("Nuevo");
		JButton editButton = new JButton("Editar");
		JButton deleteButton = new JButton("Borrar");

		panel.add(newButton);
		panel.add(editButton);
		panel.add(deleteButton);
		
		//b.setBackground(Color.WHITE);
		//b.setVerticalTextPosition(SwingConstants.BOTTOM);
		//b.setHorizontalTextPosition(SwingConstants.CENTER);
		
		panel.setBounds(0, 0, 1280, 720);
		panels.add(panel);
		return panel;
	}

	public static JPanel StockPanel(JFrame frame)
	{
		JPanel panel = new JPanel(null);
		GridLayout grid = new GridLayout(3,3);
		panel.setLayout(grid);
		frame.add(panel);
		
		JButton newButton = new JButton("Nuevo");
		JButton editButton = new JButton("Editar");
		JButton deleteButton = new JButton("Borrar");

		panel.add(newButton);
		panel.add(editButton);
		panel.add(deleteButton);
		
		//b.setBackground(Color.WHITE);
		//b.setVerticalTextPosition(SwingConstants.BOTTOM);
		//b.setHorizontalTextPosition(SwingConstants.CENTER);
		
		panel.setBounds(0, 0, 1280, 720);
		panels.add(panel);
		return panel;
	}

	public static JPanel CamionPanel(JFrame frame)
	{
		JPanel panel = new JPanel(null);
		GridLayout grid = new GridLayout(3,3);
		panel.setLayout(grid);
		frame.add(panel);
		
		JButton newButton = new JButton("Nuevo");
		JButton editButton = new JButton("Editar");
		JButton deleteButton = new JButton("Borrar");

		panel.add(newButton);
		panel.add(editButton);
		panel.add(deleteButton);
		
		//b.setBackground(Color.WHITE);
		//b.setVerticalTextPosition(SwingConstants.BOTTOM);
		//b.setHorizontalTextPosition(SwingConstants.CENTER);
		
		panel.setBounds(0, 0, 1280, 720);
		panels.add(panel);
		return panel;
	}

	public static JPanel CaminoPanel(JFrame frame)
	{
		JPanel panel = new JPanel(null);
		GridLayout grid = new GridLayout(3,3);
		panel.setLayout(grid);
		frame.add(panel);
		
		JButton newButton = new JButton("Nuevo");
		JButton editButton = new JButton("Editar");
		JButton deleteButton = new JButton("Borrar");

		panel.add(newButton);
		panel.add(editButton);
		panel.add(deleteButton);
		
		//b.setBackground(Color.WHITE);
		//b.setVerticalTextPosition(SwingConstants.BOTTOM);
		//b.setHorizontalTextPosition(SwingConstants.CENTER);
		
		panel.setBounds(0, 0, 1280, 720);
		panels.add(panel);
		return panel;
	}
	
	public static JPanel PlantaPanel(JFrame frame)
	{
		JPanel panel = new JPanel(null);
		GridLayout grid = new GridLayout(3,3);
		panel.setLayout(grid);
		frame.add(panel);
		
		JButton newButton = new JButton("Nuevo");
		JButton editButton = new JButton("Editar");
		JButton deleteButton = new JButton("Borrar");

		panel.add(newButton);
		panel.add(editButton);
		panel.add(deleteButton);
		
		//b.setBackground(Color.WHITE);
		//b.setVerticalTextPosition(SwingConstants.BOTTOM);
		//b.setHorizontalTextPosition(SwingConstants.CENTER);
		
		panel.setBounds(0, 0, 1280, 720);
		panels.add(panel);
		return panel;
	}

	public static JPanel InfoPanel(JFrame frame)
	{
		JPanel panel = new JPanel(null);
		GridLayout grid = new GridLayout(3,3);
		panel.setLayout(grid);
		frame.add(panel);
		
		JButton newButton = new JButton("Nuevo");
		JButton editButton = new JButton("Editar");
		JButton deleteButton = new JButton("Borrar");

		panel.add(newButton);
		panel.add(editButton);
		panel.add(deleteButton);
		
		//b.setBackground(Color.WHITE);
		//b.setVerticalTextPosition(SwingConstants.BOTTOM);
		//b.setHorizontalTextPosition(SwingConstants.CENTER);
		
		panel.setBounds(0, 0, 1280, 720);
		panels.add(panel);
		return panel;
	}
}
