public void crawlAndIndex(String url) throws Exception {
    // HashMap to keep track of which urls have been visited
    HashMap<String, Boolean> visited = new HashMap<>();
    // ArrayList to keep track of vertices added to the web graph
    ArrayList<String> verticesAdded = new ArrayList<>();
    // Perform depth-first crawling
    crawlDepthFirst(url, visited, verticesAdded);
}

// Helper methods
private void crawlDepthFirst(String url, HashMap<String, Boolean> visited, ArrayList<String> verticesAdded) throws Exception {
    // Mark the current URL as visited
    visited.put(url, true);
    // Get content inside the current URL through the XmlParser class
    ArrayList<String> urlContent = parser.getContent(url);
    // Update the word index HashMap with the content
    updateWordIndex(url, urlContent);
    // Add the URL as a vertex to the web graph if it hasn't been added before
    if (!verticesAdded.contains(url)) {
        internet.addVertex(url);
        verticesAdded.add(url);
    }
    // Get the links from the current webpage
    ArrayList<String> links = parser.getLinks(url);
    // Depth-first traversal
    for (String neighbor : links) {
        // If the neighbor has not been visited, recursively crawl it
        if (!visited.containsKey(neighbor) || !visited.get(neighbor)) {
            crawlDepthFirst(neighbor, visited, verticesAdded);
        }
        // Add an edge between the current URL and its neighbor
        internet.addEdge(url, neighbor);
    }
}

private void updateWordIndex(String url, ArrayList<String> urlContent) {
    // Iterate through every word in the URL content
    for (String word : urlContent) {
        word = word.toLowerCase();
        // If the word hasn't been seen before, add it with a new ArrayList
        if (!wordIndex.containsKey(word)) {
            wordIndex.put(word, new ArrayList<>());
        }
        // Add the URL to the list of URLs associated with the word if it's not already present
        ArrayList<String> urls = wordIndex.get(word);
        if (!urls.contains(url)) {
            urls.add(url);
        }
    }
}