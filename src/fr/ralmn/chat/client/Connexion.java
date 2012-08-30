package fr.ralmn.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Connexion implements Runnable {

	private Socket socket = null;
	public static Thread t2;
	public static String login = null, pass = null, message1 = null,
			message2 = null, message3 = null;
	private PrintWriter out = null;
	private BufferedReader in = null;
	@SuppressWarnings("unused")
	private Scanner sc = null;
	private boolean connect = false;
	private boolean aa;

	public Connexion(Socket s) {
		socket = s;
	}

	@SuppressWarnings("static-access")
	public Connexion(Socket socket, String login) {
		this.socket = socket;
		this.login = login;
		this.pass = "pass";
	}

	@SuppressWarnings("static-access")
	public Connexion(Socket s, String l, String p) {
		this.socket = s;
		this.login = l;
		this.pass = p;
	}

	@Override
	public void run() {
		try {

			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			sc = new Scanner(System.in);

			while (!connect) {
				String msg = in.readLine();
				System.out.println(msg);
				if (msg.equals("accept")) {

					Main.chat.editorPane1
							.setText("<html><head></head><body></body> </html");

					Main.write("Bonjour " + login);
					connect = true;
					Main.chat.menuItem2.setEnabled(true);
					Main.chat.menuItem1.setEnabled(false);
					Main.chat.setTitle("Chat by RALMN - "
							+ Main.socket.getInetAddress().getHostName());
					aa = true;
				} else if (msg.equals("notperms")) {
					Main.write("<font color=red>Tu n'a pas l'autorisation pour se connecter avec ce nom</font>");
					Main.chat.setTitle("Chat by RALMN - Connexion refuser");
					Main.chat.menuItem2.setEnabled(false);
					Main.chat.menuItem1.setEnabled(true);
					socket.close();
					socket = null;
					connect = true;
					sc.close();
					Main.t1.stop();
					break;
				} else if (msg.equals("ban")) {
					Main.write("<font color=red>Tu a été bannis</font>");
					Main.chat.setTitle("Chat by RALMN - Connexion refuser");
					Main.chat.menuItem2.setEnabled(false);
					Main.chat.menuItem1.setEnabled(true);
					socket.close();
					socket = null;
					connect = true;
					sc.close();
					Main.t1.stop();
					break;
				} else if (msg.equals("already")) {
					Main.write("<font color=red> Ce pseudo est deja connecté</font>");
					Main.chat.setTitle("Chat by RALMN - Connexion refuser");
					Main.chat.menuItem2.setEnabled(false);
					Main.chat.menuItem1.setEnabled(true);
					socket.close();
					connect = true;
					socket = null;
					sc.close();
					Main.t1.stop();
				} else if (msg.equalsIgnoreCase("Login")) {
					out.println("[LOG]" + login);
					out.flush();
					out.println("[LOG]" + pass);
					out.flush();
				} else {
					System.err.println("Vos informations sont incorrectes ");
				}

			}
			if (connect && aa) {

				t2 = new Thread(new Client(socket));
				t2.start();
				Main.t1.stop();
			}
		} catch (IOException e) {
			System.err.println("Le serveur ne répond plus ");
		}
	}
}
