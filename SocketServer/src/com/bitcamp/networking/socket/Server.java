package com.bitcamp.networking.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	// nesto kao adresa ili otvor gdje cekamo konekciju,
	// rade od 0 do cc.65000,
	// koristiti portove iznad 1500
	public static final int port = 1728;

	/**
	 * 
	 */
	public static void startServer() {
		try {
			// socket koji ceka da se neko na njega spoji
			ServerSocket server = new ServerSocket(port);
			while (true) {
				System.out.println("waiting");
				Socket client = server.accept();
				// outputStream koji je spojen na klijenat
				OutputStream os = client.getOutputStream();
				InputStream is = client.getInputStream();
				String msg = "Hello from Server!!";
				byte[] msgByte = msg.getBytes();
				os.write(msgByte.length);

				os.write(msgByte);
				// slanje poruke od strane klijenta ka serveru
				StringBuilder sb = new StringBuilder();
				int byteRead = 0;
				int msgLength = is.read();
				byte[] buffer = new byte[msgLength];
				while ((byteRead += is.read(buffer)) >= 0) {
					sb.append(new String(buffer).replaceAll("\\s+", " "));
					if (byteRead >= msgLength)
						break;

				}
				String bufferString = sb.toString();
				System.out.println(bufferString);

				client.close();
				// accept vraca new Socket
				// kada upisemo accept nista ispod ove
				// linije nece se izvrsiti dok se ne desi konekcija

				System.out.println("Gotovo");

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		startServer();
	}

}
