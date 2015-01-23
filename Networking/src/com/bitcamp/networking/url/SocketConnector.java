package com.bitcamp.networking.url;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.plaf.SliderUI;

public class SocketConnector {
	// umjesto 127.0.0.1. moze i localHost
	public static final String serverAdress = "127.0.0.1";
	// port mora biti isti ako i kod servera!!
	public static final int port = 1728;

	/**
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	private static void connectToServer() throws UnknownHostException,
			IOException {

		Socket client = new Socket(serverAdress, port);
		// InputStream prima poruke sa servera
		InputStream clientInt = client.getInputStream();
		// OutputStream salje poruke serveru
		OutputStream clientOut = client.getOutputStream();

		try {
			int msgLength = clientInt.read();
			byte[] buffer = new byte[msgLength];
			int byteRead = 0;
			// povecamo byteRead svaki put kada procitamo byte
			StringBuilder sb = new StringBuilder();

			while ((byteRead += clientInt.read(buffer)) >= 0) {
				sb.append(new String(buffer).replaceAll("\\s+", " "));
				if (byteRead >= msgLength)
					break;

			}
			String bufferString = sb.toString();
			System.out.println(bufferString);

			String msg = "Hello from client!!";
			byte[] msgByte = msg.getBytes();
			clientOut.write(msgByte.length);

			clientOut.write(msgByte);

			client.close();

			System.out.println("\nGotovo");
			client.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void main(String[] args) {
		try {
			connectToServer();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
