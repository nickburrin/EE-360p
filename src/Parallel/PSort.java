package Parallel; 

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * The PSort class which uses parallel processes to sort and array.
 * @author Nick Burrin and Ryan Templin
 *
 */
public class PSort implements Runnable {
	public static ExecutorService threadPool = Executors.newCachedThreadPool();
	private static int[] array;
	int low;
	int high;

	PSort(int a[], int begin, int end) {
		array = a;
		low = begin;
		high = end;
	}
	
	/**
	 * Partitions the array A and then creates two new threads 
	 * to sort the sub-arrays created by the partition. Controls
	 * the threads using an Executor Service.
	 * 
	 * @param A
	 * @param begin
	 * @param end
	 */
	public static void parallelSort(int A[], int begin, int end) {

		if((end - begin) <= 1)
			return;
	
		int partition = partition(A, begin, end);
		
		Future<?> f1 = threadPool.submit(new PSort(A, begin, partition));
		Future<?> f2 = threadPool.submit(new PSort(A, partition + 1, end));

		
		try{
			f1.get();
			f2.get();
		} catch(Exception e){}	

		
		threadPool.shutdown();
		
	}

	/**
	 * Rearranges the elements of array A so that everything to the 
	 * left of the pivot elements is less than pivot and everything 
	 * to the right is greater than pivot.
	 * 
	 * @param A
	 * @param left
	 * @param right
	 * @param pivot
	 * @return
	 */
	private static int partition(int A[], int left, int right) {
		right -= 1;
		int pivot = A[right];
		int leftCursor = left - 1;
		int rightCursor = right;
		while (leftCursor < rightCursor) {
			while (A[++leftCursor] < pivot);
				
			while (rightCursor > 0 && (A[--rightCursor] > pivot));
			
			if (leftCursor >= rightCursor)
				break;
			else
				swap(A, leftCursor, rightCursor);
		}
		
		swap(A, leftCursor, right);
		return leftCursor;
	}

	/**
	 * Swaps the elements of the provided array at indexes left and right
	 * 
	 * @param A
	 * @param left
	 * @param right
	 */
	public static void swap(int A[], int left, int right) {
		int temp = A[left];
		A[left] = A[right];
		A[right] = temp;
	}

	/**
	 * Called by the thread which creates the new PSort object
	 */
	public void run() {
		parallelSort(this.array, this.low, this.high);
	}
}