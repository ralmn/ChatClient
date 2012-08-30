package fr.ralmn.chat.client;

import java.io.PrintWriter;
import java.util.Scanner;

public class Checker implements Runnable {

	private PrintWriter out;
	@SuppressWarnings("unused")
	
	public Checker(PrintWriter out) {
		this.out = out;
	}

	@SuppressWarnings("deprecation")
	public void run() {


		while (true) {

			if (Main.socket == null) {
				Client.sender.stop();
				Client.reader.stop();
				Client.checker.stop();
				Main.chat.menuItem1.setEnabled(true);
				break;
			}
			out.println("[T]");
			out.flush();
		}
	}

}
