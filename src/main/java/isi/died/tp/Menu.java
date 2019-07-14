package isi.died.tp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Menu {

	public static void main(String[] args)
	{
		// comentario de ejemplo
		createAndShowGUI();
	}
	
	public static void createAndShowGUI()
	{
		JFrame frame = new JFrame("Trabajo Práctico Integrador - DIED 2019");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		GridLayout test = new GridLayout(3,2);
		frame.add(panel);
		panel.setLayout(test);
		frame.setSize(600, 400);
		frame.setMinimumSize(new Dimension(600, 400));
		
		ImageIcon insumoIcon = new ImageIcon(new ImageIcon("images/Insumo.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		ImageIcon sotckIcon = new ImageIcon(new ImageIcon("images/Stock.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		ImageIcon camionIcon = new ImageIcon(new ImageIcon("images/Camion.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		ImageIcon caminoIcon = new ImageIcon(new ImageIcon("images/Camino.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		ImageIcon plantaIcon = new ImageIcon(new ImageIcon("images/Planta.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		ImageIcon infoIcon = new ImageIcon(new ImageIcon("images/Info.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		
		JButton insumob = new JButton(insumoIcon);
		JButton stockb = new JButton(sotckIcon);
		JButton camionb = new JButton(camionIcon);
		JButton caminob = new JButton(caminoIcon);
		JButton plantab = new JButton(plantaIcon);
		JButton infob = new JButton(infoIcon);
		
		ArrayList<JButton> botones = new ArrayList<JButton>();
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
		plantab.setText("Planta");	
		infob.setText("Informacion");	
		
		for(JButton b : botones)
		{
			b.setBackground(Color.white);
			b.setVerticalTextPosition(SwingConstants.BOTTOM);
			b.setHorizontalTextPosition(SwingConstants.CENTER);
			b.setFocusPainted(false);
			panel.add(b);
		}
			
		frame.setVisible(true);
	}
}
