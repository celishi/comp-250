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
    	ArrayList<K> sortedUrls = new ArrayList<>();
		sortedUrls.addAll(results.keySet());
		sortedUrls = mergeSort(sortedUrls, results);
    	return sortedUrls;
    }

	//implementation from lecture 17
	private static <K, V extends Comparable<V>> ArrayList<K> mergeSort(ArrayList<K> list, HashMap<K, V> results){
		if(list.size() <= 1){
			return list;
		}
		else{
			int mid = (list.size() - 1)/2;
			ArrayList<K> list1 = new ArrayList<>(list.subList(0, mid));
			ArrayList<K> list2= new ArrayList<>(list.subList(mid+1, list.size()-1));
			list1 = mergeSort(list1, results);
			list2 = mergeSort(list2, results);
			return merge(list1, list2, results);
		}
	}

	private static <K, V extends Comparable<V>> ArrayList<K> merge(ArrayList<K> list1, ArrayList<K> list2, HashMap<K, V> results){
		ArrayList<K> list = new ArrayList<>();
		int i = 0, j = 0, k = 0;
        while (i < list1.size() && j < list2.size()) {
            if (results.get(list1.get(i)).compareTo(results.get(list2.get(j))) >= 0) {
                list.set(k, list1.get(i));
            } else {
                list.set(k, list2.get(j));
            }
			i++;
			j++;
			k++;
        }

        while (i < list1.size()) {
            list.set(k, list1.get(i));
			i++;
			k++;
        }
        while (j < list2.size()) {
            list.set(k, list2.get(j));
			j++;
			k++;
        }

		return list;
	}    

}