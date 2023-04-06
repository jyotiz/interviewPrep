//Dijkstras algorithm to find shortest path
/*Next we’ll calculate the time complexity using a Fibonacci heap. The Fibonacci heap allows us to insert a new element in O(1) and extract the node with minimal dist in O(log|V). Therefore, the time complexity will be:

The time taken for each iteration of the loop and extract-min is O(|V|), as one vertex is removed from Q per loop.
Iterating over all vertices’ neighbors and updating their dist values for a run of the algorithm takes O(|E|) time. Since each priority value update takes O(log|V|) time, the total of all dist calculation and priority value updates takes O(|E| \times log|V|) time.
So the overall time complexity becomes O(|V| + |E| \times log|V|).*/



class createGraph {
    
    // create edge class to have src, dest and weight for edge
    class createEdge{
        int s,d,w;
        createEdge(){
            s = d = w = 0;
        }
    };
    
    private int v;
    private int e;
    
    //graphh will have array of edges
    private createEdge[] edges;
    
    createGraph(int V, int E){
        this.v = V;
        this.e = E;
        this.edges = new createEdge[e];
        
        //Init every edge with s,d,w
        for(int i = 0; i < e; i++){
            edges[i] = new createEdge();
        }
    }
    
    public void dijkstrasAlgo(createGraph graph, int src){
        int V = graph.v, E = graph.e;
        
        //maintain dist array
        int[] dist = new int[V];
        boolean[] visited = new boolean[V];
        dist[src] = 0;
        
        //Fill with max value for remaining vertices
        for(int i = 1; i < graph.v; i++){
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        
        // for all| vertices do the loop
        for(int i = 0; i < V; i++){
           
            //Find the shortest distance node
            int u = findMinDistanceNode(dist, visited);
            //Mark it visited
            visited[u] = true;
            //Go through all edges and find shortest distance neigbors and mark them visited
            for(int j = 0; j < E; j++){
                int srcVertex = graph.edges[j].s;
                int destVertex = graph.edges[j].d;
                int weightOfEdge = graph.edges[j].w;
               
                //if distance of dest vertex can be smaller than the previous one, store that
                if( dist[destVertex] > dist[srcVertex] + weightOfEdge && !visited[destVertex] && srcVertex == u){
                    
                    dist[destVertex] = dist[srcVertex] + weightOfEdge;
                }
            }
        }
        
        printSolution(dist,src);
        
    }
    
    public int findMinDistanceNode(int[] distance, boolean[] visited){
        int minDistance = Integer.MAX_VALUE;
        int minDistanceVertex = -1;
        
        for(int i = 0; i < distance.length; i++){
            if(distance[i] < minDistance && !visited[i]){
                minDistance= distance[i];
                minDistanceVertex = i;
            }
        }
        return minDistanceVertex;
        
    }
    
    public void printSolution(int[] dist, int source){
     for (int i = 0; i < dist.length; i++) {
      System.out.println(String.format("Distance from %s to %s is %s", source, i, dist[i]));
    }
    }
        
    
    public static void main(String[] args) {
      createGraph graph  = new createGraph(6, 8);
       // edge 0 --> 1
    graph.edges[0].s = 0;
    graph.edges[0].d = 1;
    graph.edges[0].w = 10;

    // edge 0 --> 2
    graph.edges[1].s = 0;
    graph.edges[1].d = 2;
    graph.edges[1].w = 15;

    // edge 1 --> 3
    graph.edges[2].s = 1;
    graph.edges[2].d = 3;
    graph.edges[2].w = 12;

    // edge 2 --> 1
    graph.edges[3].s = 2;
    graph.edges[3].d = 4;
    graph.edges[3].w = 10;
    
    graph.edges[4].s = 3;
    graph.edges[4].d = 4;
    graph.edges[4].w = 2;

    // edge 3 --> 2
    graph.edges[5].s = 1;
    graph.edges[5].d = 5;
    graph.edges[5].w = 15;
    
    graph.edges[6].s = 3;
    graph.edges[6].d = 5;
    graph.edges[6].w = 1;
    
    graph.edges[7].s = 5;
    graph.edges[7].d = 4;
    graph.edges[7].w = 5;
    
    graph.dijkstrasAlgo(graph, 0);
}

}