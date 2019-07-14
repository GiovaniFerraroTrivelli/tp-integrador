package isi.died.tp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ButtonEdit extends JButton{
	
	ButtonEdit(){
		super();
	}
	public ButtonEdit(ImageIcon insumoIcon) {
		super(insumoIcon);
	}
	void addActionListener(JPanel panel, int i){	
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				/*switch(i) {
				case 0:
					panelInsumo();
				case 1:
					panelStock();
				case 2:
					panelCamion();
				case 3:
					panelCamino();
				case 4:
					panelPlanta();
				case 5:
					panelInfo();				
				}*/
			}
		});
	}
}
