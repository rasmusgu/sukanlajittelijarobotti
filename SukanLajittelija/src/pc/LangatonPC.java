package pc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class LangatonPC {
	static boolean k = true;
	public static void lahetaPC(String x) throws UnknownHostException, IOException {
		Socket s = new Socket("10.0.1.1", 1111);
		DataOutputStream out = new DataOutputStream(s.getOutputStream());
		out.writeUTF(x);
		out.flush();
		out.close();
		s.close();
	}

	public static void vastaanotaPC() throws IOException {
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
}
