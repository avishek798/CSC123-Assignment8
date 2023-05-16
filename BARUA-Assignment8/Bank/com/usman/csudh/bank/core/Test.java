package com.usman.csudh.bank.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	public static void main(String[] args) throws IOException, ConfigFileErrorException {

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
			System.out.println(line);

		}
	}
	// System.out.println(currencies);

//		while (matcher.find()) {
//			System.out.println("Domain: " + matcher.group(2));
//		}

//	Socket socket = new Socket("www.usman.cloud", 80);
//
//	// Sending request
//	OutputStream out = socket.getOutputStream();
//	out.write("GET /banking/exchange-rate.csv HTTP/1.1\r\n".getBytes());
//	out.write("Host: www.usman.cloud\r\n".getBytes());
//	out.write("\r\n".getBytes());
//	
//	InputStream in = socket.getInputStream();
//	
//	BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//	String line;
//	while ((line=reader.readLine())!=null) {
//		System.out.println(line);

	// System.out.println(currencies);
}
