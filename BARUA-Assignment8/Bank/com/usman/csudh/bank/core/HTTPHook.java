package com.usman.csudh.bank.core;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTTPHook extends CurrencyManager {

	@Override
	protected InputStream getInputStream() throws ConfigFileErrorException, IOException {

		Pattern pattern = Pattern.compile("^.*?\\/\\/([^:\\/\\s]+)");
		Matcher matcher = pattern.matcher(Configuration.getInstance().getWebsiteURL());
		String s = null;
		while (matcher.find()) {
			s = matcher.group(1);
		}
		String[] urlBreakdown = Configuration.getInstance().getWebsiteURL().split(s);
		String resource = urlBreakdown[1];

		Socket socket = new Socket(s, 80);

		OutputStream out = socket.getOutputStream();
		out.write(("GET " + resource + " HTTP/1.1\r\n").getBytes());
		out.write(("Host: " + s + "\r\n").getBytes());
		out.write("\r\n".getBytes());
		InputStream in = socket.getInputStream();

		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String currencies = "", line = null;
		while ((line = reader.readLine()) != null) {
			String strDigits = line.replaceAll("\\D", "");
			String strCode = strDigits.charAt(2) + "" + strDigits.charAt(3) + "" + strDigits.charAt(4);
			int code = Integer.parseInt(strCode);
			if (code != 200) {
				throw new IOException("** Currency could not be loaded, "
						+ "Currency Conversion Service and Foreign Currency accounts are not available **%n");
			}
			if (line.isBlank()) {
				while ((line = reader.readLine()) != null) {
					currencies = currencies + line + "\n";
				}

			}
		}
		InputStream exchStream = new ByteArrayInputStream(currencies.getBytes());
		socket.close();
		return exchStream;
		
	}

}
