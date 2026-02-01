import java.util.*;

public class iceskating {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];

        for(int i = 0 ; i < n; i++){
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }
        int[][] adj = new int[n][n];
        for(int i = 0 ; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i != j && (x[i] == x[j] || y[i] == y[j])) adj[i][j] = 1;
            }
        }

        boolean[] visit = new boolean[n];
        int count = 0;

        for(int i = 0 ; i < n; i++){
            if(!visit[i]){
                dfs(i, adj, visit);
                count++;
            }
        }
        System.out.println(count-1);
    }

    public static void dfs(int src, int[][] adj, boolean[] visit){
        visit[src] = true;

        for(int i = 0; i < adj.length; i++){
            if(adj[src][i] == 1 && !visit[i]){
                dfs(i, adj, visit);
            }
        }
    }
}
