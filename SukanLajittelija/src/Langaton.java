import java.io.*;
import java.net.*;

public class Langaton {
	static boolean k = true;
	public static void vastaanota() throws IOException {
		ServerSocket serv = new ServerSocket(1111);
		Socket s = serv.accept();
		DataInputStream in = new DataInputStream(s.getInputStream());
		while (k == true) {
			System.out.println(in.readUTF());
		}
		in.close();
		s.close();
		serv.close();
	}

	public static void laheta(String x) throws IOException {
		ServerSocket serv = new ServerSocket(1111);
		Socket s = serv.accept();
		DataOutputStream out = new DataOutputStream(s.getOutputStream());
		out.writeUTF(x);
		out.close();
		s.close();
		serv.close();
	}
}
