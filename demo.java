import java.io.*;
import java.util.*;

public class demo {

    public static void bfs(int start, ArrayList<ArrayList<Integer>> graph, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : graph.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    q.add(v);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        ArrayList<ArrayList<Integer>> revAdj = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
            revAdj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj.get(a).add(b);
            revAdj.get(b).add(a);
        }

        boolean[] visited = new boolean[n + 1];

        bfs(1, adj, visited);
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                System.out.println("NO");
                System.out.println(1 + " " + i);
                return;
            }
        }

        Arrays.fill(visited, false);

        bfs(1, revAdj, visited);
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                System.out.println("NO");
                System.out.println(i + " " + 1);
                return;
            }
        }

        System.out.println("YES");
    }
}
