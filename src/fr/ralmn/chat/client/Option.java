package fr.ralmn.chat.client;

public class Option {

	public static boolean sound = true;
	public static boolean emoticone = true;
	public static boolean auto_connect = false;
	public static void updateOption(){
		
		String s_sounds = Util.readConfig("sound", "true");
		Option.sound = Boolean.valueOf(s_sounds);
		String s_emo = Util.readConfig("emoticone", "true");
		
		String s_auto_connect = Util.readConfig("autoconnect", "false");
		Option.auto_connect = Boolean.valueOf(s_auto_connect);
		
	}
	
}
