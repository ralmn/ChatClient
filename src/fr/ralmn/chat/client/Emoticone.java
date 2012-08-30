package fr.ralmn.chat.client;

import java.util.ArrayList;

public class Emoticone {

	private static int S_Id = 0;
	private int id;
	private String racourcis;
	private String path;

	public Emoticone() {

		id = S_Id;
		S_Id++;
	}

	public Emoticone setRaccourcis(String r) {
		racourcis = r;
		return this;
	}

	public Emoticone setPath(String p) {
		path = p;
		return this;
	}

	public String draw() {
		String d = "<img src=\"" + path + "\" height=\"16\" width=\"16r\" >";
		return d;
	}

	public static Emoticone smile = new Emoticone().setPath("").setRaccourcis(
			":)");

	public static Emoticone getEmoticone(String r) {

		ArrayList<Emoticone> es = new ArrayList<Emoticone>();
		es.add(smile);

		for (Emoticone e : es) {
			if (e.getRacourcis().equals(r)) {
				return e;
			}
		}
		return null;
	}

	public String getRacourcis() {
		return racourcis;
	}
}
