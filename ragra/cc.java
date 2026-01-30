import java.util.*;

public class cc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //no. of vertices
        int m = sc.nextInt(); //no. of edges

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
        for(int i = 0; i < m; i++){
            int from = sc.nextInt()-1;
            int to = sc.nextInt()-1;

            adj.get(from).add(to);
            adj.get(to).add(from);
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] visit = new boolean[n];
        int count = 0;

        for(int i = 0; i < n; i++){
            if(!visit[i]){
                if(isCyclic(i, adj, visit, q)) count++;
            }
        }
        System.out.println(count);
    }

    private static boolean isCyclic(int src, ArrayList<ArrayList<Integer>> adj, boolean[] visit, Queue<Integer> q){
        q.add(src);
        visit[src] = true;
        boolean flag = true;

        while(!q.isEmpty()){
            int curr = q.poll();

            if(adj.get(curr).size() != 2) flag = false;

            for(int i : adj.get(curr)){
                if(!visit[i]){
                    q.add(i);
                    visit[i] = true;
                }
            }
        }
        return flag;
    }
}
