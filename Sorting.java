// package finalproject;

import java.util.ArrayList;
import java.util.HashMap;

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
        sortedUrls.addAll(results.keySet());    //Start with unsorted list of urls

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
    private static <K,V extends Comparable<V>> void mergeSort(K[] keys, K[] temp,HashMap<K, V> entry, int low, int high) {
        if (high <= low) {
            return;
        }
        else {
            int middle = low + (high - low) / 2 ;
            mergeSort(keys,temp,entry,low,middle);
            mergeSort(keys,temp,entry,middle+1,high);
            merge(keys, temp, entry, low, middle, high);
        }
    }
    private static <K, V extends Comparable<V>> void merge(K[] keys, K[] temp, HashMap<K, V> entry, int low, int mid, int high) {
        // Copy to temporary array
        for (int k = low; k <= high; k++) {
            temp[k] = keys[k];
        }
        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) keys[k] = temp[j++];
            else if (j > high) keys[k] = temp[i++];
            else if (entry.get(temp[j]).compareTo(entry.get(temp[i])) > 0) {
                keys[k] = temp[j++];
            }
            else keys[k] = temp[i++];
        }
    }
    public static <K, V extends Comparable<V>> ArrayList<K> fastSort(HashMap<K, V> results) {
        //written code

        // Create an array of entries from the HashMap
        ArrayList<K> keys = new ArrayList<>(results.keySet());
        K[] keysArray = (K[]) new Object[keys.size()];
        keys.toArray(keysArray);

        K[] temp = (K[]) new Object[keys.size()];

        mergeSort(keysArray, temp, results, 0, keysArray.length - 1);

        // Return the sorted keys
        ArrayList<K> sortedKeys = new ArrayList<>();
        for (K key : keysArray) {
            sortedKeys.add(key);
        }
        return sortedKeys;
    }


}