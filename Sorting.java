public void crawlAndIndex(String url) throws Exception {
    //written code
    ArrayList<String> toVisit = new ArrayList<>();  //list of urls to visit
    ArrayList<String> visited = new ArrayList<>(); //visited urls (to prevent cycle problems)
    toVisit.add(url); //add the url to visit

    while (!toVisit.isEmpty()) { //While there are still elements to visit
        String current = toVisit.remove(0);
        if (visited.contains(current)) {
            //skip if the url has already been visited
            continue;
        }
        visited.add(current); //mark current url as visited

        internet.addVertex(current); //add url to graph

        ArrayList<String> links = parser.getLinks(current);  // Retrieve all links from the current URL
        for (String link : links) {
            internet.addVertex(link);  // Ensure link is also added as a vertex before making an edge
            internet.addEdge(current, link);  // Add edges to the graph
            if (!visited.contains(link) && !toVisit.contains(link)) {
                toVisit.add(link);  // Add to visit if not already visited or in the list
            }
        }
        ArrayList<String> content = parser.getContent(current);  // Retrieve content of the URL
        for (String word : content) {
            String normalizedWord = word.toLowerCase();  // Normalize the word to lower case
            //wordIndex.putIfAbsent(normalizedWord, new ArrayList<>());

            if(!wordIndex.containsKey(normalizedWord)) {
                wordIndex.put(normalizedWord,new ArrayList<>());
            }
            if (!wordIndex.get(normalizedWord).contains(current)) {
                wordIndex.get(normalizedWord).add(current);  // Add URL to the index if not already added
            }
        }
    }
}