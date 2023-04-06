//Bellman Ford algorithm to find shortest distance for single source with support for -ve weights
//Average Case Complexity	O(VE)
//the space complexity is O(V).

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
    
    public void bellmanFord(createGraph graph, int src){
        int V = graph.v, E = graph.e;
        
        //maintain dist array
        int[] dist = new int[V];
        dist[src] = 0;
        
        //Fill with max value for remaining vertices
        for(int i = 1; i < graph.v; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        
        // for |v-1| vertices do the loop
        for(int i = 1; i < V; i++){
            //each time go through all edges and check condition
            for(int j = 0; j < E; j++){
                int srcVertex = graph.edges[j].s;
                int destVertex = graph.edges[j].d;
                int weightOfEdge = graph.edges[j].w;
                
                //if distance of dest vertex can be smaller than the previous one, store that
                if( dist[destVertex] > dist[srcVertex] + weightOfEdge){
                    dist[destVertex] = dist[srcVertex] + weightOfEdge;
                }
            }
        }
        
        // Go through all edges one last time, if the distance still reduces for any edge, it means there is a -ve cycle
        for(int i =0; i < E; i++){
             int srcVertex = graph.edges[i].s;
            int destVertex = graph.edges[i].d;
            int weightOfEdge = graph.edges[i].w;
            
            if( dist[destVertex] > dist[srcVertex] + weightOfEdge ){
                System.out.println("Contains Negative Cycle..terminating");
                return;
            }
        }
        
        printSolution(dist,V);
        
    }
    
    public void printSolution(int[] dist, int V){
        System.out.println("Vertex Distance from Source");
    for (int i = 0; i < V-1; ++i)
      System.out.println(i + "\t\t" + dist[i]);
    }
        
    
    public static void main(String[] args) {
      createGraph graph  = new createGraph(5, 8);
       // edge 0 --> 1
    graph.edges[0].s = 0;
    graph.edges[0].d = 1;
    graph.edges[0].w = 4;

    // edge 0 --> 2
    graph.edges[1].s = 0;
    graph.edges[1].d = 2;
    graph.edges[1].w = 5;

    // edge 1 --> 3
    graph.edges[2].s = 1;
    graph.edges[2].d = 2;
    graph.edges[2].w = 5;

    // edge 2 --> 1
    graph.edges[3].s = 2;
    graph.edges[3].d = 3;
    graph.edges[3].w = 3;

    // edge 3 --> 2
    graph.edges[4].s = 3;
    graph.edges[4].d = 1;
    graph.edges[4].w = -10;
    
    graph.bellmanFord(graph, 0);
}

}