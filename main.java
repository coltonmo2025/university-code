package algs_HW1;

public class main {

	public static int bruteForceExponent(int x, int e) {
		int output = x;
		// System.out.println("exponent is: " + e);

		while (e > 1) {
			output *= x;
			e--;
		}

		return output;

	}

	public static int algorithmExponent(int x, int n) {
		int output = x;
		boolean flag = false;

		if (n % 2 == 1) {
			flag = true;
			n--;
		}

		output = bruteForceExponent(x, n / 2);

		output *= output;

		if (flag == true) {
			output *= x;
		}

		return output;
	}

	public static int polynomialNaive(int x, int k) {
		int output = 0;

		// a is always set to one

		for (int i = 1; i <= k; i++) {
			output += bruteForceExponent(x, i);
		}

		return output;
	}

	public static int hornerMethod(int x, int k) {
		int output = 0;
		int step = 1;

		for (int i = 1; i <= k; i++) {
			step = step * x;
			output += step;

		}

		return output;
	}

	public static void main(String[] args) {
		// Number of trials is a number you have to tweak until you get a elapsed time
		// that is closer to 1 second
		// Elapsed time is the time before you average by the number of trials
		int numberOfTrials = 1000000;
		long startTime = System.currentTimeMillis();

		for (int i = 0; i < numberOfTrials; i++) {

			// bruteForceExponent(15000,110000);

			// algorithmExponent(15000, 110000);

			// System.out.println(polynomialNaive(15000, 200));

			// System.out.println(hornerMethod(15000, 200));

		}

		long endTime = System.currentTimeMillis();

		double time = (endTime - startTime) / (double) numberOfTrials;

		System.out.println(time * 1000);

	}
}
