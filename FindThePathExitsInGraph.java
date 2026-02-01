import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FindThePathExitsInGraph {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        if(n == 1 || source == destination) return true; ///important ha ye case..!!!
        boolean[] visit = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        ArrayList<ArrayList<Integer>> adj = adjList(n, edges);
        //return BFS(n, edges,source, destination, adj, q, visit);
        return DFS(n, edges,source, destination, adj, visit);
        
    }

    public ArrayList<ArrayList<Integer>> adjList(int n, int[][] edges){
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
        for(int i = 0; i < edges.length; i++){
            int from = edges[i][0];
            int to = edges[i][1];
            adj.get(from).add(to);
            adj.get(to).add(from);
        }
        return adj;
    }

    public boolean BFS(int n, int[][] edges, int source, int destination, ArrayList<ArrayList<Integer>> adjList, Queue<Integer> q, boolean[] visit){
        q.add(source);
        visit[source] = true;
        while(!q.isEmpty()){
            int curr = q.poll();
            for(int i : adjList.get(curr)){
                if(visit[i] == false){
                    q.add(i);
                    visit[i] = true;
                    if(i == destination) return true;
                }
            }
        }
        return false;
    }

    public boolean DFS(int n, int[][] edges, int source, int destination, ArrayList<ArrayList<Integer>> adjList, boolean[] visit){
        if(n == 1 || source == destination) return true; ///important ha ye case..!!!
        visit[source] = true;
        for(int i : adjList.get(source)){
            if(visit[i] == false){
                if(DFS(n, edges, i, destination, adjList, visit)) return true;
            }
        }
        return false;
    }
}

