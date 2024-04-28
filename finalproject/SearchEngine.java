package finalproject;

import java.util.HashMap;
import java.util.ArrayList;

public class SearchEngine {
	public HashMap<String, ArrayList<String>> wordIndex;   // this will contain a set of pairs (String, ArrayList of Strings)	
	public MyWebGraph internet;
	public XmlParser parser;

	public SearchEngine(String filename) throws Exception{
		this.wordIndex = new HashMap<String, ArrayList<String>>();
		this.internet = new MyWebGraph();
		this.parser = new XmlParser(filename);
	}
	
	/* 
	 * This does an exploration of the web, starting at the given url.
	 * For each new page seen, it updates the wordIndex, the web graph,
	 * and the set of visited vertices.
	 * 
	 * 	This method will fit in about 30-50 lines (or less)
	 */
	public void crawlAndIndex(String url) throws Exception {
		if(!internet.getVertices().contains(url)){
			internet.addVertex(url);
		}
		internet.setVisited(url, true);
		internet.setPageRank(url, 1);//set to 1 for computing ranks

		ArrayList<String> words = parser.getContent(url);
		for(String word: words){
			word = word.toLowerCase();

			if(!wordIndex.containsKey(word)){
				wordIndex.put(word, new ArrayList<>());
			}
			if(!wordIndex.get(word).contains(url)){
				wordIndex.get(word).add(url);
			}
		}

		ArrayList<String> links = parser.getLinks(url);
		for(String neighbour: links){
			if(!internet.getVisited(neighbour)){ // do we need !internet.getVisited(neighbour)?
				crawlAndIndex(neighbour);
			}
			internet.addEdge(url, neighbour);
		}

	}
	
	/* 
	 * This computes the pageRanks for every vertex in the web graph.
	 * It will only be called after the graph has been constructed using
	 * crawlAndIndex(). 
	 * To implement this method, refer to the algorithm described in the 
	 * assignment pdf. 
	 * 
	 * This method will probably fit in about 30 lines.
	 */
	public void assignPageRanks(double epsilon) {
		ArrayList<String> vertices = internet.getVertices();
		boolean converged = false;

		while(!converged){
			ArrayList<Double> previous = new ArrayList<>();
			for(String url: vertices){
				previous.add(internet.getPageRank(url));
			}

			ArrayList<Double> current = computeRanks(vertices);

			converged = true;
			for(int i=0; i<vertices.size(); i++){
				double diff = Math.abs(previous.get(i) - current.get(i));
				if(diff >= epsilon){
					converged = false;
					break;
				}
			}
		}
	}
	
	/*
	 * The method takes as input an ArrayList<String> representing the urls in the web graph 
	 * and returns an ArrayList<double> representing the newly computed ranks for those urls. 
	 * Note that the double in the output list is matched to the url in the input list using 
	 * their position in the list.
	 * 
	 * This method will probably fit in about 20 lines.
	 */
	public ArrayList<Double> computeRanks(ArrayList<String> vertices) {
		ArrayList<Double> list = new ArrayList<>();

		for(String url: vertices){
			double rank = 0.5 + 0.5*sumOutVertices(url);
			list.add(rank);
		}

		for(int i = 0; i<vertices.size(); i++){
			internet.setPageRank(vertices.get(i), list.get(i));
		}

		return list;
	}

	private double sumOutVertices(String url){
		Double rank = 0.0;
		for(String vertex: internet.getEdgesInto(url)){
			rank += internet.getPageRank(vertex)/internet.getOutDegree(vertex);
		}
		return rank;
	}

	/* Returns a list of urls containing the query, ordered by rank
	 * Returns an empty list if no web site contains the query.
	 * 
	 * This method will probably fit in about 10-15 lines.
	 */
	public ArrayList<String> getResults(String query) {	
		ArrayList<String> urls;

		if(wordIndex.containsKey(query.toLowerCase())) urls = wordIndex.get(query.toLowerCase());
		else {
			urls = new ArrayList<>();
		}

		HashMap<String, Double> list = new HashMap<>();
		for(String url: urls){
			list.put(url, internet.getPageRank(url));
		}

		ArrayList<String> sorted = Sorting.fastSort(list);
		
		return sorted;
	}
}
