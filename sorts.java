package algs_HW2;

import java.util.Arrays;
import java.util.Random;

public class sorts {
	public static void insertionSort(int[] arr) {

		int length = arr.length;

		for (int i = 0; i < length; i++) {

			int j = i;
			while ((j > 0) && arr[j - 1] > arr[j]) {

				// swap function
				swap(arr, j, j - 1);
				j = j - 1;
			}
		}

	}

	public static void swap(int[] arr, int loc1, int loc2) {
		int temp = arr[loc1];
		arr[loc1] = arr[loc2];
		arr[loc2] = temp;
	}

	public static void mergeSort(int[] arr) {
		int items = arr.length;
		if (items < 2) {
			return;
		}

		// allocates memory to arrays
		int arrayOne[] = new int[items / 2];
		int arrayTwo[] = new int[items - (items / 2)];

		// assigns subsection a
		for (int i = 0; i < items / 2; i++) {
			arrayOne[i] = arr[i];
		}

		// assigns subsection b
		for (int i = arr.length / 2; i < arr.length; i++) {
			arrayTwo[i - (items/2)] = arr[i];
		}

		mergeSort(arrayOne);
		mergeSort(arrayTwo);

		merge(arr, arrayOne, arrayTwo);
	}

	public static void merge(int[] arr, int[] a, int[] b) {
		int left = a.length;
		int right = b.length;
		
		int i = 0, j = 0, k = 0;
		
		while (i < left && j < right) {
			if (a[i]<= b[j]) {
				arr[k++] = a[i++];
			}
			else {
				arr[k++] = b[j++];
			}
		}
		
		while (i < left) {
			arr[k++] = a[i++];
		}
		while (j < right) {
			arr[k++] = b[j++];
		}

	}

	public static void main(String[] args) {
		// Number of trials is a number you have to tweak until you get a elapsed time
		// that is closer to 1 second
		// Elapsed time is the time before you average by the number of trials

		int numberOfItems = 100_000;

		long startTime = System.currentTimeMillis();
		// for making the random object
		Random random = new Random();
		int array[] = new int[numberOfItems];

		// assigns everything to random
		for (int i = 0; i < numberOfItems; i++) {
			//array[i] = random.nextInt(1000);
			
			array[i] = i;

		}
		System.out.println(Arrays.toString(array));
		
		//insertionSort(array);
		
		mergeSort(array);

		System.out.println(Arrays.toString(array));
		long endTime = System.currentTimeMillis();
		double time = (endTime - startTime);

		System.out.println(time);
		System.out.println(time / (numberOfItems * numberOfItems));

	}
}
