package Parallel;

import static java.lang.System.*;

import java.util.Arrays;
import java.util.Random;

public class Main{
	public static void main(String args[]){
	     // Test Q1 implementation

		 //int A[] = {42, 2, 9, 25, 49, 49, 121, 100, -10, 14, 33, 47, 85, -5, 4, -7};
		 int A[] = getArray(15);
		 out.println(Arrays.toString(A));
		 PSort.parallelSort(A, 0, A.length);
		 
		 out.println(Arrays.toString(A));
		 out.println();
		
		 // Test Q2 implementation with 3 threads
	/*	 int result = PSearch.parallelSearch(20, A, 3);
		 out.println(result);
		 // should return -1 as 20 is not in array
	*/	 // ... verification code --- written by us. 
		 // (you do not need to write this code)
	}
	
	public static int[] getArray(int size) {
		Random gen = new Random();
		int[] array = new int[size];
		int item = 0;
		
		for (int i = 0; i < size; i++) {
			item = gen.nextInt(51) - 25;
			array[i] = item;
		}
		return array;
	}
}