package bank.net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.usman.csudh.bank.MainBank;

public class NetworkListener {
		public static void main(String[] args) throws IOException {
			
			ServerSocket server = new ServerSocket(80);
			
			while (true) {
				Socket socket = server.accept();
				banner(socket.getOutputStream());
				Thread bank=new MainBank(socket.getInputStream(), socket.getOutputStream());
				bank.start();
				
			}
		}
		
		public static void banner(OutputStream out) throws IOException {
			out.write("Welcome to Bank\r\n".getBytes());
			out.flush();
		}
}
