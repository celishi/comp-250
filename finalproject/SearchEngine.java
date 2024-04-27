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
		internet.addVertex(url);
		internet.setVisited(url, true);
		internet.setPageRank(url, 1);

		ArrayList<String> words = parser.getContent(url);
		for(String word: words){
			word.toLowerCase();

			if(!wordIndex.containsKey(word)){
				wordIndex.put(word, new ArrayList<>());
			}
			if(!wordIndex.get(word).contains(url)){
				wordIndex.get(word).add(url);
			}
		}

		for(String neighbour: parser.getLinks(url)){
			if(!internet.getVertices().contains(neighbour) || !internet.getVisited(neighbour)){ // do we need !internet.getVisited(neighbour)?
				crawlAndIndex(neighbour);
				internet.addEdge(neighbour, url);
			}
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

			// doing ArrayList<Double> current = new ArrayList<>(); doesnt work for some reason
			// i keep getting cpu running for too long and i dont know why, even thought this code
			// does the exact same thing as the function computeRanks(vertices)
			ArrayList<Double> current = computeRanks(vertices);
			// ArrayList<Double> current = new ArrayList<>();
			// for(String url: vertices){
			// 	double rank = 0.0;
			// 	for(String vertex: internet.getEdgesInto(url)){
			// 		rank += internet.getPageRank(vertex)/internet.getOutDegree(vertex);
			// 	}
			// 	rank = 0.5 + 0.5*rank;
			// 	internet.setPageRank(url, rank);
			// 	current.add(rank);
			// }

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
		ArrayList<String> urls = wordIndex.getOrDefault(query.toLowerCase(), new ArrayList<>());
		HashMap<String, Double> list = new HashMap<>();

		for(String url: urls){
			list.put(url, internet.getPageRank(url));
		}

		ArrayList<String> sorted = Sorting.fastSort(list);
		
		return sorted;
	}
}
