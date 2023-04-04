/* Online Java Compiler and Editor */
import java.util.*;
public class graphOperations{
    private int numOfVertices;
    private boolean adjMatrix[][];
    private boolean visited[];
    private int V = 13;
    
    
    //Adjacency Matric Representation
    public graphOperations(int numOfVertices){
        this.numOfVertices = numOfVertices;
        this.adjMatrix = new boolean [numOfVertices][numOfVertices];
        this.visited = new boolean[numOfVertices];
    }
    
    public void addEdge(int a, int b){
        this.adjMatrix[a][b] = true;
        this.adjMatrix[b][a] = true;
    }
    
    public void removeEdge(int a, int b){
        this.adjMatrix[a][b] = false;
        this.adjMatrix[b][a] = false;
    }
    
    public String printMatrix(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < numOfVertices; i++){
            sb.append(i + ":");
            for(boolean j : adjMatrix[i]){
                sb.append((j ? 1 : 0) + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
    //Adjacency List representation
    public void addEdgeToList(ArrayList<ArrayList<Integer>> alist, int a, int b){
        alist.get(a).add(b);
        alist.get(b).add(a);
    }
    
    public void printGraphFromList(ArrayList<ArrayList<Integer>> alist){
        for(int i = 0; i < alist.size(); i++){
            System.out.println(i + ":");
            for(int j  = 0; j < alist.get(i).size(); j++){
                   System.out.print("->" + alist.get(i).get(j));
            }
                 System.out.println();
        }
    }
    
    
    //DFS search
    public void dfsTraversal(ArrayList<ArrayList<Integer>> alist, int vertex){
        visited[vertex] = true;
        System.out.print(vertex + " ");
        Iterator<Integer> ite = alist.get(vertex).listIterator();
        while(ite.hasNext()){
            int adj = ite.next();
            if( !visited[adj]){
                visited[adj] = true;
                dfsTraversal(alist, adj);
            }
        }
    }
    
    //BFS search
    public void BFS(ArrayList<ArrayList<Integer>> alist, int s) {

    boolean visited[] = new boolean[V];

    LinkedList<Integer> queue = new LinkedList();

    visited[s] = true;
    queue.add(s);

    while (queue.size() != 0) {
      s = queue.poll();
      System.out.print(s + " ");

      Iterator<Integer> i = alist.get(s).listIterator();
      while (i.hasNext()) {
        int n = i.next();
        if (!visited[n]) {
          visited[n] = true;
          queue.add(n);
        }
      }
    }
  }

    
     public static void main(String []args){
        graphOperations g= new graphOperations(4);
        /*g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        
        System.out.print(g.printMatrix());*/
        
        //For adjacency List we use below:
        int V = 4;
        ArrayList<ArrayList<Integer>> alist = new ArrayList<ArrayList<Integer>>(V);
        for(int i = 0; i < V; i++){
            alist.add(new ArrayList<Integer>());
        }
        g.addEdgeToList(alist,0, 1);
        g.addEdgeToList(alist,0, 2);
        g.addEdgeToList(alist,1, 2);
        g.addEdgeToList(alist,2, 0);
        g.addEdgeToList(alist,2, 3);
        
        //g.printGraphFromList(alist);
        
        //g.dfsTraversal(alist,2);
        g.BFS(alist,0);

     }
}