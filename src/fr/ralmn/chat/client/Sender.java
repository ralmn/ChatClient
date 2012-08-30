package fr.ralmn.chat.client;

import java.io.PrintWriter;
import java.util.Scanner;

public class Sender extends Thread {

	private PrintWriter out;
	@SuppressWarnings("unused")
	private String login = null, message = null;
	private Scanner sc = null;

	public Sender(PrintWriter out) {
		this.out = out;
	}

	@SuppressWarnings("deprecation")
	public void run() {

		sc = new Scanner(System.in);

		while (true) {

			if (Main.socket == null) {
				this.stop();
				Client.reader.stop();
				Client.checker.stop();

				Main.chat.menuItem1.setEnabled(true);
				break;
			}
			message = sc.nextLine();
			out.println(message);
			out.flush();
		}
	}
}
