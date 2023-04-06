class Solution {
    public int minCostConnectPoints(int[][] points) {
        int r = points.length;
        PriorityQueue<Edge> pq = new PriorityQueue<>((x, y) -> x.cost - y.cost);
        UnionFind uf = new UnionFind(r);

    //Kruskals algorithm : add smallest edges in queue
      /*  for(int i = 0; i < r; i++){
            int x1 = points[i][0];
            int y1 = points[i][1];
            for(int j  = i+1; j < r; j++){
                int x2 = points[j][0];
                int y2 = points[j][1];

                int dist = Math.abs(x2-x1) + Math.abs(y2-y1);
                Edge edge = new Edge(i, j, dist);
                pq.add(edge);
            }
        }


//for n-1 edges, keep polling and adding if not already visited
        int result = 0;
        int count = r-1;
        while(!pq.isEmpty() && count > 0){
            Edge edge = pq.poll();
            if(!uf.isConnected(edge.point1, edge.point2)){
                uf.union(edge.point1, edge.point2);
                result += edge.cost;
                count--;
            }
        }
*/

        //Prims algorithm - needs a visited array
        boolean[] visited = new boolean[r];
        int result = 0;
        int count = r-1;

        //Pick first point
        int x1 = points[0][0];
        int y1 = points[0][1];


        //Calculate edges with all remaining points
            for(int j  = 1; j < r; j++){
                int x2 = points[j][0];
                int y2 = points[j][1];
                int dist = Math.abs(x2-x1) + Math.abs(y2-y1);
                Edge edge = new Edge(0, j, dist);
                pq.add(edge);
                }
        
        //Mark visited true
              visited[0] = true;

        //Now while q is not empty or coutn >0, remove smallest edge, check if point 2 is visited, if not visit and add edges with all vertices which are not yet visited
              while(!pq.isEmpty() && count > 0){
                    Edge edge = pq.poll();
                    int point1 = edge.point1;
                    int point2 = edge.point2;
                    int cost = edge.cost;

                    if(!visited[point2]){

                        visited[point2] = true;
                        result += cost;
                        for(int j = 0; j < r; j++){
                            if(!visited[j]){
  int distance = Math.abs(points[point2][0] - points[j][0]) + 
                                       Math.abs(points[point2][1] - points[j][1]);
                        pq.add(new Edge(point2, j, distance));
                            }
                        }
                       
                  count--;
                    }
              }
    

        return result;
    }

       class Edge{
           int point1;
           int point2;
           int cost;
           Edge(int point1, int point2, int cost){
               this.point1 = point1;
               this.point2 = point2;
               this.cost = cost;
           }
       }

       class UnionFind{
            int[] root;
            int[] rank;

            UnionFind(int size){
                this.root = new int[size];
                this.rank = new int[size];
                for(int i = 0; i < size; i++){
                    root[i]=i;
                    rank[i]=1;
                }
            }

            public int find(int x){
                while(x != root[x]){
                    x = root[x];
                }
                return x;
            }

            public void union(int x, int y){
                int rootX = find(x);
                int rootY = find(y);

                if( rootX != rootY){
                    if(rank[rootX] > rank[rootY]){
                        root[rootY] = rootX;
                    }
                    if( rank[rootY] > rank[rootX]){
                        root[rootX] = rootY;
                    }
                    else{
                        root[rootY] = rootX;
                        rank[rootX] +=1;
                    }
                }

            }

            public boolean isConnected(int x, int y){
                return find(x) == find(y);
            }

       }
    }
