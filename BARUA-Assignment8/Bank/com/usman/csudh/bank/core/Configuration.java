package com.usman.csudh.bank.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Configuration {

	private static final String CONFIG_FILE = "config.txt";
	private static Configuration self = null;
	private static Map<String, String> config = new TreeMap<String, String>();

	private static boolean support;
	private static String currenciesSource;
	private static String websiteURL;
	private static String fileName;

	static BufferedReader reader = null;

	private Configuration() throws IOException, ConfigFileErrorException {
		reader = new BufferedReader(new InputStreamReader(new FileInputStream((new File(CONFIG_FILE)))));
		String s;
		String[] content;
		while ((s = reader.readLine()) != null) {
			content = s.split("=");
			config.put(content[0], content[1]);
		}

		if (!(config.containsKey("support.currencies") && config.containsKey("currencies.source")
				&& config.containsKey("webservice.url") && config.containsKey("currency.file"))) {
			throw new ConfigFileErrorException("** Currency could not be loaded, "
					+ "Currency Conversion Service and Foreign Currency accounts are not available **%n");
		}
		support = Boolean.parseBoolean(config.get("support.currencies"));
		currenciesSource = config.get("currencies.source");
		websiteURL = config.get("webservice.url");
		fileName = config.get("currency.file");
	}

	public static Configuration getInstance() throws IOException, ConfigFileErrorException {
		if (self == null) {
			self = new Configuration();
		}
		return self;
	}

	public boolean isSupport() {
		return support;
	}

	public String getCurrenciesSource() {
		return currenciesSource;
	}

	public String getWebsiteURL() {
		return websiteURL;
	}

	public String getFileName() {
		return fileName;
	}

}
