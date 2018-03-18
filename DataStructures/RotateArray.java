/**
 * Rotate an array of size n by d elements
 * 
 * problem source : gfg.org
 */
public class RotateArray {
	
	//test bench
	public static void main(String[] args) {
		// sample input
		int[] inputArr1 = { 1, 2, 3, 4, 5, 6, 7 };
		int[] inputArr2 = { 11, 21, 43, 24, 53, 66, 117 };
		RotateArray rotate = new RotateArray();

		// test approach 1
		rotate.tempArrSolution(inputArr1, inputArr1.length, 3);
		printArray(inputArr1);

		rotate.tempArrSolution(inputArr2, inputArr2.length, 4);
		printArray(inputArr2);

		// test single rotatiom approach
		rotate.singleRotationSolution(inputArr1, inputArr1.length, 4);
		printArray(inputArr1);

		// test Jump/Juggle approach
		rotate.jugglingAlgo(inputArr1, inputArr1.length, 4);
		printArray(inputArr1);
	}

	/**
	 * solve by creating a temp array of size d.
	 * copy first d elements to temp.
	 * shift remaining elements to beginning
	 * copy temp array to the end
	 * Time = O(n) , Space = O(d)
	 */
	public void tempArrSolution(int[] arr, int n, int d) {
		int[] temp = new int[d];
		for( int i = 0; i<d; i++) {
			temp[i] = arr[i];
		}
		for(int j=d; j<n; j++) {
			arr[j-d] = arr[j];
		}
		for(int k=n-d; k<n; k++) {
			arr[k] = temp[k+d-n];
		}
	}

	/**
	 * solve by rotating one element to the left 
	 * at time for d times
	 * Time = O(n*d) , Space = O(1)
	 */
	public void singleRotationSolution(int[] arr, int n, int d) {
		for(int i = 0; i < d; i++) {
			rotateLeftByOne(arr);
		}
	}


	/**
	 * Instead of rotating one by one - divide the array in different sets
	 * where number of sets is equal to GCD
	 * Time = O(n) , Space = O(1)
	 */
	public void jugglingAlgo(int[] arr, int n, int d){
		int temp, current, jumpNext;
		for(int i = 0; i < gcd(d,n); i++) {
			temp = arr[i];
			current = i;
			while (true) {
				jumpNext = current + d;
				//comeback to front of array if needed
				if (jumpNext >= n) 
					jumpNext = jumpNext -n;
				// if a single iteration is complete - break
				if (jumpNext == i)
					break;
				arr[current] = arr[jumpNext];
				current = jumpNext;
			}
			arr[current] = temp;
		}
	}

	public int gcd(int a, int b) {
		if (b==0)
			return a;
		else
			return gcd(b, a%b); 
	}


	// ------ Helper Methods--------------

	/**
	 * rotate array to left by one position
	 */
	public void rotateLeftByOne(int[] arr) {
		int temp = arr[0];

		for(int i = 0; i < arr.length-1; i++) {
			arr[i] = arr[i+1];
		}

		arr[arr.length-1] = temp;
	}

	/**
	 * print array
	 */
	public static void printArray(int[] arr) {
		for(int item : arr) {
			System.out.println(item + " ");
		}
		System.out.println("----------- ");
	}

}