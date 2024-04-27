package finalproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry; // You may (or may not) need it to implement fastSort

public class Sorting {

	/*
	 * This method takes as input an HashMap with values that are Comparable. 
	 * It returns an ArrayList containing all the keys from the map, ordered 
	 * in descending order based on the values they mapped to. 
	 * 
	 * The time complexity for this method is O(n^2) as it uses bubble sort, where n is the number 
	 * of pairs in the map. 
	 */
    public static <K, V extends Comparable<V>> ArrayList<K> slowSort (HashMap<K, V> results) {
        ArrayList<K> sortedUrls = new ArrayList<K>();
        sortedUrls.addAll(results.keySet());	//Start with unsorted list of urls

        int N = sortedUrls.size();
        for(int i=0; i<N-1; i++){
			for(int j=0; j<N-i-1; j++){
				if(results.get(sortedUrls.get(j)).compareTo(results.get(sortedUrls.get(j+1))) < 0){
					K temp = sortedUrls.get(j);
					sortedUrls.set(j, sortedUrls.get(j+1));
					sortedUrls.set(j+1, temp);					
				}
			}
        }
        return sortedUrls;                    
    }
    
    
	/*
	 * This method takes as input an HashMap with values that are Comparable. 
	 * It returns an ArrayList containing all the keys from the map, ordered 
	 * in descending order based on the values they mapped to. 
	 * 
	 * The time complexity for this method is O(n*log(n)), where n is the number 
	 * of pairs in the map. 
	 */
	// public static <K, V extends Comparable<V>> ArrayList<K> fastSort(HashMap<K, V> results) {
    //     ArrayList<K> urls = new ArrayList<K>();
	// 	K[] keysArray = urls.toArray((K[]) new Object[urls.size()]);

	// 	mergeSort(keysArray, results, 0, keysArray.length - 1);
	// 	urls.clear();
    //     urls.addAll(Arrays.asList(keysArray));

    //     return urls;                    
    // }
 
    // private static <K, V extends Comparable<V>> void mergeSort(K[] keys, HashMap<K, V> results, int start, int end) {
	// 	if(start < end){
	// 		int mid = start + (start - end) / 2;
	// 		mergeSort(keys, results, start, mid);
	// 		mergeSort(keys, results, mid + 1, end);
	// 		merge(keys, results, start, mid, end);
	// 	}
    // }

    // private static <K, V extends Comparable<V>> void merge(K[] keys, HashMap<K, V> results, int start, int mid, int end) {
    //     K[] temp = Arrays.copyOf(keys, keys.length);
	// 	int i = 0, j = 0, k = 0;

    //     while (i < mid && j < end) {
    //         if (results.get(temp[i]).compareTo(results.get(temp[j])) >= 0) {
    //             keys[k++] = temp[i++];
    //         } else {
    //             keys[k++] = temp[i++];
    //         }
    //     }

    //     while (i <= mid) {
    //         keys[k++] = temp[i++];
    //     }
    //     while (j <= end) {
    //         keys[k++] = temp[i++];
    //     }
    // }
	public static <K, V extends Comparable<V>> ArrayList<K> fastSort(HashMap<K, V> results) {
		ArrayList<K> sortedUrls = new ArrayList<>(results.keySet()); // Start with unsorted list of urls
		K[] keysArray = sortedUrls.toArray((K[]) new Object[sortedUrls.size()]); // Convert ArrayList to array
		mergeSort(keysArray, results, 0, keysArray.length - 1); // Sort the array
		sortedUrls.clear(); // Clear the existing list
		sortedUrls.addAll(Arrays.asList(keysArray)); // Add sorted keys back to the list
		return sortedUrls;                    
	}
	
	private static <K, V extends Comparable<V>> void mergeSort(K[] keys, HashMap<K, V> results, int low, int high) {
		if (low < high) {
			int mid = low + (high - low) / 2;
			mergeSort(keys, results, low, mid); // Sort left half
			mergeSort(keys, results, mid + 1, high); // Sort right half
			merge(keys, results, low, mid, high); // Merge the sorted halves
		}
	}
	
	private static <K, V extends Comparable<V>> void merge(K[] keys, HashMap<K, V> results, int low, int mid, int high) {
		K[] temp = Arrays.copyOf(keys, keys.length);
	
		int i = low, j = mid + 1, k = low;
		while (i <= mid && j <= high) {
			if (results.get(temp[i]).compareTo(results.get(temp[j])) <= 0) {
				keys[k++] = temp[i++];
			} else {
				keys[k++] = temp[j++];
			}
		}
		while (i <= mid) {
			keys[k++] = temp[i++];
		}
		while (j <= high) {
			keys[k++] = temp[j++];
		}
	}
	
}