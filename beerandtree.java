import java.util.*;

public class beerandtree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int d = sc.nextInt();
        int h = sc.nextInt();

        if (!(d >= h && d <= 2 * h)) {
            System.out.println(-1);
            return;
        }

        int[] node = new int[n];
        for (int i = 0; i < n; i++) {
            node[i] = i + 1;
        }

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        int count = 0;
        int round = d - h;
        int c = round;

        int tempH = h;
        int i =0;
        
        if (d == h) {
            tempH = h - 1;
        }

        if (count == 0) {
            while (tempH > 0 && i + 1 < n) {
                int from = node[i];
                int to = node[i + 1];

                adj.get(from).add(to);
                adj.get(to).add(from);

                tempH--;
                i++;
            }
            count++;
        }

        int root = node[0];

        int j = i + 1;
        int last = root;

        if (d != h) {
            while (c > 0 && j < n) {
                int to = node[j];

                adj.get(last).add(to);
                adj.get(to).add(last);

                last = to;
                c--;
                j++;
            }
        }

        for (int k = j; k < n; k++) {
            int to = node[k];
            adj.get(root).add(to);
            adj.get(to).add(root);
        }

        for (int u = 1; u <= n; u++) {
            for (int v : adj.get(u)) {
                if (u < v) {
                    System.out.println(u + " " + v);
                }
            }
        }
    }
}
