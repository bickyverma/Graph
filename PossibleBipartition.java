import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class PossibleBipartition {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
        for(int i = 0; i < dislikes.length; i++){
            int from = dislikes[i][0]-1;
            int to = dislikes[i][1]-11971;
            adj.get(from).add(to);
            adj.get(to).add(from);
        }

        int[] visit = new int[n];
        Arrays.fill(visit, -1);
        
        for(int i = 0; i < adj.size(); i++){
            Queue<Integer> q = new LinkedList<>();
            if(visit[i] == -1){
                visit[i] = 0;
                q.add(i);

                while(!q.isEmpty()){
                    int curr = q.poll();
                    for(int j : adj.get(curr)){
                        if(visit[j] == -1){
                        int like = visit[curr] == 0 ? 1 : 0;
                        visit[j] = like;
                        q.add(j);
                        }else if(visit[j] == visit[curr]) return false;
                    } 
                }
            }
        }
        return true;
    }
}
