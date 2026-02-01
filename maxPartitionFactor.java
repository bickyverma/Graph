import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class maxPartitionFactor {
    public int maxPartitionFactor(int[][] points) {
        int n = points.length;
        if (n == 2) return 0;
        
        int low = 0, ans = 0, high = 400000000;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canPartition(mid, points, n)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return ans;
    }

    public boolean canPartition(int d, int[][] points, int n){
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
        for(int from = 0; from < n; from++){
            for(int to = from+1; to < n; to++){
                long dist = (long)Math.abs(points[from][0] - points[to][0]) + (long)Math.abs(points[from][1] - points[to][1]);
                if(dist < d){
                    adj.get(from).add(to);
                    adj.get(to).add(from);
                }
            }
        }
        int[] mark = new int[n];
        for(int i = 0; i < n; i++){
            if(mark[i] == 0){
                if(!isBipartite(i, mark, adj)) return false;
            }
        }
        return true;
    }

    public boolean isBipartite(int src, int[] mark, ArrayList<ArrayList<Integer>> adj){
        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        mark[src] = 1;

        while(!q.isEmpty()){
            int curr = q.poll();
            for(int i : adj.get(curr)){
                if(mark[i] == 0){
                    mark[i] = -mark[curr];
                    q.add(i);
                }else if(mark[i] == mark[curr]) return false;
            }
        }
        return true;
    }
}
