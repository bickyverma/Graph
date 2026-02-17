import java.io.*;
import java.util.*;

class planetKingdom{
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stt.nextToken());
        int m = Integer.parseInt(stt.nextToken());

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        ArrayList<ArrayList<Integer>> adjRev = new ArrayList<>();

        for(int i = 0 ; i < n; i++){
            adj.add(new ArrayList<>());
            adjRev.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++){
            stt = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(stt.nextToken()) - 1;
            int to = Integer.parseInt(stt.nextToken()) - 1;

            adj.get(from).add(to);
            adjRev.get(to).add(from);
        }

        Stack<Integer> st = new Stack<>();
        int v = adj.size();
        boolean[] visit = new boolean[v];

        for(int i = 0; i < v; i++){
            if(!visit[i]){
                dfs1(i, adj,visit, st);
            }
        }

        for(int i = 0; i < v; i++){
            visit[i] = false;
        }

        long count = 0;              // changed to long
        long[] ans = new long[v];    // changed to long array

        while(!st.isEmpty()){
            int curr = st.pop();

            if(!visit[curr]){
                count++;
                dfs2(curr, adjRev, visit, ans, count);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(count).append("\n");

        for(int i = 0; i < n; i++){
            sb.append(ans[i]).append(" ");
        }

        System.out.println(sb);
    }

    public static void dfs1(int src, ArrayList<ArrayList<Integer>> adj, boolean[] visit, Stack<Integer> st){
        visit[src] = true;

        for(int i : adj.get(src)){
            if(!visit[i]){
                dfs1(i, adj, visit, st);
            }
        }
        st.push(src);
    }

    public static void dfs2(int src, ArrayList<ArrayList<Integer>> adjRev,  boolean[] visit, long[] ans, long count){
        visit[src] = true;
        ans[src] = count;

        for(int i : adjRev.get(src)){
            if(!visit[i]){
                dfs2(i, adjRev, visit, ans, count);
            }
        }
    }
}
