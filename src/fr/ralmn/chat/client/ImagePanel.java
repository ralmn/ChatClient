package fr.ralmn.chat.client;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {
	public BufferedImage image;
	BorderLayout borderLayout1 = new BorderLayout();
	int x = 0;
	int y = 0;

	public ImagePanel(BufferedImage img) {
		image = img;
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ImagePanel(int x, int y) {
		this.x = x;
		this.y = y;
		try {
			image = ImageIO.read(new URL("http://harmonycraft.fr/bg/bg.png"));
			try {
				jbInit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int imageWight = image.getWidth(this);
		int imageHeight = image.getHeight(this);

		g.drawImage(image, x, y, null);
		repaint();
	}

	private void jbInit() throws Exception {
		this.setLayout(borderLayout1);
	}
}