package fr.ralmn.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.SocketException;

public class Reader extends Thread {

	private BufferedReader in;
	private String message = null;

	public Reader(BufferedReader in) {

		this.in = in;
	}

	@SuppressWarnings("deprecation")
	public void run() {

		while (true) {
			try {

				if (Main.socket == null || Main.socket.isClosed()) {
					this.stop();
					Client.sender.stop();
					Client.checker.stop();

					Main.chat.menuItem1.setEnabled(true);
					break;
				}
				
				
				
				try {
					message = in.readLine();
				} catch (SocketException e) {
					this.stop();
					Client.sender.stop();
					Client.checker.stop();

					Main.chat.menuItem1.setEnabled(true);
					Main.chat.menuItem2.setEnabled(false);
					
					Main.write("Erreur Connexion serveur !");
					
					break;
				}
				
				
				
				
				if(message.equalsIgnoreCase("[T]")){
					continue;
				}else if(message.equalsIgnoreCase("[kick]")){
					Main.chat.menuItem1.setEnabled(true);
					Main.disconnecting();
					Main.chat.menuItem2.setEnabled(false);
					Main.write("<font color=red> Vous avez été kicker </font>");
					Main.chat.setTitle("Chat by RALMN - non connecter (kicker)");
					continue;
				}else if(message.equalsIgnoreCase("[ban]")){
					Main.disconnecting();
					Main.chat.menuItem1.setEnabled(true);
					Main.chat.menuItem2.setEnabled(false);
					Main.write("<font color=red> Vous avez été banis </font>");
					Main.chat.setTitle("Chat by RALMN - non connecter (Bannis)");
					continue;
				}else if(message.equalsIgnoreCase("[END]")){
					Main.disconnecting();
					Main.chat.menuItem1.setEnabled(true);
					Main.chat.menuItem2.setEnabled(false);
					Main.write("<font color=red> Le serveur a etait arreter ! </font>");
					Main.chat.setTitle("Chat by RALMN - non connecter (Bannis)");
					continue;
				}
				
				if (message == null) {
					this.stop();
					Client.sender.stop();
					break;
				}

				Main.write(message);
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

}
