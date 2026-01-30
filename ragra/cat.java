import java.util.*;

public class cat {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] cat = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            cat[i] = sc.nextInt();
        }

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] visited = new boolean[n + 1];

        int answer = dfs(1, 0, m, cat, adj, visited);

        System.out.println(answer);
    }

    public static int dfs(int src, int consecutiveCats, int m, int[] cat, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {

        visited[src] = true;

        if (cat[src] == 1)
            consecutiveCats++;
        else
            consecutiveCats = 0;

        if (consecutiveCats > m)
            return 0;

        boolean isLeaf = true;
        int count = 0;

        for (int next : adj.get(src)) {
            if (!visited[next]) {
                isLeaf = false;
                count += dfs(next, consecutiveCats, m, cat, adj, visited);
            }
        }

        if (isLeaf && src != 1)
            return 1;

        return count;
    }
}
