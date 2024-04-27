package finalproject;

import java.util.ArrayList;
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
    public static <K, V extends Comparable<V>> ArrayList<K> fastSort(HashMap<K, V> results) {
        ArrayList<K> urls = new ArrayList<K>();
		urls.addAll(results.keySet());

		mergeSort(urls, results, 0, urls.size() - 1);

        return urls;                    
    }
 
    private static <K, V extends Comparable<V>> void mergeSort(ArrayList<K> keys, HashMap<K, V> results, int start, int end) {
		if(end <= start) return;

		int mid = (start + end) / 2;
		mergeSort(keys, results, start, mid);
		mergeSort(keys, results, mid + 1, end);
		merge(keys, results, start, mid, end);
    }

    private static <K, V extends Comparable<V>> void merge(ArrayList<K> keys, HashMap<K, V> results, int start, int mid, int end) {
        ArrayList<K> temp = new ArrayList<>();
		int i = start;
		int j = mid + 1;

        while (i <= mid && j <= end) {
            if (results.get(keys.get(i)).compareTo(results.get(keys.get(j))) <= 0) {
                temp.add(keys.get(j++));
            } else {
                temp.add(keys.get(i++));
            }
        }

        while (i <= mid) {
            temp.add(keys.get(i++));
        }
        while (j <= end) {
			temp.add(keys.get(j++));
        }

		for (int k = start; k <= end; k++) {
			keys.set(k, temp.get(k - start));
		}
    }	
}