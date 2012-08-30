package fr.ralmn.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {

	private Socket socket;
	private PrintWriter out = null;
	private BufferedReader in = null;
	@SuppressWarnings("unused")
	private Scanner sc;
	public static Thread reader = null;
	public static Thread sender = null;
	public static Thread checker = null;
	public Client(Socket s) {
		socket = s;
	}

	public void run() {
		try {
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			sc = new Scanner(System.in);

			sender = new Thread(new Sender(out));
			sender.start();
			reader = new Thread(new Reader(in));
			reader.start();
			checker = new Thread(new Checker(out));
			checker.start();

		} catch (IOException e) {
			System.err.println("Le serveur distant s'est déconnecté !");
		}
	}

}
