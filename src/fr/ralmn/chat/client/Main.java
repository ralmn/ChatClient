package fr.ralmn.chat.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import fr.ralmn.chat.client.Notification.State;

public class Main {

	public static Socket socket = null;
	public static Thread t1;
	static formChat chat;

	public static void main(String[] args) {
		
		Option.updateOption();
		
		
		chat = new formChat();
		chat.setVisible(true);
		chat.editorPane1.setEditable(false);
		
		if(Option.auto_connect){
			autoconnect();
		}
	
	}
	

	private static void autoconnect() {
		
		String lo = Util.readConfig("username");
		
		if(lo != null){
			
			String pwd = Util.readConfig("password");
			String ip = Util.readConfig("server");
			if(pwd != null){
				Login(ip, lo, pwd);
			}else{
				Login(ip, lo);
			}
		}
		
	}


	@SuppressWarnings("static-access")
	public static void Login(String ip, String login, String pwd) {

		chat.menuItem1.setEnabled(false);
		chat.setDefaultCloseOperation(chat.EXIT_ON_CLOSE);
		try {
			int port = 30000;

			if (ip.contains(":")) {
				String p = ip.split(":")[1];
				port = Integer.parseInt(p);
			}

			socket = new Socket(ip, port);
		} catch (Exception e) {
			write("<font color=red><b> Le serveur " + ip
					+ " est indisponible </b> </font> ");
			chat.menuItem1.setEnabled(true);
			return;
		}
		if (!socket.isConnected()) {
			System.out.println("Erreur");
		}
		t1 = new Thread(new Connexion(socket, login, pwd));
		t1.start();

	}

	@SuppressWarnings("static-access")
	public static void Login(String ip, String login) {
		chat.menuItem1.setEnabled(false);
		chat.setDefaultCloseOperation(chat.EXIT_ON_CLOSE);

		int port = 30000;

		if (ip.contains(":")) {
			String p = ip.split(":")[1];
			port = Integer.parseInt(p);
		}

		try {
			socket = new Socket(ip, port);
		} catch (Exception e) {
			write("<font color=red><b> Le serveur <i>" + ip
					+ "</i> est indisponible </b> </font> ");
			chat.menuItem1.setEnabled(true);
			return;
		}
		if (!socket.isConnected()) {
			System.out.println("Erreur");
		}
		t1 = new Thread(new Connexion(socket, login));
		t1.start();
	}

	public static void write(String t) {
		String text = chat.editorPane1.getText();
		text = text.replace("</html>", "");
		text = text.replace("</body>", "");
		text += "<p style=\"margin-top: 0\">";
		text += t + "</p> </body> </html>";

		if (!chat.isFocused()) {
			if (Option.sound) {
				Thread sounds_play = new Sounds("base.wav");
				sounds_play.start();
			}
		}

		// text = accent(text);
		chat.editorPane1.setText(text);

	}

	private static String accent(String text) {

		text = text.replace("â", "&arcirc;");
		text = text.replace("é", "&eacute;");
		text = text.replace("à", "&agrave;");
		text = text.replace("è", "&egrave;");
		text = text.replace("ä", "&auml;");
		text = text.replace("ë", "&euml;");
		text = text.replace("ê", "&ercirc;");
		text = text.replace("î", "&ircirc;");
		text = text.replace("ï", "&arcirc;");
		text = text.replace("ü", "&uuml;");
		text = text.replace("ù", "&ugrave;");
		text = text.replace("û", "&urcirc;");
		text = text.replace("ç", "ccedil;");
		text = text.replace("ö", "&ouml;");
		text = text.replace("ô", "&orcirc;");
		text = text.replace("ù", "&ugrave;");
		text = text.replace("ì", "&igrave;");

		return text;
	}

	@SuppressWarnings("deprecation")
	public static void disconnecting() throws IOException {
		if (socket != null) {

			PrintWriter out = new PrintWriter(socket.getOutputStream());
			out.println("[END]");
			out.flush();

			try {
				Client.sender.stop();
				Client.reader.stop();
				Client.checker.stop();
			} catch (NullPointerException e) {
			}

			socket.close();
			socket = null;
		}
	}
}
