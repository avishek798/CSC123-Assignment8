package com.usman.csudh.bank.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public abstract class CurrencyManager {

	public static CurrencyManager getInstance(String s) throws Exception {
		if (s.equalsIgnoreCase("file")) {
			return new FileHook();
		} else if (s.equalsIgnoreCase("webservice")) {
			return new HTTPHook();
		} else {
			throw new Exception("** Currency could not be loaded, "
					+ "Currency Conversion Service and Foreign Currency accounts are not available **%n");
		}
	}

	public ArrayList<String> readCurrency() throws ConfigFileErrorException, IOException {

		ArrayList<String> list = new ArrayList<>();

		InputStream in = getInputStream();

		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = null;
		while ((line = reader.readLine()) != null) {
			list.add(line);
		}
		return list;
	}

	protected abstract InputStream getInputStream() throws ConfigFileErrorException, IOException;

}