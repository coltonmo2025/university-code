
// only one of this
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Head {

	public static void main(String[] args) {
		try {

			int socketCount = 5;
			int min = 1000;
			int max = 1000000;

			int numPrimes = 0;

			Thread[] threads = new Thread[socketCount];
			int[] results = new int[socketCount];

			ServerSocket s = new ServerSocket(8000);

			for (int i = 0; i < socketCount; i++) {
				int start = (((max - min) / socketCount) * i) + min;
				System.out.println("start for node: " + i + " was " + start);

				int stop = ((((max - min) / socketCount) * (i + 1)) + min);
				System.out.println("stop for node: " + i + "was " + stop);

				clientHandler clientThread = new clientHandler(start, stop, i, results, s);

				Thread th = new Thread(clientThread);
				threads[i] = th;
				th.start();
			}

			for (int i = 0; i < socketCount; i++) {
				try {
					threads[i].join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			int fin = 0;

			for (int i = 0; i < socketCount; i++) {
				fin += results[i];
			}

			System.out.println(fin);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}

class clientHandler extends Thread {

	Socket s = new Socket();

	clientHandler(int start, int stop, int threadNum, int[] results, ServerSocket ss) {
		try {
			s = ss.accept();
			System.out.println("Accepted");
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(start);
			oos.writeObject(stop);

			results[threadNum] += (int) ois.readObject();

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

//ss[i].close();
//s.close();

// this ^ is more of a c thing