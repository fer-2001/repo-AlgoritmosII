package util;

/**
 * Sorts arrays of comparable objects using a variety of options.
 * 
 * @author aguirre
 *
 * @param <E> is the type of the elements of the array.
 */
public class ArraySorter<E extends Comparable<E>> {

	/**
	 * The array to sort.
	 */
	private E[] array;
	
	/**
	 * The algorithm to use for sorting.
	 */
	private SortAlgorithm algorithm = SortAlgorithm.SELECTIONSORT;

	/**
	 * Default constructor. Sets the array to sort and sorting algorithm to INSERTION SORT.
	 * @param array is the array to sort.
	 */
	public ArraySorter(E[] array) {
		if (array == null) throw new IllegalArgumentException("array must be non-null");
		this.array = array;
	}

	/**
	 * Constructor that sets array and sorting algorithm.
	 * @param array is the array to sort.
	 * @param algorithm is the algorithm to use for sorting.
	 */
	public ArraySorter(E[] array, SortAlgorithm algorithm) {
		if (array == null) throw new IllegalArgumentException("array must be non-null");
		this.array = array;
		this.algorithm = algorithm;
	}

	/**
	 * Sets the algorithm to use for sorting.
	 * @param algorithm is the algorithm to set for sorting.
	 */
	public void setAlgorithm(SortAlgorithm algorithm) {
		if (algorithm == null) throw new IllegalArgumentException("algorithm can't be null");
		this.algorithm = algorithm;
	}
	
	/**
	 * Retrieves the (sorted or yet unsorted) array within the ArraySorter.
	 * @return the array stored within the ArraySorter object.
	 */
	public E[] getArray() {
		return this.array;
	}

	/**
	 * Sets the array to be sorted.
	 * @param array is the new array to sort.
	 */
	public void setArray(E[] array) {
		//throw new UnsupportedOperationException("method not yet implemented");
		this.array = array.clone();
	}

	/**
	 * Sorts the array.
	 * The array can then be retrieved using getArray() method.
	 */
	public void sort() {
		switch (this.algorithm) {
		case INSERTIONSORT: 	
			insertionSort(array); 
			break;
		case BUBBLESORT:
			bubbleSort(array); 
			break;
		case SELECTIONSORT:
			selectionSort(array); 
			break;
		default:
            throw new UnsupportedOperationException("sorting method not yet implemented"); 
		}	
	}

	/**
	 * Sorts an array. Implements the insertion sort algorithm.
	 * @param <T> is the type of the elements in the array.
	 * @param array is the array to be sorted.
	 */
	private static <T extends Comparable<T>> void insertionSort(T[] array) {
		if (array == null) throw new IllegalArgumentException("array is null, can't sort");
		for ( int unsorted = 1; unsorted < array.length; unsorted++){
			T key = array[unsorted];
			int loc = unsorted-1;
			while ((loc >= 0) && (array[loc].compareTo(key) > 0)){
				array[loc+1] = array[loc];
				loc--;
			}
			array[loc+1] = key ;
		}
	}

	/**
	 * Sorts an array. Implements the selection sort algorithm.
	 * @param <T> is the type of the elements in the array.
	 * @param array is the array to be sorted.
	 */
	private static <T extends Comparable<T>> void selectionSort(T[] array) {
		if (array == null) throw new IllegalArgumentException("array is null, can't sort");
		for(int last = array.length-1; last>0; last--){
			int largest = indexOfLargest(array, last+1);
			T swap = array[last];
			array[last] = array[largest];
			array[largest]=swap;

		}
	}
	private static <T extends Comparable<T>> int indexOfLargest(T[] array, int n){
		int largest = 0;
		for(int i=1; i<n; i++) {
			if(array[i].compareTo(array[largest]) > 0){
				largest = i;
			}
		}
		return  largest;
	}
	/**
	 * Sorts an array. Implements the bubblesort sort algorithm.
	 * @param <T> is the type of the elements in the array.
	 * @param array is the array to be sorted.
	 */
	private static <T extends Comparable<T>> void bubbleSort(T[] array) {
		if (array == null) throw new IllegalArgumentException("array is null, can't sort");
		int n = array.length;
		boolean sorted = false;
		// I representa el numero de pasada
		for (int i = 1; i < n && !sorted; ++i) {
			sorted = true;
			// J representa el indice con el que estamos trabajando (haciendo intercambios)
			for (int j = 0; j < n-i; ++j) {
				if (array[j].compareTo(array[j + 1]) > 0) {
					T aux = array[j + 1];
					array[j + 1] = array[j];
					array[j] = aux;
					sorted = false;
				}
			}
		}
	}

	/**
	 * Checks if a given array is sorted.
	 * @param <T> is the type of the elements in the array.
	 * @param array is the array to be checked for sortedness.
	 * @return true iff the array is sorted.
	 */
	public static <T extends Comparable<T>> boolean isSorted(T[] array) {
		for(int i=0; i<array.length-1; i++){
			if((array[i].compareTo(array[i+1])) > 0){
				return false;
			}
			return true;
		}
		return  true;
	}

}
