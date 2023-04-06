/* Time Complexity: O( V + E logV ) .

Space Complexity: O(V+ E)*/
import java.util.*;
public class Graph{
    
    private int V;
    private int distance[];
    private PriorityQueue<Node> pq;
    private Set<Integer> settled;
    private List<List<Node>> adj;
    
    Graph(int v){
        this.V = v;
        distance = new int[v];
        pq = new PriorityQueue<Node>(v, new Node());
        settled = new HashSet<Integer>();
    }
     
     public void dijkstrasAlgo( List<List<Node>> adj, int src){
         this.adj = adj;
         
         //Init max distances
         for(int  i =0; i < V; i++){
             distance[i] = Integer.MAX_VALUE;
         }
         
         //Add src node to Q with cost as 0
         pq.add(new Node(src, 0));
         distance[src] = 0;
         
         //Keep populating hashset and settle the nodes one by one
         while( settled.size() != V){
             
             if(pq.isEmpty()) return;
             //Take the smallest distance node
             int u = pq.remove().node;
             
             //If already there skip
             if(settled.contains(u))
                continue;
            //If not add in hashset and find neighbors
            settled.add(u);
            findNeighbours(u);
            
            //Every time we add nodes in PQ, smallest node is picked and added to hashset if not already, then we find its neighbors and in Q and next time we again choose the smallest neighbor and keep doing this until hashset is full
         }
     }
     
     //
     public void findNeighbours(int u){
         int edgeDistance = -1;
         int newDistance = -1;
         for(int i = 0; i < adj.get(u).size(); i++){
             Node v = adj.get(u).get(i);
             //If neighbor is not in hashset
             if(!settled.contains(v.node)){
                 //calculate smallest distance and update
                  edgeDistance = v.cost;
                  newDistance = edgeDistance + distance[u];
                  
                  if(newDistance < distance[v.node]){
                      distance[v.node] = newDistance;
                  }
                  
                  //Add in PQ
                  pq.add(new Node(v.node, distance[v.node]));
             }
         }
     }
     
     
     public static void main(String []args){
       int V = 5;
       int src = 0;
       
       List<List<Node>> adj =  new ArrayList<List<Node>>();
       for(int i = 0; i < V; i++){
           List<Node> item = new ArrayList<>();
           adj.add(item);
       }
       
        adj.get(0).add(new Node(1, 9));
        adj.get(0).add(new Node(2, 6));
        adj.get(0).add(new Node(3, 5));
        adj.get(0).add(new Node(4, 3));
 
        adj.get(2).add(new Node(1, 2));
        adj.get(2).add(new Node(3, 4));
        
          Graph dikstra = new Graph(V);
        dikstra.dijkstrasAlgo(adj, src);
 
        // Printing the shortest path to all the nodes
        // from the source node
        System.out.println("The shorted path from node :");
 
        for (int i = 0; i < dikstra.distance.length; i++)
            System.out.println(src + " to " + i + " is "
                               + dikstra.distance[i]);
     }
     
    
}

//Override comparator for PQ to decide on cost
     class Node implements Comparator<Node>{
         public int node;
         public int cost;
         
          // Constructor 1
    public Node() {} 
         Node(int node, int cost){
             this.node = node;
             this.cost = cost;
         }
         
         @Override
         public int compare(Node n1, Node n2){
             if(n1.cost < n2.cost) return -1;
             if(n1.cost > n2.cost) return 1;
             return 0;
         }
     }