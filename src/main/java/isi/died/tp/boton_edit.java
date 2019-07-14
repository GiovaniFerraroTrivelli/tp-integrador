package isi.died.tp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class boton_edit extends JButton{
	
	boton_edit(){
		super();
	}
	public boton_edit(ImageIcon insumoIcon) {
		super(insumoIcon);
	}
	void addActionListener(JPanel panel){	
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
			}
		});
	}
}
