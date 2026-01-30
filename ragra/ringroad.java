import java.util.*;

public class ringroad {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++){
            adj.add(new ArrayList<>());
        } 

        for (int i = 0; i < n; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int c = sc.nextInt();

            adj.get(u).add(new int[]{v, c, 0}); 
            adj.get(v).add(new int[]{u, c, 1}); 
        }

        ArrayList<Integer> ring = new ArrayList<>();

        int start = 1;
        int curr = start;
        int prev = -1;

        while (true) {
            ring.add(curr);
            int next = -1;
            for (int i = 0; i < adj.get(curr).size(); i++) {
                int[] check = adj.get(curr).get(i);
                if (check[0] != prev) {
                    next = check[0];
                    break;
                }
            }
            if (next == start) break;
            prev = curr;
            curr = next;
        }

        int clockwise = 0;
        for (int i = 0; i < ring.size(); i++) {
            int from = ring.get(i);
            int to = ring.get((i + 1) % ring.size());

            for (int j = 0; j < adj.get(from).size(); j++) {
                int[] check = adj.get(from).get(j);
                if (check[0] == to) {
                    if (check[2] == 1) clockwise += check[1];
                    break;
                }
            }
        }

        int anticlockwise = 0;
        for (int i = 0; i < ring.size(); i++) {
            int from = ring.get(i);
            int to = ring.get((i - 1 + ring.size()) % ring.size());

            for (int j = 0; j < adj.get(from).size(); j++) {
                int[] check = adj.get(from).get(j);
                if (check[0] == to) {
                    if (check[2] == 1) anticlockwise += check[1];
                    break;
                }
            }
        }
        System.out.println(Math.min(clockwise, anticlockwise));
    }
}
