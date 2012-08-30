package fr.ralmn.chat.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class Notification {

	private String text;
	private String title;
	private JPopupMenu popup;
	private State state;

	enum State {
		Info, Alert
	}

	public Notification(String tttle, String txt) {
		text = txt;
		title = tttle;
		state = State.Info;
	}

	public Notification(String tttle, String txt, State s) {
		text = txt;
		title = tttle;
		state = s;
	}

	public Notification init() {

		popup = new JPopupMenu();

		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		DisplayMode dim = gs[0].getDisplayMode();

		JLabel label_title = new JLabel();
		label_title.setBounds(0, 0, 50, 25);
		
		JPanel back = new BackgroundsNotification(popup);
		
		back.add(label_title, BorderLayout.NORTH);
		back.add(new ImagePanel(getIcon()), BorderLayout.WEST);
		label_title.setText(title);
		JLabel lbl_text = new JLabel();
		lbl_text.setText(text);
		back.add(lbl_text, BorderLayout.CENTER);
		popup.setSize(800, 400);
		popup.setLocation((int) (dim.getWidth() - (popup.getSize().getWidth() /2)), 0);
		popup.add(back);
		return this;
	}

	public void draw() {

		if (popup == null)
			init();

		popup.setVisible(true);

	}

	private BufferedImage getIcon() {

		BufferedImage img = null;

		if (state == State.Info) {
			try {
				img = ImageIO.read(Notification.class
						.getResource("info-icon.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (state == state.Alert) {
			try {
				img = ImageIO.read(Notification.class
						.getResource("alert-icon.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return img;
	}

}
