package fr.ralmn.chat.client;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class Sounds extends Thread {

	private String fileName;
	private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb

	public Sounds(String f) {
		fileName = f;
	}

	public void run() {
		File f = new File(Sounds.class.getResource(fileName).getFile());
			
		if (!f.exists()) {
			System.err.println("Ce sons n'existe pas ! ");
			return;	
		}

		AudioInputStream Ainp = null;
		try {
			Ainp = AudioSystem.getAudioInputStream(f);
		} catch (Exception e) {
		}

		AudioFormat format = Ainp.getFormat();
		SourceDataLine auline = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
		try {

			auline = (SourceDataLine) AudioSystem.getLine(info);
			auline.open(format);
		} catch (Exception e) {
		}

		
		
		auline.start();
		int nBytesRead = 0;
		byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
		try {
			while (nBytesRead != -1) {
				nBytesRead = Ainp.read(abData, 0, abData.length);
				if (nBytesRead >= 0) {
					auline.write(abData, 0, nBytesRead);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			auline.drain();
			auline.close();
		}
	}

}
