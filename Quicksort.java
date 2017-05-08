import java.util.Random;

/**
 * Examine the behavior of the Quicksort algorithm based on pivot locations.
 * Discover sorting times in sorted and unsorted arrays.
 * 
 * @author Tim Liu
 * @Version 22 February, 2017
 *
 */
public final class Quicksort {
	final static double nanoToMili = 1000000.0;

	public static void main(String[] args) {
		
		/* This loop will create arrays from length 10,000 to 10,000,000 in multiples of ten.
		 * We will use these arrays of such length to sort them using two different methods,
		 * pivot first and pivtot mid on an already sorted array and unsorted array. */
		for (int i = 10000; i <= 10000000; i *= 10) {
			try {
				long elapsedTime1st = 0;
				long elapsedTimeMid = 0;
				long elapsedTimeMid2 = 0;
				long elapsedTime1st2 = 0;
				Random rand = new Random();
				
				/* Initialize arrays of i size */
				int[] a = new int[i];
				int[] a1st = new int[i];
				int[] amid = new int[i];
				
				/* Generate random integers, insert into respective arrays. */
				for (int j = 0; j < i; j++) {
					a[j] = rand.nextInt(i) + 1; // random integer between 1 and
												// MAX_VALUE
					a1st[j] = a[j];
					amid[j] = a[j];
				}
				
				System.out.println("Array length " + i + ":\n");

				long startMid = System.nanoTime();
				QuicksortMid(amid);
				long endMid = System.nanoTime();
				elapsedTimeMid += endMid - startMid;
				System.out.println("Time of MIDDLE pivot procedure in an UNSORTED array: " + elapsedTimeMid / nanoToMili
						+ " miliseconds");

				long startMid2 = System.nanoTime();
				QuicksortMid(amid);
				long endMid2 = System.nanoTime();
				elapsedTimeMid2 += endMid2 - startMid2;
				System.out.println("Time of MIDDLE pivot procedure in a SORTED array: " + elapsedTimeMid2 / nanoToMili
						+ " miliseconds\n");

				long start1st = System.nanoTime();
				Quicksort1st(a1st);
				long end1st = System.nanoTime();
				elapsedTime1st += end1st - start1st;
				System.out.println("Time of FIRST pivot procedure in an UNSORTED array: " + elapsedTime1st / nanoToMili
						+ " miliseconds.");

				long start1st2 = System.nanoTime();
				Quicksort1st(a1st);
				long end1st2 = System.nanoTime();
				elapsedTime1st2 += end1st2 - start1st2;
				System.out.println("Time of FIRST pivot procedure in an SORTED array:  " + elapsedTime1st2 / nanoToMili
						+ " miliseconds.");
				System.out.println(
						"------------------------------------------------------------------------------------------");
				
				/** The largest value of n is beyond the maximum primitive value, so I can't be sure which n 
				 * will cause overflow without testing perhaps BigInteger.
				 */
				
			} catch (StackOverflowError t) {
				System.out.println("Caught " + t);
			}
		}
	}
	/**
	 * Pivot method, using the 1st element in the array
	 * 
	 * @param theArray the input array to be sorted
	 * @param theLeft the left most element 
	 * @param theRight the right most element
	 * @return the right-most integer element after pivoting
	 */
	public static int Pivot1st(int[] theArray, int theLeft, int theRight) {
		int left = theLeft + 1;
		int right = theRight;
		int pivot = theArray[theLeft];

		while (left <= right) {

			while (left <= right) {
				left++;
			}

			while (theArray[right] > pivot) {
				right--;
			}

			if (left <= right) {
				swap(theArray, left, right);
				left++;
				right--;
			}
		}
		swap(theArray, theLeft, right);
		return right;
	}

	/** 
	 * This pivot method using the middle element of the array.
	 * @param theArray
	 * @param theLeft
	 * @param theRight
	 * @return the right-most integer element after pivoting.
	 */
	public static int PivotMid(int[] theArray, int theLeft, int theRight) {
		int left = theLeft + 1;
		int right = theRight;
		int pivotIndex = (left + right) / 2;
		swap(theArray, theLeft, pivotIndex);
		int pivot = theArray[theLeft];

		while (left <= right) {

			while (left <= right && theArray[left] < pivot) {
				left++;
			}

			while (theArray[right] > pivot) {
				right--;
			}
			if (left <= right) {
				swap(theArray, left, right);
				left++;
				right--;
			}
		}
		swap(theArray, theLeft, right);
		return right;

	}
	
	/**
	 * Helper method to swap elements.
	 * 
	 * @param a
	 * @param l
	 * @param r
	 */
	private static void swap(int[] a, int l, int r) {
		int temp = a[l];
		a[l] = a[r];
		a[r] = temp;
	}

	/** 
	 * Recursive quicksort using the fist element as pivot.
	 * 
	 * @param a
	 * @param l
	 * @param r
	 */
	public static void Quicksort1st(int[] a, int l, int r) {
		if (r > 1) {
			int p = Pivot1st(a, l, r);
			if (l < p - 1) {
				Quicksort1st(a, l, p - 1);
			}
			if (p < r) {
				Quicksort1st(a, p + 1, r);
			}

		}
	}
	/**
	 * Simple method that calls the quicksort method, takes an array as a single argument.
	 * @param theArray to be sorted
	 */
	public static void Quicksort1st(int[] theArray) {
		Quicksort1st(theArray, 0, theArray.length - 1);
	}

	/**
	 * Recursive quicksort using the middle element as pivot.
	 * @param a
	 * @param l
	 * @param r
	 */
	public static void QuicksortMid(int[] a, int l, int r) {

		if (r > 1) {
			int p = PivotMid(a, l, r);
			if (l < p - 1) {
				QuicksortMid(a, l, p - 1);
			}
			if (p < r) {
				QuicksortMid(a, p + 1, r);
			}
		}
	}

	/**
	 * Simple method that calls the quicksort method, takes an array as a single argument.
	 * @param a
	 */
	public static void QuicksortMid(int[] a) {
		QuicksortMid(a, 0, a.length - 1);
	}
}
