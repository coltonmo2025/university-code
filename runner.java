

public class runner extends Thread{
	
	public static boolean isPrime(int num) {
		
		boolean flag = true;
		int i =2;
		while (flag && (i <= num/2)) {
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

	@Override
	public void run() {
		System.out.println("This code is running in a thread");
	}

	public static void main(String[] args) {

		//these are easy to change!
		
		int min = 1000;
		int max = 1000000;
		int threads = 4;
		
		int[] threadCollecter = new int[threads - 1];
		
		for (int i = 0; i < threads; i++) {
			Thread object = new Thread(new runner());
			object.start();
		}
		
		double startTime = System.currentTimeMillis();
		
		
		System.out.println(primesBetweenRange(min,max));
		
		System.out.println("That took: " + ((System.currentTimeMillis() - startTime)/1000)+ " millisecond(s).");
	}

}
