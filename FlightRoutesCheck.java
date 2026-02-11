import java.util.*;

public class FlightRoutesCheck {

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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        ArrayList<ArrayList<Integer>> revAdj = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
            revAdj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

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

