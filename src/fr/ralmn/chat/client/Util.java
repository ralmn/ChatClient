package fr.ralmn.chat.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Util {

	public static File getWorkDir() {

		File f = getWorkingDirectory("UniversalChat");
		return f;
	}

	private static File getWorkingDirectory(String applicationName) {
		String userHome = System.getProperty("user.home", ".");
		File workingDirectory;
		if (getPlatform() == OS.solaris || getPlatform() == OS.linux) {
			workingDirectory = new File(userHome, '.' + applicationName + '/');
		} else if (getPlatform() == OS.windows) {
			String applicationData = System.getenv("APPDATA");
			if (applicationData != null)
				workingDirectory = new File(applicationData, "."
						+ applicationName + '/');
			else
				workingDirectory = new File(userHome,
						'.' + applicationName + '/');
		} else if (getPlatform() == OS.macos) {
			workingDirectory = new File(userHome,
					"Library/Application Support/" + applicationName);
		} else {
			workingDirectory = new File(userHome, applicationName + '/');
		}

		if ((!workingDirectory.exists()) && (!workingDirectory.mkdirs()))
			throw new RuntimeException(
					"The working directory could not be created: "
							+ workingDirectory);
		return workingDirectory;
	}

	private static OS getPlatform() {
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.contains("win"))
			return OS.windows;
		if (osName.contains("mac"))
			return OS.macos;
		if (osName.contains("solaris"))
			return OS.solaris;
		if (osName.contains("sunos"))
			return OS.solaris;
		if (osName.contains("linux"))
			return OS.linux;
		if (osName.contains("unix"))
			return OS.linux;
		return OS.unknown;
	}

	private static enum OS {
		linux, solaris, windows, macos, unknown;
	}

	public static String getLastServer() {
		return readConfig("server");
	}

	public static String readConfig(String value) {
		String r = "";
		File f = new File(getWorkDir(), "config.properties");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(f));
			r = prop.getProperty(value);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return r;
	}

	public static String readConfig(String value, String d) {
		String r = "";
		File f = new File(getWorkDir(), "config.properties");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(f));

			if (!prop.contains(value)) {
				prop.setProperty(value, d);
			}

			r = prop.getProperty(value, d);
			prop.store(new FileOutputStream(f), "");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return r;
	}

	public static void writeConfig(String key, String v) {

		File f = new File(getWorkDir(), "config.properties");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(f));
			prop.setProperty(key, v);
			prop.store(new FileOutputStream(f), "");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
