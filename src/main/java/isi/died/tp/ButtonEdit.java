package isi.died.tp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonEdit extends JButton {

	ButtonEdit() {
		super();
	}

	public ButtonEdit(ImageIcon insumoIcon) {
		super(insumoIcon);
	}

	void addActionListener(JPanel panel, int i) {
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Menu.showPanel(i);
			}
		});
	}
}
