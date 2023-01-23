
// multiple of this
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Node {

	public static boolean isPrime(int num) {

		boolean flag = true;
		int i = 2;
		while (flag && (i <= num / 2)) {
			if (num % i == 0) {
				flag = false;
			}
			i++;
		}

		return flag;
	}

	// min inclusive, max exclusive
	public static int primesBetweenRange(int min, int max) {
		int count = 0;

		for (int i = min; i < max; i++) {
			if (isPrime(i)) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		try {

			int numPrimes;
			System.out.println("About to call");
			Socket s = new Socket("localhost", 8000);
			System.out.println("Connected");
// DataOutputStream
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

			// these must be sent to each node from the head
			int min = (int) ois.readObject();
			System.out.println(min);
			int max = (int) ois.readObject();
			System.out.println(max);
			//
			numPrimes = primesBetweenRange(min, max);

			oos.writeObject(numPrimes);

			s.close();

		} catch (IOException | ClassNotFoundException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}