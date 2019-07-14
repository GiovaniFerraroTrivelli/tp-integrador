package guiTP;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Menu {

	public static void main(String[] args){
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		GridLayout test = new GridLayout(3,2);
		frame.add(panel);
		panel.setLayout(test);
		frame.setSize(600, 400);
		
		ImageIcon insumoIcon = new ImageIcon(new ImageIcon("Insumo.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		ImageIcon sotckIcon = new ImageIcon(new ImageIcon("Stock.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		ImageIcon camionIcon = new ImageIcon(new ImageIcon("Camion.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		ImageIcon caminoIcon = new ImageIcon(new ImageIcon("Camino.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		ImageIcon plantaIcon = new ImageIcon(new ImageIcon("Planta.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		ImageIcon infoIcon = new ImageIcon(new ImageIcon("Info.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		

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
	
		for(JButton b : botones){
			b.setBackground(Color.white);
		}
	
		insumob.setVerticalTextPosition(SwingConstants.BOTTOM);
		insumob.setHorizontalTextPosition(SwingConstants.CENTER);
		insumob.setText("Insumos");	
		
		stockb.setVerticalTextPosition(SwingConstants.BOTTOM);
		stockb.setHorizontalTextPosition(SwingConstants.CENTER);
		stockb.setText("Stock");	
		
		camionb.setVerticalTextPosition(SwingConstants.BOTTOM);
		camionb.setHorizontalTextPosition(SwingConstants.CENTER);
		camionb.setText("Camiones");
		
		caminob.setVerticalTextPosition(SwingConstants.BOTTOM);
		caminob.setHorizontalTextPosition(SwingConstants.CENTER);
		caminob.setText("Caminos");	
		
		plantab.setVerticalTextPosition(SwingConstants.BOTTOM);
		plantab.setHorizontalTextPosition(SwingConstants.CENTER);
		plantab.setText("Planta");	
		
		infob.setVerticalTextPosition(SwingConstants.BOTTOM);
		infob.setHorizontalTextPosition(SwingConstants.CENTER);
		infob.setText("Informacion");	
		
		panel.add(insumob);
		panel.add(stockb);
		panel.add(camionb);
		panel.add(caminob);
		panel.add(plantab);
		panel.add(infob);
		
		frame.setVisible(true);
	}
}
