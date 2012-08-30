package fr.ralmn.chat.client;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class BackgroundsNotification extends JPanel {

	JPopupMenu owner;

	public BackgroundsNotification(JPopupMenu o) {
		owner = o;
	}

	public void paintComponent(Graphics g) {
		owner.setOpaque(false);
		owner.setSize(800, 700);
		setBounds(0, 0, 800, 700);
		setBackground(Color.BLUE);
	}
}
