import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        //if(m == (n*(n-1))/2) System.out.println(-1);
        
        boolean[][] rail = new boolean[n + 1][n + 1];
        
        for(int i = 0; i < m; i++){
            int from = sc.nextInt();
            int to = sc.nextInt();
            rail[from][to] = true;
            rail[to][from] = true;
        }

        boolean directRail = rail[1][n];
        
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        
        if(directRail) {
            for(int i = 1; i <= n; i++) {
                for(int j = i+1; j <= n; j++) {
                    if(!rail[i][j]) {
                        adj.get(i).add(j);
                        adj.get(j).add(i);
                    }
                }
            }
        } else {
            for (int i = 1; i <= n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    if (rail[i][j]) {
                        adj.get(i).add(j);
                        adj.get(j).add(i);
                    }
                }
            }
        }
        
        int answer = bfs(n, adj);
        System.out.println(answer);
    }
    private static int bfs(int n, ArrayList<ArrayList<Integer>> adj) {
        Queue<Integer> q = new LinkedList<>();
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        
        q.add(1);
        dist[1] = 0;
        
        while(!q.isEmpty()) {
            int node = q.poll(); 
            for(int next : adj.get(node)) {
                if(dist[next] == -1) {
                    dist[next] = dist[node] + 1;
                    q.add(next);
                }
            }
        }
        return dist[n];
    }
}
