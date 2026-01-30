import java.util.*;

public class dijikasrta {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
        for(int i = 0; i < m; i++){
            int from = sc.nextInt()-1;
            int to = sc.nextInt()-1;
            int weight = sc.nextInt();

            adj.get(from).add(new int[]{to, weight});
            adj.get(to).add(new int[]{from, weight});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        boolean[] fnl = new boolean[n];
        //int src = adj.get(0).get(0);
        int src = 0;
        dist[src] = 0;
        pq.add(new int[]{src, 0});

        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int curr_node = curr[0];
            int curr_dist = curr[1];


            if(fnl[curr_node]) continue;
            fnl[curr_node] = true;
            //if(curr_dist > dist[curr_node]) continue;

            for(int[] i : adj.get(curr_node)){
                int neigh_node = i[0];
                int neigh_dist = i[1];
                if(fnl[neigh_node]) continue;
               // fnl[neigh_node] = true;

                if(dist[neigh_node] > curr_dist + neigh_dist){
                    dist[neigh_node] = curr_dist + neigh_dist;

                    parent[neigh_node] = curr_node;

                    pq.add(new int[]{neigh_node, dist[neigh_node]});
                }

            }
        }
        if(dist[n - 1] == Integer.MAX_VALUE){
            System.out.println(-1);
            return;
        }
        ArrayList<Integer> path = new ArrayList<>();
        int node = n - 1;
        while(node != -1){
            path.add(node + 1);
            node = parent[node];
        }

        Collections.reverse(path);

        for(int x : path){
            System.out.print(x + " ");
        }
    }
}
